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

/**
 *
 * @author pablo
 */
public class EntradaDAOMongo implements IEntradaDAO {

    private static EntradaDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private final MongoCollection<Entrada> coleccion;
    private final String NOMBRE_COLECCION = "RegistroEntradas";

    EntradaDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Entrada.class);
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
            return entradaMapper.todtoViejoList(documentos);
        } catch (Exception e) {
            throw new PersistenciaEntradasException("Error al obtener entradas por fechas", e);
        }
    }
}
