package BOs;

import DTOs.PedidoDTO;
import DTOs.PersistenciaPedidoDTO;
import DTOs.PersistenciaProductoDTO;
import DTOs.PersistenciaSaborDTO;
import DTOs.PersistenciaTamanioDTO;
import DTOs.PersistenciaToppingDTO;
import DTOs.PersistenciaProductoPedidoDTO;
import DTOs.PersistenciaProductoTamanioDTO;
import DTOs.PersistenciaProductoTamanioIngredienteDTO;
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
import IDAOs.ingredientes.IIngredienteDAOMongo;
import acceso.AccesoDatos;
import entidades.Pedido;
import entidades.ProductoPedido;
import excepciones.PersistenciaException;
import java.util.Optional;
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
    IIngredienteDAOMongo ingredienteDAO = AccesoDatos.getIngredienteDAO();

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
    public void registrarPedido(PedidoDTO pedidoDTO) throws NegocioException {
        try {
            PersistenciaPedidoDTO pedidoEntidad = pedidoMapper.toPersistenciaPedidoDTO(pedidoDTO);

            List<PersistenciaProductoPedidoDTO> productosPedidoEntidades = new ArrayList<>();
            for (ProductoPedidoDTO ppDTO : pedidoDTO.getProductos()) {
                if (ppDTO == null || ppDTO.getProducto() == null || ppDTO.getProducto().getNombre() == null || ppDTO.getProducto().getNombre().trim().isEmpty()) {
                    throw new NegocioException("Uno de los productos en el pedido tiene información inválida o faltante.");
                }
                productosPedidoEntidades.add(convertirYValidarProductoPedido(ppDTO));
            }
            pedidoEntidad.setProductos(productosPedidoEntidades);

            pedidoDAO.registrarPedido(pedidoEntidad);
            for (PersistenciaProductoPedidoDTO productoPedidoDTO : pedidoEntidad.getProductos()) {
                String idProducto = productoPedidoDTO.getProducto().getId();
                String idTamanio = productoPedidoDTO.getTamanio().getId();
                int cantidadPedida = productoPedidoDTO.getCantidad();

                PersistenciaProductoDTO productoDetalle = productoDAO.buscarPorId(idProducto);

                if (productoDetalle != null) {
                    Optional<PersistenciaProductoTamanioDTO> tamanioProd = productoDetalle.getTamanios().stream()
                            .filter(tp -> tp.getTamanio().getId().equals(idTamanio))
                            .findFirst();

                    if (tamanioProd.isPresent()) {
                        for (PersistenciaProductoTamanioIngredienteDTO ingredienteProd : tamanioProd.get().getIngredientes()) {
                            String idIngrediente = ingredienteProd.getIngrediente().getId();
                            double totalADescontar = ingredienteProd.getCantidad() * cantidadPedida;

                            // 4. Descontar ingrediente del inventario
                            ingredienteDAO.descontarStock(idIngrediente, totalADescontar);
                        }
                    }
                }
            }

            notificarObservers();

        } catch (PersistenciaException ex) {
            Logger.getLogger(PedidoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al registrar");
        }
    }

    @Override
    public List<PedidoDTO> obtenerPedidosDelivery() throws NegocioException {
        List<PersistenciaPedidoDTO> persistenciaPedidos;
        try {
            persistenciaPedidos = pedidoDAO.obtenerPedidosDelivery();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener los pedidos delivery", e);
        }
        List<PedidoDTO> pedidosDTO = new ArrayList<>();

        for (PersistenciaPedidoDTO persistenciaPedido : persistenciaPedidos) {
            pedidosDTO.add(pedidoMapper.toPedidoDTO(persistenciaPedido));
        }

        return pedidosDTO;
    }

    @Override
    public void actualizarEstado(String idPedido) throws NegocioException {
        try {
            pedidoDAO.actualizarEstado(idPedido);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al intentar actualizar el estado del pedido", e);
        }
    }

    private PersistenciaProductoPedidoDTO convertirYValidarProductoPedido(ProductoPedidoDTO ppDTO) throws NegocioException, PersistenciaException {
        PersistenciaProductoDTO productoEnt = productoDAO.buscarPorNombre(ppDTO.getProducto().getNombre());
        if (productoEnt == null) {
            throw new NegocioException("El producto '" + ppDTO.getProducto().getNombre() + "' no fue encontrado.");
        }
        PersistenciaProductoDTO productoNuevo = new PersistenciaProductoDTO();
        productoNuevo.setId(productoEnt.getId());
        productoNuevo.setNombre(productoEnt.getNombre());

        PersistenciaTamanioDTO tamanioEnt = null;
        if (ppDTO.getTamanio() != null && ppDTO.getTamanio().getNombre() != null && !ppDTO.getTamanio().getNombre().trim().isEmpty()) {
            tamanioEnt = tamanioDAO.buscarPorNombre(ppDTO.getTamanio().getNombre());
            if (tamanioEnt == null) {
                throw new NegocioException("El tamaño '" + ppDTO.getTamanio().getNombre() + "' no fue encontrado.");
            }
        }
        tamanioEnt.setImagenData(null);
        tamanioEnt.setPrecioAdicional(null);

        PersistenciaSaborDTO saborEnt = null;
        if (ppDTO.getSabor() != null && ppDTO.getSabor().getNombre() != null && !ppDTO.getSabor().getNombre().trim().isEmpty()) {
            saborEnt = saborDAO.buscarPorNombre(ppDTO.getSabor().getNombre());
            if (saborEnt == null) {
                throw new NegocioException("El sabor '" + ppDTO.getSabor().getNombre() + "' no fue encontrado.");
            }
        }
        saborEnt.setImagenData(null);

        PersistenciaToppingDTO toppingEnt = null;
        if (ppDTO.getTopping() != null && ppDTO.getTopping().getNombre() != null && !ppDTO.getTopping().getNombre().trim().isEmpty()) {
            toppingEnt = toppingDAO.buscarPorNombre(ppDTO.getTopping().getNombre());
            if (toppingEnt == null) {
                throw new NegocioException("El topping '" + ppDTO.getTopping().getNombre() + "' no fue encontrado.");
            }
        }
        if (toppingEnt != null) {
            toppingEnt.setImagenData(null);
        }
        PersistenciaProductoPedidoDTO ppEntidad = new PersistenciaProductoPedidoDTO();
        ppEntidad.setProducto(productoNuevo);
        ppEntidad.setTamanio(tamanioEnt);
        ppEntidad.setSabor(saborEnt);
        if (toppingEnt != null) {
            ppEntidad.setTopping(toppingEnt);
        }
        ppEntidad.setCantidad(ppDTO.getCantidad());

        return ppEntidad;
    }
}
