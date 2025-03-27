/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestion;

import DTOs.EfectivoDTO;
import DTOs.PedidoDTO;
import DTOs.ProductoMostrarDTO;
import DTOs.ProductoPedidoDTO;
import DTOs.SaboresMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.ToppingsMostrarDTO;
import DTOs.TarjetaDTO;
import exception.GestionException;
import java.util.List;

/**
 *
 * @author Jp
 */
public interface IGestionPedidos {

    public PedidoDTO getPedido();

    public List<ProductoMostrarDTO> cargarProductos();

    public void agregarProducto(ProductoMostrarDTO producto);

    public void iniciarPedido();

    public void crearProductoPedido();

    public List<TamanioMostrarDTO> cargarTamanios();

    public void agregarTamanio(TamanioMostrarDTO tamanio);

    public List<SaboresMostrarDTO> cargarSabores();

    public void agregarSabor(SaboresMostrarDTO sabor);

    public List<ToppingsMostrarDTO> cargarToppings();

    public void agregarTopping(ToppingsMostrarDTO topping);

    public boolean validarTarjetaPresentacion(TarjetaDTO tarjeta) throws GestionException;

    public boolean cancelarPedido(PedidoDTO pedido) throws GestionException;

    public boolean agregarProductoPedidoAPedido() throws GestionException;

    public boolean terminarPedido() throws GestionException;

    public double calcularCosto();

    public double calcularTotal();

    public double calcularCambio(EfectivoDTO efectivo);

    public void cancelarProductoPedido(ProductoPedidoDTO productoPedido);

    public void imprimirPedidoConsola();
    
    public void setProductoPedidoActual(ProductoPedidoDTO productoPedidoActual);
    
    public ProductoPedidoDTO getProductoPedidoActual();
    
     public double actualizarTotal();
}
