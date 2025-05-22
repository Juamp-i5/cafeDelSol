package pantallas.CRUDEntradas;

import DTOs.CRUDEntradas.DetalleEntradaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import control.ControlNavegacion;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pablo
 */
public final class PantallaTablaDetallesEntrada extends javax.swing.JFrame {

    private final EntradaViejaDTO entrada;

    /**
     * Creates new form PantallaTablaDetallesEntrada
     *
     * @param entrada
     */
    public PantallaTablaDetallesEntrada(EntradaViejaDTO entrada) {
        this.entrada = entrada;
        initComponents();
        mostrarDetallesEnTabla();
    }

    /**
     *
     */
    public void mostrarDetallesEnTabla() {
        DefaultTableModel modelo = (DefaultTableModel) TablaDetallesEntrada.getModel();
        modelo.setRowCount(0);
        if (entrada != null && entrada.getDetallesEntrada() != null) {
            List<DetalleEntradaDTO> registros = entrada.getDetallesEntrada();
            if (registros != null) {
                for (DetalleEntradaDTO registro : registros) {
//                    List<IngredienteViejoListDTO> listaIngredientes = ControlNavegacion.buscarIngredientesPorFiltros(registro.getNombreIngrediente(), registro.getNivelStock().toString());
//                    IngredienteViejoListDTO ingrediente = listaIngredientes.getFirst();
                    modelo.addRow(new Object[]{
                        registro.getNombreIngrediente(),
                        null,
//                        ingrediente.getCantidadDisponible(),
                        null,
//                        ingrediente.getUnidadMedida(),
                        registro.getPrecioUnitario(),
                        registro.getPrecioTotal(),
                        null
//                        ingrediente.getNivelStock()
                    });
                }
            }
        }
    }

    class encabezadoReporte extends PdfPageEventHelper {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY);
        Font tituloFont = new Font(Font.FontFamily.HELVETICA, 16, Font.NORMAL, BaseColor.BLACK);
        @Override
        public void onEndPage(PdfWriter w, Document documento) {
            PdfContentByte pcb = w.getDirectContent();
            Phrase titulo = new Phrase("Reporte de Entrada", tituloFont);
            Phrase pagina = new Phrase("Pagina " + w.getPageNumber(), font);
            ColumnText.showTextAligned(pcb, Element.ALIGN_LEFT, titulo, documento.leftMargin(), documento.top() + 10, 0);
            ColumnText.showTextAligned(pcb, Element.ALIGN_RIGHT, pagina, documento.right() - documento.left(), documento.bottom() - 20, 0); 
        }
    }

    private void EstructuraPDF(File archivo) throws DocumentException, IOException {
        Document documento = new Document();
        PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(archivo));
        writer.setPageEvent(new encabezadoReporte());
        documento.open();
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph(" "));
        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);
        BaseColor colorEncabezado = new BaseColor(240, 240, 240);
        Font encabezadoFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Font cuerpoFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell celda;
        celda = new PdfPCell(new Phrase("Producto", encabezadoFont));
        celda.setBackgroundColor(colorEncabezado);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase("Stock actual", encabezadoFont));
        celda.setBackgroundColor(colorEncabezado);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase("Unidad de medida", encabezadoFont));
        celda.setBackgroundColor(colorEncabezado);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase("Precio unitario", encabezadoFont));
        celda.setBackgroundColor(colorEncabezado);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase("Subtotal", encabezadoFont));
        celda.setBackgroundColor(colorEncabezado);
        tabla.addCell(celda);
        if (entrada != null && entrada.getDetallesEntrada() != null) {
            for (DetalleEntradaDTO detalle : entrada.getDetallesEntrada()) {
//                List<IngredienteViejoListDTO> listaIngredientes = ControlNavegacion.buscarIngredientesPorFiltros(detalle.getNombreIngrediente(), detalle.getNivelStock().toString());
//                IngredienteViejoListDTO ingrediente = listaIngredientes.getFirst();
                tabla.addCell(new Phrase(detalle.getNombreIngrediente(), cuerpoFont));
                tabla.addCell(new Phrase(" ")); //Stock actual
                tabla.addCell(new Phrase(" ")); //Unidad de medida
                tabla.addCell(new Phrase(String.valueOf("$ " + detalle.getPrecioUnitario()), cuerpoFont));
                tabla.addCell(new Phrase(String.valueOf("$ " + detalle.getPrecioTotal()), cuerpoFont));
//                tabla.addCell(new Phrase(String.valueOf(ingrediente.getUnidadMedida()), cuerpoFont)); //Unidad de medida
//                tabla.addCell(new Phrase(String.valueOf(ingrediente.getCantidadDisponible()), cuerpoFont)); //Stock actual
            }
        }
        documento.add(tabla);
        documento.add(new Paragraph(" "));
        Paragraph total = new Paragraph("Precio total de entrada: $" + String.format("%.2f", entrada.getPrecioTotal()), cuerpoFont); 
        documento.add(total);
        Paragraph proveedor = new Paragraph("Proveedor: " + entrada.getProveedor(), cuerpoFont);
        documento.add(proveedor);
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        Paragraph fechaHora = new Paragraph("Fecha y hora de registro: " + entrada.getFechaHora().format(formatoFechaHora), cuerpoFont);
        documento.add(fechaHora);
        documento.close();
    }
    
    public void generarPDF(){
        String usuario = System.getProperty("user.home");
        File Descargas = new File(usuario, "Downloads");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String tiempo = LocalDateTime.now().format(formatter);
        File reporte = new File(Descargas, "ReporteDeEntrada_" + tiempo + ".pdf");
        try {
            EstructuraPDF(reporte);
            JOptionPane.showMessageDialog(this, "PDF generado exitosamente.");
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(reporte);
            }
        } catch (DocumentException | HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(this, "Error al generar PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        jSeparator1 = new javax.swing.JSeparator();
        BtnPDF = new javax.swing.JButton();
        LbTitulo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        ScrollPaneDetalleEntrada = new javax.swing.JScrollPane();
        TablaDetallesEntrada = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BtnVolverAtras.setText("<--");
        BtnVolverAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVolverAtrasActionPerformed(evt);
            }
        });

        BtnPDF.setText("Descargar PDF");
        BtnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPDFActionPerformed(evt);
            }
        });

        LbTitulo.setText("Detalles de entrada");

        TablaDetallesEntrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Stock actual", "Unidad de medida", "Precio unitario", "Precio total", "Estado"
            }
        ));
        ScrollPaneDetalleEntrada.setViewportView(TablaDetallesEntrada);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(LbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 463, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BtnVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(ScrollPaneDetalleEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 981, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(LbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPaneDetalleEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnPDF, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(BtnVolverAtras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        LbTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnVolverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVolverAtrasActionPerformed
        ControlNavegacion.mostrarPantallaHistorialEntradas();
        this.dispose();
    }//GEN-LAST:event_BtnVolverAtrasActionPerformed

    private void BtnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPDFActionPerformed
        generarPDF();
    }//GEN-LAST:event_BtnPDFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnPDF;
    private javax.swing.JButton BtnVolverAtras;
    private javax.swing.JLabel LbTitulo;
    private javax.swing.JScrollPane ScrollPaneDetalleEntrada;
    private javax.swing.JTable TablaDetallesEntrada;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
