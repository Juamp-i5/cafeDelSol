/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.presentacion;

import DTOs.ProductoMostrarDTO;
import DTOs.ProductoPedidoDTO;
import DTOs.SaboresMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.ToppingsMostrarDTO;
import control.ControlNavegacion;
import pantallas.EditarProducto;

/**
 *
 * @author katia
 */
public class PruebaEditar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Simulamos un producto seleccionado
        ProductoMostrarDTO producto = new ProductoMostrarDTO(1L, "Frappe frío", "../img/latteFrio.jpeg");
        TamanioMostrarDTO tamanio = new TamanioMostrarDTO(2L, "Mediano", "../img/tamanioMediano.jpg", 5);
        SaboresMostrarDTO sabor = new SaboresMostrarDTO(3L, "Moka", "../img/saborMoka.jpg");
        ToppingsMostrarDTO topping = new ToppingsMostrarDTO(1L, "Azúcar", "../img/azucar.jpeg");

        // Crear el objeto ProductoPedidoDTO
        ProductoPedidoDTO productoPedido = new ProductoPedidoDTO();
        productoPedido.setProducto(producto);
        productoPedido.setTamanio(tamanio);
        productoPedido.setSabor(sabor);
        productoPedido.setTopping(topping);

        // Iniciar la ventana de edición del producto
        new EditarProducto(productoPedido);
        
    }
    
}
