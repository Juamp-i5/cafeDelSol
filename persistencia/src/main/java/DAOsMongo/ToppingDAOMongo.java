package DAOsMongo;

import DTOs.PersistenciaToppingDTO;
import entidades.Topping;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.IToppingDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.IConexionMongo;
import interfacesMappers.IToppingMapper;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import utils.DependencyInjectors;

/**
 *
 * @author rodri
 * La clase `ToppingDAOMongo` implementa la interfaz `IToppingDAO` y es el
 * **Objeto de Acceso a Datos (DAO)** para la entidad `Topping` en una base de datos MongoDB.
 * Adopta el patrón **Singleton** para asegurar una única instancia, lo cual es
 * eficiente para la gestión de la conexión y las operaciones con la colección "toppings".
 * Su responsabilidad principal es gestionar la persistencia de los datos de los toppings,
 * incluyendo la conversión entre la entidad `Topping` (específica de MongoDB)
 * y el `PersistenciaToppingDTO` (usado por la capa de negocio), y manejar las
 * excepciones de la capa de persistencia.
 */
public class ToppingDAOMongo implements IToppingDAO {

    // Singleton
    private static ToppingDAOMongo instancia;
    private final MongoDatabase database; // Objeto que representa la base de datos MongoDB.

    private final MongoCollection<Topping> coleccion; // Colección de MongoDB donde se almacenan los toppings.
    private final String NOMBRE_COLECCION = "toppings"; // Nombre de la colección en la base de datos.

    // Inyección de dependencia del mapeador de Topping para la capa de persistencia.
    private final IToppingMapper toppingMapper = DependencyInjectors.getInstancia().getToppingMapper();

    /**
     * Constructor privado de `ToppingDAOMongo`. Es privado para forzar el uso
     * del método `getInstance()` y asegurar que solo haya una instancia de esta clase
     * (patrón Singleton). Inicializa la conexión a la base de datos y la colección
     * de MongoDB para los toppings.
     *
     * @param conexion Objeto que proporciona la conexión a la base de datos MongoDB.
     */
    private ToppingDAOMongo(IConexionMongo conexion) {
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Topping.class);
    }

    /**
     * Obtiene la única instancia de `ToppingDAOMongo`. Si la instancia aún no ha sido
     * creada, la inicializa utilizando la conexión proporcionada. De lo contrario,
     * devuelve la instancia existente. Este método implementa el patrón Singleton.
     *
     * @param conexion Objeto que proporciona la conexión a la base de datos MongoDB.
     * @return La única instancia de `ToppingDAOMongo`.
     */
    public static ToppingDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new ToppingDAOMongo(conexion);
        }
        return instancia;
    }

    //Métodos de la colección
    /**
     * Busca y recupera todos los objetos `Topping` de la colección en la base de datos.
     * Itera sobre los documentos encontrados y convierte cada entidad `Topping`
     * a su correspondiente `PersistenciaToppingDTO` utilizando el mapeador antes de
     * agregarlo a la lista de resultados.
     *
     * @return Una lista de `PersistenciaToppingDTO` que representan todos los toppings en la base de datos.
     * @throws PersistenciaException Si ocurre un error durante la consulta en la base de datos.
     */
    @Override
    public List<PersistenciaToppingDTO> buscarTodos() throws PersistenciaException {
        List<PersistenciaToppingDTO> toppings = new ArrayList<>();
        try (MongoCursor<Topping> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                toppings.add(toppingMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar todos los toppings en la base de datos", e);
        }
        return toppings;
    }

    /**
     * Busca un objeto `Topping` específico en la base de datos por su nombre.
     * Aplica un filtro a la colección para encontrar el documento que coincide con el nombre.
     * Si se encuentra un topping, lo convierte a un `PersistenciaToppingDTO`.
     * Se asume que el nombre del topping es único, devolviendo el primer resultado encontrado.
     *
     * @param nombre El nombre del topping a buscar.
     * @return El `PersistenciaToppingDTO` correspondiente al nombre, o `null` si no se encuentra.
     * @throws PersistenciaException Si ocurre un error durante la consulta en la base de datos.
     */
    @Override
    public PersistenciaToppingDTO buscarPorNombre(String nombre) throws PersistenciaException {
        List<PersistenciaToppingDTO> productos = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        try (MongoCursor<Topping> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                productos.add(toppingMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar el topping por nombre en la base de datos");
        }
        if (productos.isEmpty()) {
            return null;
        }
        return productos.get(0);
    }

    /**
     * Guarda un nuevo objeto `Topping` en la base de datos. Antes de la inserción,
     * el `PersistenciaToppingDTO` de entrada se convierte a una entidad `Topping`.
     * Si el DTO no contiene un ID, se le asigna un nuevo `ObjectId` (identificador
     * nativo de MongoDB). Después de una inserción exitosa, el ID generado por MongoDB
     * se actualiza de vuelta en el `PersistenciaToppingDTO` para que refleje el ID de la base de datos.
     *
     * @param toppingDTO El `PersistenciaToppingDTO` que contiene los datos del topping a guardar.
     * @throws PersistenciaException Si ocurre un error al guardar el topping en la base de datos.
     */
    @Override
    public void guardar(PersistenciaToppingDTO toppingDTO) throws PersistenciaException {
        try {
            Topping topping = toppingMapper.toEntity(toppingDTO);
            if (topping.getId() == null) {
                topping.setId(new ObjectId());
            }
            coleccion.insertOne(topping);
            toppingDTO.setId(topping.getId().toHexString());
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar topping en la base de datos", e);
        }
    }
}
