/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import gestion.IGestionPedidos;
import gestion.ManejadorPedidos;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pantallas.*;

/**
 *
 * @author Jp
 */
public class ControlNavegacion {
    private static IGestionPedidos gestor = new ManejadorPedidos();
    
    public static void mostrarPantallaProductos(){
        JFrame productos = new Productos(gestor.cargarProductos());
        productos.setLocationRelativeTo(null);
        productos.setVisible(true);
    }
    
    public static void mostrarPantallaTamanios(){
        JOptionPane.showMessageDialog(null, "Se selecciono");
    }
}
