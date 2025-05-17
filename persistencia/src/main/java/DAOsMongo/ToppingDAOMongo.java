package DAOsMongo;

import DTOs.SaborDTO;
import DTOs.ToppingDTO;
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
import entidades.Sabor;
import interfacesMappers.IToppingMapper;
import mappers.ToppingMapper;
import org.bson.conversions.Bson;

/**
 *
 * @author rodri
 */
public class ToppingDAOMongo implements IToppingDAO {

    //Singleton
    private static ToppingDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private MongoCollection<Topping> coleccion;
    private final String NOMBRE_COLECCION = "toppings";

    private final IToppingMapper toppingMapper = new ToppingMapper();

    private ToppingDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Topping.class);
    }

    public static ToppingDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new ToppingDAOMongo(conexion);
        }
        return instancia;
    }

    //Métodos de la colección
    @Override
    public List<ToppingDTO> buscarTodos() throws PersistenciaException {
        List<ToppingDTO> toppings = new ArrayList<>();
        try (MongoCursor<Topping> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                toppings.add(toppingMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar todos los toppings en la base de datos", e);
        }
        return toppings;
    }

    @Override
    public ToppingDTO buscarPorNombre(String nombre) throws PersistenciaException {
        List<ToppingDTO> productos = new ArrayList<>();
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
}
