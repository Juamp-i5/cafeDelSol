package IDAOs;

import entidades.Pedido;
import excepciones.PersistenciaException;

/**
 *
 * @author rodri
 */
public interface IPedidoDAO {
    
    public Pedido registrarPedido (Pedido pedido) throws PersistenciaException;
    
}
