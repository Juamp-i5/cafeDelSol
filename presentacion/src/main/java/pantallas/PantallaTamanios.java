/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import DTOs.TamanioMostrarDTO;
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
 * @author rodri
 */
public class PantallaTamanios extends javax.swing.JFrame {

    private final int COLUMNAS_TABLA_TAMANIOS = 3;
    private final int PADDING_HORIZONTAL = 10;
    private final int PADDING_VERTICAL = 10;
    private final int ANCHO_PANEL = 250;
    private final int ALTO_PANEL = 300;
    private final int ANCHO_IMAGEN = 250;
    private final int ALTO_IMAGEN = 250;
    private final Color COLOR_HOVER_PANEL = new Color(220, 220, 220);
    private final Border BORDE_PANEL = BorderFactory.createLineBorder(Color.GRAY, 1);
    
    List<TamanioMostrarDTO> tamanios;
    private Modo modo;
    private JPanel panelConTodosLosTamanios;
    
    //private PantallaEditarProducto editarProductoFrame;

    /**
     * Creates new form Tamanios
     */

    public PantallaTamanios(List<TamanioMostrarDTO> tamanios, Modo modo) {
        this.tamanios = tamanios;
        this.modo = modo;
        initComponents2();
        setTitle("Tamaños");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        agregarTamanios();
    }

    

    private void agregarTamanios() {
        for (TamanioMostrarDTO tamanio : tamanios) {
            JPanel panelTamanio = crearPanelTamanio(tamanio);
            panelTamanio.addMouseListener(new EventosPanelTamanio(tamanio, panelTamanio));
            panelConTodosLosTamanios.add(panelTamanio);
        }
        panelConTodosLosTamanios.revalidate();
        panelConTodosLosTamanios.repaint();
        
    }

    private void tamanioSeleccionado(TamanioMostrarDTO tamanio) {
        ControlNavegacion.gestor.agregarTamanio(tamanio);
        if (modo == Modo.CREACION){
            ControlNavegacion.mostrarPantallaSabores(Modo.CREACION);
        } else if (modo == Modo.EDICION){
            ControlNavegacion.mostrarPantallaEditarProducto(ControlNavegacion.gestor.getProductoPedidoActual());
        }
        this.dispose();
    }

    private class EventosPanelTamanio extends MouseAdapter {
        private final TamanioMostrarDTO tamanio;
        private final JPanel panel;
        
        public EventosPanelTamanio(TamanioMostrarDTO tamanio, JPanel panel) {
            this.tamanio = tamanio;
            this.panel = panel;
        }
        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
            tamanioSeleccionado(tamanio);
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panel.setBackground(COLOR_HOVER_PANEL);
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            panel.setBackground(null);
        }
    }
    
    private JPanel crearPanelTamanio(TamanioMostrarDTO tamanio) {
        JPanel panelTamanio = new JPanel();
        panelTamanio.setLayout(new BorderLayout());
        panelTamanio.setBorder(BORDE_PANEL);
        panelTamanio.setPreferredSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));

        cargarImagenTamanio(tamanio.getUrlImagen(), panelTamanio);
        cargarNombreTamanio(tamanio.getNombre(), panelTamanio);

        return panelTamanio;
    }
    
    private void cargarImagenTamanio(String urlImagen, JPanel panelTamanio) {
        JLabel lblImagen = new JLabel();
        ImageIcon icono = new ImageIcon(urlImagen);
        Image img = icono.getImage().getScaledInstance(ANCHO_IMAGEN, ALTO_IMAGEN, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(img));
        lblImagen.setHorizontalAlignment(JLabel.CENTER);

        panelTamanio.add(lblImagen, BorderLayout.CENTER);
    }
    
    private void cargarNombreTamanio(String nombre, JPanel panelTamanio) {
        JLabel lblNombre = new JLabel(nombre, SwingConstants.CENTER);
        panelTamanio.add(lblNombre, BorderLayout.SOUTH);
    }
    
    private void regresar() {
        if (modo == Modo.CREACION) {
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
    
    private void initComponents2() {
        setTitle("Tamaños");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Selecciona un tamaño", SwingConstants.LEFT);
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 48));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        panelConTodosLosTamanios = new JPanel();
        panelConTodosLosTamanios.setLayout(new GridLayout(0, COLUMNAS_TABLA_TAMANIOS, PADDING_HORIZONTAL, PADDING_VERTICAL));
        add(new JScrollPane(panelConTodosLosTamanios), BorderLayout.CENTER);

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
