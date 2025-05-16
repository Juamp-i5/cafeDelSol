package pantallas;

import control.ControlNavegacion;
import control.Modo;
import exception.GestionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Clase que representa la pantalla principal del menú de opciones en la
 * aplicación de barista. Contiene botones para realizar diferentes acciones
 * como realizar un pedido, ver el historial de pedidos y revisar el estado de
 * los pedidos.
 *
 * @author norma
 */
public class PantallaMenuPrincipal extends javax.swing.JFrame {

    /**
     * Constructor de la clase MenuPrincipal. Inicializa los componentes de la interfaz
     * gráfica.
     */
    public PantallaMenuPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sepArriba = new javax.swing.JSeparator();
        sepAbajo = new javax.swing.JSeparator();
        lblTitulo = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        btnHistorialPedidos = new javax.swing.JButton();
        btnRealizarPedido = new javax.swing.JButton();
        btnEstadoPedido = new javax.swing.JButton();
        lblSubtitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu barista");
        setResizable(false);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lblTitulo.setText("Menú de opciones");

        btnLogOut.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnLogOut.setText("Log out");
        btnLogOut.setMaximumSize(new java.awt.Dimension(120, 70));
        btnLogOut.setMinimumSize(new java.awt.Dimension(120, 70));
        btnLogOut.setPreferredSize(new java.awt.Dimension(120, 70));
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        btnHistorialPedidos.setBackground(new java.awt.Color(255, 51, 0));
        btnHistorialPedidos.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnHistorialPedidos.setText("<html><center>Reporte de<br>ventas</center></html>");
        btnHistorialPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialPedidosActionPerformed(evt);
            }
        });

        btnRealizarPedido.setBackground(new java.awt.Color(255, 255, 51));
        btnRealizarPedido.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnRealizarPedido.setText("<html><center>Realizar<br>un pedido</center></html>");
        btnRealizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarPedidoActionPerformed(evt);
            }
        });

        btnEstadoPedido.setBackground(new java.awt.Color(255, 153, 0));
        btnEstadoPedido.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnEstadoPedido.setText("<html><center>Estado de<br>los pedidos</center></html>");
        btnEstadoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoPedidoActionPerformed(evt);
            }
        });

        lblSubtitulo.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblSubtitulo.setText("¿Qué deseas hacer?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sepArriba)
            .addComponent(sepAbajo)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSubtitulo)
                        .addGap(405, 405, 405))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRealizarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(btnEstadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(btnHistorialPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lblSubtitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHistorialPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEstadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRealizarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(94, 94, 94)
                .addComponent(sepAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Acción cuando se hace clic en el botón "Log out". Muestra un mensaje
     * indicando que esta opción está por desarrollarse.
     *
     * @param evt El evento generado por el clic del botón.
     */
    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        JOptionPane.showMessageDialog(null, "Por desarrollar");
    }//GEN-LAST:event_btnLogOutActionPerformed

    /**
     * Acción cuando se hace clic en el botón "Realizar pedido". Va a la
     * pantalla de productos y cierra la ventana actual.
     *
     * @param evt El evento generado por el clic del botón.
     */
    private void btnRealizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarPedidoActionPerformed
        ControlNavegacion.iniciarPedidoNuevo();
        ControlNavegacion.mostrarPantallaProductos(Modo.CREACION);
        this.dispose();
    }//GEN-LAST:event_btnRealizarPedidoActionPerformed

    /**
     * Acción cuando se hace clic en el botón "Estado de pedidos". Muestra un
     * mensaje indicando que esta opción está por desarrollarse.
     *
     * @param evt El evento generado por el clic del botón.
     */
    private void btnEstadoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoPedidoActionPerformed
        JOptionPane.showMessageDialog(null, "Por desarrollar");
    }//GEN-LAST:event_btnEstadoPedidoActionPerformed

    /**
     * Acción cuando se hace clic en el botón "Historial de pedidos". Muestra un
     * mensaje indicando que esta opción está por desarrollarse.
     *
     * @param evt El evento generado por el clic del botón.
     */
    private void btnHistorialPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialPedidosActionPerformed
        JOptionPane.showMessageDialog(null, "Por desarrollar");
    }//GEN-LAST:event_btnHistorialPedidosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEstadoPedido;
    private javax.swing.JButton btnHistorialPedidos;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnRealizarPedido;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JSeparator sepAbajo;
    private javax.swing.JSeparator sepArriba;
    // End of variables declaration//GEN-END:variables
}
