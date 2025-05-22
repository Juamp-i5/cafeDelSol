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
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import com.mongodb.client.model.Projections;
import static com.mongodb.client.model.Projections.computed;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import com.mongodb.client.model.UnwindOptions;
import entidades.Ingrediente;
import java.util.Arrays;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo
 */
public class EntradaDAOMongo implements IEntradaDAO {

    private static EntradaDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private final MongoCollection<Entrada> coleccion;
    private final MongoCollection<Ingrediente> coleccionIngrediente;

    private final String NOMBRE_COLECCION = "RegistroEntradas";
    private final String NOMBRE_COLECCION_INGREDIENTES = "ingredientes";

    EntradaDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Entrada.class);
        this.coleccionIngrediente = database.getCollection(NOMBRE_COLECCION_INGREDIENTES, Ingrediente.class);
    }

    IEntradaMapperPersistencia entradaMapper = new EntradaMapperPersistencia();

    public static EntradaDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new EntradaDAOMongo(conexion);
        }
        return instancia;
    }

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
