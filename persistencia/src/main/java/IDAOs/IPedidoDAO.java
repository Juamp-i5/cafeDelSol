package IDAOs;

import entidades.Pedido;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IPedidoDAO {

    public Pedido registrarPedido(Pedido pedido) throws PersistenciaException;
    
    public List<Pedido> obtenerPedidosDelivery() throws PersistenciaException;
}
