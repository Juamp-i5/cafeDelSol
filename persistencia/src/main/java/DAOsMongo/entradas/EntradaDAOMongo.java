package DAOsMongo.entradas;

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
import org.bson.conversions.Bson;

/**
 *
 * @author pablo
 */
public class EntradaDAOMongo implements IEntradaDAO  {
    
    private static EntradaDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private final MongoCollection<Entrada> coleccion;
    private final String NOMBRE_COLECCION = "RegistroEntradas";

    private EntradaDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Entrada.class);
    }

    public static EntradaDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new EntradaDAOMongo(conexion);
        }
        return instancia;
    }

    @Override
    public boolean registrarEntrada(Entrada entrada) throws PersistenciaEntradasException {
        try {
            InsertOneResult resultado = coleccion.insertOne(entrada);
            if (!resultado.wasAcknowledged()) {
                throw new PersistenciaEntradasException("La inserción no fue reconocida por el servidor.");
            }
            return true;
        } catch (PersistenciaEntradasException e) {
            throw new PersistenciaEntradasException("Error al insertar el ingrediente", e);
        }
    }

    @Override
    public List<Entrada> obtenerTodasLasEntradas() throws PersistenciaEntradasException {
        try {
            return coleccion.find().into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaEntradasException("Error al obtener todas las entradas de la base de datos", e);
        }
    }

    @Override
    public List<Entrada> obtenerEntradasHastaFecha(LocalDateTime fechaFin) throws PersistenciaEntradasException {
        try {
            LocalDateTime finDelDia = LocalDateTime.of(fechaFin.toLocalDate(), LocalTime.MAX);
            Bson filtro = Filters.lte("fechaHora", finDelDia);
            List<Entrada> entradas = coleccion.find(filtro).into(new ArrayList<>());

            return entradas;

        } catch (Exception e) {
            throw new PersistenciaEntradasException("Error al obtener entradas hasta la fecha: " + fechaFin, e);
        }
    }

    @Override
    public List<Entrada> obtenerEntradasDesdeFecha(LocalDateTime fechaInicio) throws PersistenciaEntradasException {
        try {
            LocalDateTime inicioDelDia = fechaInicio.toLocalDate().atStartOfDay();
            Bson filtro = Filters.gte("fechaHora", inicioDelDia);
            return coleccion.find(filtro).into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaEntradasException("Error al obtener entradas desde la fecha: " + fechaInicio, e);
        }
    }

    @Override
    public List<Entrada> obtenerListaEntradaPorRangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaEntradasException {
        try {
            LocalDateTime desde = fechaInicio.toLocalDate().atStartOfDay();
            LocalDateTime hasta = fechaFin.toLocalDate().atTime(LocalTime.MAX);

            Bson filtro = Filters.and(
                    Filters.gte("fechaHora", desde),
                    Filters.lte("fechaHora", hasta)
            );
            return coleccion.find(filtro).into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaEntradasException("Error al obtener entradas por rango de fecha", e);
        }
    }
}
