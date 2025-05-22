package DAOsMongo.ingredientes;

import DTOs.ingredientes.DetallesIngredienteViejoDTOPersistencia;
import DTOs.ingredientes.IIngredienteMapperPersistencia;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import DTOs.ingredientes.IngredienteMapperPersistencia;
import IDAOs.ingredientes.IIngredienteDAOMongo;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UnwindOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import conexion.IConexionMongo;
import entidades.Ingrediente;
import excepciones.PersistenciaIngredientesException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Clase DAO que logra la gestión de ingredientes en MongoDB. Sigue el patrón
 * Singleton para garantizar una única instancia.
 *
 * @author norma
 */
public class IngredienteDAOMongo implements IIngredienteDAOMongo {

    private static IngredienteDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private final MongoCollection<Ingrediente> coleccion;
    private static final String NOMBRE_COLECCION = "ingredientes";

    IIngredienteMapperPersistencia ingredienteMapper = new IngredienteMapperPersistencia();

    /**
     * Constructor privado para el patrón Singleton. Configura el CodecRegistry
     * para permitir el uso de POJOs.
     *
     * @param conexion Conexión a la base de datos.
     */
    private IngredienteDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;

        CodecRegistry codecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        this.database = conexion.getDatabase().withCodecRegistry(codecRegistry);
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Ingrediente.class);
    }

    /**
     * Obtiene la instancia única de IngredienteDAOMongo.
     *
     * @param conexion Conexión a la base de datos.
     * @return Instancia única del DAO.
     */
    public static IngredienteDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new IngredienteDAOMongo(conexion);
        }
        return instancia;
    }

    /**
     * Agrega un nuevo ingrediente a la colección.
     *
     * @param ingrediente ingrediente a guardar.
     * @return true si se agregó correctamente.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * operación.
     */
    @Override
    public boolean agregarIngrediente(IngredienteDTOPersistencia ingrediente) throws PersistenciaIngredientesException {
        try {
            Ingrediente entidadIngrediente = ingredienteMapper.toMongo(ingrediente);
            if (ingrediente.getId() == null || ingrediente.getId().trim().equals("")) {
                entidadIngrediente.setId(new ObjectId());
                ingrediente.setId(entidadIngrediente.getId().toHexString());
            }
            coleccion.insertOne(entidadIngrediente);
            System.out.println("Ingrediente agregado correctamente.");
            return true;
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al agregar el ingrediente: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene los detalles de un ingrediente, incluyendo información del
     * proveedor.
     *
     * @param idIngrediente ID del ingrediente a consultar.
     * @return ingrediente con todos sus detalles.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * operación.
     */
    @Override
    public DetallesIngredienteViejoDTOPersistencia obtenerDetallesIngrediente(String idIngrediente) throws PersistenciaIngredientesException {
        try {
            List<Bson> pipeline = Arrays.asList(
                    Aggregates.match(Filters.eq("_id", new ObjectId(idIngrediente))),
                    Aggregates.lookup(
                            "proveedores",
                            "idProveedor",
                            "_id",
                            "detalleProveedor"
                    ),
                    Aggregates.unwind("$detalleProveedor", new UnwindOptions().preserveNullAndEmptyArrays(true))
            );
            MongoCollection<Document> coleccionDocumentos = database.getCollection("ingredientes");
            Document resultado = coleccionDocumentos.aggregate(pipeline).first();
            if (resultado == null) {
                return null;
            }
            DetallesIngredienteViejoDTOPersistencia dto = new DetallesIngredienteViejoDTOPersistencia();
            dto.setId(resultado.getObjectId("_id").toHexString());
            dto.setNombre(resultado.getString("nombre"));
            dto.setCantidadDisponible(((Number) resultado.get("cantidadDisponible")).doubleValue());
            dto.setCantidadMinima(((Number) resultado.get("cantidadMinima")).doubleValue());
            dto.setUnidadMedida(resultado.getString("unidadMedida"));
            dto.setNivelStock(resultado.getString("nivelStock"));
            Document proveedorDoc = (Document) resultado.get("detalleProveedor");
            if (proveedorDoc != null) {
                dto.setNombreProveedor(proveedorDoc.getString("nombre"));
            } else {
                dto.setNombreProveedor("Desconocido");
            }
            //dto.setNombreProveedor(proveedorDoc.getString("nombre"));
            return dto;
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al obtener detalles del ingrediente: " + e.getMessage(), e);
        }
    }

    /**
     * Edita el nombre de un ingrediente existente.
     *
     * @param idIngrediente ID del ingrediente a editar.
     * @param nombreNuevo Nuevo nombre del ingrediente.
     * @return ingrediente con todos sus detalles actualizados.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * operación.
     */
    @Override
    public DetallesIngredienteViejoDTOPersistencia editarIngrediente(String idIngrediente, String nombreNuevo)
            throws PersistenciaIngredientesException {
        try {
            Bson filtro = Filters.eq("_id", new ObjectId(idIngrediente));
            Bson actualizacion = Updates.set("nombre", nombreNuevo);
            UpdateResult resultado = coleccion.updateOne(filtro, actualizacion);
            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaIngredientesException("No se encontró el ingrediente con el ID proporcionado.");
            }
            return obtenerDetallesIngrediente(idIngrediente);
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al editar el ingrediente", e);
        }
    }

    /**
     * Busca ingredientes que coincidan con los filtros de nombre y nivel de
     * stock.
     *
     * @param filtroNombre Filtro por nombre (expresión regular).
     * @param filtroNivelStock Filtro por nivel de stock.
     * @return lista de ingredientes buscados.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * búsqueda.
     */
    @Override
    public List<IngredienteDTOPersistencia> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) throws PersistenciaIngredientesException {
        try {
            List<Bson> filtros = new ArrayList<>();
            if (filtroNombre != null && !filtroNombre.isEmpty()) {
                filtros.add(Filters.regex("nombre", filtroNombre, "i"));
            }
            if (filtroNivelStock != null && !filtroNivelStock.isEmpty()) {
                filtros.add(Filters.eq("nivelStock", filtroNivelStock));
            }
            Bson filtroFinal = filtros.isEmpty() ? new Document() : Filters.and(filtros);
            Bson projection = Projections.fields(
                    Projections.include("_id", "nombre", "cantidadDisponible", "unidadMedida", "nivelStock")
            );
            List<Ingrediente> lista = new ArrayList<>();
            MongoCollection<Document> coleccionDocumentos = database.getCollection("ingredientes");
            coleccionDocumentos.find(filtroFinal).projection(projection).forEach(doc -> {
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setId(doc.getObjectId("_id"));
                ingrediente.setNombre(doc.getString("nombre"));
                ingrediente.setCantidadDisponible(((Number) doc.get("cantidadDisponible")).doubleValue());
                ingrediente.setUnidadMedida(doc.getString("unidadMedida"));
                ingrediente.setNivelStock(doc.getString("nivelStock"));
                lista.add(ingrediente);
            });
            return ingredienteMapper.toDTOList(lista);
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al buscar ingredientes: " + e.getMessage(), e);
        }
    }

    /**
     * Aumenta la cantidad disponible de un ingrediente y actualiza el nivel de
     * stock.
     *
     * @param idIngrediente ID del ingrediente.
     * @param cantidad Cantidad a aumentar.
     * @return true si se actualizó correctamente.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * operación.
     */
    @Override
    public boolean aumentarStock(String idIngrediente, Double cantidad) throws PersistenciaIngredientesException {
        try {
            Ingrediente ingrediente = coleccion.find(Filters.eq("_id", new ObjectId(idIngrediente))).first();
            double cantidadActual = ingrediente.getCantidadDisponible();
            double nuevaCantidad = cantidadActual + cantidad;
            coleccion.updateOne(Filters.eq("_id", new ObjectId(idIngrediente)), Updates.set("cantidadDisponible", nuevaCantidad));
            actualizarNivelStock(idIngrediente);
            return true;
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al aumentar stock: " + e.getMessage(), e);
        }
    }

    /**
     * Reduce la cantidad disponible de un ingrediente, validando que no sea
     * negativa.
     *
     * @param idIngrediente ID del ingrediente.
     * @param cantidad Cantidad a reducir.
     * @return true si se redujo correctamente.
     * @throws PersistenciaIngredientesException Si la cantidad es insuficiente
     * o hay un error.
     */
    @Override
    public boolean reducirStock(String idIngrediente, Double cantidad) throws PersistenciaIngredientesException {
        try {
            Ingrediente ingrediente = coleccion.find(Filters.eq("_id", new ObjectId(idIngrediente))).first();
            double cantidadActual = ingrediente.getCantidadDisponible();
            double nuevaCantidad = cantidadActual - cantidad;
            if (nuevaCantidad < 0) {
                throw new PersistenciaIngredientesException("No se puede reducir stock: cantidad insuficiente.");
            }
            coleccion.updateOne(Filters.eq("_id", new ObjectId(idIngrediente)), Updates.set("cantidadDisponible", nuevaCantidad));
            actualizarNivelStock(idIngrediente);
            return true;
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al reducir stock: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza el campo "nivelStock" de un ingrediente dependiendo de su
     * cantidad disponible actual.
     *
     * @param idIngrediente ID del ingrediente a actualizar.
     * @throws PersistenciaIngredientesException Si ocurre un error en la
     * actualización.
     */
    @Override
    public void actualizarNivelStock(String idIngrediente) throws PersistenciaIngredientesException {
        try {
            Ingrediente ingrediente = coleccion.find(Filters.eq("_id", new ObjectId(idIngrediente))).first();
            double cantidadDisponible = ingrediente.getCantidadDisponible();
            double cantidadMinima = ingrediente.getCantidadMinima();
            String nuevoNivelStock = cantidadDisponible < cantidadMinima ? "BAJOSTOCK" : "ENSTOCK";
            coleccion.updateOne(Filters.eq("_id", new ObjectId(idIngrediente)), Updates.set("nivelStock", nuevoNivelStock));
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al actualizar nivel de stock: " + e.getMessage(), e);
        }
    }

    /**
     * Verifica si existe un ingrediente por nombre exacto.
     *
     * @param nombre Nombre del ingrediente a buscar.
     * @return true si existe, false en caso contrario.
     * @throws PersistenciaIngredientesException Si ocurre un error en la
     * búsqueda.
     */
    @Override
    public boolean obtenerIngredientePorNombre(String nombre) throws PersistenciaIngredientesException {
        try {
            Bson filtro = Filters.regex("nombre", "^" + Pattern.quote(nombre) + "$", "i");
            Ingrediente resultado = coleccion.find(filtro).first();
            return resultado != null;
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al buscar ingrediente por nombre: " + e.getMessage(), e);
        }
    }

    /**
     * Descuenta directamente una cantidad del stock de un ingrediente.
     *
     * @param idIngrediente ID del ingrediente.
     * @param cantidad Cantidad a descontar.
     */
    @Override
    public void descontarStock(String idIngrediente, double cantidad) {
        Bson filtro = Filters.eq("_id", new ObjectId(idIngrediente));
        Bson update = Updates.inc("cantidadDisponible", -cantidad);

        coleccion.updateOne(filtro, update);
    }

}
