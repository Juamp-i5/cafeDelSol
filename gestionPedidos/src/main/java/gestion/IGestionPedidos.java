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
import java.util.List;

/**
 *
 * @author Jp
 */
public interface IGestionPedidos {

    public PedidoDTO getPedido();

    public ProductoPedidoDTO getProductoPedido();

    public List<ProductoMostrarDTO> cargarProductos();

    public void agregarProducto(ProductoMostrarDTO producto);

    public void crearPedido();

    public void crearProductoPedido();

    public List<TamanioMostrarDTO> cargarTamanios();

    public void agregarTamanio(TamanioMostrarDTO tamanio);

    public List<SaboresMostrarDTO> cargarSabores();

    public void agregarSabor(SaboresMostrarDTO sabor);

    public List<ToppingsMostrarDTO> cargarToppings();

    public void agregarTopping(ToppingsMostrarDTO topping);

    public boolean validarTarjetaPresentacion(TarjetaDTO tarjeta);
    
    public boolean cancelarPedido(PedidoDTO pedido);

    public boolean agregarProductoPedidoAPedido(ProductoPedidoDTO productoPedido);
    

    public boolean terminarPedido();
    
    public double calcularCosto();
     
    public double calcularTotal();
    
    public double calcularCambio(EfectivoDTO efectivo);

}
