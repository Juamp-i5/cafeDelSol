package pantallas.ingredientes;

import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDIngredientes.NivelStock;
import control.ControlNavegacion;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author norma
 */
public class PantallaBuscarIngrediente extends javax.swing.JFrame {

    DefaultTableModel modeloTablaIngredientes;
    private Timer debounceTimer;
    Consumer<IngredienteViejoListDTO> regreso;

    private final int DEBOUNCE_DELAY = 50;

    public PantallaBuscarIngrediente() {
        initComponents();
        btnSeleccionar.setVisible(false);
        this.modeloTablaIngredientes = obtenerModeloTablaIngredientes();
        setupInitialUIState();
        cargarDatos();
    }

    public PantallaBuscarIngrediente(Consumer regreso) {
        this();
        this.regreso = regreso;
        this.btnSeleccionar.setVisible(true);
        this.btnVolverAtras.setVisible(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                regreso.accept(null);
            }
        });
    }

    private DefaultTableModel obtenerModeloTablaIngredientes() {
        return (DefaultTableModel) this.tablaIngredientes.getModel();
    }

    private void setupInitialUIState() {
        this.setLocationRelativeTo(null);
        checkBoxNombre.setSelected(false);
        txtNombre.setEnabled(false);

        checkBoxNivelStock.setSelected(false);
        comboBoxNivelStock.setEnabled(false);
    }

    private void cargarDatos() {
        cargarNivelesStockComboBox();
        cargarTablaIngredientes();
    }

    private void cargarNivelesStockComboBox() {
        comboBoxNivelStock.removeAllItems();

        for (NivelStock nivelStock : NivelStock.values()) {
            comboBoxNivelStock.addItem(nivelStock.name());
        }
    }

    private void cargarTablaIngredientes() {
        List<IngredienteViejoListDTO> ingredientes = obtenerIngredientes();

        this.modeloTablaIngredientes.setRowCount(0);

        for (IngredienteViejoListDTO ingrediente : ingredientes) {
            this.modeloTablaIngredientes.addRow(
                    new Object[]{
                        ingrediente.getId(),
                        ingrediente.getNombre(),
                        ingrediente.getCantidadDisponible(),
                        ingrediente.getUnidadMedida(),
                        ingrediente.getNivelStock()
                    }
            );
        }

    }

    private List<IngredienteViejoListDTO> obtenerIngredientes() {
        String filtroNombre = getFiltroNombre();
        String filtroNivelStock = getFiltroNivelStock();

        return ControlNavegacion.buscarIngredientesPorFiltros(filtroNombre, filtroNivelStock);
    }

    private String getFiltroNombre() {
        if (txtNombre.isEnabled()) {
            return txtNombre.getText();
        } else {
            return "";
        }
    }

    private String getFiltroNivelStock() {
        if (comboBoxNivelStock.isEnabled()) {
            return comboBoxNivelStock.getSelectedItem().toString();
        } else {
            return "";
        }
    }

    private void debounce(Runnable action) {
        if (debounceTimer != null && debounceTimer.isRunning()) {
            debounceTimer.stop();
        }

        ActionListener taskPerformer = evt -> {
            action.run();
        };

        debounceTimer = new Timer(DEBOUNCE_DELAY, taskPerformer);
        debounceTimer.setRepeats(false);
        debounceTimer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        sepArriba = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JTextField();
        comboBoxNivelStock = new javax.swing.JComboBox<>();
        btnSeleccionar = new javax.swing.JButton();
        btnVolverAtras = new javax.swing.JButton();
        scrollPanelIngredientes = new javax.swing.JScrollPane();
        tablaIngredientes = new javax.swing.JTable();
        checkBoxNombre = new javax.swing.JCheckBox();
        checkBoxNivelStock = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitulo.setText("Buscar ingrediente");
        lblTitulo.setToolTipText("");

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        comboBoxNivelStock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        comboBoxNivelStock.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxNivelStock.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxNivelStockItemStateChanged(evt);
            }
        });

        btnSeleccionar.setBackground(new java.awt.Color(255, 204, 0));
        btnSeleccionar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Cantidad disponible", "Unidad de medida", "Estado"
            }
        ));
        scrollPanelIngredientes.setViewportView(tablaIngredientes);

        checkBoxNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        checkBoxNombre.setText("Nombre ");
        checkBoxNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxNombreActionPerformed(evt);
            }
        });

        checkBoxNivelStock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        checkBoxNivelStock.setText("Nivel de stock");
        checkBoxNivelStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxNivelStockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sepArriba)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkBoxNivelStock)
                                    .addComponent(checkBoxNombre))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxNivelStock, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(scrollPanelIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxNivelStock)
                    .addComponent(comboBoxNivelStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPanelIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        if (regreso != null) {
            int row = this.tablaIngredientes.getSelectedRow();
            IngredienteViejoListDTO ingredienteSeleccionado = new IngredienteViejoListDTO(
                    modeloTablaIngredientes.getValueAt(row, 0).toString(),
                    modeloTablaIngredientes.getValueAt(row, 1).toString()
            );
            regreso.accept(ingredienteSeleccionado);
            this.dispose();
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnVolverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverAtrasActionPerformed
        ControlNavegacion.mostrarPantallaListaIngredientes();
        this.dispose();
    }//GEN-LAST:event_btnVolverAtrasActionPerformed

    private void checkBoxNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxNombreActionPerformed
        boolean selected = checkBoxNombre.isSelected();
        txtNombre.setEnabled(selected);

        debounce(() -> {
            cargarTablaIngredientes();
        });

    }//GEN-LAST:event_checkBoxNombreActionPerformed

    private void checkBoxNivelStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxNivelStockActionPerformed
        boolean selected = checkBoxNivelStock.isSelected();
        comboBoxNivelStock.setEnabled(selected);

        debounce(() -> {
            cargarTablaIngredientes();
        });
    }//GEN-LAST:event_checkBoxNivelStockActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        debounce(() -> {
            cargarTablaIngredientes();
        });
    }//GEN-LAST:event_txtNombreKeyTyped

    private void comboBoxNivelStockItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxNivelStockItemStateChanged
        debounce(() -> {
            cargarTablaIngredientes();
        });
    }//GEN-LAST:event_comboBoxNivelStockItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JButton btnVolverAtras;
    private javax.swing.JCheckBox checkBoxNivelStock;
    private javax.swing.JCheckBox checkBoxNombre;
    private javax.swing.JComboBox<String> comboBoxNivelStock;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane scrollPanelIngredientes;
    private javax.swing.JSeparator sepArriba;
    private javax.swing.JTable tablaIngredientes;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
