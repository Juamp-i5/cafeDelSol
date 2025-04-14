package DAOsMongo;

import entidades.Producto;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.IProductoDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexionMongo.IConexionMongo;

/**
 *
 * @author rodri
 */
public class ProductoDAOMongo implements IProductoDAO {

    //Singleton
    private static ProductoDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private final MongoCollection<Producto> coleccion;
    private final String NOMBRE_COLECCION = "productos";

    private ProductoDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Producto.class);
    }

    public static ProductoDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new ProductoDAOMongo(conexion);
        }
        return instancia;
    }

    //Métodos de la coleccion
    @Override
    public List<Producto> buscarTodos() throws PersistenciaException {
        List<Producto> productos = new ArrayList<>();

        if (conexion.getDatabase() == null) {
            return List.of(
                    new Producto(1L, "Affogato", 50, "../img/affogato.jpg"),
                    new Producto(2L, "Café Americano", 40, "../img/cafeAmericano.jpg"),
                    new Producto(3L, "Café Descafeinado", 30, "../img/cafeDescafeinado.jpg"),
                    new Producto(4L, "Capuchino", 50, "../img/capuchino.jpg"),
                    new Producto(5L, "Caramel Macchiato", 50, "../img/caramelMacchiato.jpg")
            );
        } else {
            //lógica de mongo
        }

        return productos;
    }

    @Override
    public Producto buscarPorNombre(String nombre) throws PersistenciaException {
        List<Producto> productos = buscarTodos(); // usa tu método existente

        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }

        return null; // si no lo encuentra
    }

}
