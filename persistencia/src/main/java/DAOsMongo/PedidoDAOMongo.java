package DAOsMongo;

import entidades.Pedido;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.IPedidoDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexionMongo.IConexionMongo;

/**
 *
 * @author rodri
 */
public class PedidoDAOMongo implements IPedidoDAO {

    //Singleton
    private static PedidoDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private final MongoCollection<Pedido> coleccion;
    private final String NOMBRE_COLECCION = "pedidos";

    private PedidoDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Pedido.class);
    }

    public static PedidoDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new PedidoDAOMongo(conexion);
        }
        return instancia;
    }

    //Métodos de la colección
    @Override
    public Pedido registrarPedido(Pedido pedido) throws PersistenciaException {
        List<Pedido> pedidos = new ArrayList<>();

        if (conexion.getDatabase() == null) {
            pedidos.add(pedido);

            StringBuilder mensaje = new StringBuilder();
            for (Pedido pedidoMostrar : pedidos) {
                mensaje.append(pedidoMostrar.toString()).append("\n");
            }

            System.out.println(mensaje.toString());
        } else {
            // lógica de mongo

        }

        return pedido;
    }
}
