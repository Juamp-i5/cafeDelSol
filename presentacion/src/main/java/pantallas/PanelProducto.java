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
 * Clase que representa un panel personalizado para mostrar un producto con su
 * imagen y nombre. Este panel contiene una imagen del producto, ajustada a un
 * tamaño específico, y el nombre del producto debajo de la imagen. La imagen se
 * carga a partir de una URL proporcionada en el objeto ProductoMostrarDTO.
 *
 * @author rodri
 */
public class PanelProducto extends JPanel {

    private final int ANCHO_PANEL_PRODUCTO = 150;
    private final int ALTO_PANEL_PRODUCTO = 150;
    private final int ANCHO_IMAGEN_PRODUCTO = 160;
    private final int ALTO_IMAGEN_PRODUCTO = 100;
    private final Border BORDE_PANEL_PRODUCTO = BorderFactory.createLineBorder(Color.GRAY, 1);

    /**
     * Constructor de la clase PanelProducto. Inicializa el panel con una imagen
     * y un nombre de producto.
     *
     * @param producto El objeto ProductoMostrarDTO que contiene los datos del
     * producto, como la URL de la imagen y el nombre.
     */
    public PanelProducto(ProductoMostrarDTO producto) {
        this.setLayout(new BorderLayout());
        this.setBorder(BORDE_PANEL_PRODUCTO);
        this.setPreferredSize(new Dimension(ANCHO_PANEL_PRODUCTO, ALTO_PANEL_PRODUCTO));

        cargarImagenProducto(producto.getUrlImagen());
        cargarNombreProducto(producto.getNombre());
    }

    /**
     * Carga la imagen del producto en el panel, con un tamaño fijo.
     *
     * @param urlImagenProducto La URL de la imagen del producto.
     */
    private void cargarImagenProducto(String urlImagenProducto) {
        JLabel lblImagen = new JLabel();
        ImageIcon icono = new ImageIcon(urlImagenProducto);
        Image img = icono.getImage().getScaledInstance(ANCHO_IMAGEN_PRODUCTO, ALTO_IMAGEN_PRODUCTO, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(img));
        lblImagen.setHorizontalAlignment(JLabel.CENTER);

        this.add(lblImagen, BorderLayout.CENTER);
    }

    /**
     * Carga y muestra el nombre del producto en la parte inferior del panel.
     *
     * @param nombreProducto El nombre del producto a mostrar.
     */
    private void cargarNombreProducto(String nombreProducto) {
        JLabel lblNombre = new JLabel(nombreProducto, SwingConstants.CENTER);

        this.add(lblNombre, BorderLayout.SOUTH);
    }
}
