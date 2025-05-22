package BOs;

import DTOs.PedidoDTO;
import DTOs.PersistenciaProductoDTO;
import DTOs.PersistenciaSaborDTO;
import DTOs.PersistenciaTamanioDTO;
import DTOs.PersistenciaToppingDTO;
import DTOs.PersistenciaProductoPedidoDTO;
import DTOs.ProductoPedidoDTO;
import excepciones.NegocioException;
import interfacesBO.IPedidoBO;
import java.util.ArrayList;
import interfacesObservers.NuevaVentaObserver;
import interfacesMapper.IPedidoMapper;
import java.util.List;
import mapper.PedidoMapper;
import IDAOs.IPedidoDAO;
import IDAOs.IProductoDAO;
import IDAOs.ISaborDAO;
import IDAOs.ITamanioDAO;
import IDAOs.IToppingDAO;
import acceso.AccesoDatos;
import entidades.Pedido;
import entidades.ProductoPedido;
import excepciones.PersistenciaException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

            List<ProductoPedidoDTO> pds = pedidoDTO.getProductos();
            List<ProductoPedido> pdsE = new ArrayList<>();
            Pedido pedido = pedidoMapper.toEntity(pedidoDTO);

            for (ProductoPedidoDTO pd : pds) {

                PersistenciaProductoDTO producto;
                PersistenciaTamanioDTO tamanio;
                PersistenciaSaborDTO sabor;
                PersistenciaToppingDTO topping;

                producto = productoDAO.buscarPorNombre(pd.getProducto().getNombre());
                tamanio = tamanioDAO.buscarPorNombre(pd.getTamanio().getNombre());
                sabor = saborDAO.buscarPorNombre(pd.getSabor().getNombre());
                if (pd.getTopping() != null) {
                    topping = toppingDAO.buscarPorNombre(pd.getTopping().getNombre());
                }
            }

            pedido.setPedido(pdsE);
            pedidoDAO.registrarPedido(pedido);
            return pedidoDTO;

        } catch (PersistenciaException ex) {
            Logger.getLogger(PedidoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al registrar");
        }
        return null;
    }

    @Override
    public List<PedidoDTO> obtenerPedidosDelivery() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarEstado(String idPedido) throws NegocioException {
        try {
            pedidoDAO.actualizarEstado(idPedido);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al intentar actualizar el estado del pedido", e);
        }
    }

}
