/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pantallas;

import DTOs.salida.ProductoResumenDTO;
import DTOs.salida.DetalleComandaViejaDTO;

/**
 *
 * @author norma
 */
public class PanelProductos extends javax.swing.JPanel {

    public PanelProductos(ProductoResumenDTO producto, DetalleComandaViejaDTO detalleComanda) {
        initComponents();
        
        lblNombreProducto.setText(producto.getNombre());  
        lblPrecioProducto.setText(String.format("$%.2f", producto.getPrecio()));  
        lblDetalle.setText(detalleComanda.getComentario());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDetalle = new javax.swing.JLabel();
        lblNombreProducto = new javax.swing.JLabel();
        lblDetalles = new javax.swing.JLabel();
        btnSignoPrecio = new javax.swing.JLabel();
        lblPrecioProducto = new javax.swing.JLabel();

        lblDetalle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDetalle.setText("Detalle");

        lblNombreProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreProducto.setText("Nombre Producto");

        lblDetalles.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDetalles.setText("Detalles:");

        btnSignoPrecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSignoPrecio.setText("$");

        lblPrecioProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPrecioProducto.setText("100");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(lblDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(268, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSignoPrecio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(69, 69, 69)
                    .addComponent(lblNombreProducto)
                    .addContainerGap(337, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(110, 110, 110)
                    .addComponent(lblDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(333, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSignoPrecio)
                    .addComponent(lblPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblDetalle)
                .addGap(49, 49, 49))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(lblNombreProducto)
                    .addContainerGap(87, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(63, Short.MAX_VALUE)
                    .addComponent(lblDetalles)
                    .addGap(50, 50, 50)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnSignoPrecio;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblDetalles;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblPrecioProducto;
    // End of variables declaration//GEN-END:variables
}
