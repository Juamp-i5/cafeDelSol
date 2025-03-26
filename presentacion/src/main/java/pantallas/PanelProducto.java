/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import DTOs.ProductoMostrarDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author rodri
 */
public class PanelProducto extends JPanel {
   private final int ANCHO_PANEL_PRODUCTO = 150;
    private final int ALTO_PANEL_PRODUCTO = 150;
    private final int ANCHO_IMAGEN_PRODUCTO = 160;
    private final int ALTO_IMAGEN_PRODUCTO = 100;
    private final Border BORDE_PANEL_PRODUCTO = BorderFactory.createLineBorder(Color.GRAY, 1);
    
    public PanelProducto(ProductoMostrarDTO producto) {
        this.setLayout(new BorderLayout());
        this.setBorder(BORDE_PANEL_PRODUCTO);
        this.setPreferredSize(new Dimension(ANCHO_PANEL_PRODUCTO, ALTO_PANEL_PRODUCTO));

        cargarImagenProducto(producto.getUrlImagen());
        cargarNombreProducto(producto.getNombre());
    }

    private void cargarImagenProducto(String urlImagenProducto) {
        JLabel lblImagen = new JLabel();
        ImageIcon icono = new ImageIcon(urlImagenProducto);
        Image img = icono.getImage().getScaledInstance(ANCHO_IMAGEN_PRODUCTO, ALTO_IMAGEN_PRODUCTO, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(img));
        lblImagen.setHorizontalAlignment(JLabel.CENTER);

        this.add(lblImagen, BorderLayout.CENTER);
    }

    private void cargarNombreProducto(String nombreProducto) {
        JLabel lblNombre = new JLabel(nombreProducto, SwingConstants.CENTER);

        this.add(lblNombre, BorderLayout.SOUTH);
    }
}
