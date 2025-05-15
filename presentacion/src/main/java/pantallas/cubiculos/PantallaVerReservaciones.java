/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas.cubiculos;

import pantallas.*;
import DTOs.PedidoDTO;
import control.ControlNavegacion;
import DTOs.ProductoPedidoDTO;
import control.Modo;
import exception.GestionException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Clase que representa la pantalla de total desglosado de un pedido.
 *
 * @author norma
 */
public class PantallaVerReservaciones extends javax.swing.JFrame {

    private final int MOVIMIENTO_SCROLL_MOUSE = 15;

    /**
     * Constructor de la clase TotalDesglosado. Inicializa los componentes de la
     * interfaz y ajusta el tamaño de la ventana.
     *
     * Llama a los métodos para cargar los productos del pedido y ajustar el
     * scroll.
     */
    public PantallaVerReservaciones() {
        initComponents();
        setSize(1000, 800);
        cargarPanelesProductosPedidos();
        ajustarScroll();
    }

    /**
     * Ajusta el comportamiento del scroll vertical de los productos en el
     * panel.
     */
    private void ajustarScroll() {
        pnlProductosPedidos.getVerticalScrollBar().setUnitIncrement(MOVIMIENTO_SCROLL_MOUSE);
    }

    /**
     * Carga los paneles de los productos pedidos y actualiza la información del
     * total.
     */
    public void cargarPanelesProductosPedidos() {
        int posicionScroll = pnlProductosPedidos.getVerticalScrollBar().getValue();
        JPanel contenedorPanelesProductosPedidos = obtenerPanelesProductosPedidos();
        this.pnlProductosPedidos.setViewportView(contenedorPanelesProductosPedidos);
        pnlProductosPedidos.getVerticalScrollBar().setValue(posicionScroll);
    }

    /**
     * Obtiene un panel que contiene todos los productos en el pedido.
     *
     * @return JPanel que contiene los productos pedidos.
     */
    private JPanel obtenerPanelesProductosPedidos() {
        PedidoDTO pedido = ControlNavegacion.getPedido();
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

    /**
     * Configura los listeners para cada panel de producto (editar, cancelar,
     * agregar cantidad, quitar cantidad).
     *
     * @param panelProductoPedido El panel del producto para configurar.
     */
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

    /**
     * Cancela un producto específico del pedido y actualiza la interfaz.
     *
     * @param productoPedido El producto a cancelar.
     */
    private void cancelarProductoPedido(ProductoPedidoDTO productoPedido) {
        int opc = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas cancelar el producto pedido?",
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opc == JOptionPane.YES_OPTION) {
            ControlNavegacion.cancelarProductoPedido(productoPedido);

            ControlNavegacion.mostrarPantallaProductoPedidoCancelado(this);

            cargarPanelesProductosPedidos();
        }
    }

    /**
     * Cancela todo el pedido y navega a la pantalla principal.
     */
    private void cancelarPedido() {
        int opc = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas cancelar el pedido?",
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opc == JOptionPane.YES_OPTION) {
            ControlNavegacion.cancelarPedido();

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

    /**
     * Abre la pantalla para editar un producto del pedido.
     *
     * @param productoPedido El producto a editar.
     */
    private void editarProductoPedido(ProductoPedidoDTO productoPedido) {
        this.dispose();
        ControlNavegacion.mostrarPantallaEditarProducto(productoPedido);
    }

    /**
     * Incrementa la cantidad de un producto en el pedido. Si la cantidad es
     * menor a 99, la aumenta en 1.
     *
     * @param productoPedido El producto a modificar.
     */
    private void agregarCantidad(ProductoPedidoDTO productoPedido) {
        if (productoPedido.getCantidad() < 99) {
            productoPedido.setCantidad(productoPedido.getCantidad() + 1);
            ControlNavegacion.actualizarTotal();
            cargarPanelesProductosPedidos();
        } else {
            JOptionPane.showMessageDialog(this, "La cantidad máxima permitida es 99.", "Límite alcanzado", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Disminuye la cantidad de un producto en el pedido. Si la cantidad es
     * mayor a 1, la disminuye en 1.
     *
     * @param productoPedido El producto a modificar.
     */
    private void quitarCantidad(ProductoPedidoDTO productoPedido) {
        if (productoPedido.getCantidad() != 1) {
            productoPedido.setCantidad(productoPedido.getCantidad() - 1);
            ControlNavegacion.actualizarTotal();
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
        btnVolver = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnVolver.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnVolver.setText("<--");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabelTitulo.setText("Ver Reservaciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(104, Short.MAX_VALUE)
                .addComponent(pnlProductosPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabelTitulo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabelTitulo)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlProductosPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Maneja el evento cuando se hace clic en el botón de pago con "Tarjeta".
     * Si el pedido está vacío, muestra un mensaje de error. Si hay productos,
     * navega a la pantalla de pago con tarjeta.
     *
     * @param evt el evento generado al hacer clic en el botón de pago con
     * "Tarjeta"
     */
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        PedidoDTO pedido = ControlNavegacion.getPedido();
        List<ProductoPedidoDTO> listaProductosPedidos = pedido.getPedido();
        if (listaProductosPedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El pedido está vacío", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ControlNavegacion.mostrarPantallaPagoTarjeta();
            this.dispose();
        }
    }//GEN-LAST:event_btnVolverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane pnlProductosPedidos;
    // End of variables declaration//GEN-END:variables
}
