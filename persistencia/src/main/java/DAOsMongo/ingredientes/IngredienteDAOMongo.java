package DAOsMongo.ingredientes;

import DTOs.ingredientes.DetallesIngredienteViejoDTOPersistencia;
import DTOs.ingredientes.IIngredienteMapperPersistencia;
import DTOs.ingredientes.IProveedorMapperPersistencia;
import DTOs.ingredientes.IngredienteMapperPersistencia;
import DTOs.ingredientes.IngredienteViejoListDTOPersistencia;
import DTOs.ingredientes.ProveedorMapperPersistencia;
import IDAOs.ingredientes.IIngredienteDAOMongo;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import conexion.IConexionMongo;
import entidades.Ingrediente;
import enumIngredientes.NivelStock;
import enumIngredientes.UnidadMedida;
import excepciones.PersistenciaIngredientesException;
import interfacesMappers.IIngredienteMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import mappers.IngredienteMapper;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author norma
 */
public class IngredienteDAOMongo implements IIngredienteDAOMongo {

    private static IngredienteDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private final MongoCollection<Ingrediente> coleccion;
    private static final String NOMBRE_COLECCION = "ingredientes";

    IProveedorMapperPersistencia proveedorMapper = new ProveedorMapperPersistencia();
    IIngredienteMapperPersistencia ingredienteMapper = new IngredienteMapperPersistencia(proveedorMapper);
    IIngredienteMapper ingrediente2Mapper = new IngredienteMapper();


    public IngredienteDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;

        // Registrar codec para POJOs
        CodecRegistry codecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        this.database = conexion.getDatabase().withCodecRegistry(codecRegistry);
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Ingrediente.class);
    }

    public static IngredienteDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new IngredienteDAOMongo(conexion);
        }
        return instancia;
    }

    @Override
    public boolean agregarIngrediente(Ingrediente ingrediente) throws PersistenciaIngredientesException {
        try {
            coleccion.insertOne(ingrediente);
            System.out.println("Ingrediente agregado correctamente.");
            return true;
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al agregar el ingrediente: " + e.getMessage(), e);
        }
    }

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
                    Aggregates.unwind("$detalleProveedor")
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
            dto.setUnidadMedida(UnidadMedida.valueOf(resultado.getString("unidadMedida")));
            dto.setNivelStock(NivelStock.valueOf(resultado.getString("nivelStock")));
            Document proveedorDoc = (Document) resultado.get("detalleProveedor");
            dto.setNombreProveedor(proveedorDoc.getString("nombre"));
            return dto;
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al obtener detalles del ingrediente: " + e.getMessage(), e);
        }
    }

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

    @Override
    public List<IngredienteViejoListDTOPersistencia> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) throws PersistenciaIngredientesException {
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
                ingrediente.setUnidadMedida(UnidadMedida.valueOf(doc.getString("unidadMedida")));
                ingrediente.setNivelStock(NivelStock.valueOf(doc.getString("nivelStock")));
                lista.add(ingrediente);
            });
            return ingredienteMapper.toDTOList(lista);
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al buscar ingredientes: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean aumentarStock(String idIngrediente, Double cantidad) throws PersistenciaIngredientesException {
        try {
            MongoCollection<Document> coleccionDocumentos = database.getCollection("ingredientes");
            Document doc = coleccionDocumentos.find(Filters.eq("_id", new ObjectId(idIngrediente))).first();
            double cantidadActual = ((Number) doc.get("cantidadDisponible")).doubleValue();
            double nuevaCantidad = cantidadActual + cantidad;
            coleccion.updateOne(Filters.eq("_id", new ObjectId(idIngrediente)), Updates.set("cantidadDisponible", nuevaCantidad));
            actualizarNivelStock(idIngrediente);
            return true;
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al aumentar stock: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean reducirStock(String idIngrediente, Double cantidad) throws PersistenciaIngredientesException {
        try {
            MongoCollection<Document> coleccionDocumentos = database.getCollection("ingredientes");
            Document doc = coleccionDocumentos.find(Filters.eq("_id", new ObjectId(idIngrediente))).first();
            double cantidadActual = ((Number) doc.get("cantidadDisponible")).doubleValue();
            double nuevaCantidad = cantidadActual - cantidad;
            if (nuevaCantidad < 0) {
                throw new PersistenciaIngredientesException("No se puede reducir stock: cantidad insuficiente.");
            }
            coleccion.updateOne(Filters.eq("_id", new ObjectId(idIngrediente)), Updates.set("cantidadDisponible", nuevaCantidad));
            actualizarNivelStock(idIngrediente);
            return true;
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al aumentar stock: " + e.getMessage(), e);
        }
    }

    @Override
    public void actualizarNivelStock(String idIngrediente) throws PersistenciaIngredientesException {
        try {
            MongoCollection<Document> coleccionDocumentos = database.getCollection("ingredientes");
            ObjectId oid = new ObjectId(idIngrediente);
            Document doc = coleccionDocumentos.find(Filters.eq("_id", oid)).first();
            if (doc == null) {
                throw new PersistenciaIngredientesException("Ingrediente no encontrado con id: " + idIngrediente);
            }
            double cantidadDisponible = ((Number) doc.get("cantidadDisponible")).doubleValue();
            double cantidadMinima = ((Number) doc.get("cantidadMinima")).doubleValue();
            String nuevoNivelStock = cantidadDisponible < cantidadMinima ? "BAJOSTOCK" : "ENSTOCK";
            coleccion.updateOne(Filters.eq("_id", oid), Updates.set("nivelStock", nuevoNivelStock));
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al actualizar nivel de stock: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean obtenerIngredientePorNombre(String nombre) throws PersistenciaIngredientesException {
        try {
            Bson filtro = Filters.regex("nombre", "^" + Pattern.quote(nombre) + "$", "i");
            MongoCollection<Document> coleccionDocumentos = database.getCollection("ingredientes");
            Document resultado = coleccionDocumentos.find(filtro).first();
            return resultado != null;
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al buscar ingrediente por nombre: " + e.getMessage(), e);
        }
    }

}
