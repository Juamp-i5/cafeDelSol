package IDAOs;

import DTOs.PersistenciaPedidoDTO;
import entidades.Pedido;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IPedidoDAO {

    public PersistenciaPedidoDTO registrarPedido(PersistenciaPedidoDTO pedido) throws PersistenciaException;
    
    public List<PersistenciaPedidoDTO> obtenerPedidosDelivery() throws PersistenciaException;
    
    public void actualizarEstado(String idPedido) throws PersistenciaException;
}
