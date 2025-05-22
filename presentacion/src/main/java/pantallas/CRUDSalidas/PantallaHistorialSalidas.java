/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas.CRUDSalidas;

import DTOs.CRUDSalidas.DetalleSalidaDTO;
import DTOs.CRUDSalidas.SalidaListDTO;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import control.ControlNavegacion;
import excepciones.GestionCRUDSalidasException;
import gestionSalidas.GestorCRUDSalidas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author katia
 */
public class PantallaHistorialSalidas extends javax.swing.JFrame {

    private JLabel lblTitulo, lblFechaInicio, lblFechaFin;
    private JSeparator separador;
    private DatePicker pickerInicio, pickerFin;
    private JButton btnBuscar, btnRegresar;
    private JTable tablaHistorial;
    private DefaultTableModel modeloTabla;

    
    /**
     * Creates new form PantallaHistorialSalidas
     */
    public PantallaHistorialSalidas() {
        configurarVentana();
        initComponents2();
        cargarTodasLasSalidas();
    }

    private void configurarVentana() {
        setTitle("Historial de Salidas");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void initComponents2() {
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(235, 235, 235));

        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(235, 235, 235));
        lblTitulo = new JLabel("Historial de salidas");
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        separador = new JSeparator();

        panelTitulo.add(lblTitulo, BorderLayout.NORTH);
        panelTitulo.add(separador, BorderLayout.SOUTH);
        add(panelTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(new Color(235, 235, 235));

        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        panelFiltros.setBackground(new Color(235, 235, 235));
        lblFechaInicio = new JLabel("Fecha Inicio:");
        lblFechaFin = new JLabel("Fecha Fin:");
        lblFechaInicio.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblFechaFin.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        DatePickerSettings settingsInicio = new DatePickerSettings();
        DatePickerSettings settingsFin = new DatePickerSettings();
        pickerInicio = new DatePicker(settingsInicio);
        pickerFin = new DatePicker(settingsFin);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setPreferredSize(new Dimension(100, 35));
        btnBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        panelFiltros.add(lblFechaInicio);
        panelFiltros.add(pickerInicio);
        panelFiltros.add(lblFechaFin);
        panelFiltros.add(pickerFin);
        panelFiltros.add(btnBuscar);

        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Fecha", "Cantidad de salidas", ""}, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        tablaHistorial = new JTable(modeloTabla);
        tablaHistorial.getColumnModel().getColumn(0).setMinWidth(0);
        tablaHistorial.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaHistorial.getColumnModel().getColumn(0).setWidth(0);
        tablaHistorial.setRowHeight(40);
        tablaHistorial.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        tablaHistorial.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JScrollPane scroll = new JScrollPane(tablaHistorial);
        panelCentro.add(panelFiltros, BorderLayout.NORTH);
        panelCentro.add(scroll, BorderLayout.CENTER);
        add(panelCentro, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(235, 235, 235));
        btnRegresar = new JButton("<---");
        btnRegresar.setPreferredSize(new Dimension(200, 70));
        btnRegresar.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        btnRegresar.setBackground(Color.YELLOW);
        panelInferior.add(btnRegresar, BorderLayout.WEST);
        add(panelInferior, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarSalidas());
        btnRegresar.addActionListener(e -> {
            this.dispose();
            ControlNavegacion.volverPantallaAnterior();
        });

    }
    

    
    private void cargarTodasLasSalidas() {
        List<SalidaListDTO> lista = ControlNavegacion.obtenerTodasLasSalidas();
        mostrarAgrupadas(lista);
    }
    
    private void buscarSalidas() {
        modeloTabla.setRowCount(0);
        LocalDate inicio = pickerInicio.getDate();
        LocalDate fin = pickerFin.getDate();

        if (inicio == null || fin == null) {
            JOptionPane.showMessageDialog(this, "Selecciona ambas fechas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (inicio.isAfter(fin)) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser posterior a la final.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<SalidaListDTO> lista = ControlNavegacion.obtenerSalidasPorRango(inicio, fin);
        mostrarAgrupadas(lista);
    }
    
    private void mostrarAgrupadas(List<SalidaListDTO> lista) {
        modeloTabla.setRowCount(0);
        Map<LocalDate, List<SalidaListDTO>> agrupadas = lista.stream()
            .collect(Collectors.groupingBy(SalidaListDTO::getFecha));

        for (Map.Entry<LocalDate, List<SalidaListDTO>> entry : agrupadas.entrySet()) {
            LocalDate fecha = entry.getKey();
            int totalSalidas = entry.getValue().size();
            String idCualquiera = entry.getValue().get(0).getId();
            modeloTabla.addRow(new Object[]{idCualquiera, fecha.toString(), totalSalidas, "Detalles"});
        }

        tablaHistorial.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        tablaHistorial.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("Detalles");
            setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Detalles");
            button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            
            button.addActionListener(e -> {
                int row = table.getSelectedRow();
                String fechaTexto = (String) table.getModel().getValueAt(row, 1);
                try{
                    LocalDate fecha = LocalDate.parse(fechaTexto);
                    List<DetalleSalidaDTO> detalles = ControlNavegacion.obtenerDetallesPorFecha(fecha);
                    
                    if (!detalles.isEmpty()) {
                    PantallaDetalleSalida.setDetalles(detalles);
                    new PantallaDetalleSalida().setVisible(true);
                    PantallaHistorialSalidas.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontraron detalles para la fecha.", "Sin datos", JOptionPane.INFORMATION_MESSAGE);
                }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al obtener detalles: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                     int row, int column) {
            this.table = table;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "Detalles";
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaHistorialSalidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaHistorialSalidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaHistorialSalidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaHistorialSalidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaHistorialSalidas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
