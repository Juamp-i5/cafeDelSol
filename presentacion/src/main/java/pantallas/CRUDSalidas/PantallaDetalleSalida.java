/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas.CRUDSalidas;

import DTOs.CRUDSalidas.DetalleSalidaDTO;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import control.ControlNavegacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author katia
 */
public class PantallaDetalleSalida extends javax.swing.JFrame {

    private static List<DetalleSalidaDTO> detalles;
    
    private JLabel lblTitulo, lblFecha;
    private JTextField txtFecha;
    private JTable tablaDetalles;
    private DefaultTableModel modeloTabla;
    private JButton btnRegresar, btnGenerarPDF;

    
    /**
     * Creates new form PantallaDetalleSalida
     */
    public PantallaDetalleSalida() {    
        configurarVentana();
        initComponents2();
    }
    
    public static void setDetalles(List<DetalleSalidaDTO> listaDetalles) {
        detalles = listaDetalles;
    }
    
    private void configurarVentana() {
        setTitle("Detalles de Salidas");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(235, 235, 235));
    }
    
    private void generarPDF(){
        Document document = new Document();
        try {
            String fecha = detalles.get(0).getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
            String nombreArchivo = "salidas_" + fecha + "_" + timestamp + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
            document.open();

            com.itextpdf.text.Font fontTitulo = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font fontNormal = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12);

            document.add(new Paragraph("Reporte de Salidas", fontTitulo));
            document.add(new Paragraph("Fecha: " + fecha, fontNormal));
            document.add(Chunk.NEWLINE);

            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);
            tabla.addCell("Cantidad");
            tabla.addCell("Ingrediente");
            tabla.addCell("Motivo");

            for (DetalleSalidaDTO dto : detalles) {
                tabla.addCell(String.valueOf(dto.getCantidad()));
                tabla.addCell(dto.getNombreIngrediente());
                tabla.addCell(dto.getMotivo().toString());
            }

            document.add(tabla);
            document.close();

            JOptionPane.showMessageDialog(this, "PDF generado exitosamente:\n" + nombreArchivo);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void initComponents2() {
        if (detalles == null || detalles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se ha proporcionado información para mostrar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(235, 235, 235));

        lblTitulo = new JLabel("Detalles");
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        panelTitulo.add(lblTitulo, BorderLayout.WEST);

        JPanel panelFecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelFecha.setBackground(new Color(235, 235, 235));
        lblFecha = new JLabel("Fecha:");
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        txtFecha = new JTextField(10);
        txtFecha.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        txtFecha.setText(detalles.get(0).getFecha().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        txtFecha.setEditable(false);

        panelFecha.add(lblFecha);
        panelFecha.add(txtFecha);
        panelTitulo.add(panelFecha, BorderLayout.EAST);

        getContentPane().add(panelTitulo, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new Object[]{"Cantidad", "Ingrediente", "Motivo"}, 0);
        tablaDetalles = new JTable(modeloTabla);
        tablaDetalles.setRowHeight(40);
        tablaDetalles.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tablaDetalles.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));

        JScrollPane scrollTabla = new JScrollPane(tablaDetalles);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        getContentPane().add(scrollTabla, BorderLayout.CENTER);

        for (DetalleSalidaDTO dto : detalles) {
            modeloTabla.addRow(new Object[]{
                dto.getCantidad(),
                dto.getNombreIngrediente(),
                dto.getMotivo().toString()
            });
        }

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(235, 235, 235));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnRegresar = new JButton("<---");
        btnRegresar.setPreferredSize(new Dimension(200, 70));
        btnRegresar.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        btnRegresar.setBackground(Color.YELLOW);
        btnRegresar.addActionListener(e -> {
            this.dispose();
            ControlNavegacion.volverPantallaAnterior();
        });

        btnGenerarPDF = new JButton("Generar PDF");
        btnGenerarPDF.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnGenerarPDF.setPreferredSize(new Dimension(160, 40));
        btnGenerarPDF.addActionListener(e -> {
            generarPDF();
        });

        panelInferior.add(btnRegresar, BorderLayout.WEST);
        panelInferior.add(btnGenerarPDF, BorderLayout.EAST);
        getContentPane().add(panelInferior, BorderLayout.SOUTH);

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
            java.util.logging.Logger.getLogger(PantallaDetalleSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaDetalleSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaDetalleSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaDetalleSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaDetalleSalida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
