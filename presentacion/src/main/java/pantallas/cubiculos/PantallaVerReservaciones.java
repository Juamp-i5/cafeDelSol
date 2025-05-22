/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas.cubiculos;

import pantallas.*;
import DTOs.PedidoDTO;
import control.ControlNavegacion;
import DTOs.PersistenciaProductoPedidoDTO;
import DTOs.cubiculos.ReservacionDTOMostrar;
import DTOs.cubiculos.ReservacionDetalleDTO;
import control.Modo;
import control.ModoCubiculos;
import exception.GestionException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Clase que representa la pantalla de total desglosado de un pedido.
 *
 * @author norma
 */
public class PantallaVerReservaciones extends javax.swing.JFrame {

    private final int MOVIMIENTO_SCROLL_MOUSE = 15;
    private ModoCubiculos modo;

    /**
     * Constructor de la clase PantallaVerReservaciones. Inicializa los
     * componentes de la interfaz y ajusta el tamaño de la ventana.
     *
     * Llama a los métodos para cargar las reservaciones pendientes e iniciadas
     * y ajustar el scroll.
     */
    public PantallaVerReservaciones(ModoCubiculos modo) {
        this.modo = modo;
        initComponents();
        setSize(1000, 800);
        cargarPanelesReservaciones(null, null);
        ajustarScroll();
    }

    /**
     * Ajusta el comportamiento del scroll vertical de las reservaciones en el
     * panel.
     */
    private void ajustarScroll() {
        pnlVerReservaciones.getVerticalScrollBar().setUnitIncrement(MOVIMIENTO_SCROLL_MOUSE);
    }

    /**
     * Carga los paneles de las reservaciones
     */
    public void cargarPanelesReservaciones(LocalDate fechaInicio, LocalDate fechaFin) {
        int posicionScroll = pnlVerReservaciones.getVerticalScrollBar().getValue();
        JPanel contenedorPanelesReservaciones = obtenerPanelesReservaciones(fechaInicio, fechaFin);
        this.pnlVerReservaciones.setViewportView(contenedorPanelesReservaciones);
        pnlVerReservaciones.getVerticalScrollBar().setValue(posicionScroll);
    }

    /**
     * Obtiene un panel que contiene todos los datos de las reservaciones.
     *
     * @return JPanel que contiene los datos de las reservaciones.
     */
    private JPanel obtenerPanelesReservaciones(LocalDate fechaInicio, LocalDate fechaFin) {

        List<ReservacionDTOMostrar> listaVerReservaciones = new ArrayList<>();
        JPanel contenedorPanelesVerReservaciones = new JPanel();

        if (modo == ModoCubiculos.VER) {
            listaVerReservaciones = ControlNavegacion.cargarReservacionesPendientes(fechaInicio, fechaFin);

            contenedorPanelesVerReservaciones.setLayout(new BoxLayout(contenedorPanelesVerReservaciones, BoxLayout.Y_AXIS));

            for (ReservacionDTOMostrar reservacion : listaVerReservaciones) {
                PanelReservacion panelReservacion = new PanelReservacion(reservacion);
                configurarPanelReservacion(panelReservacion);
                contenedorPanelesVerReservaciones.add(panelReservacion);
            }
        } else {
            listaVerReservaciones = ControlNavegacion.cargarReservacionesHistorial(fechaInicio, fechaFin);

            contenedorPanelesVerReservaciones.setLayout(new BoxLayout(contenedorPanelesVerReservaciones, BoxLayout.Y_AXIS));

            for (ReservacionDTOMostrar reservacion : listaVerReservaciones) {
                PanelReservacionHistorial panelReservacion = new PanelReservacionHistorial(reservacion);
                configurarPanelReservacionHistorial(panelReservacion);
                contenedorPanelesVerReservaciones.add(panelReservacion);
            }
        }

        return contenedorPanelesVerReservaciones;
    }

