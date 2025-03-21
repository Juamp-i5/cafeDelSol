/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import DTOs.TamanioMostrarDTO;
import control.ControlNavegacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
 * @author rodri
 */
public class Tamanios extends javax.swing.JFrame {
    
    List<TamanioMostrarDTO> tamanios;
    /**
     * Creates new form Tamanios
     */
    public Tamanios(List<TamanioMostrarDTO> tamanios) {
        this.tamanios = tamanios;
        initComponents();
        setTitle("Tamaños");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        agregarTamanios();
        
    }
    
    private void agregarTamanios() {
        int columnas = 3; // Número de productos por fila
        int filas = (int) Math.ceil((double) tamanios.size() / columnas); // Calcula filas necesarias

        jPanel1.setLayout(new GridLayout(filas, columnas, 10, 10)); // Espaciado entre productos

        for (TamanioMostrarDTO tamanio : tamanios) {
            JPanel panelTamanio = new JPanel();
            panelTamanio.setLayout(new BorderLayout());
            panelTamanio.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            // Cargar imagen desde la URL o ruta del sistema
            JLabel lblImagen = new JLabel();
            ImageIcon icono = new ImageIcon(tamanio.getUrlImagen());
            Image img = icono.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
            lblImagen.setHorizontalAlignment(JLabel.CENTER);

            JLabel lblNombre = new JLabel(tamanio.getNombre(), SwingConstants.CENTER);

            panelTamanio.add(lblImagen, BorderLayout.CENTER);
            panelTamanio.add(lblNombre, BorderLayout.SOUTH);

            panelTamanio.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tamanioSeleccionado(tamanio); // Método que recibe el ProductoMostrarDTO
                }
            });

            jPanel1.add(panelTamanio);


        }

        jPanel1.revalidate();
        jPanel1.repaint();
    }
    
    private void tamanioSeleccionado(TamanioMostrarDTO tamanio) {
        this.dispose();
        ControlNavegacion.mostrarPantallaSabores();
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
            .addGap(0, 870, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 493, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
