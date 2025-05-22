/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import DTOs.PedidoDTO;
import control.ControlNavegacion;
import DTOs.PersistenciaProductoPedidoDTO;
import DTOs.ProductoPedidoDTO;
import control.Modo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Clase que representa la pantalla de total desglosado de un pedido.
 *
 * @author norma
 */
public class TotalDesglosado extends javax.swing.JFrame {

    private final int MOVIMIENTO_SCROLL_MOUSE = 15;

    /**
     * Constructor de la clase TotalDesglosado. Inicializa los componentes de la
     * interfaz y ajusta el tamaño de la ventana.
     *
     * Llama a los métodos para cargar los productos del pedido y ajustar el
     * scroll.
     */
    public TotalDesglosado() {
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
        this.lblTotalTotal.setText(String.format("%.2f", ControlNavegacion.calcularTotal()));
        pnlProductosPedidos.getVerticalScrollBar().setValue(posicionScroll);
    }

    /**
     * Obtiene un panel que contiene todos los productos en el pedido.
     *
     * @return JPanel que contiene los productos pedidos.
     */
    private JPanel obtenerPanelesProductosPedidos() {
        PedidoDTO pedido = ControlNavegacion.getPedido();
//        List<PersistenciaProductoPedidoDTO> listaProductosPedidos = pedido.getPedido();

        JPanel contenedorPanelesProductosPedidos = new JPanel();
        contenedorPanelesProductosPedidos.setLayout(new BoxLayout(contenedorPanelesProductosPedidos, BoxLayout.Y_AXIS));

//        for (PersistenciaProductoPedidoDTO productoPedido : listaProductosPedidos) {
//            PanelProductoPedido panelProductoPedido = new PanelProductoPedido(productoPedido);
//            configurarPanelProducto(panelProductoPedido);
//            contenedorPanelesProductosPedidos.add(panelProductoPedido);
//        }
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
        btnAgregarOtro = new javax.swing.JButton();
        btnTarjeta = new javax.swing.JButton();
        btnEfectivo = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        lblTotalTotal = new javax.swing.JLabel();
        lblSigno = new javax.swing.JLabel();
        btnCancelarPedido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAgregarOtro.setBackground(new java.awt.Color(255, 255, 51));
        btnAgregarOtro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnAgregarOtro.setText("Agregar otro Producto");
        btnAgregarOtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarOtroActionPerformed(evt);
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
                        .addComponent(btnAgregarOtro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarOtro, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Maneja el evento cuando se hace clic en el botón "Agregar Otro Producto".
     * Muestra un mensaje de confirmación y, si se confirma, navega a la
     * pantalla de creación de productos.
     *
     * @param evt el evento generado al hacer clic en el botón "Agregar Otro
     * Producto"
     */
    private void btnAgregarOtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarOtroActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea agregar otro producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {

            ControlNavegacion.mostrarPantallaProductos(Modo.CREACION);
            dispose();
        }
    }//GEN-LAST:event_btnAgregarOtroActionPerformed

    /**
     * Maneja el evento cuando se hace clic en el botón "Cancelar Pedido".
     * Cancela el pedido actual.
     *
     * @param evt el evento generado al hacer clic en el botón "Cancelar Pedido"
     */
    private void btnCancelarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPedidoActionPerformed
        cancelarPedido();
    }//GEN-LAST:event_btnCancelarPedidoActionPerformed

    /**
     * Maneja el evento cuando se hace clic en el botón de pago con "Tarjeta".
     * Si el pedido está vacío, muestra un mensaje de error. Si hay productos,
     * navega a la pantalla de pago con tarjeta.
     *
     * @param evt el evento generado al hacer clic en el botón de pago con
     * "Tarjeta"
     */
    private void btnTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarjetaActionPerformed
        PedidoDTO pedido = ControlNavegacion.getPedido();
        List<ProductoPedidoDTO> listaProductosPedidos = pedido.getProductos();
        if (listaProductosPedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El pedido está vacío", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ControlNavegacion.mostrarPantallaPagoTarjeta();
            this.dispose();
        }
    }//GEN-LAST:event_btnTarjetaActionPerformed

    /**
     * Maneja el evento cuando se hace clic en el botón de pago con "Efectivo".
     * Si el pedido está vacío, muestra un mensaje de error. Si hay productos,
     * navega a la pantalla de pago en efectivo.
     *
     * @param evt el evento generado al hacer clic en el botón de pago con
     * "Efectivo"
     */
    private void btnEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEfectivoActionPerformed
        PedidoDTO pedido = ControlNavegacion.getPedido();
        List<ProductoPedidoDTO> listaProductosPedidos = pedido.getProductos();
        if (listaProductosPedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El pedido está vacío", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ControlNavegacion.mostrarPantallaPagoEfectivo();
            this.dispose();
        }
    }//GEN-LAST:event_btnEfectivoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarOtro;
    private javax.swing.JButton btnCancelarPedido;
    private javax.swing.JButton btnEfectivo;
    private javax.swing.JButton btnTarjeta;
    private javax.swing.JLabel lblSigno;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalTotal;
    private javax.swing.JScrollPane pnlProductosPedidos;
    // End of variables declaration//GEN-END:variables
}