    /**
     * Configura los listeners para cada panel de reservacion (iniciar,
     * cancelar).
     *
     * @param panelRes El panel de reservacion a configurar.
     */
    private void configurarPanelReservacion(PanelReservacion panelRes) {
        panelRes.setCancelarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarReservacion(panelRes.getReservacion());
                cargarPanelesReservaciones(null, null);
                pnlVerReservaciones.revalidate();
                pnlVerReservaciones.repaint();
            }
        });
        panelRes.setIniciarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarReservacion(panelRes.getReservacion());
                cargarPanelesReservaciones(null, null);
                pnlVerReservaciones.revalidate();
                pnlVerReservaciones.repaint();
            }
        });
        panelRes.setConcluirActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                concluirReservacion(panelRes.getReservacion());
                cargarPanelesReservaciones(null, null);
                pnlVerReservaciones.revalidate();
                pnlVerReservaciones.repaint();
            }
        });

    }

    /**
     * Configura los listeners para cada panel de reservacion (ver detalle).
     *
     *
     * @param panelRes El panel de reservacion historial a configurar.
     */
    private void configurarPanelReservacionHistorial(PanelReservacionHistorial panelRes) {
        panelRes.setVerDetalleActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDetalle(panelRes.getReservacion());
            }
        });
    }

    /**
     * Cancela una reservacion, modifica el estado y guarda el motivo de
     * cancelacion.
     *
     * @param ReservacionDTOMostrar la reservacion a cancelar
     */
    private void cancelarReservacion(ReservacionDTOMostrar reservacion) {
        if (reservacion.getEstado() == "ACTIVA") {
            JOptionPane.showMessageDialog(
                    null,
                    "No puedes cancelar una reservacion Activa.",
                    "Reservacion Activa",
                    JOptionPane.WARNING_MESSAGE
            );
        } else {
            String motivo = optionPaneCancelar();
            if (motivo != null) {
                ControlNavegacion.cancelarReservacion(reservacion.getNumReservacion(), motivo);
            }
        }
    }

    /**
     * Inicia una reservacion, modifica el estado a ACTIVA
     *
     *
     * @param ReservacionDTOMostrar reservacion a iniciar
     */
    private void iniciarReservacion(ReservacionDTOMostrar reservacion) {
        if (reservacion.getEstado() == "ACTIVA") {
            JOptionPane.showMessageDialog(
                    null,
                    "La reservacion se encuentra activa",
                    "Reservacion Activa",
                    JOptionPane.WARNING_MESSAGE
            );
        } else {
            int opc = JOptionPane.showConfirmDialog(
                    this,
                    "¿Activar la Reservacion?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (opc == JOptionPane.YES_OPTION) {
                boolean exito = ControlNavegacion.actualizarEstadoReservacion(reservacion.getNumReservacion(), "ACTIVA");
                if(!exito){
                    JOptionPane.showMessageDialog(this, "Aun no se puede iniciar la reservacion.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * Termina una reservacion, modifica el estado a CONCLUIDA
     *
     *
     * @param ReservacionDTOMostrar reservacion a concluir
     */
    private void concluirReservacion(ReservacionDTOMostrar reservacion) {
        if (reservacion.getEstado() == "PENDIENTE") {
            JOptionPane.showMessageDialog(
                    null,
                    "La reservacion no se encuentra activa",
                    "Concluir reservacion",
                    JOptionPane.WARNING_MESSAGE
            );
        } else {
            int opc = JOptionPane.showConfirmDialog(
                    this,
                    "¿Concluir la Reservacion?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (opc == JOptionPane.YES_OPTION) {
                ControlNavegacion.actualizarEstadoReservacion(reservacion.getNumReservacion(), "CONCLUIDA");
            }
        }
    }

    /**
     * Configura el option pane que se genera al seleccionar cancelar una
     * reservacion.
     *
     * @return String el mensaje escrito en el motivo.
     */
    private String optionPaneCancelar() {
        JTextField campoMotivo = new JTextField();

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(new JLabel("Escribe el motivo de cancelación:"), BorderLayout.NORTH);
        panel.add(campoMotivo, BorderLayout.CENTER);

        int resultado = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Motivo de Cancelación",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultado == JOptionPane.OK_OPTION) {
            String motivo = campoMotivo.getText().trim();
            if (motivo.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "Debes escribir un motivo para cancelar.",
                        "Motivo requerido",
                        JOptionPane.WARNING_MESSAGE
                );
                return null;
            }
            return motivo;
        }
        return null;
    }

    private void filtrarFechas() {
        LocalDate fecha1 = fechaDesde.getDate();
        LocalDate fecha2 = fechaHasta.getDate();
        if (fecha1 != null && fecha2 != null) {
            if (fecha1.isAfter(fecha2)) {
                JOptionPane.showMessageDialog(
                        null,
                        "La primera fecha no puede ser mayor que segunda.",
                        "Rango de fechas inválido",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                cargarPanelesReservaciones(fecha1, fecha2);
            }
        } else if (fecha1 != null) {
            cargarPanelesReservaciones(fecha1, null);
        } else if (fecha2 != null) {
            cargarPanelesReservaciones(null, fecha2);
        } else {
            cargarPanelesReservaciones(null, null);
        }
        pnlVerReservaciones.revalidate();
        pnlVerReservaciones.repaint();
    }

    public void mostrarDetalle(ReservacionDTOMostrar reservacion) {
        ReservacionDetalleDTO dtoDetalle = ControlNavegacion.getDetalleReservacion(reservacion.getNumReservacion());
        
        StringBuilder mensaje = new StringBuilder("Detalles de la Reservación:\n");
        mensaje.append("Número de Reservación: ").append(dtoDetalle.getNumReservacion()).append("\n");
        mensaje.append("Nombre del cliente: ").append(dtoDetalle.getNombre()).append("\n");
        mensaje.append("Número de teléfono: ").append(dtoDetalle.getTelefono()).append("\n");
        mensaje.append("Fecha de reservación: ").append(dtoDetalle.getFechaReserva().toString()).append("\n");
        mensaje.append("Hora de inicio: ").append(dtoDetalle.getHoraInicio().toString()).append("\n");
        mensaje.append("Hora de finalización: ").append(dtoDetalle.getHoraFin().toString()).append("\n");
        mensaje.append("Estado: ").append(dtoDetalle.getEstado()).append("\n");
        mensaje.append("Cubículo: ").append(dtoDetalle.getNombreCubiculo()).append("\n");
        mensaje.append("Precio por hora: ").append("$" + dtoDetalle.getPrecioHora()).append("\n");
        mensaje.append("Precio total: ").append("$" + dtoDetalle.getPrecioReservacion()).append("\n");
        if(dtoDetalle.getMotivo() != null){
            mensaje.append("Motivo de movimiento: ").append(dtoDetalle.getMotivo()).append("\n");
        }
        if(dtoDetalle.getNumReservacionNuevo()!= null){
            mensaje.append("Reservación nueva: ").append(dtoDetalle.getNumReservacionNuevo()).append("\n");
        }
        if(dtoDetalle.getFechaModificacion()!= null){
            mensaje.append("Fecha de modificación: ").append(dtoDetalle.getFechaModificacion().toString()).append("\n");
        }
        
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Detalles", JOptionPane.INFORMATION_MESSAGE);
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

        pnlVerReservaciones = new javax.swing.JScrollPane();
        btnVolver = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelTitulo = new javax.swing.JLabel();
        fechaDesde = new com.github.lgooddatepicker.components.DatePicker();
        fechaHasta = new com.github.lgooddatepicker.components.DatePicker();
        jLabelFechaDesde = new javax.swing.JLabel();
        jLabelFechaHasta = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();

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

        jLabelFechaDesde.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelFechaDesde.setText("Desde");

        jLabelFechaHasta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelFechaHasta.setText("Hasta");

        btnFiltrar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(104, Short.MAX_VALUE)
                .addComponent(pnlVerReservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabelTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabelFechaDesde)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabelFechaHasta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnFiltrar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabelTitulo)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFechaDesde)
                    .addComponent(jLabelFechaHasta)
                    .addComponent(btnFiltrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlVerReservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        ControlNavegacion.volverPantallaAnterior();
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        filtrarFechas();
    }//GEN-LAST:event_btnFiltrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnVolver;
    private com.github.lgooddatepicker.components.DatePicker fechaDesde;
    private com.github.lgooddatepicker.components.DatePicker fechaHasta;
    private javax.swing.JLabel jLabelFechaDesde;
    private javax.swing.JLabel jLabelFechaHasta;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane pnlVerReservaciones;
    // End of variables declaration//GEN-END:variables
}
