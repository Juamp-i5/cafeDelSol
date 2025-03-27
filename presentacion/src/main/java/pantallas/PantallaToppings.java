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
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 * Clase que representa la pantalla de selección de toppings. Permite a los
 * usuarios seleccionar toppings para un producto y realizar acciones como
 * regresar a la pantalla anterior o continuar sin un topping.
 *
 * @author katia
 */
public class PantallaToppings extends javax.swing.JFrame {

    private final int COLUMNAS_TABLA_TOPPINGS = 2;
    private final int PADDING_HORIZONTAL = 20;
    private final int PADDING_VERTICAL = 20;
    private final Color COLOR_HOVER_PANEL = new Color(220, 220, 220);

    List<ToppingsMostrarDTO> toppings;
    private Modo modo;
    private JPanel panelConTodosLosToppings;

    /**
     * Constructor de la clase PantallaToppings. Inicializa los componentes de
     * la interfaz y carga los toppings en la pantalla.
     *
     * @param toppings Lista de toppings a mostrar en la pantalla.
     * @param modo Modo en el que se encuentra la pantalla (CREACION o EDICION).
     */
    public PantallaToppings(List<ToppingsMostrarDTO> toppings, Modo modo) {
        this.toppings = toppings;
        this.modo = modo;
        initComponents2();
        setTitle("Toppings");
        cargarToppings();
    }

    /**
     * Carga y muestra todos los toppings en el panel correspondiente. Se
     * agregan paneles individuales para cada topping y se les asigna un
     * manejador de eventos.
     */
    private void cargarToppings() {
        for (ToppingsMostrarDTO topping : toppings) {
            JPanel panelTopping = new PanelTopping(topping);
            panelTopping.addMouseListener(new EventosPanelTopping(topping, panelTopping));
            panelConTodosLosToppings.add(panelTopping);
        }
        panelConTodosLosToppings.revalidate();
        panelConTodosLosToppings.repaint();

    }

    /**
     * Clase interna que maneja los eventos de los paneles de toppings.
     */
    private class EventosPanelTopping extends MouseAdapter {

        private final ToppingsMostrarDTO topping;
        private final JPanel panel;

        /**
         * Constructor de la clase de eventos del panel de toppings.
         *
         * @param topping Topping asociado al panel.
         * @param panel Panel que contiene el topping.
         */
        public EventosPanelTopping(ToppingsMostrarDTO topping, JPanel panel) {
            this.topping = topping;
            this.panel = panel;
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
            toppingSeleccionado(topping);
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

    /**
     * Método que maneja la selección de un topping. Agrega el topping
     * seleccionado a la lista de toppings del producto actual y navega según el
     * modo.
     *
     * @param topping El topping seleccionado.
     */
    private void toppingSeleccionado(ToppingsMostrarDTO topping) {
        ControlNavegacion.agregarTopping(topping);

        if (modo == Modo.CREACION) {
            ControlNavegacion.mostrarAgregarTerminarPedido();
        } else if (modo == Modo.EDICION) {
            ControlNavegacion.mostrarPantallaEditarProducto(ControlNavegacion.getProductoPedidoActual());
        }
        this.dispose();
    }

    /**
     * Método que maneja la acción de regresar a la pantalla anterior.
     * Dependiendo del modo, navega hacia la pantalla de creación o edición.
     */
    private void regresar() {
        if (modo == Modo.CREACION) {
            ControlNavegacion.volverPantallaAnterior();
        } else if (modo == Modo.EDICION) {
            ControlNavegacion.mostrarPantallaEditarProducto(ControlNavegacion.getProductoPedidoActual());
            this.dispose();
        }
    }

    /**
     * Método que maneja la acción de continuar sin seleccionar un topping.
     * Continúa con la acción correspondiente según el modo de la pantalla.
     */
    private void continuar() {
        ControlNavegacion.agregarTopping(null);
        if (modo == Modo.CREACION) {
            ControlNavegacion.mostrarAgregarTerminarPedido();
        } else if (modo == Modo.EDICION) {
            ControlNavegacion.mostrarPantallaEditarProducto(ControlNavegacion.getProductoPedidoActual());
        }
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
    /**
     * Método para inicializar componentes personalizados. Configura la
     * estructura y apariencia de la pantalla.
     */
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

        JButton btnContinuar = new JButton();
        btnContinuar.setBackground(new java.awt.Color(255, 255, 51));
        btnContinuar.setFont(new java.awt.Font("Segoe UI", 0, 24));
        btnContinuar.setText("Continuar sin topping");
        btnContinuar.setPreferredSize(new java.awt.Dimension(300, 70));
        btnContinuar.addActionListener(e -> continuar());

        panelNavegacion.add(btnContinuar, BorderLayout.EAST);
        add(panelNavegacion, BorderLayout.SOUTH);
    }
}
