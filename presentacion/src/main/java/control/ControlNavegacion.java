/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DTOs.ProductoMostrarDTO;
import gestion.IGestionPedidos;
import gestion.ManejadorPedidos;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pantallas.*;

/**
 *
 * @author Jp
 */
public class ControlNavegacion {

    public static IGestionPedidos gestor = new ManejadorPedidos();
    private static Stack framesVisitados = new Stack();

    public static void mostrarPantallaProductos() {
        JFrame productos = new Productos(gestor.cargarProductos());
        productos.setLocationRelativeTo(null);
        productos.setVisible(true);
        
        framesVisitados.add(productos);
    }

    public static void mostrarPantallaMenuPrincipal() {
        JFrame menuPrincipal = new MenuPrincipal();
        menuPrincipal.setLocationRelativeTo(null);
        menuPrincipal.setVisible(true);

        framesVisitados.add(menuPrincipal);
    }

    public static void volverPantallaAnterior() {
        if (!framesVisitados.isEmpty()) {
            JFrame ventanaActual = (JFrame) framesVisitados.pop();
            ventanaActual.dispose();

            if (!framesVisitados.isEmpty()) {
                JFrame frameAnterior = (JFrame) framesVisitados.peek();
                frameAnterior.setLocationRelativeTo(null);
                frameAnterior.setVisible(true);
            }
        }
    }
     
    public static void mostrarPantallaTamanios(){
        JFrame tamanios = new Tamanios(gestor.cargarTamanios());
        tamanios.setLocationRelativeTo(null);
        tamanios.setVisible(true);
        
        framesVisitados.add(tamanios);
    }

    public static void mostrarPantallaSabores(){
        JFrame sabores = new Sabores(gestor.cargarSabores());
        sabores.setLocationRelativeTo(null);
        sabores.setVisible(true);
        
        framesVisitados.add(sabores);
    }
    
    public static void mostrarPantallaToppings(){
        JFrame toppings = new Toppings(gestor.cargarToppings());
        toppings.setLocationRelativeTo(null);
        toppings.setVisible(true);
        
        framesVisitados.add(toppings);
    }
    
    public static void mostrarAgregarTerminarPedido(){
        JFrame agregarTerminarPedido = new AgregarOTerminarPedido();
        agregarTerminarPedido.setLocationRelativeTo(null);
        agregarTerminarPedido.setVisible(true);
        
        framesVisitados.add(agregarTerminarPedido);
    }
    
    public static void mostrarPagoTarjeta(){
        JFrame pagoTarjeta = new PagoTarjeta();
        pagoTarjeta.setLocationRelativeTo(null);
        pagoTarjeta.setVisible(true);
        
        framesVisitados.add(pagoTarjeta);
    }
    
    public static void mostrarPantallaTarjetaRechazada(){
        JOptionPane.showMessageDialog(null,"Tarjeta rechazada");
    }
    
    public static void mostrarPantallaTotalDesglosado(){
        JFrame totalDesglosado = new TotalDesglosado();
        totalDesglosado.setLocationRelativeTo(null);
        totalDesglosado.setVisible(true);
        
        framesVisitados.add(totalDesglosado);
    }

    public static void mostrarPantallaProductos(EditarProducto editarProductoFrame) {
        JFrame productos = new Productos(gestor.cargarProductos(), editarProductoFrame);
        productos.setLocationRelativeTo(null);
        productos.setVisible(true);

        framesVisitados.add(productos);
    }
    
    public static void mostrarPantallaTamanios(EditarProducto editarProductoFrame) {
        JFrame tamanios = new Tamanios(gestor.cargarTamanios(), editarProductoFrame);
        tamanios.setLocationRelativeTo(null);
        tamanios.setVisible(true);

        framesVisitados.add(tamanios);
    }
    
    public static void mostrarPantallaSabores(EditarProducto editarProductoFrame){
        JFrame sabores = new Sabores(gestor.cargarSabores(), editarProductoFrame);
        sabores.setLocationRelativeTo(null);
        sabores.setVisible(true);
        
        framesVisitados.add(sabores);
    }
    
    public static void mostrarPantallaToppings(EditarProducto editarProductoFrame){
        JFrame toppings = new Toppings(gestor.cargarToppings(), editarProductoFrame);
        toppings.setLocationRelativeTo(null);
        toppings.setVisible(true);
        
        framesVisitados.add(toppings);
    }
    
    public static void mostrarPantallaEditarProducto(ProductoMostrarDTO producto){
        JFrame editarProducto = new EditarProducto(producto);
        editarProducto.setLocationRelativeTo(null);
        editarProducto.setVisible(true);
        
        framesVisitados.add(editarProducto);
    }
    
    public static void mostrarPantallaPagoEfectivo(){
        JFrame pagoEfectivo = new PagoEfectivo();
        pagoEfectivo.setLocationRelativeTo(null);
        pagoEfectivo.setVisible(true);
        
        framesVisitados.add(pagoEfectivo);
    }
    
    public static void mostrarPantallaPedidoRealizado(){
         JOptionPane.showMessageDialog(null, "Pedido realizado");
         new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ControlNavegacion.mostrarPantallaMenuPrincipal();
                }
            }, 3000);
    }
}
