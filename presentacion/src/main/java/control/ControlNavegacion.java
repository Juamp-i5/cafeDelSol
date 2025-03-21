/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import gestion.IGestionPedidos;
import gestion.ManejadorPedidos;
import java.util.Stack;
import javax.swing.JFrame;
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

}
