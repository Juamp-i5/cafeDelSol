/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import DTOs.PedidoDTO;
import control.ControlNavegacion;
import DTOs.ProductoPedidoDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author norma
 */
public class TotalDesglosado extends javax.swing.JFrame {

    /**
     * Creates new form TotalDesglosado
     */
    public TotalDesglosado() {
        initComponents();
        setSize(1000, 800);
        cargarPanelesProductosPedidos();
    }

    public void cargarPanelesProductosPedidos() {
        JPanel contenedorPanelesProductosPedidos = obtenerPanelesProductosPedidos();
        this.pnlProductosPedidos.setViewportView(contenedorPanelesProductosPedidos);
        this.lblTotalTotal.setText(String.format("%.2f", ControlNavegacion.gestor.calcularTotal()));
    }

    private JPanel obtenerPanelesProductosPedidos() {
        PedidoDTO pedido = ControlNavegacion.gestor.getPedido();
        List<ProductoPedidoDTO> listaProductosPedidos = pedido.getPedido();

        JPanel contenedorPanelesProductosPedidos = new JPanel();
        contenedorPanelesProductosPedidos.setLayout(new BoxLayout(contenedorPanelesProductosPedidos, BoxLayout.Y_AXIS));

        for (ProductoPedidoDTO productoPedido : listaProductosPedidos) {
            PanelProductoPedido panelProductoPedido = new PanelProductoPedido(productoPedido);
            configurarPanelProducto(panelProductoPedido);
            contenedorPanelesProductosPedidos.add(panelProductoPedido);
        }

        return contenedorPanelesProductosPedidos;
    }

    private void configurarPanelProducto(PanelProductoPedido panelProductoPedido) {
        panelProductoPedido.setCancelarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarProductoPedido(panelProductoPedido.getProductoPedido());
            }
        });

        panelProductoPedido.setEditarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProductoPedido(panelProductoPedido.getProductoPedido());
            }
        });

        panelProductoPedido.setAgregarCantidadActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCantidad(panelProductoPedido.getProductoPedido());
            }
        });

        panelProductoPedido.setQuitarCantidadActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitarCantidad(panelProductoPedido.getProductoPedido());
            }
        });

    }

    private void cancelarProductoPedido(ProductoPedidoDTO productoPedido) {
        int opc = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas cancelar el producto pedido?",
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opc == JOptionPane.YES_OPTION) {
            ControlNavegacion.gestor.cancelarProductoPedido(productoPedido);

            ControlNavegacion.mostrarPantallaProductoPedidoCancelado(this);

            cargarPanelesProductosPedidos();
        }
    }

    private void cancelarPedido() {
        int opc = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas cancelar el pedido?",
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opc == JOptionPane.YES_OPTION) {
            ControlNavegacion.gestor.cancelarPedido(ControlNavegacion.gestor.getPedido());

            ControlNavegacion.mostrarPantallaPedidoCancelado(this);
            this.dispose();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ControlNavegacion.mostrarPantallaMenuPrincipal();
                }
            }, 1000);
        }
    }

    private void editarProductoPedido(ProductoPedidoDTO productoPedido) {
        this.dispose();
        ControlNavegacion.mostrarPantallaEditarProducto(productoPedido);
    }

    private void agregarCantidad(ProductoPedidoDTO productoPedido) {
        if (productoPedido.getCantidad() < 99) {
            productoPedido.setCantidad(productoPedido.getCantidad() + 1);
            ControlNavegacion.gestor.actualizarTotal();
            cargarPanelesProductosPedidos();
        } else {
            JOptionPane.showMessageDialog(this, "La cantidad máxima permitida es 99.", "Límite alcanzado", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void quitarCantidad(ProductoPedidoDTO productoPedido) {
        if (productoPedido.getCantidad() != 1) {
            productoPedido.setCantidad(productoPedido.getCantidad() - 1);
            ControlNavegacion.gestor.actualizarTotal();
            cargarPanelesProductosPedidos();
        } else {
            JOptionPane.showMessageDialog(this, "La cantidad mínima permitida es 1.", "Mínimo alcanzado", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Thte javax.swing.JButton btnTarjeta; private javax.swing.JLabel lblSigno;
     * private javax.swing.JLabel lblTotal; private javax.swing.JLabel
     * lblTotalTotal; private javax.swing.JScrollPane pnlProductosPedidos; //
     * End of variables declaration } is method is called from within the
     * constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlProductosPedidos = new javax.swing.JScrollPane();
        btnRegresar = new javax.swing.JButton();
        btnTarjeta = new javax.swing.JButton();
        btnEfectivo = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        lblTotalTotal = new javax.swing.JLabel();
        lblSigno = new javax.swing.JLabel();
        btnCancelarPedido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnRegresar.setBackground(new java.awt.Color(255, 255, 51));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnRegresar.setText("< ---");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnTarjeta.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnTarjeta.setText("Tarjeta");
        btnTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarjetaActionPerformed(evt);
            }
        });

        btnEfectivo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnEfectivo.setText("Efectivo");
        btnEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEfectivoActionPerformed(evt);
            }
        });

        lblTotal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTotal.setText("Total");

        lblTotalTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTotalTotal.setText("......");

        lblSigno.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSigno.setText("$");

        btnCancelarPedido.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnCancelarPedido.setText("Cancelar Pedido");
        btnCancelarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pnlProductosPedidos)
                        .addGap(18, 18, 18)
                        .addComponent(lblSigno, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotal)
                            .addComponent(lblTotalTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 356, Short.MAX_VALUE)
                        .addComponent(btnTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addGap(6, 6, 6))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCancelarPedido)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnCancelarPedido)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSigno)
                            .addComponent(lblTotalTotal))
                        .addGap(52, 52, 52))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(pnlProductosPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegresar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        ControlNavegacion.mostrarAgregarTerminarPedido();
        dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnCancelarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPedidoActionPerformed
        cancelarPedido();
    }//GEN-LAST:event_btnCancelarPedidoActionPerformed

    private void btnTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarjetaActionPerformed
        ControlNavegacion.mostrarPagoTarjeta();
        this.dispose();
    }//GEN-LAST:event_btnTarjetaActionPerformed

    private void btnEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEfectivoActionPerformed
        ControlNavegacion.mostrarPantallaPagoEfectivo();
        this.dispose();
    }//GEN-LAST:event_btnEfectivoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarPedido;
    private javax.swing.JButton btnEfectivo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnTarjeta;
    private javax.swing.JLabel lblSigno;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalTotal;
    private javax.swing.JScrollPane pnlProductosPedidos;
    // End of variables declaration//GEN-END:variables
}
