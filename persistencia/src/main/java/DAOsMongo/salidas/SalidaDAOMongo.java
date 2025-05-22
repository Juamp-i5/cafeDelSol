/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOsMongo.salidas;

import IDAOs.salidas.ISalidaDAO;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.ConexionMongo;
import conexion.IConexionMongo;
import entidades.Salida;
import excepciones.PersistenciaSalidasException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Implementación de ISalidaDAO. Se utiliza MongoDB como base de datos. Esta
 * clase sigue el patrón singleton para garantizar que solo exista una instancia
 * de la DAO.
 *
 * @author katia
 */
public class SalidaDAOMongo implements ISalidaDAO {

    private static SalidaDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;
    private final MongoCollection<Salida> coleccion;
    private static final String NOMBRE_COLECCION = "salidas";

    /**
     * Constructor que inicializa la conexión, la bd y la colección.
     *
     * @param conexion Objeto de conexión a MongoDB que proporciona acceso a la
     * bd.
     */
    public SalidaDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;
        CodecRegistry codecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );
        this.database = conexion.getDatabase().withCodecRegistry(codecRegistry);
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Salida.class);
    }

    /**
     * Devuelve la instancia única de SalidaDAOMongo. Si no existe, la crea.
     *
     * @param conexion Objeto de conexión a MongoDB.
     * @return Instancia única de la DAO.
     */
    public static SalidaDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new SalidaDAOMongo(conexion);
        }
        return instancia;
    }

    /**
     * Registra una nueva salida en la base de datos.
     *
     * @param salida Salida que se desea perssitir.
     * @return True si la inserción fue exitosa.
     * @throws PersistenciaSalidasException Por si ocurre un error al insertar
     * la salida.
     */
    @Override
    public boolean registrarSalida(Salida salida) throws PersistenciaSalidasException {
        try {
            coleccion.insertOne(salida);
            return true;
        } catch (Exception e) {
            throw new PersistenciaSalidasException("Error al registrar la salida: " + e.getMessage(), e);
        }
    }

    /**
     * Consulta todas las salidas almacenadas en la base de datos.
     *
     * @return Lista de objetos tipo Salida.
     * @throws PersistenciaSalidasException Por si ocurre un error al recuperar
     * las salidas.
     */
    @Override
    public List<Salida> consultarTodas() throws PersistenciaSalidasException {
        try {
            List<Salida> lista = coleccion.find().into(new ArrayList<>());
            return lista;
        } catch (Exception e) {
            throw new PersistenciaSalidasException("Error al consultar todas las salidas: " + e.getMessage(), e);
        }
    }

    /**
     * Consulta las salidas registradas en un periodo específico.
     *
     * @param fechaInicio Fecha de inicio del rango.
     * @param fechaFin Fecha de fin del rango.
     * @return Lista de salidas dentro del rango de fechas.
     * @throws PersistenciaSalidasException En caso de error durante la
     * consulta.
     */
    @Override
    public List<Salida> consultarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaSalidasException {
        try {
            Bson filtro = Filters.and(
                    Filters.gte("fecha", fechaInicio),
                    Filters.lte("fecha", fechaFin)
            );
            return coleccion.find(filtro).into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaSalidasException("Error al consultar salidas por fechas: " + e.getMessage(), e);
        }
    }

    /**
     * Consulta una salida específica por su identificador único.
     *
     * @param id Identificador único de MongoDB.
     * @return Objeto de tipo Salida correspondiente al ID.
     * @throws PersistenciaSalidasException Si no se encuentra la salida o si
     * ocurre un error al obtenerla.
     */
    @Override
    public Salida consultarPorId(ObjectId id) throws PersistenciaSalidasException {
        try {
            Salida salida = coleccion.find(Filters.eq("_id", id)).first();
            if (salida == null) {
                throw new PersistenciaSalidasException("No se encontró ninguna salida con ese ID.");
            }
            return salida;
        } catch (Exception e) {
            throw new PersistenciaSalidasException("Error al consultar la salida por ID: " + e.getMessage(), e);
        }
    }
}
