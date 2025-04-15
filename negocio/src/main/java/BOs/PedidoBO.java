package BOs;

import DTOs.PedidoDTO;
import DTOs.ProductoPedidoDTO;
import entidades.Pedido;
import entidades.Producto;
import entidades.ProductoPedido;
import entidades.Sabor;
import entidades.Tamanio;
import entidades.Topping;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfacesBO.IPedidoBO;
import java.util.ArrayList;
import interfacesObservers.NuevaVentaObserver;
import interfacesMapper.IPedidoMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.PedidoMapper;
import IDAOs.IPedidoDAO;
import IDAOs.IProductoDAO;
import IDAOs.ISaborDAO;
import IDAOs.ITamanioDAO;
import IDAOs.IToppingDAO;
import acceso.AccesoDatos;

/**
 *
 * @author rodri
 */
public class PedidoBO implements IPedidoBO {

    IPedidoMapper pedidoMapper = PedidoMapper.getInstance();
    IProductoDAO productoDAO = AccesoDatos.getProductoDAO();
    ISaborDAO saborDAO = AccesoDatos.getSaborDAO();
    ITamanioDAO tamanioDAO = AccesoDatos.getTamanioDAO();
    IToppingDAO toppingDAO = AccesoDatos.getToppingDAO();
    IPedidoDAO pedidoDAO = AccesoDatos.getPedidoDAO();

    private List<NuevaVentaObserver> observers = new ArrayList<>();

    public void agregarObserver(NuevaVentaObserver observer) {
        observers.add(observer);
    }

    private void notificarObservers() {
        for (NuevaVentaObserver observer : observers) {
            observer.update();
        }
    }

    private static PedidoBO instanceBO;

    public PedidoBO() {
    }

    public static PedidoBO getInstance() {
        if (instanceBO == null) {
            instanceBO = new PedidoBO();
        }
        return instanceBO;
    }

    @Override
    public PedidoDTO registrarPedido(PedidoDTO pedidoDTO) throws NegocioException {
        try {
            notificarObservers();

            List<ProductoPedidoDTO> pds = pedidoDTO.getPedido();
            List<ProductoPedido> pdsE = new ArrayList<>();
            Pedido pedido = pedidoMapper.toEntity(pedidoDTO);

            for (ProductoPedidoDTO pd : pds) {

                Producto producto;
                Tamanio tamanio;
                Sabor sabor;
                Topping topping;

                producto = productoDAO.buscarPorNombre(pd.getProducto().getNombre());
                tamanio = tamanioDAO.buscarPorNombre(pd.getTamanio().getNombre());
                sabor = saborDAO.buscarPorNombre(pd.getSabor().getNombre());
                if (pd.getTopping() != null) {
                    topping = toppingDAO.buscarPorNombre(pd.getTopping().getNombre());
                    pdsE.add(new ProductoPedido(producto, tamanio, sabor, topping));
                } else {
                    pdsE.add(new ProductoPedido(producto, tamanio, sabor));
                }
            }

            pedido.setPedido(pdsE);
            pedidoDAO.registrarPedido(pedido);
            return pedidoDTO;

        } catch (PersistenciaException ex) {
            Logger.getLogger(PedidoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al registrar");
        }

    }

}
