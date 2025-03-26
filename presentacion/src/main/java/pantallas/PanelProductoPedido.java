/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pantallas;

import DTOs.ProductoPedidoDTO;
import java.awt.event.ActionListener;

/**
 *
 * @author norma
 */
public class PanelProductoPedido extends javax.swing.JPanel {
     private ProductoPedidoDTO productoPedido;
     
    /**
     * Creates new form PanelTotalDesglosado
     * @param productoPedido
     */
    public PanelProductoPedido(ProductoPedidoDTO productoPedido) {
        initComponents();
        this.productoPedido = productoPedido;
        this.lblNombreProducto.setText(productoPedido.getProducto().getNombre());
        this.lblTamaño.setText(productoPedido.getTamanio().getNombre());
        this.lblSabor.setText(productoPedido.getSabor().getNombre());
        this.lblTopping.setText("");
        if (productoPedido.getTopping() != null){
            this.lblTopping.setText(productoPedido.getTopping().getNombre());
        }
        this.lblCantidadCantidad.setText(String.format("%d", productoPedido.getCantidad()));
        double costo = productoPedido.getCosto();  
        this.lblPrecio.setText(String.format("%.2f", costo));
    }
    
     public ProductoPedidoDTO getProductoPedido() {
        return this.productoPedido;
    }

    public void setCancelarActionListener(ActionListener listener) {
        this.btnCancelar.addActionListener(listener);
    }

    public void setEditarActionListener(ActionListener listener) {
        this.btnEditar.addActionListener(listener);
    }
    
    public void setAgregarCantidadActionListener(ActionListener listener) {
        this.btnAgregar.addActionListener(listener);
    }
    
    public void setQuitarCantidadActionListener(ActionListener listener) {
        this.btnQuitar.addActionListener(listener);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombreProducto = new javax.swing.JLabel();
        lblTamaño = new javax.swing.JLabel();
        lblSabor = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblSigno = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        lblTopping = new javax.swing.JLabel();
        lblCantidadCantidad = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        lblCantidad = new javax.swing.JLabel();
        btnQuitar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNombreProducto.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNombreProducto.setText("Producto");

        lblTamaño.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTamaño.setText("Tamaño");

        lblSabor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSabor.setText("Sabor");

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        lblSigno.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblSigno.setText("$");

        lblPrecio.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPrecio.setText("Precio");

        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCancelar.setText("x");

        btnEditar.setBackground(new java.awt.Color(153, 153, 153));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");

        lblTopping.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblCantidadCantidad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCantidadCantidad.setText("1");

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAgregar.setText("+");

        lblCantidad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCantidad.setText("Cantidad");

        btnQuitar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnQuitar.setText("-");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTamaño)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnQuitar)
                                .addGap(29, 29, 29)
                                .addComponent(lblCantidadCantidad)
                                .addGap(32, 32, 32)
                                .addComponent(btnAgregar)
                                .addGap(52, 52, 52))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSabor)
                                    .addComponent(lblTopping)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblNombreProducto)
                                        .addGap(56, 56, 56)
                                        .addComponent(lblCantidad)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(lblSigno)
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnEditar)
                        .addGap(99, 342, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblPrecio)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblNombreProducto)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCantidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTamaño)
                        .addComponent(lblCantidadCantidad)
                        .addComponent(btnQuitar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSabor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTopping)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditar)
                .addGap(14, 14, 14))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecio)
                    .addComponent(lblSigno))
                .addGap(90, 90, 90))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCantidadCantidad;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblSabor;
    private javax.swing.JLabel lblSigno;
    private javax.swing.JLabel lblTamaño;
    private javax.swing.JLabel lblTopping;
    // End of variables declaration//GEN-END:variables
}
