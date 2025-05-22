package DAOsMongo;

import entidades.Pedido;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.IPedidoDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.IConexionMongo;
import org.bson.conversions.Bson;

/**
 *
 * @author rodri
 */
public class PedidoDAOMongo implements IPedidoDAO {

    //Singleton
    private static PedidoDAOMongo instancia;
    private final MongoDatabase database;

    private final MongoCollection<Pedido> coleccion;
    private final String NOMBRE_COLECCION = "pedidos";

    private PedidoDAOMongo(IConexionMongo conexion) {
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Pedido.class);
    }

    public static PedidoDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new PedidoDAOMongo(conexion);
        }
        return instancia;
    }

    @Override
    public Pedido registrarPedido(Pedido pedido) throws PersistenciaException {
        return pedido;
    }

    @Override
    public List<Pedido> obtenerPedidosDelivery() throws PersistenciaException {
        List<Pedido> pedidos = new ArrayList<>();
        Bson filtro = Filters.eq("estado", "PENDIENTE");
        try (MongoCursor<Pedido> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                pedidos.add(cursor.next());
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar todos los pedidos en la base de datos", e);
        }
        return pedidos;
    }
}
