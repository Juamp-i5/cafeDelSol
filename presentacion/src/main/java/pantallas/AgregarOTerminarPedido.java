/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import DTOs.ProductoPedidoDTO;
import control.ControlNavegacion;
import control.Modo;
import gestion.IGestionPedidos;
import gestion.ManejadorPedidos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author pablo
 */
public class AgregarOTerminarPedido extends javax.swing.JFrame {
    /**
     * Creates new form AgregarOTerminarPedido
     *
     */
    
    final int ANCHO_FRAME_AGREGAR_TERMINAR_PEDIDO = 1000;
    final int ALTO_FRAME_AGREGAR_TERMINAR_PEDIDO = 800;
    final int TANAMNIO_SIMBOLO_AGREGAR = 30;
    final int TANAMNIO_SIMBOLO_TERMINAR = 30;
    final int CANTIDAD_FILAS = 1;
    final int CANTIDAD_COLUMNAS = 2;
    final int ESPACIO_ENTRE_BOTONES_HORIZONTAL = 10;
    final int ESPACIO_ENTRE_BOTONES_VERTICAL = 10;
    
    
    public AgregarOTerminarPedido() {
        setTitle("");
        setSize(ANCHO_FRAME_AGREGAR_TERMINAR_PEDIDO, ALTO_FRAME_AGREGAR_TERMINAR_PEDIDO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(crearPanelPedido(), BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel crearPanelPedido() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(Color.WHITE);

        JPanel panelCentral = new JPanel(new GridLayout(CANTIDAD_FILAS, CANTIDAD_COLUMNAS, ESPACIO_ENTRE_BOTONES_HORIZONTAL, ESPACIO_ENTRE_BOTONES_VERTICAL));

        JButton btnAgregar = new JButton("+");
        JButton btnTerminar = new JButton("->");

        btnAgregar.setFont(new Font("Arial", Font.BOLD, TANAMNIO_SIMBOLO_AGREGAR));
        btnTerminar.setFont(new Font("Arial", Font.BOLD, TANAMNIO_SIMBOLO_TERMINAR));

        panelCentral.add(crearBotonPanel(btnAgregar, "Agregar otro producto"));
        panelCentral.add(crearBotonPanel(btnTerminar, "Terminar pedido"));

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAgregarSeleccionado();
            }
        });

        btnTerminar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnTerminarSeleccionado();
            }
        });
        return panelPrincipal;
    }

    public void BtnAgregarSeleccionado() {

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea agregar otro producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {

            ControlNavegacion.gestor.calcularCosto();
            ControlNavegacion.agregarProductoPedidoAPedido();

            ControlNavegacion.mostrarPantallaProductos(Modo.CREACION);
            dispose();
        }
    }

    public void BtnTerminarSeleccionado() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea finalizar el pedido?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            
            ControlNavegacion.gestor.calcularCosto();
            
            ControlNavegacion.agregarProductoPedidoAPedido();
            boolean terminado = ControlNavegacion.gestor.terminarPedido();

            if (terminado) {
                ControlNavegacion.mostrarPantallaTotalDesglosado();

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error: No se puede finalizar un pedido vacío", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private JPanel crearBotonPanel(JButton boton, String texto) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(boton, BorderLayout.CENTER);
        JLabel etiqueta = new JLabel(texto, SwingConstants.CENTER);
        panel.add(etiqueta, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
