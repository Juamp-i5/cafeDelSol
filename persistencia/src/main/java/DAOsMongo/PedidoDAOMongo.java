package DAOsMongo;

import DTOs.PersistenciaPedidoDTO;
import entidades.Pedido;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.IPedidoDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import conexion.IConexionMongo;
import interfacesMappers.IPedidoMapper;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import utils.DependencyInjectors;

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
    
    private final IPedidoMapper pedidoMapper = DependencyInjectors.getInstancia().getPedidoMapper();


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
    public PersistenciaPedidoDTO registrarPedido(PersistenciaPedidoDTO pedido) throws PersistenciaException {
        if (pedido == null) {
            throw new PersistenciaException("Error al registrar el pedido en la base de datos, el pedido no puede ser nulo");
        }
        Pedido entidad = pedidoMapper.toEntity(pedido);
        if (pedido.getId() == null || pedido.getId().trim().equals("")) {
            entidad.setId(new ObjectId());
            pedido.setId(entidad.getId().toHexString());
        }
        try {
            coleccion.insertOne(entidad);
        } catch (Exception e) {
            throw new PersistenciaException("Error al registrar el pedido en la base de datos", e);
        }
        return pedidoMapper.toPersistenciaProductoTamanioDTO(entidad);
    }

    @Override
    public List<PersistenciaPedidoDTO> obtenerPedidosDelivery() throws PersistenciaException {
        List<PersistenciaPedidoDTO> pedidos = new ArrayList<>();
        Bson filtro = Filters.eq("estado", "PENDIENTE");
        try (MongoCursor<Pedido> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                pedidos.add(pedidoMapper.toPersistenciaProductoTamanioDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar todos los productos habilitados en la base de datos", e);
        }
        return pedidos;
    }

    @Override
    public void actualizarEstado(String idPedido) throws PersistenciaException {
        try {
            String nuevoEstado = "TERMINADO";
            coleccion.updateOne(Filters.eq("_id", new ObjectId(idPedido)), Updates.set("estado", nuevoEstado));
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar el estado del pedido", e);
        }
    }
    
    
}
