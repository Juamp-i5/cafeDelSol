package pantallas;

import DTOs.ProductoMostrarDTO;
import control.ControlNavegacion;
import control.Modo;
import exception.GestionException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * Clase que representa la pantalla para seleccionar un producto.
 * 
 * @author Jp
 */
public class PantallaSeleccionarProducto extends javax.swing.JFrame {

    private final int COLUMNAS_TABLA_PRODUCTOS_CARGADOS = 3;
    private final int PADDING_HORIZONTAL_PANEL_PRODUCTO = 10;
    private final int PADDING_VERTICAL_PANEL_PRODUCTO = 10;
    private final int MOVIMIENTO_SCROLL_MOUSE = 15;
    private final Color COLOR_HOVER_PANEL_PRODUCTO = new Color(220, 220, 220);

    private List<ProductoMostrarDTO> productos;
    private Modo modo;

    /**
     * Constructor de la clase PantallaSeleccionarProducto.
     * 
     * @param productos Lista de productos a mostrar.
     * @param modo Modo de operación (CREACION o EDICION).
     */
    public PantallaSeleccionarProducto(List<ProductoMostrarDTO> productos, Modo modo) {
        this.productos = productos;
        this.modo = modo;

        initComponents();
        cargarProductos();
        ajustarScroll();
    }

    /**
     * Ajusta el incremento del scroll para mejorar la navegación.
     */
    private void ajustarScroll() {
        scrProductos.getVerticalScrollBar().setUnitIncrement(MOVIMIENTO_SCROLL_MOUSE);
    }

    /**
     * Carga los productos en el panel de selección.
     */
    private void cargarProductos() {
        int filas = (int) Math.ceil((double) productos.size() / COLUMNAS_TABLA_PRODUCTOS_CARGADOS);

        pnlProductos.setLayout(new GridLayout(
                filas,
                COLUMNAS_TABLA_PRODUCTOS_CARGADOS,
                PADDING_HORIZONTAL_PANEL_PRODUCTO,
                PADDING_VERTICAL_PANEL_PRODUCTO
        ));

        for (ProductoMostrarDTO producto : productos) {
            JPanel panelProducto = new PanelProducto(producto);
            panelProducto.addMouseListener(new EventosPanelProducto(producto, panelProducto));
            pnlProductos.add(panelProducto);
        }
        pnlProductos.revalidate();
        pnlProductos.repaint();
    }

    /**
     * Clase interna para manejar los eventos de los paneles de producto.
     */
    private class EventosPanelProducto extends MouseAdapter {

        private final ProductoMostrarDTO producto;
        private final JPanel panel;

         /**
         * Constructor de la clase de eventos.
         * 
         * @param producto Producto asociado al panel.
         * @param panel Panel del producto.
         */
        public EventosPanelProducto(ProductoMostrarDTO producto, JPanel panel) {
            this.producto = producto;
            this.panel = panel;
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
            productoSeleccionado(producto);
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panel.setBackground(COLOR_HOVER_PANEL_PRODUCTO);
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            panel.setBackground(null);
        }
    }

     /**
     * Maneja la selección de un producto.
     * 
     * @param producto Producto seleccionado.
     */
    private void productoSeleccionado(ProductoMostrarDTO producto) {
        ControlNavegacion.agregarProducto(producto);

        if (modo == Modo.CREACION) {
            try {
                ControlNavegacion.mostrarPantallaTamanios(Modo.CREACION);
            } catch (GestionException ex) {
                Logger.getLogger(PantallaSeleccionarProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (modo == Modo.EDICION) {
            ControlNavegacion.mostrarPantallaEditarProducto(ControlNavegacion.getProductoPedidoActual());
        }

        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sepArriba = new javax.swing.JSeparator();
        sepAbajo = new javax.swing.JSeparator();
        scrProductos = new javax.swing.JScrollPane();
        pnlProductos = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Productos");
        setResizable(false);

        javax.swing.GroupLayout pnlProductosLayout = new javax.swing.GroupLayout(pnlProductos);
        pnlProductos.setLayout(pnlProductosLayout);
        pnlProductosLayout.setHorizontalGroup(
            pnlProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1010, Short.MAX_VALUE)
        );
        pnlProductosLayout.setVerticalGroup(
            pnlProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 522, Short.MAX_VALUE)
        );

        scrProductos.setViewportView(pnlProductos);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lblTitulo.setText("Selecciona tu producto");

        btnRegresar.setBackground(new java.awt.Color(255, 255, 51));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnRegresar.setText("<---");
        btnRegresar.setMaximumSize(new java.awt.Dimension(120, 70));
        btnRegresar.setMinimumSize(new java.awt.Dimension(120, 70));
        btnRegresar.setPreferredSize(new java.awt.Dimension(120, 70));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sepArriba)
            .addComponent(sepAbajo)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrProductos))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblTitulo)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrProductos)
                .addGap(18, 18, 18)
                .addComponent(sepAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     /**
     * Acción cuando se hace clic en el botón "Regresar".
     * Dependiendo del modo actual, regresa a la pantalla anterior o muestra la pantalla de edición del producto.
     *
     * @param evt Evento de acción generado al hacer clic en el botón.
     */
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        if (modo == Modo.CREACION) {
            ControlNavegacion.volverPantallaAnterior();
        } else if (modo == Modo.EDICION) {
            ControlNavegacion.mostrarPantallaEditarProducto(ControlNavegacion.getProductoPedidoActual());
            this.dispose();
        }
    }//GEN-LAST:event_btnRegresarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlProductos;
    private javax.swing.JScrollPane scrProductos;
    private javax.swing.JSeparator sepAbajo;
    private javax.swing.JSeparator sepArriba;
    // End of variables declaration//GEN-END:variables
}
