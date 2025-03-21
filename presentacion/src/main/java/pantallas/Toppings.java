/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import DTOs.ToppingsMostrarDTO;
import control.ControlNavegacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author katia
 */
public class Toppings extends javax.swing.JFrame {

    List<ToppingsMostrarDTO> toppings;
    private EditarProducto editarProductoFrame;

    
    /**
     * Creates new form Toppings
     */
    public Toppings(List<ToppingsMostrarDTO> toppings) {
        this.toppings = toppings;
        initComponents();
        setTitle("Toppings");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        agregarToppings();
    }
    
    public Toppings(List<ToppingsMostrarDTO> toppings, EditarProducto editarProductoFrame) {
        this.toppings = toppings;
        this.editarProductoFrame = editarProductoFrame;
        initComponents();
        setTitle("Toppings");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        agregarToppings();
    }
    
    
    private void agregarToppings() {
        int columnas = 2; // Número de productos por fila
        int filas = (int) Math.ceil((double) toppings.size() / columnas); // Calcula filas necesarias

        jPanel1.setLayout(new GridLayout(filas, columnas, 10, 10)); // Espaciado entre productos

        for (ToppingsMostrarDTO topping : toppings) {
            JPanel panelToppings = new JPanel();
            panelToppings.setLayout(new BorderLayout());
            panelToppings.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            // Cargar imagen desde la URL o ruta del sistema
            JLabel lblImagen = new JLabel();
            ImageIcon icono = new ImageIcon(topping.getUrlImagen());
            Image img = icono.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
            lblImagen.setHorizontalAlignment(JLabel.CENTER);

            JLabel lblNombre = new JLabel(topping.getNombre(), SwingConstants.CENTER);

            panelToppings.add(lblImagen, BorderLayout.CENTER);
            panelToppings.add(lblNombre, BorderLayout.SOUTH);

            panelToppings.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    toppingSeleccionado(topping); // Método que recibe el ProductoMostrarDTO
                }
            });

            jPanel1.add(panelToppings);
        }

        jPanel1.revalidate();
        jPanel1.repaint();
    }
    
    private void toppingSeleccionado(ToppingsMostrarDTO topping){
        if (editarProductoFrame != null){
            editarProductoFrame.actualizarTopping(topping);
            editarProductoFrame.setVisible(true);
        } else{
            ControlNavegacion.mostrarAgregarTerminarPedido();
        }
        ControlNavegacion.gestor.agregarTopping(topping);
        this.dispose();
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 286, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 177, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
