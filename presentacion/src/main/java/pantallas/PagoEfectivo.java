/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import DTOs.EfectivoDTO;
import java.awt.*;
import javax.swing.*;
import control.ControlNavegacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author norma
 */
public class PagoEfectivo extends javax.swing.JFrame {

    private JLabel lblPrecio;
    private JLabel lblPrecioValor;
    private JLabel lblCantidadRecibida;
    private JTextField txtCantidadRecibida;
    private JLabel lblCambio;
    private JLabel lblCambioValor;
    private JButton btnRegresar;
    private JButton btnAceptar;

    /**
     * Creates new form PagoEfectivo
     */
    public PagoEfectivo() {

        pagoEnEfectivoInformacion();
    }

    private void pagoEnEfectivoInformacion() {
        setSize(800, 600);

        lblPrecio = new JLabel("Precio:");
        lblPrecioValor = new JLabel(String.valueOf(ControlNavegacion.gestor.getPedido().getCostoTotal()));
        lblCantidadRecibida = new JLabel("Cantidad Recibida:");
        txtCantidadRecibida = new JTextField(0);
        lblCambio = new JLabel("Cambio:");
        lblCambioValor = new JLabel("0.00");

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(lblPrecio);
        panel.add(lblPrecioValor);

        panel.add(lblCantidadRecibida);
        panel.add(txtCantidadRecibida);

        panel.add(lblCambio);
        panel.add(lblCambioValor);

        panelPrincipal.add(panel, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BorderLayout());

        btnRegresar = new JButton("Regresar");
        btnAceptar = new JButton("Terminar pedido");

        JPanel panelVacio = new JPanel();

        panelBotones.add(btnRegresar, BorderLayout.WEST);
        panelBotones.add(panelVacio, BorderLayout.CENTER);
        panelBotones.add(btnAceptar, BorderLayout.EAST);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);

        txtCantidadRecibida.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarCambio();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarCambio();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarCambio();
            }

            private void actualizarCambio() {
                try {
                    double cantidadRecibida = Double.parseDouble(txtCantidadRecibida.getText());
                    EfectivoDTO efectivo = new EfectivoDTO(cantidadRecibida);

                    double cambio = ControlNavegacion.gestor.calcularCambio(efectivo);

                    if (cambio < 0) {
                        lblCambioValor.setText("Cantidad insuficiente");
                    } else {
                        lblCambioValor.setText(String.valueOf(cambio));
                    }
                } catch (NumberFormatException ex) {
                    lblCambioValor.setText("-");
                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlNavegacion.volverPantallaAnterior(); 
                dispose(); 
            }
        });

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double cantidadRecibida = Double.parseDouble(txtCantidadRecibida.getText());
                    EfectivoDTO efectivo = new EfectivoDTO(cantidadRecibida);

                    double cambio = ControlNavegacion.gestor.calcularCambio(efectivo);

                    if (cambio >= 0) {
                        ControlNavegacion.mostrarPantallaPedidoRealizado();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cantidad insuficiente para completar el pago", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una cantidad válida", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();

        jSeparator2.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
