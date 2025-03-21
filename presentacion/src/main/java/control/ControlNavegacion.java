/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import gestion.IGestionPedidos;
import gestion.ManejadorPedidos;
import java.util.Stack;
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
    }

    public static void mostrarPantallaSabores(){
        JFrame sabores = new Sabores(gestor.cargarSabores());
        sabores.setLocationRelativeTo(null);
        sabores.setVisible(true);
    }
    
    public static void mostrarPantallaToppings(){
        JFrame toppings = new Toppings(gestor.cargarToppings());
        toppings.setLocationRelativeTo(null);
        toppings.setVisible(true);
    }
    
    public static void mostrarPantallaAgregarProducto(){
        JOptionPane.showMessageDialog(null, "Se selecciono topping");
    }
    
    public static void mostrarAgregarTerminarPedido(){
        JFrame agregarTerminarPedido = new AgregarOTerminarPedido();
        agregarTerminarPedido.setLocationRelativeTo(null);
        agregarTerminarPedido.setVisible(true);
    }
    
    public static void mostrarPagoTarjeta(){
        JFrame pagoTarjeta = new PagoTarjeta();
        pagoTarjeta.setLocationRelativeTo(null);
        pagoTarjeta.setVisible(true);
    }
    
    public static void mostrarPantallaTarjetaRechazada(){
        JOptionPane.showMessageDialog(null,"Tarjeta rechazada");
    }
}
