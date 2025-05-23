package pantallas.CRUDEntradas;

import DTOs.CRUDEntradas.DetalleEntradaDTO;
import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDIngredientes.NivelStock;
import DTOs.CRUDIngredientes.UnidadMedida;
import control.ControlNavegacion;
import java.awt.Color;
import java.awt.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pablo
 */
public final class PantallaTablaRegistroEntrada extends javax.swing.JFrame {

    private final List<DetalleEntradaDTO> registroNuevo = new ArrayList<>();
    private EntradaNuevaDTO entrada;
    private DetalleEntradaDTO detalle;
    private DefaultTableModel modeloTablaIngrdientes;
    private int LIMITE_LONGITUD_NOMBREPROVEEDOR = 50;
    private int LIMITE_LONGITUD_NUMERO = 10;

    /**
     * Creates new form PantallaTablaRegistroEntrada
     *
     *
     */
    public PantallaTablaRegistroEntrada() {
        initComponents();
        inicializarTabla();
    }

    private double parseDoubleSafely(Object value) {
        if (value == null) {
            return 0.0;
        }
        if (value instanceof Number number) {
            return number.doubleValue();
        } else if (value instanceof String string) {
            try {
                return Double.parseDouble(string);
            } catch (NumberFormatException e) {
                System.out.println("Valor no numérico: " + value);
            }
        }
        return 0.0;
    }

    private boolean esNumeroValido(String texto) {
        return texto != null && texto.matches("\\d+(\\.\\d+)?");
    }

    private boolean esProveedorNumerico(String proveedor) {
        return proveedor != null
                && !proveedor.matches("\\d+");
    }

