/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestion;

import DTOs.ProductoMostrarDTO;
import java.util.List;

/**
 *
 * @author Jp
 */
public interface IGestionPedidos {

    public List<ProductoMostrarDTO> cargarProductos();
    
    public void agregarProducto(ProductoMostrarDTO producto);
    
    public void crearPedido();
    
    public void crearProductoPedido();
}
