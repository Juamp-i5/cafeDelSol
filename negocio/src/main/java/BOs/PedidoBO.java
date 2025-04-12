/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAOs.PedidoDAOImp;
import DAOs.ProductoDAOImp;
import DAOs.SaborDAOImp;
import DAOs.TamanioDAOImp;
import DAOs.ToppingDAOImp;
import DTOs.PedidoDTO;
import DTOs.ProductoPedidoDTO;
import entidades.Pedido;
import entidades.Producto;
import entidades.ProductoPedido;
import entidades.Sabor;
import entidades.Tamanio;
import entidades.Topping;
import exception.NegocioException;
import exception.persistenciaException;
import interfaces.IPedido;
import interfaces.IProducto;
import interfaces.ISabor;
import interfaces.ITamanio;
import interfaces.ITopping;
import interfacesBO.IPedidoBO;

import java.util.ArrayList;
import java.util.List;
import observers.interfaces.NuevaVentaObserver;

import interfacesMapper.IPedidoMapper;
import interfacesMapper.IProductoMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.PedidoMapper;

/**
 *
 * @author rodri
 */
public class PedidoBO implements IPedidoBO {

    IPedidoMapper pedidoMapper = PedidoMapper.getInstance();
    IProducto productoDAO = ProductoDAOImp.getInstance();
    ISabor saborDAO = SaborDAOImp.getInstance();
    ITamanio tamanioDAO = TamanioDAOImp.getInstance();
    ITopping toppingDAO = ToppingDAOImp.getInstance();
    IPedido pedidoDAO = PedidoDAOImp.getInstance();

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
            
        } catch (persistenciaException ex) {
            Logger.getLogger(PedidoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al registrar");
        }
        

    }

}
