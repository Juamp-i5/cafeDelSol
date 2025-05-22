package pantallas.ingredientes;

import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import control.ControlNavegacion;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author norma
 */
public class PantallaListaIngredientes extends javax.swing.JFrame {

    List<IngredienteViejoListDTO> ingredientes;
    DefaultTableModel modeloTablaIngredientes;
    private Timer debounceTimer;

    private final int DEBOUNCE_DELAY = 50;

    public PantallaListaIngredientes() {
        initComponents();
        this.modeloTablaIngredientes = obtenerModeloTablaIngredientes();
        cargarTablaIngredientes();
    }

    private DefaultTableModel obtenerModeloTablaIngredientes() {
        return (DefaultTableModel) this.tablaIngredientes.getModel();
    }

    private void cargarTablaIngredientes() {
        this.ingredientes = obtenerIngredientes();

        if (ingredientes == null || ingredientes.isEmpty()) {
            return;
        }

        this.modeloTablaIngredientes.setRowCount(0);

        for (IngredienteViejoListDTO ingrediente : ingredientes) {
            this.modeloTablaIngredientes.addRow(
                    new Object[]{
                        ingrediente.getNombre(),
                        ingrediente.getCantidadDisponible(),
                        ingrediente.getUnidadMedida(),
                        ingrediente.getNivelStock()
                    }
            );
        }

    }

    private List<IngredienteViejoListDTO> obtenerIngredientes() {
        return ControlNavegacion.buscarIngredientesPorFiltros("", "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        sepArriba = new javax.swing.JSeparator();
        btnVolverAtras = new javax.swing.JButton();
        scrollPanelListaIngredientes = new javax.swing.JScrollPane();
        tablaIngredientes = new javax.swing.JTable();
        btnAgregarIngrediente = new javax.swing.JButton();
        btnVerDetalles = new javax.swing.JButton();
        btnBuscarIngrediente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitulo.setText("Lista de ingredientes");
        lblTitulo.setToolTipText("");

        btnVolverAtras.setBackground(new java.awt.Color(255, 204, 0));
        btnVolverAtras.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVolverAtras.setText("<---");
        btnVolverAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverAtrasActionPerformed(evt);
            }
        });

        tablaIngredientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Cantidad disponible", "Unidad de medida", "Estado"
            }
        ));
        scrollPanelListaIngredientes.setViewportView(tablaIngredientes);

        btnAgregarIngrediente.setBackground(new java.awt.Color(255, 204, 0));
        btnAgregarIngrediente.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnAgregarIngrediente.setText("Agregar Ingrediente");
        btnAgregarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarIngredienteActionPerformed(evt);
            }
        });

        btnVerDetalles.setBackground(new java.awt.Color(255, 204, 0));
        btnVerDetalles.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnVerDetalles.setText("Ver Detalles");
        btnVerDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetallesActionPerformed(evt);
            }
        });

        btnBuscarIngrediente.setBackground(new java.awt.Color(255, 204, 0));
        btnBuscarIngrediente.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBuscarIngrediente.setText("Buscar Ingrediente");
        btnBuscarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarIngredienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sepArriba)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(scrollPanelListaIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarIngrediente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregarIngrediente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 41, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sepArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scrollPanelListaIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVerDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnBuscarIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnAgregarIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)))
                .addComponent(btnVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetallesActionPerformed

        int filaSeleccionada = tablaIngredientes.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un ingrediente de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        IngredienteViejoListDTO ingredienteSeleccionado = ingredientes.get(filaSeleccionada);

        ControlNavegacion.mostrarPantallaVerDetallesIngrediente(ingredienteSeleccionado.getId());
        this.dispose();
    }//GEN-LAST:event_btnVerDetallesActionPerformed

    private void btnBuscarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarIngredienteActionPerformed
        ControlNavegacion.mostrarBuscarIngrediente();
        this.dispose();
    }//GEN-LAST:event_btnBuscarIngredienteActionPerformed

    private void btnAgregarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarIngredienteActionPerformed
        ControlNavegacion.mostrarPantallaAgregarIngrediente();
        this.dispose();
    }//GEN-LAST:event_btnAgregarIngredienteActionPerformed

    private void btnVolverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverAtrasActionPerformed
        ControlNavegacion.mostrarPantallaMenuIniciado();
        this.dispose();
    }//GEN-LAST:event_btnVolverAtrasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarIngrediente;
    private javax.swing.JButton btnBuscarIngrediente;
    private javax.swing.JButton btnVerDetalles;
    private javax.swing.JButton btnVolverAtras;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane scrollPanelListaIngredientes;
    private javax.swing.JSeparator sepArriba;
    private javax.swing.JTable tablaIngredientes;
    // End of variables declaration//GEN-END:variables
}
