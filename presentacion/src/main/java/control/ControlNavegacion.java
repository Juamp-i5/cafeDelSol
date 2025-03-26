/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DTOs.ProductoPedidoDTO;
import gestion.IGestionPedidos;
import gestion.ManejadorPedidos;
import java.awt.Component;
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

    public static void iniciarPedidoNuevo() {
        gestor.iniciarPedido();
    }
    
    public static void agregarProductoPedidoAPedido(){
        gestor.agregarProductoPedidoAPedido();
    }

    // PANTALLAS
    public static void mostrarPantallaProductos(Modo modo) {
        JFrame productos = new PantallaSeleccionarProducto(gestor.cargarProductos(), modo);
        productos.setLocationRelativeTo(null);
        productos.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(productos);
    }

    public static void mostrarPantallaMenuPrincipal() {
        JFrame menuPrincipal = new PantallaMenuPrincipal();
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

    public static void mostrarPantallaTamanios() {
        JFrame tamanios = new Tamanios(gestor.cargarTamanios());
        tamanios.setLocationRelativeTo(null);
        tamanios.setVisible(true);
        
        gestor.imprimirPedidoConsola();

        framesVisitados.add(tamanios);
    }

    public static void mostrarPantallaSabores() {
        JFrame sabores = new Sabores(gestor.cargarSabores());
        sabores.setLocationRelativeTo(null);
        sabores.setVisible(true);
        
        gestor.imprimirPedidoConsola();

        framesVisitados.add(sabores);
    }

    public static void mostrarPantallaToppings(Modo modo) {
        JFrame toppings = new PantallaToppings(gestor.cargarToppings(), modo);
        toppings.setLocationRelativeTo(null);
        toppings.setVisible(true);
        
        gestor.imprimirPedidoConsola();

        framesVisitados.add(toppings);
    }
    
    public static void mostrarAgregarTerminarPedido() {
        JFrame agregarTerminarPedido = new AgregarOTerminarPedido();
        agregarTerminarPedido.setLocationRelativeTo(null);
        agregarTerminarPedido.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.empty();
    }

    public static void mostrarPagoTarjeta() {
        JFrame pagoTarjeta = new PagoTarjeta();
        pagoTarjeta.setLocationRelativeTo(null);
        pagoTarjeta.setVisible(true);
        gestor.imprimirPedidoConsola();

        framesVisitados.add(pagoTarjeta);
    }

    public static void mostrarPantallaTarjetaRechazada() {
        JOptionPane.showMessageDialog(null, "Tarjeta rechazada");
    }

    public static void mostrarPantallaTotalDesglosado() {
        JFrame totalDesglosado = new TotalDesglosado();
        totalDesglosado.setLocationRelativeTo(null);
        totalDesglosado.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(totalDesglosado);
    }

    public static void mostrarPantallaTamanios(PantallaEditarProducto editarProductoFrame) {
        if (editarProductoFrame != null) {
            editarProductoFrame.dispose();
        }
        JFrame tamanios = new Tamanios(gestor.cargarTamanios(), editarProductoFrame);
        tamanios.setLocationRelativeTo(null);
        tamanios.setVisible(true);
        
        gestor.imprimirPedidoConsola();

        framesVisitados.add(tamanios);
    }

    public static void mostrarPantallaSabores(PantallaEditarProducto editarProductoFrame) {
        if (editarProductoFrame != null) {
            editarProductoFrame.dispose();
        }
        JFrame sabores = new Sabores(gestor.cargarSabores(), editarProductoFrame);
        sabores.setLocationRelativeTo(null);
        sabores.setVisible(true);
        
        gestor.imprimirPedidoConsola();

        framesVisitados.add(sabores);
    }

//    public static void mostrarPantallaToppings(PantallaEditarProducto editarProductoFrame) {
//        if (editarProductoFrame != null) {
//            editarProductoFrame.dispose();
//        }
//        JFrame toppings = new PantallaToppings(gestor.cargarToppings(), editarProductoFrame);
//        toppings.setLocationRelativeTo(null);
//        toppings.setVisible(true);
//        
//        gestor.imprimirPedidoConsola();
//
//        framesVisitados.add(toppings);
//    }

    public static void mostrarPantallaEditarProducto(ProductoPedidoDTO productoPedido) {
        JFrame editarProducto = new PantallaEditarProducto(productoPedido);
        editarProducto.setLocationRelativeTo(null);
        editarProducto.setVisible(true);
        
        gestor.imprimirPedidoConsola();

        framesVisitados.add(editarProducto);
    }

    public static void mostrarPantallaPagoEfectivo() {
        JFrame pagoEfectivo = new PagoEfectivo();
        pagoEfectivo.setLocationRelativeTo(null);
        pagoEfectivo.setVisible(true);
        
        gestor.imprimirPedidoConsola();

        framesVisitados.add(pagoEfectivo);
    }

    public static void mostrarPantallaPedidoRealizado() {
        JOptionPane.showMessageDialog(null, "Pedido realizado");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ControlNavegacion.mostrarPantallaMenuPrincipal();
            }
        }, 1000);
    }

    public static void mostrarPantallaPedidoCancelado(Component frame) {

        JOptionPane.showMessageDialog(
                frame,
                "El pedido ha sido cancelado con éxito.",
                "Cancelación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
        );

    }

    public static void mostrarPantallaProductoPedidoCancelado(Component frame) {

        JOptionPane.showMessageDialog(
                frame,
                "El producto pedido ha sido cancelado con éxito.",
                "Cancelación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
        );

    }
}
