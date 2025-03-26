/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import DTOs.ProductoMostrarDTO;
import control.ControlNavegacion;
import DTOs.TarjetaDTO;
import control.ControlNavegacion;
import gestion.IGestionPedidos;
import gestion.ManejadorPedidos;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author pablo
 */
public class PagoTarjeta extends javax.swing.JFrame {

    /**
     * Creates new form PagoTarjeta
     */
    final int CANTIDAD_FILAS = 4;
    final int CANTIDAD_COLUMNAS = 2;
    final int ESPACIO_ENTRE_BOTONES_HORIZONTAL = 10;
    final int ESPACIO_ENTRE_BOTONES_VERTICAL = 10;
    final int MARGEN_PANEL_ARRIBA = 20;
    final int MARGEN_PANEL_IZQUIERDA = 40;
    final int MARGEN_PANEL_ABAJO = 20;
    final int MARGEN_PANEL_DERECHA = 20;
    final int TAMANIO_TITULO = 14;
    final int DIMENSION_BOTON_X = 200;
    final int DIMENSION_BOTON_Y = 80;
    final int TAMANIO_TEXTO = 24;
    final int TAMANIO_BTN_ATRAS = 48;
    
    

    private TarjetaDTO tarjeta;

    public PagoTarjeta() {
        setTitle("Pago con tarjeta");
        setSize(1000, 800);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));

//        JLabel lblTitulo = new JLabel("Pago con tarjeta", SwingConstants.LEFT);
//        lblTitulo.setForeground(Color.GRAY);
//        lblTitulo.setFont(new Font("Arial", Font.BOLD, TAMANIO_TITULO));
//        JPanel panelTitulo = new JPanel(new BorderLayout());
//        panelTitulo.add(lblTitulo, BorderLayout.WEST);
//        panelTitulo.setBackground(Color.WHITE);

        JPanel panelFormulario = new JPanel(new GridLayout(CANTIDAD_FILAS, CANTIDAD_COLUMNAS, ESPACIO_ENTRE_BOTONES_HORIZONTAL, ESPACIO_ENTRE_BOTONES_VERTICAL));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(MARGEN_PANEL_ARRIBA, MARGEN_PANEL_IZQUIERDA, MARGEN_PANEL_ABAJO, MARGEN_PANEL_DERECHA));

        panelFormulario.add(new JLabel("16 dígitos de la tarjeta")).setFont(new Font("Segoe UI", Font.PLAIN, TAMANIO_TEXTO));
        JTextField txtNumero = new JTextField();
        panelFormulario.add(txtNumero);

        panelFormulario.add(new JLabel("Nombre de banco")).setFont(new Font("Segoe UI", Font.PLAIN, TAMANIO_TEXTO));
        JTextField txtBanco = new JTextField();
        panelFormulario.add(txtBanco);

        panelFormulario.add(new JLabel("Fecha exp")).setFont(new Font("Segoe UI", Font.PLAIN, TAMANIO_TEXTO));
        JTextField txtFecha = new JTextField();
        panelFormulario.add(txtFecha);

        panelFormulario.add(new JLabel("CVV")).setFont(new Font("Segoe UI", Font.PLAIN, TAMANIO_TEXTO));
        JPasswordField txtCVV = new JPasswordField();
        panelFormulario.add(txtCVV);
        
        JPanel panelInferior = new JPanel(new BorderLayout());

        JButton btnAtras = new JButton("<--");
        btnAtras.setBackground(Color.YELLOW);
        btnAtras.setPreferredSize(new Dimension(DIMENSION_BOTON_X, DIMENSION_BOTON_Y));
        btnAtras.setFont(new Font("Segoe UI", Font.PLAIN, TAMANIO_BTN_ATRAS));

        JButton btnConfirmar = new JButton("Confirmar pago");
        btnConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, TAMANIO_TEXTO));

        panelInferior.add(btnAtras, BorderLayout.WEST);
        panelInferior.add(btnConfirmar, BorderLayout.EAST);

        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAtrasSeleccionado();
            }
        });
        btnConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnConfirmarSeleccionado(txtNumero, txtBanco, txtFecha, txtCVV);
            }
        });
//        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    public void BtnAtrasSeleccionado() {
        ControlNavegacion.volverPantallaAnterior();
        dispose();
    }

    public void BtnConfirmarSeleccionado(JTextField txtNumero, JTextField txtBanco, JTextField txtFecha, JPasswordField txtCVV) {
        System.out.println("Confirmar tarjeta");
        TarjetaDTO tarjetaIngresada = new TarjetaDTO(
                txtNumero.getText(),
                txtBanco.getText(),
                txtFecha.getText(),
                new String(txtCVV.getPassword())
        );
        if (ControlNavegacion.gestor.validarTarjetaPresentacion(tarjetaIngresada)) {
            
            ControlNavegacion.mostrarPantallaPedidoRealizado();
            
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Datos de la tarjeta inválidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
