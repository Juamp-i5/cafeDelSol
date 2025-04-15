package DAOsMongo;

import IDAOs.IProductoPedidoDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.IConexionMongo;
import entidades.ProductoPedido;

public class ProductoPedidoDAOMongo implements IProductoPedidoDAO {

    //Singleton
    private static ProductoPedidoDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private MongoCollection<ProductoPedido> coleccion;
    private final String NOMBRE_COLECCION = "productos_pedidos";

    private ProductoPedidoDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, ProductoPedido.class);
    }

    public static ProductoPedidoDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new ProductoPedidoDAOMongo(conexion);
        }
        return instancia;
    }

    //Métodos de la colección
}
