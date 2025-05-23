/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas.CRUDSalidas;

import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDSalidas.SalidaNuevaDTO;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import control.ControlNavegacion;
import enums.MotivoEnum;
import excepciones.GestionCRUDIngredientesException;
import excepciones.GestionCRUDSalidasException;
import gestionIngredientes.GestorCRUDIngredientes;
import gestionSalidas.GestorCRUDSalidas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

/**
 *
 * @author katia
 */
public class PantallaRegistrarSalida extends javax.swing.JFrame {

    private JLabel lblTitulo, lblFecha, lblNombre, lblCantidad, lblMotivo;
    private JSeparator separador, separadorInferior;
    private JComboBox<String> comboIngredientes;
    private JTextField txtCantidad;
    private JComboBox<MotivoEnum> comboMotivo;
    private DatePicker datePicker;
    private JButton btnRegresar, btnConfirmar;

    public PantallaRegistrarSalida() {
        configurarVentana();
        initComponents2();
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
            .addGap(0, 999, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configurarVentana() {
        setTitle("Registro Salida");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void cargarIngredientes() {
        List<IngredienteViejoListDTO> ingredientes = ControlNavegacion.buscarIngredientesPorFiltros("", null);
        comboIngredientes.removeAllItems();
        for (IngredienteViejoListDTO ingrediente : ingredientes) {
            comboIngredientes.addItem(ingrediente.getNombre());
        }
    }

    
    private void confirmarSalida() {
        try {
            LocalDate fecha = datePicker.getDate();
            String nombreIngrediente = (String) comboIngredientes.getSelectedItem();
            String textoCantidad = txtCantidad.getText().trim();
            MotivoEnum motivo = (MotivoEnum) comboMotivo.getSelectedItem();

//            if (nombreIngrediente == null || nombreIngrediente.isBlank()) {
//                throw new GestionCRUDSalidasException("Debes seleccionar un ingrediente.");
//            }
//            
//            if (textoCantidad.isBlank()) {
//                throw new GestionCRUDSalidasException("Debes ingresar una cantidad.");
//            }
            
            Double cantidad = Double.parseDouble(textoCantidad);
            
            String idIngrediente = ControlNavegacion.obtenerIdIngredientePorNombre(nombreIngrediente);

            SalidaNuevaDTO salidaDTO = new SalidaNuevaDTO(fecha, idIngrediente, cantidad, motivo);

            boolean exito = ControlNavegacion.registrarSalida(salidaDTO);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Salida registrada correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar la salida.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void initComponents2() {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(235, 235, 235));

        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(235, 235, 235));
        lblTitulo = new JLabel("Registro Salida");
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        separador = new JSeparator();
        separador.setPreferredSize(new Dimension(1024, 2));

        panelTitulo.add(lblTitulo, BorderLayout.NORTH);
        panelTitulo.add(separador, BorderLayout.SOUTH);
        getContentPane().add(panelTitulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(210, 210, 210));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 30, 20, 30);
        gbc.anchor = GridBagConstraints.WEST;
        
        Font fuenteCampos = new Font("Segoe UI", Font.PLAIN, 24);
        Dimension dimensionCampos = new Dimension(300, 40);

        lblFecha = new JLabel("Fecha");
        lblNombre = new JLabel("Nombre");
        lblCantidad = new JLabel("Cantidad");
        lblMotivo = new JLabel("Motivo");

        lblFecha.setFont(fuenteCampos);
        lblNombre.setFont(fuenteCampos);
        lblCantidad.setFont(fuenteCampos);
        lblMotivo.setFont(fuenteCampos);
        
        DatePickerSettings settings = new DatePickerSettings();
        settings.setFormatForDatesCommonEra("dd/MM/yyyy");
        datePicker = new DatePicker(settings);
        datePicker.setDate(LocalDate.now());
        datePicker.getSettings().setDateRangeLimits(LocalDate.now().minusDays(2), LocalDate.now());

        comboIngredientes = new JComboBox<>();
        txtCantidad = new JTextField(15);
        comboMotivo = new JComboBox<>(MotivoEnum.values());
        cargarIngredientes();

        comboIngredientes.setFont(fuenteCampos);
        txtCantidad.setFont(fuenteCampos);
        comboMotivo.setFont(fuenteCampos);
    
        comboIngredientes.setPreferredSize(dimensionCampos);
        txtCantidad.setPreferredSize(dimensionCampos);
        comboMotivo.setPreferredSize(dimensionCampos);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCentral.add(lblFecha, gbc);
        gbc.gridx = 1;
        panelCentral.add(datePicker, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCentral.add(lblNombre, gbc);
        gbc.gridx = 1;
        panelCentral.add(comboIngredientes, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCentral.add(lblCantidad, gbc);
        gbc.gridx = 1;
        panelCentral.add(txtCantidad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelCentral.add(lblMotivo, gbc);
        gbc.gridx = 1;
        panelCentral.add(comboMotivo, gbc);

        getContentPane().add(panelCentral, BorderLayout.CENTER);
        
        JPanel panelInferiorTotal = new JPanel();
        panelInferiorTotal.setLayout(new BorderLayout());

        separadorInferior = new JSeparator();
        separadorInferior.setPreferredSize(new Dimension(1024, 2));
        panelInferiorTotal.add(separadorInferior, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new BorderLayout());
        panelBotones.setBackground(new Color(210, 210, 210));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnRegresar = new JButton("<---");
        btnRegresar.setPreferredSize(new Dimension(200, 70));
        btnRegresar.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        btnRegresar.setBackground(Color.YELLOW);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setPreferredSize(new Dimension(200, 70));
        btnConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        btnConfirmar.setBackground(Color.GREEN);

        panelBotones.add(btnRegresar, BorderLayout.WEST);
        panelBotones.add(btnConfirmar, BorderLayout.EAST);

        panelInferiorTotal.add(panelBotones, BorderLayout.SOUTH);

        
        getContentPane().add(panelInferiorTotal, BorderLayout.SOUTH);

        
        btnConfirmar.addActionListener(e -> confirmarSalida());
        
        btnRegresar.addActionListener(e -> {
            this.dispose();
            ControlNavegacion.volverPantallaAnterior();
        });
    }
    
    
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PantallaRegistrarSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PantallaRegistrarSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PantallaRegistrarSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PantallaRegistrarSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PantallaRegistrarSalida().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