    public void inicializarTabla() {
        if (modeloTablaIngrdientes == null) {
            modeloTablaIngrdientes = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 2 || column == 4;
                }
            };
            modeloTablaIngrdientes.addColumn("Nombre");
            modeloTablaIngrdientes.addColumn("Stock actual");
            modeloTablaIngrdientes.addColumn("Cantidad agregada");
            modeloTablaIngrdientes.addColumn("Unidad de medida");
            modeloTablaIngrdientes.addColumn("Precio unitario");
            modeloTablaIngrdientes.addColumn("Precio total");
            modeloTablaIngrdientes.addColumn("Estado");
            jTable1.setModel(modeloTablaIngrdientes);
            jTable1.getColumnModel().getColumn(2).setCellRenderer(new Placeholder("Ingrese cantidad"));
            jTable1.getColumnModel().getColumn(4).setCellRenderer(new Placeholder("Ingrese precio"));
        }
    }

    public class Placeholder extends DefaultTableCellRenderer {

        private final String placeholder;
        private final boolean editando = false;

        public Placeholder(String placeholder) {
            this.placeholder = placeholder;
            setForeground(Color.GRAY);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (!editando && (value == null || value.toString().isEmpty() || value.equals(placeholder))) {
                label.setText(placeholder);
                label.setForeground(Color.GRAY);
            } else {
                label.setForeground(table.getForeground());
            }
            return label;
        }
    }

    public void volverAtras() {
        if (modeloTablaIngrdientes.getRowCount() != 0) {
            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "        ¿Seguro que deseas volver?\nTu registro se eliminará automáticamente",
                    "Advertencia",
                    JOptionPane.YES_NO_OPTION
            );
            if (respuesta == JOptionPane.YES_OPTION) {
                ControlNavegacion.mostrarPantallaHistorialEntradas();
                this.dispose();
            }
        } else {
            ControlNavegacion.mostrarPantallaHistorialEntradas();
            this.dispose();
        }
    }

    public void cancelarIngrediente() {
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que deseas eliminar este ingrediente?",
                "Registro",
                JOptionPane.YES_NO_OPTION
        );
        if (respuesta == JOptionPane.YES_OPTION) {
            int fila = jTable1.getSelectedRow();
            if (fila != -1) {
                modeloTablaIngrdientes.removeRow(fila);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
            }
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

        BtnVolverAtras = new javax.swing.JButton();
        BtnConfirmar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        LbTitulo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        LbProveedor = new javax.swing.JLabel();
        TFProveedor = new javax.swing.JTextField();
        BtnAgregarIngrediente = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        TablaRegistro = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BtnVolverAtras.setBackground(new java.awt.Color(255, 204, 0));
        BtnVolverAtras.setText("<--");
        BtnVolverAtras.setFont(new java.awt.Font("Segoe UI", 1, 18));
        BtnVolverAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVolverAtrasActionPerformed(evt);
            }
        });

        BtnConfirmar.setFont(new java.awt.Font("Segoe UI", 1, 18));
        BtnConfirmar.setText("Confirmar");
        BtnConfirmar.setBackground(new java.awt.Color(255, 204, 0));
        BtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConfirmarActionPerformed(evt);
            }
        });

        LbTitulo.setText("Registro Entrada");

        LbProveedor.setText("Proveedor");

        BtnAgregarIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 18));
        BtnAgregarIngrediente.setText("Agregar Ingrediente");
        BtnAgregarIngrediente.setBackground(new java.awt.Color(255, 204, 0));
        BtnAgregarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarIngredienteActionPerformed(evt);
            }
        });

        BtnCancelar.setBackground(new java.awt.Color(255, 204, 0));
        BtnCancelar.setText("Cancelar Ingrediente");
        BtnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 18));
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Stock actual", "Cantidad agregada", "Unidad de medida", "Precio unitario", "Precio total", "Estado"
            }
        ));
        TablaRegistro.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(LbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(TablaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 986, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LbProveedor)
                                    .addComponent(TFProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnAgregarIngrediente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnCancelar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(BtnVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LbProveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addComponent(BtnAgregarIngrediente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TablaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        LbTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnVolverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVolverAtrasActionPerformed
        volverAtras();
    }//GEN-LAST:event_BtnVolverAtrasActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        cancelarIngrediente();
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConfirmarActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas realizar el registro?",
                "Registro",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            if (modeloTablaIngrdientes.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Agregue al menos un ingrediente.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (TFProveedor.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un proveedor válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (TFProveedor.getText().trim().length() > 50) {
                JOptionPane.showMessageDialog(this, "El nombre del proveedor no debe exceder los 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                TFProveedor.setText(null);
                return;
            }
            if (!esProveedorNumerico(TFProveedor.getText().trim())) {
                JOptionPane.showMessageDialog(this, "El nombre del proveedor no puede ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (detalle == null) {
                detalle = new DetalleEntradaDTO();
            }
            if (entrada == null) {
                entrada = new EntradaNuevaDTO();
            }
            registroNuevo.clear();
            double totalEntrada = 0.0;
            double totalCantidadIngredientes = 0.0;

            for (int i = 0; i < modeloTablaIngrdientes.getRowCount(); i++) {
                Double cantidadAgregada = null;
                Double precioUnitario = null;
                String nombreIngrediente = null;
                Double stockActual = null;
                Double precioTotal = 0.0;

                Object nombreObj = modeloTablaIngrdientes.getValueAt(i, 0);
                Object stockObj = modeloTablaIngrdientes.getValueAt(i, 1);
                Object cantidadObj = modeloTablaIngrdientes.getValueAt(i, 2);
                UnidadMedida unidad = (UnidadMedida) modeloTablaIngrdientes.getValueAt(i, 3);
                Object precioUnitarioObj = modeloTablaIngrdientes.getValueAt(i, 4);
                NivelStock estadoObj = (NivelStock) modeloTablaIngrdientes.getValueAt(i, 6);

                try {
                    if (cantidadObj instanceof String string) {
                        cantidadAgregada = Double.valueOf(string);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Cantidad agregada inválida en fila " + (i + 1) + ".", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    if (precioUnitarioObj instanceof String string) {
                        precioUnitario = Double.valueOf(string);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Precio unitario inválido en fila " + (i + 1) + ".", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (cantidadObj instanceof String cantidadStr && !cantidadStr.trim().isEmpty() && !esNumeroValido(cantidadStr)) {
                    JOptionPane.showMessageDialog(this, "Cantidad agregada inválida (solo números) en fila " + (i + 1) + ".", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (precioUnitarioObj instanceof String precioStr && !precioStr.trim().isEmpty() && !esNumeroValido(precioStr)) {
                    JOptionPane.showMessageDialog(this, "Precio unitario inválido (solo números) en fila " + (i + 1) + ".", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (nombreObj != null) {
                    nombreIngrediente = nombreObj.toString();
                }
                if (stockObj instanceof Number number) {
                    stockActual = number.doubleValue();
                }

                if (cantidadAgregada == null || precioUnitario == null) {
                    JOptionPane.showMessageDialog(this, "Complete la cantidad agregada y precio unitario en la fila " + (i + 1) + " para realizar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (cantidadAgregada <= 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad agregada no puede ser cero en la fila " + (i + 1) + ".", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                precioTotal = cantidadAgregada * precioUnitario;
                modeloTablaIngrdientes.setValueAt(precioTotal, i, 5);
                DetalleEntradaDTO preRegistro = new DetalleEntradaDTO();
                preRegistro.setNombreIngrediente(nombreIngrediente);
                preRegistro.setCantidadIngrediente(cantidadAgregada);
                preRegistro.setPrecioUnitario(precioUnitario);
                preRegistro.setPrecioTotal(precioTotal);
                preRegistro.setNivelStock(estadoObj);

                String idIngrediente = ControlNavegacion.obtenerIdIngredientePorNombre(nombreIngrediente);
                boolean resultado = ControlNavegacion.aumentarStock(idIngrediente, cantidadAgregada);
                if (resultado != true) {
                    JOptionPane.showMessageDialog(this, "No se pudo aumentar el stock.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                preRegistro.setIdIngrediente(idIngrediente);
                registroNuevo.add(preRegistro);

                totalEntrada += precioTotal;
                totalCantidadIngredientes += cantidadAgregada;
            }

            entrada.setFechaHora(LocalDateTime.now());
            entrada.setProveedor(TFProveedor.getText().trim());
            entrada.setPrecioTotal(totalEntrada);
            entrada.setDetallesEntrada(registroNuevo);

            ControlNavegacion.registrarEntrada(entrada);
            ControlNavegacion.mostrarPantallaHistorialEntradas();
            this.dispose();
        }
    }//GEN-LAST:event_BtnConfirmarActionPerformed

    private void BtnAgregarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarIngredienteActionPerformed
        String proveedor = TFProveedor.getText().trim();
        if (proveedor.length() > LIMITE_LONGITUD_NOMBREPROVEEDOR) {
            JOptionPane.showMessageDialog(this, "El nombre del proveedor no debe exceder los " + LIMITE_LONGITUD_NOMBREPROVEEDOR + " caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            TFProveedor.setText(null);
            return;
        }

        modeloTablaIngrdientes.addTableModelListener(e -> {
            int fila = e.getFirstRow();
            int col = e.getColumn();

            if (col == 2 || col == 4) {
                Object valorObj = modeloTablaIngrdientes.getValueAt(fila, col);
                if (valorObj instanceof String valorStr && !valorStr.trim().isEmpty() && !esNumeroValido(valorStr)) {
                    JOptionPane.showMessageDialog(this, "Ingrese un número válido en la columna " + (col == 2 ? "'Cantidad agregada'" : "'Precio unitario'") + " (solo números) en la fila " + (fila + 1) + ".", "Error", JOptionPane.ERROR_MESSAGE);
                    modeloTablaIngrdientes.setValueAt(null, fila, col);
                }

                try {
                    double valor = parseDoubleSafely(modeloTablaIngrdientes.getValueAt(fila, col));

                    if (valor < 0) {
                        JOptionPane.showMessageDialog(this, "No se permiten valores negativos en la columna " + (col == 2 ? "'Cantidad agregada'" : "'Precio unitario'") + " en la fila " + (fila + 1) + ".", "Error", JOptionPane.ERROR_MESSAGE);
                        modeloTablaIngrdientes.setValueAt(null, fila, col);
                    }

                    String valorComoTexto = String.valueOf(valor);
                    if (valorComoTexto.length() > LIMITE_LONGITUD_NUMERO) {
                        JOptionPane.showMessageDialog(this, "El valor en la columna " + (col == 2 ? "'Cantidad agregada'" : "'Precio unitario'") + " no debe exceder los " + LIMITE_LONGITUD_NUMERO + " dígitos en la fila " + (fila + 1) + ".", "Error", JOptionPane.ERROR_MESSAGE);
                        modeloTablaIngrdientes.setValueAt(null, fila, col);
                        return;
                    }

                    double cantidad = parseDoubleSafely(modeloTablaIngrdientes.getValueAt(fila, 2));
                    double precioUnitario = parseDoubleSafely(modeloTablaIngrdientes.getValueAt(fila, 4));
                    modeloTablaIngrdientes.setValueAt(cantidad * precioUnitario, fila, 5);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un número válido en la columna " + (col == 2 ? "'Cantidad agregada'" : "'Precio unitario'") + " en la fila " + (fila + 1) + ".", "Error", JOptionPane.ERROR_MESSAGE);
                    modeloTablaIngrdientes.setValueAt(null, fila, col);
                }
            }
        });
        this.setVisible(false);
        ControlNavegacion.mostrarPantallaIngrediente(ingredienteNuevo -> {
            this.setVisible(true);
            if (ingredienteNuevo == null) {
                JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún ingrediente.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DetallesIngredienteViejoDTO ingrediente = ControlNavegacion.obtenerDetallesIngrediente(ingredienteNuevo.getId());
            boolean encontrado = false;
            for (int i = 0; i < modeloTablaIngrdientes.getRowCount(); i++) {
                String nombreExistente = String.valueOf(modeloTablaIngrdientes.getValueAt(i, 0));
                if (nombreExistente.equalsIgnoreCase(ingredienteNuevo.getNombre())) {
                    encontrado = true;
                    JOptionPane.showMessageDialog(this, "El ingrediente \"" + ingredienteNuevo.getNombre() + "\" ya se encuentra en la lista.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }

            if (!encontrado) {
                modeloTablaIngrdientes.addRow(new Object[]{
                    ingrediente.getNombre(),
                    ingrediente.getCantidadDisponible(),
                    null,
                    ingrediente.getUnidadMedida(),
                    null,
                    0.0,
                    ingrediente.getNivelStock()
                });
            }
        });
    }//GEN-LAST:event_BtnAgregarIngredienteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarIngrediente;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnConfirmar;
    private javax.swing.JButton BtnVolverAtras;
    private javax.swing.JLabel LbProveedor;
    private javax.swing.JLabel LbTitulo;
    private javax.swing.JTextField TFProveedor;
    private javax.swing.JScrollPane TablaRegistro;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
