package DAOsMongo.entradas;

import DTOs.entradas.EntradaMapperPersistencia;
import DTOs.entradas.EntradaNuevaDTOPersistencia;
import DTOs.entradas.EntradaViejaDTOPersistencia;
import IDAOs.entradas.IEntradaDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import conexion.IConexionMongo;
import entidades.Entrada;
import excepciones.PersistenciaEntradasException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import DTOs.entradas.IEntradaMapperPersistencia;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UnwindOptions;
import entidades.Ingrediente;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo Implementación de la interfaz IEntradaDAO que proporciona
 * métodos para la persistencia de datos de las entradas en una base de datos
 * MongoDB. Esta clase sigue el patrón Singleton para asegurar una única
 * instancia y gestiona las interacciones con las colecciones "RegistroEntradas"
 * e "ingredientes". También utiliza un mapeador para convertir entre objetos de
 * dominio (Entidad) y objetos de transferencia de datos (DTOs).
 */
public class EntradaDAOMongo implements IEntradaDAO {

    private static EntradaDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private final MongoCollection<Entrada> coleccion;

    private final String NOMBRE_COLECCION = "RegistroEntradas";
    IEntradaMapperPersistencia entradaMapper = new EntradaMapperPersistencia();

    /**
     * El constructor privado de la clase asegura que solo se pueda instanciar a
     * través del método estático `getInstance()`, garantizando el patrón
     * Singleton. Aquí se inicializa la conexión a la base de datos y se
     * obtienen las referencias a las colecciones de MongoDB para Entradas e
     * Ingredientes.
     *
     * @param conexion Una implementación de la interfaz IConexionMongo para
     * acceder a la base de datos.
     */
    EntradaDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Entrada.class);
    }

    /**
     * Proporciona la única instancia de la clase `EntradaDAOMongo`, siguiendo
     * el patrón Singleton. Si la instancia aún no ha sido creada, la
     * inicializa; de lo contrario, devuelve la existente. Esto asegura que
     * todos los componentes de la aplicación compartan la misma conexión y
     * configuración de DAO.
     *
     * @param conexion La implementación de la interfaz IConexionMongo necesaria
     * para inicializar la conexión.
     * @return La única instancia de `EntradaDAOMongo`.
     */
    public static EntradaDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new EntradaDAOMongo(conexion);
        }
        return instancia;
    }

    /**
     * Registra una nueva entrada en la colección de MongoDB "RegistroEntradas".
     * Antes de la inserción, convierte el DTO de la nueva entrada a la entidad
     * de persistencia correspondiente utilizando el `entradaMapper`. Si la
     * operación de inserción no es reconocida por el servidor de la base de
     * datos, se lanza una `PersistenciaEntradasException`.
     *
     * @param entrada El objeto `EntradaNuevaDTOPersistencia` que contiene los
     * datos de la entrada a registrar.
     * @return `true` si la entrada se registró exitosamente.
     * @throws PersistenciaEntradasException Si ocurre un error durante el
     * proceso de inserción en la base de datos.
     */
    @Override
    public boolean registrarEntrada(EntradaNuevaDTOPersistencia entrada) throws PersistenciaEntradasException {
        try {
            InsertOneResult resultado = coleccion.insertOne(entradaMapper.toEntityNuevo(entrada));
            if (!resultado.wasAcknowledged()) {
                throw new PersistenciaEntradasException("La inserción no fue reconocida por el servidor.");
            }
            return true;
        } catch (PersistenciaEntradasException e) {
            throw new PersistenciaEntradasException("Error al insertar el ingrediente", e);
        }
    }

    /**
     * Recupera una lista de entradas de la base de datos que caen dentro de un
     * rango de fechas y horas especificado. Construye dinámicamente los filtros
     * de MongoDB (`$gte` para fecha de inicio y `$lte` para fecha de fin) para
     * realizar la consulta en la colección "RegistroEntradas". Los objetos
     * `LocalDateTime` se ajustan para incluir el día completo si solo se
     * proporciona una fecha. Finalmente, los documentos de `Entrada` obtenidos
     * se mapean a `EntradaViejaDTOPersistencia` antes de ser devueltos.
     *
     * @param fechaInicio La fecha y hora de inicio del rango de búsqueda (puede
     * ser `null` para no filtrar por inicio).
     * @param fechaFin La fecha y hora de fin del rango de búsqueda (puede ser
     * `null` para no filtrar por fin).
     * @return Una lista de `EntradaViejaDTOPersistencia` que cumplen con los
     * criterios de fecha.
     * @throws PersistenciaEntradasException Si ocurre un error durante la
     * consulta de las entradas.
     */
    @Override
    public List<EntradaViejaDTOPersistencia> obtenerEntradasPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaEntradasException {
        try {
            List<Bson> filtros = new ArrayList<>();
            if (fechaInicio != null) {
                LocalDateTime inicioDelDia = fechaInicio.toLocalDate().atStartOfDay();
                filtros.add(Filters.gte("fechaHora", inicioDelDia));
            }
            if (fechaFin != null) {
                LocalDateTime finDelDia = fechaFin.toLocalDate().atTime(LocalTime.MAX);
                filtros.add(Filters.lte("fechaHora", finDelDia));
            }
            Bson filtroFinal = filtros.isEmpty() ? new Document() : Filters.and(filtros);
            List<Entrada> documentos = coleccion.find(filtroFinal).into(new ArrayList<>());
            return entradaMapper.todtoViejoPersistenciaList(documentos);
        } catch (Exception e) {
            throw new PersistenciaEntradasException("Error al obtener entradas por fechas", e);
        }
    }

    /**
     * Obtiene los detalles completos de una entrada específica, incluyendo la
     * información de los ingredientes asociados a cada detalle de entrada. Esto
     * se logra mediante un complejo pipeline de agregación de MongoDB que
     * realiza las siguientes operaciones: filtra por el ID de la entrada,
     * desestructura los detalles de la entrada (`$unwind`), realiza un "join"
     * con la colección de ingredientes (`$lookup`) para incrustar su
     * información, vuelve a desestructurar el resultado del `lookup` y
     * finalmente reagrupa (`$group`) los documentos para reconstruir la entrada
     * con los detalles enriquecidos. Si el ID proporcionado es inválido o la
     * entrada no se encuentra, se lanza una excepción.
     *
     * @param entradaId El ID de la entrada en formato String.
     * @return Un objeto `EntradaViejaDTOPersistencia` con todos los detalles de
     * la entrada e información de ingredientes, o `null` si no se encuentra.
     * @throws PersistenciaEntradasException Si el ID es inválido o si ocurre un
     * error durante la agregación.
     */
    @Override
    public EntradaViejaDTOPersistencia obtenerDetallesConIngredientes(String entradaId) throws PersistenciaEntradasException {
        ObjectId objectId;
        try {
            objectId = new ObjectId(entradaId);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaEntradasException("ID de entrada inválido: " + entradaId, e);
        }

        List<Bson> pipeline = new ArrayList<>();

        pipeline.add(Aggregates.match(Filters.eq("_id", objectId)));

        pipeline.add(Aggregates.unwind("$detallesEntrada", new UnwindOptions().preserveNullAndEmptyArrays(true)));

        pipeline.add(Aggregates.lookup(
                "ingredientes",
                "detallesEntrada.idIngrediente",
                "_id",
                "detallesEntrada.ingredienteInfo"
        ));

        pipeline.add(Aggregates.unwind("$ingredienteInfo", new UnwindOptions().preserveNullAndEmptyArrays(true)));

        pipeline.add(Aggregates.group(
                "$_id",
                Accumulators.first("fechaHora", "$fechaHora"),
                Accumulators.first("proveedor", "$proveedor"),
                Accumulators.first("precioTotal", "$precioTotal"),
                Accumulators.push("detallesEntrada", new Document()
                        .append("cantidad", "$detallesEntrada.cantidad")
                        .append("nivelStock", "$detallesEntrada.nivelStock")
                        .append("nombreIngrediente", "$detallesEntrada.nombreIngrediente")
                        .append("precioTotal", "$detallesEntrada.precioTotal")
                        .append("precioUnitario", "$detallesEntrada.precioUnitario")
                        .append("idIngrediente", "$detallesEntrada.idIngrediente")
                        .append("ingredienteInfo", "$detallesEntrada.ingredienteInfo")
                )
        ));

        pipeline.add(Aggregates.project(Projections.fields(
                Projections.computed("idEntrada", "$_id"),
                Projections.include("fechaHora", "proveedor", "precioTotal", "detallesEntrada")
        )));

        try {
            entidades.Entrada entradaEntity = coleccion.aggregate(pipeline, entidades.Entrada.class).first();

            if (entradaEntity == null) {
                return null;
            }
            return entradaMapper.todtoViejoPersistencia(entradaEntity);
        } catch (Exception e) {
            throw new PersistenciaEntradasException("Error al obtener la entrada con detalles de ingredientes.", e);
        }
    }
}
