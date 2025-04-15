package DAOsMongo;

import entidades.Topping;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.IToppingDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.IConexionMongo;

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
    public List<Topping> buscarTodos() throws PersistenciaException {
        List<Topping> toppings = new ArrayList<>();

        if (conexion.getDatabase() == null) {
            return List.of(
                    new Topping(1L, "Azúcar", "../img/azucar.jpeg"),
                    new Topping(2L, "Canela", "../img/canela.jpg"),
                    new Topping(3L, "Nutella", "../img/nutella.jpg"),
                    new Topping(4L, "Cajeta", "../img/cajeta.jpg")
            );
        } else {
            // lógica de mongo
        }

        return toppings;
    }

    @Override
    public Topping buscarPorNombre(String nombre) throws PersistenciaException {
        List<Topping> toppings = buscarTodos(); // usa tu método existente

        for (Topping p : toppings) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }

        return null; // si no lo encuentra
    }
}
