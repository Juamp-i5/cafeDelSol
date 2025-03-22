/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import DTOs.ProductoPedidoDTO;
import control.ControlNavegacion;
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

    public static IGestionPedidos gestion = new ManejadorPedidos();
    private ProductoPedidoDTO ProductoPedido;

    /**
     * Creates new form AgregarOTerminarPedido
     */
    public AgregarOTerminarPedido() {
        setTitle("");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(crearPanelPedido(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel crearPanelPedido() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel superior con título en gris
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(Color.WHITE);

        // Panel central con botones
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));

        JButton btnAgregar = new JButton("+");
        JButton btnTerminar = new JButton("->");

        btnAgregar.setFont(new Font("Arial", Font.BOLD, 30));
        btnTerminar.setFont(new Font("Arial", Font.BOLD, 30));

        panelCentral.add(crearBotonPanel(btnAgregar, "Agregar otro producto"));
        panelCentral.add(crearBotonPanel(btnTerminar, "Terminar pedido"));

        // Panel inferior con botón de retroceso
        JPanel panelInferior = new JPanel(new BorderLayout());
        JButton btnAtras = new JButton("<-");
        btnAtras.setFont(new Font("Arial", Font.BOLD, 20));
        btnAtras.setBackground(Color.GRAY);

        panelInferior.add(btnAtras, BorderLayout.WEST);

        // Agregar los subpaneles al panel principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        // Agregar eventos a los botones
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAtrasSeleccionado();
            }
        });

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

    public void BtnAtrasSeleccionado() {
        ControlNavegacion.volverPantallaAnterior();
        dispose();
    }

    public void BtnAgregarSeleccionado() {
        if (ProductoPedido == null) {
            ProductoPedido = new ProductoPedidoDTO(); 
        }
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea agregar otro producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean agregado = gestion.agregarProductoPedidoAPedido(ProductoPedido);
            if (agregado) {
                ControlNavegacion.mostrarPantallaProductos();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error: No se pudo agregar el producto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void BtnTerminarSeleccionado() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea finalizar el pedido?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean terminado = gestion.terminarPedido();
            if (terminado) {
                JOptionPane.showMessageDialog(this, "Pedido finalizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                //muestra siguiente pantalla
                
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error: No se puede finalizar un pedido vacío", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método auxiliar para organizar botones con etiquetas
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
