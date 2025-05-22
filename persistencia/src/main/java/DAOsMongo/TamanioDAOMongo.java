package DAOsMongo;

import DTOs.PersistenciaTamanioDTO;
import entidades.Tamanio;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.ITamanioDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.IConexionMongo;
import interfacesMappers.ITamanioMapper;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import utils.DependencyInjectors;

/**
 *
 * @author rodri
 * Esta clase, `TamanioDAOMongo`, implementa la interfaz `ITamanioDAO` y se encarga
 * de las operaciones de **acceso a datos (DAO)** para la entidad `Tamanio` en una
 * base de datos MongoDB. Sigue el patrón Singleton para asegurar que solo haya
 * una instancia de este DAO interactuando con la colección `tamanios`. Es responsable
 * de convertir entre la entidad `Tamanio` (usada internamente por MongoDB) y el
 * DTO de persistencia `PersistenciaTamanioDTO` (usado por la capa de negocio),
 * manejando las excepciones específicas de la persistencia.
 */
public class TamanioDAOMongo implements ITamanioDAO {

    //Singleton
    private static TamanioDAOMongo instancia;
    private final MongoDatabase database; // Objeto que representa la base de datos MongoDB.

    private final MongoCollection<Tamanio> coleccion; // Colección de MongoDB donde se almacenan los tamaños.
    private final String NOMBRE_COLECCION = "tamanios"; // Nombre de la colección en la base de datos.

    // Inyección de dependencia del mapeador de Tamanio para la capa de persistencia.
    private final ITamanioMapper tamanioMapper = DependencyInjectors.getInstancia().getTamanioMapper();

    /**
     * Constructor privado de `TamanioDAOMongo` para implementar el patrón Singleton.
     * Recibe una implementación de `IConexionMongo` para obtener la base de datos
     * y la colección, asegurando una conexión activa a MongoDB.
     *
     * @param conexion Objeto que proporciona la conexión a la base de datos MongoDB.
     */
    private TamanioDAOMongo(IConexionMongo conexion) {
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Tamanio.class);
    }

    /**
     * Proporciona la única instancia de la clase `TamanioDAOMongo`, siguiendo el patrón Singleton.
     * Si la instancia aún no ha sido creada, la inicializa utilizando la conexión proporcionada;
     * de lo contrario, devuelve la existente.
     *
     * @param conexion Objeto que proporciona la conexión a la base de datos MongoDB.
     * @return La única instancia de `TamanioDAOMongo`.
     */
    public static TamanioDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new TamanioDAOMongo(conexion);
        }
        return instancia;
    }

    //Métodos de la colección
    /**
     * Busca y recupera todos los objetos `Tamanio` de la colección en la base de datos.
     * Convierte las entidades `Tamanio` recuperadas de MongoDB a `PersistenciaTamanioDTO`
     * utilizando el mapeador antes de devolverlas.
     *
     * @return Una lista de `PersistenciaTamanioDTO` que representan todos los tamaños en la base de datos.
     * @throws PersistenciaException Si ocurre un error durante la consulta a la base de datos.
     */
    @Override
    public List<PersistenciaTamanioDTO> buscarTodos() throws PersistenciaException {
        List<PersistenciaTamanioDTO> tamanios = new ArrayList<>();
        try (MongoCursor<Tamanio> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                tamanios.add(tamanioMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar todos los productos en la base de datos", e);
        }
        return tamanios;
    }

    /**
     * Busca un `Tamanio` específico en la base de datos por su nombre.
     * Utiliza un filtro para encontrar el documento que coincide con el nombre proporcionado.
     * Si se encuentra, mapea la entidad `Tamanio` a un `PersistenciaTamanioDTO`.
     *
     * @param nombre El nombre del tamaño a buscar.
     * @return El `PersistenciaTamanioDTO` correspondiente al nombre, o `null` si no se encuentra.
     * @throws PersistenciaException Si ocurre un error durante la consulta a la base de datos.
     */
    @Override
    public PersistenciaTamanioDTO buscarPorNombre(String nombre) throws PersistenciaException {
        List<PersistenciaTamanioDTO> tamanios = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        try (MongoCursor<Tamanio> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                tamanios.add(tamanioMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar el tamanio por nombre en la base de datos");
        }
        if (tamanios.isEmpty()) {
            return null;
        }
        return tamanios.get(0);
    }

    /**
     * Guarda un nuevo `Tamanio` en la base de datos. Si el DTO de entrada no tiene un ID,
     * se le asigna un nuevo `ObjectId` antes de la inserción. Después de la inserción,
     * el ID generado por MongoDB se asigna de vuelta al DTO de persistencia.
     *
     * @param tamanioDTO El `PersistenciaTamanioDTO` que contiene los datos del tamaño a guardar.
     * @throws PersistenciaException Si ocurre un error al guardar el tamaño en la base de datos.
     */
    @Override
    public void guardar(PersistenciaTamanioDTO tamanioDTO) throws PersistenciaException {
        try {
            Tamanio tamanio = tamanioMapper.toEntity(tamanioDTO);
            if (tamanio.getId() == null) {
                tamanio.setId(new ObjectId());
            }
            coleccion.insertOne(tamanio);
            tamanioDTO.setId(tamanio.getId().toHexString());
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar tamanio en la base de datos", e);
        }
    }
}
