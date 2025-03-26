/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import DTOs.ToppingsMostrarDTO;
import control.ControlNavegacion;
import control.Modo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author katia
 */
public class PantallaToppings extends javax.swing.JFrame {

    private final int COLUMNAS_TABLA_TOPPINGS = 2;
    private final int PADDING_HORIZONTAL = 20;
    private final int PADDING_VERTICAL = 20;
    private final int ANCHO_PANEL = 250;
    private final int ALTO_PANEL = 300;
    private final int ANCHO_IMAGEN = 250;
    private final int ALTO_IMAGEN = 250;
    private final Color COLOR_HOVER_PANEL = new Color(220, 220, 220);
    private final Border BORDE_PANEL = BorderFactory.createLineBorder(Color.GRAY, 1);
    
    List<ToppingsMostrarDTO> toppings;
    private Modo modo;
    private JPanel panelConTodosLosToppings;

    /**
     * Creates new form Toppings
     */
    public PantallaToppings(List<ToppingsMostrarDTO> toppings, Modo modo) {
        this.toppings = toppings;
        this.modo = modo;
        initComponents2();
        setTitle("Toppings");
        cargarToppings();
    }

    private void cargarToppings() {
        for (ToppingsMostrarDTO topping : toppings) {
            JPanel panelTopping = crearPanelTopping(topping);
            panelTopping.addMouseListener(new EventosPanelTopping(topping, panelTopping));
            panelConTodosLosToppings.add(panelTopping);
        }

            panelConTodosLosToppings.revalidate();
            panelConTodosLosToppings.repaint();
        
    }
    
    private class EventosPanelTopping extends MouseAdapter{
        private final ToppingsMostrarDTO topping;
        private final JPanel panel;
        
        public EventosPanelTopping(ToppingsMostrarDTO topping, JPanel panel){
            this.topping = topping;
            this.panel = panel;
        }
        
        @Override
        public void mousePressed(java.awt.event.MouseEvent evt){
            toppingSeleccionado(topping);
        }
        
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt){
            panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panel.setBackground(COLOR_HOVER_PANEL);
        }
        
        @Override
        public void mouseExited(java.awt.event.MouseEvent evt){
            panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            panel.setBackground(null);
        }
    }

    
    private JPanel crearPanelTopping(ToppingsMostrarDTO topping){
        JPanel panelTopping = new JPanel();
        panelTopping.setLayout(new BorderLayout());
        panelTopping.setBorder(BORDE_PANEL);
        panelTopping.setPreferredSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));

        cargarImagenTopping(topping.getUrlImagen(), panelTopping);
        cargarNombreTopping(topping.getNombre(), panelTopping);

        return panelTopping;
    }
    
    private void cargarImagenTopping(String urlImagen, JPanel panelTopping){
        JLabel lblImagen = new JLabel();
        ImageIcon icono = new ImageIcon(urlImagen);
        Image img = icono.getImage().getScaledInstance(ANCHO_IMAGEN, ALTO_IMAGEN, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(img));
        lblImagen.setHorizontalAlignment(JLabel.CENTER);

        panelTopping.add(lblImagen, BorderLayout.CENTER);
    }

    private void cargarNombreTopping(String nombre, JPanel panelTopping) {
        JLabel lblNombre = new JLabel(nombre, SwingConstants.CENTER);
        panelTopping.add(lblNombre, BorderLayout.SOUTH);
    }
    
    private void toppingSeleccionado(ToppingsMostrarDTO topping) {
        ControlNavegacion.gestor.agregarTopping(topping);

        if (modo == Modo.CREACION) {
            ControlNavegacion.mostrarAgregarTerminarPedido();
        } else if (modo == Modo.EDICION) {
            ControlNavegacion.mostrarPantallaEditarProducto(ControlNavegacion.gestor.getProductoPedidoActual());
        }
        this.dispose();
    }
    
    private void regresar() {
        if (modo == Modo.CREACION){
            ControlNavegacion.volverPantallaAnterior();
        } else if (modo == Modo.EDICION) {
            ControlNavegacion.mostrarPantallaEditarProducto(ControlNavegacion.gestor.getProductoPedido());
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
            .addGap(0, 345, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    private void initComponents2() {
        setTitle("Toppings");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Selecciona tu topping", SwingConstants.LEFT);
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 48));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        panelConTodosLosToppings = new JPanel();
        panelConTodosLosToppings.setLayout(new GridLayout(0, COLUMNAS_TABLA_TOPPINGS, PADDING_HORIZONTAL, PADDING_VERTICAL));
        add(new JScrollPane(panelConTodosLosToppings), BorderLayout.CENTER);

        JPanel panelNavegacion = new JPanel(new BorderLayout());
        JButton btnRegresar = new JButton();
        btnRegresar.setBackground(new java.awt.Color(255, 255, 51));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 48));
        btnRegresar.setText("<---");
        btnRegresar.setPreferredSize(new java.awt.Dimension(180, 70));
        btnRegresar.addActionListener(e -> regresar());

        panelNavegacion.add(btnRegresar, BorderLayout.WEST);
        add(panelNavegacion, BorderLayout.SOUTH);
    }
}
