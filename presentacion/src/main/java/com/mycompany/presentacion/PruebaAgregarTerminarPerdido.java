/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.presentacion;

import DTOs.PedidoDTO;
import DTOs.ProductoMostrarDTO;
import DTOs.PersistenciaProductoPedidoDTO;
import DTOs.SaborMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.ToppingMostrarDTO;
import control.ControlNavegacion;
//import gestion.IGestionPedidos;
import gestion.ManejadorPedidos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pablo
 */
public class PruebaAgregarTerminarPerdido {
//public static IGestionPedidos gestion = new ManejadorPedidos();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {      
        ControlNavegacion.mostrarAgregarTerminarPedido();
//        ProductoMostrarDTO producto = new ProductoMostrarDTO();
//        PedidoDTO pedido = new PedidoDTO();
//        List<ProductoPedidoDTO> lista = new ArrayList<>();
//        
//        producto.setNombre("Café"); // Establecer nombre del producto correctamente
//        producto.setId(1L);
//        
//        SaborMostrarDTO sabor = new SaborMostrarDTO();
//        sabor.setNombre("Fresa"); // Establecer nombre del sabor correctamente
//        sabor.setId(1L);
//        
//        TamanioMostrarDTO tamanio = new TamanioMostrarDTO();
//        tamanio.setNombre("Grande"); // Establecer nombre del tamaño correctamente
//        tamanio.setId(1L);
//        
//        ToppingMostrarDTO topping = new ToppingMostrarDTO();
//        topping.setNombre("Cajeta"); // Establecer nombre del topping correctamente
//        
//        topping.setId(1L);
//        
//        PersistenciaProductoPedidoDTO PEDIDO1 = new PersistenciaProductoPedidoDTO(producto, tamanio, sabor, topping);
//        PersistenciaProductoPedidoDTO PEDIDO2 = new PersistenciaProductoPedidoDTO(producto, tamanio, sabor, topping);
//        PersistenciaProductoPedidoDTO PEDIDO3 = new PersistenciaProductoPedidoDTO(producto, tamanio, sabor, topping);
//        
//        
//        
//        lista.add(PEDIDO1);
////        lista.add(PEDIDO2);
////        lista.add(PEDIDO3);
//        
//        pedido.setPedido(lista);
//
//        gestion.terminarPedido();
//        boolean agregado = gestion.agregarProductoPedidoAPedido(PEDIDO1);
//
//        System.out.println("¿Se agregó el producto después de terminar el pedido? " + agregado);
//        System.out.println(pedido);
    }
    
}
