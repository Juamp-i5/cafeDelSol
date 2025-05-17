/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import DTOs.ProductoMostrarDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
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
    private static final String RUTA_RELATIVA_IMAGEN_POR_DEFECTO = "../img/image-not-found.png";

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

        cargarImagenProducto(producto.getImagenData());
        cargarNombreProducto(producto.getNombre());
    }

    /**
     * Carga la imagen del producto en el panel, con un tamaño fijo.
     *
     * @param imagenData el arreglo de bytes de la imagen del producto.
     */
    private void cargarImagenProducto(byte[] datosImagenProducto) {
        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon iconoFinal = null;

        if (datosImagenProducto != null && datosImagenProducto.length > 0) {
            try {
                ImageIcon icono = new ImageIcon(datosImagenProducto);
                if (icono.getImage() != null && icono.getIconWidth() > 0 && icono.getIconHeight() > 0) {
                    Image img = icono.getImage().getScaledInstance(ANCHO_IMAGEN_PRODUCTO, ALTO_IMAGEN_PRODUCTO, Image.SCALE_SMOOTH);
                    iconoFinal = new ImageIcon(img);
                } else {
                    System.err.println("Advertencia: Los datos del arreglo de bytes no forman una imagen válida. Se intentará cargar la imagen por defecto.");
                }
            } catch (Exception e) {
                System.err.println("Error al crear ImageIcon desde el arreglo de bytes: " + e.getMessage() + ". Se intentará cargar la imagen por defecto.");
            }
        }

        if (iconoFinal == null) { // Si no se pudo cargar la imagen principal
            System.out.println("Cargando imagen por defecto desde: " + RUTA_RELATIVA_IMAGEN_POR_DEFECTO);
            try {
                File archivoImagenPorDefecto = new File(RUTA_RELATIVA_IMAGEN_POR_DEFECTO);
                if (archivoImagenPorDefecto.exists() && archivoImagenPorDefecto.isFile()) {
                    ImageIcon iconoPorDefecto = new ImageIcon(archivoImagenPorDefecto.getAbsolutePath());
                    if (iconoPorDefecto.getImage() != null && iconoPorDefecto.getIconWidth() > 0 && iconoPorDefecto.getIconHeight() > 0) {
                        Image imgPorDefecto = iconoPorDefecto.getImage().getScaledInstance(ANCHO_IMAGEN_PRODUCTO, ALTO_IMAGEN_PRODUCTO, Image.SCALE_SMOOTH);
                        iconoFinal = new ImageIcon(imgPorDefecto);
                    } else {
                        lblImagen.setText("<html>Default img<br>inválida</html>");
                        System.err.println("Error: El archivo de imagen por defecto es inválido o no se pudo cargar: " + archivoImagenPorDefecto.getAbsolutePath());
                    }
                } else {
                    lblImagen.setText("<html>Default img<br>no encontrada</html>");
                    System.err.println("Error: No se encontró el archivo de imagen por defecto en la ruta: " + archivoImagenPorDefecto.getAbsolutePath()
                            + ". Asegúrate de que la aplicación se ejecuta desde el directorio correcto (ej. tuProyecto/) y la ruta relativa es correcta.");
                }
            } catch (Exception e) {
                lblImagen.setText("<html>Error al<br>cargar default</html>");
                System.err.println("Error al cargar la imagen por defecto desde el sistema de archivos: " + e.getMessage());
            }
        }

        lblImagen.setIcon(iconoFinal);

        if (this.getLayout() instanceof BorderLayout) {
            Component oldCenterComponent = ((BorderLayout) this.getLayout()).getLayoutComponent(BorderLayout.CENTER);
            if (oldCenterComponent != null) {
                this.remove(oldCenterComponent);
            }
        }

        this.add(lblImagen, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
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
