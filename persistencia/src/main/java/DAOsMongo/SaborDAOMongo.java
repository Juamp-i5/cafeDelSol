package DAOsMongo;

import DTOs.PersistenciaSaborDTO;
import entidades.Sabor;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.ISaborDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.IConexionMongo;
import interfacesMappers.ISaborMapper;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import utils.DependencyInjectors;

/**
 *
 * @author rodri
 * La clase `SaborDAOMongo` es una implementación de la interfaz `ISaborDAO`,
 * diseñada para gestionar las operaciones de **acceso a datos (DAO)** para la
 * entidad `Sabor` en una base de datos MongoDB. Esta clase sigue el patrón
 * Singleton para asegurar una única instancia, lo cual es óptimo para manejar
 * la conexión y las interacciones con la colección "sabores". Su función
 * principal es actuar como un puente entre la capa de negocio (a través de los DTOs)
 * y la base de datos, manejando la conversión entre las entidades `Sabor`
 * y los DTOs de persistencia `PersistenciaSaborDTO`, y gestionando las excepciones
 * relacionadas con la persistencia.
 */
public class SaborDAOMongo implements ISaborDAO {

    //Singleton
    private static SaborDAOMongo instancia;
    private final MongoDatabase database; // Representa la base de datos MongoDB.

    private final MongoCollection<Sabor> coleccion; // Colección de MongoDB para los sabores.
    private final String NOMBRE_COLECCION = "sabores"; // Nombre de la colección en la base de datos.

    // Inyección de dependencia del mapeador de Sabor para la capa de persistencia.
    private final ISaborMapper saborMapper = DependencyInjectors.getInstancia().getSaborMapper();

    /**
     * Constructor privado de `SaborDAOMongo`, vital para el patrón Singleton.
     * Recibe una implementación de `IConexionMongo` para obtener la conexión
     * a la base de datos y configurar la colección con la que operará.
     *
     * @param conexion Objeto que proporciona la conexión a la base de datos MongoDB.
     */
    private SaborDAOMongo(IConexionMongo conexion) {
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Sabor.class);
    }

    /**
     * Proporciona la única instancia de la clase `SaborDAOMongo`, siguiendo el patrón Singleton.
     * Si la instancia es nula, la crea utilizando la conexión proporcionada; de lo contrario,
     * devuelve la instancia ya existente. Esto asegura un manejo eficiente de recursos.
     *
     * @param conexion Objeto que proporciona la conexión a la base de datos MongoDB.
     * @return La única instancia de `SaborDAOMongo`.
     */
    public static SaborDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new SaborDAOMongo(conexion);
        }
        return instancia;
    }

    //Métodos de la colección
    /**
     * Busca y recupera todos los objetos `Sabor` de la colección en la base de datos MongoDB.
     * Cada entidad `Sabor` recuperada se mapea a un `PersistenciaSaborDTO` antes de ser
     * devuelta en una lista, lo que asegura que la capa de negocio reciba un formato de datos
     * adecuado.
     *
     * @return Una lista de `PersistenciaSaborDTO` que representan todos los sabores almacenados.
     * @throws PersistenciaException Si ocurre un error al consultar los datos en la base de datos.
     */
    @Override
    public List<PersistenciaSaborDTO> buscarTodos() throws PersistenciaException {
        List<PersistenciaSaborDTO> sabores = new ArrayList<>();
        try (MongoCursor<Sabor> cursor = coleccion.find().iterator()) { // Itera sobre todos los documentos de la colección.
            while (cursor.hasNext()) { // Mientras haya documentos por procesar.
                sabores.add(saborMapper.toDTO(cursor.next())); // Mapea la entidad a DTO y la añade a la lista.
            }
        } catch (Exception e) {
            // Envuelve cualquier excepción en una PersistenciaException para un manejo consistente de errores.
            throw new PersistenciaException("Error al consultar todos los sabores en la base de datos", e);
        }
        return sabores;
    }

    /**
     * Busca un objeto `Sabor` específico en la base de datos por su nombre.
     * Utiliza un filtro para encontrar el documento que coincide con el nombre proporcionado.
     * Si el sabor se encuentra, se mapea la entidad `Sabor` a un `PersistenciaSaborDTO`.
     *
     * @param nombre El nombre del sabor a buscar.
     * @return El `PersistenciaSaborDTO` correspondiente al nombre, o `null` si no se encuentra.
     * @throws PersistenciaException Si ocurre un error durante la consulta en la base de datos.
     */
    @Override
    public PersistenciaSaborDTO buscarPorNombre(String nombre) throws PersistenciaException {
        List<PersistenciaSaborDTO> sabores = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre); // Crea un filtro para buscar por el campo "nombre".
        try (MongoCursor<Sabor> cursor = coleccion.find(filtro).iterator()) { // Ejecuta la consulta con el filtro.
            while (cursor.hasNext()) {
                sabores.add(saborMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            // Lanza una PersistenciaException con un mensaje descriptivo.
            throw new PersistenciaException("Error al consultar el sabor por nombre en la base de datos");
        }
        if (sabores.isEmpty()) {
            return null; // Si no se encuentra el sabor, devuelve null.
        }
        return sabores.get(0); // Devuelve el primer sabor encontrado (asumiendo unicidad por nombre).
    }

    /**
     * Guarda un nuevo objeto `Sabor` en la base de datos. Si el DTO de entrada no
     * tiene un ID asignado, se le genera un nuevo `ObjectId` antes de la inserción.
     * Después de la inserción exitosa, el ID generado por MongoDB se vuelve a asignar
     * al DTO de persistencia, asegurando que el objeto DTO refleje el estado de la base de datos.
     *
     * @param saborDTO El `PersistenciaSaborDTO` que contiene los datos del sabor a guardar.
     * @throws PersistenciaException Si ocurre un error al intentar guardar el sabor en la base de datos.
     */
    @Override
    public void guardar(PersistenciaSaborDTO saborDTO) throws PersistenciaException {
        try {
            Sabor sabor = saborMapper.toEntity(saborDTO); // Mapea el DTO a una entidad Sabor.
            if (sabor.getId() == null) {
                sabor.setId(new ObjectId()); // Si el ID es nulo, genera un nuevo ObjectId.
            }
            coleccion.insertOne(sabor); // Inserta la entidad en la colección de MongoDB.
            saborDTO.setId(sabor.getId().toHexString()); // Actualiza el DTO con el ID generado por la base de datos.
        } catch (Exception e) {
            // Captura cualquier excepción y la propaga como PersistenciaException.
            throw new PersistenciaException("Error al guardar sabor en la base de datos", e);
        }
    }
}
