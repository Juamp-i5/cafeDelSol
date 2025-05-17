/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import DTOs.TamanioMostrarDTO;
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
 * Clase que representa un panel personalizado para mostrar un tamaño de
 * producto con su imagen y nombre. Este panel contiene una imagen del tamaño
 * del producto, ajustada a un tamaño específico, y el nombre del tamaño debajo
 * de la imagen. La imagen se carga a partir de una URL proporcionada en el
 * objeto TamanioMostrarDTO.
 *
 * @author pablo
 */
public class PanelTamanio extends JPanel {

    private final int ANCHO_PANEL = 250;
    private final int ALTO_PANEL = 300;
    private final int ANCHO_IMAGEN = 250;
    private final int ALTO_IMAGEN = 250;
    private final Border BORDE_PANEL = BorderFactory.createLineBorder(Color.GRAY, 1);
    private static final String RUTA_RELATIVA_IMAGEN_POR_DEFECTO_TAMANIO = "../img/image-not-found.png";

    /**
     * Constructor de la clase PanelTamanio. Inicializa el panel con una imagen
     * y un nombre de tamaño.
     *
     * @param tamanio El objeto TamanioMostrarDTO que contiene los datos del
     * tamaño, como los bytes de la imagen y el nombre.
     */
    public PanelTamanio(TamanioMostrarDTO tamanio) {
        this.setLayout(new BorderLayout());
        this.setBorder(BORDE_PANEL);
        this.setPreferredSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));

        cargarImagenTamanio(tamanio.getImagenData());
        cargarNombreTamanio(tamanio.getNombre());
    }

    /**
     * Carga la imagen del tamaño en el panel, con un tamaño fijo.
     *
     * @param datosImagenTamanio Los bytes la imagen del tamaño.
     */
    private void cargarImagenTamanio(byte[] datosImagenTamanio) {
        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon iconoFinal = null;

        if (datosImagenTamanio != null && datosImagenTamanio.length > 0) {
            try {
                ImageIcon icono = new ImageIcon(datosImagenTamanio);
                // Verificar si la imagen se cargó correctamente desde los bytes
                if (icono.getImage() != null && icono.getIconWidth() > 0 && icono.getIconHeight() > 0) {
                    Image img = icono.getImage().getScaledInstance(ANCHO_IMAGEN, ALTO_IMAGEN, Image.SCALE_SMOOTH);
                    iconoFinal = new ImageIcon(img);
                } else {
                    System.err.println("Advertencia: Los datos del arreglo de bytes para el tamaño no forman una imagen válida. Se intentará cargar la imagen por defecto.");
                }
            } catch (Exception e) {
                System.err.println("Error al crear ImageIcon para el tamaño desde el arreglo de bytes: " + e.getMessage() + ". Se intentará cargar la imagen por defecto.");
            }
        }

        // Si no se pudo cargar la imagen principal (datos nulos, vacíos, o error al procesar bytes)
        if (iconoFinal == null) {
            System.out.println("Cargando imagen por defecto para tamaño desde: " + RUTA_RELATIVA_IMAGEN_POR_DEFECTO_TAMANIO);
            try {
                File archivoImagenPorDefecto = new File(RUTA_RELATIVA_IMAGEN_POR_DEFECTO_TAMANIO);
                if (archivoImagenPorDefecto.exists() && archivoImagenPorDefecto.isFile()) {
                    ImageIcon iconoPorDefecto = new ImageIcon(archivoImagenPorDefecto.getAbsolutePath());
                    // Verificar que la imagen por defecto se cargó correctamente
                    if (iconoPorDefecto.getImage() != null && iconoPorDefecto.getIconWidth() > 0 && iconoPorDefecto.getIconHeight() > 0) {
                        Image imgPorDefecto = iconoPorDefecto.getImage().getScaledInstance(ANCHO_IMAGEN, ALTO_IMAGEN, Image.SCALE_SMOOTH);
                        iconoFinal = new ImageIcon(imgPorDefecto);
                    } else {
                        lblImagen.setText("<html>Default img<br>inválida</html>");
                        System.err.println("Error: El archivo de imagen por defecto (tamaño) es inválido o no se pudo cargar: " + archivoImagenPorDefecto.getAbsolutePath());
                    }
                } else {
                    lblImagen.setText("<html>Default img<br>no encontrada</html>");
                    System.err.println("Error: No se encontró el archivo de imagen por defecto (tamaño) en la ruta: " + archivoImagenPorDefecto.getAbsolutePath()
                            + ". Asegúrate de que la aplicación se ejecuta desde el directorio correcto y la ruta relativa es correcta.");
                }
            } catch (Exception e) {
                lblImagen.setText("<html>Error al<br>cargar default</html>");
                System.err.println("Error al cargar la imagen por defecto (tamaño) desde el sistema de archivos: " + e.getMessage());
                // e.printStackTrace(); // Considera mostrar el stack trace durante la depuración
            }
        }

        lblImagen.setIcon(iconoFinal); // Establecer el icono final (principal o por defecto)

        // Lógica para añadir/actualizar el JLabel en el contenedor
        // Esto asume que 'this' es un contenedor que usa BorderLayout.
        if (this.getLayout() instanceof BorderLayout) {
            Component oldCenterComponent = ((BorderLayout) this.getLayout()).getLayoutComponent(BorderLayout.CENTER);
            if (oldCenterComponent != null) {
                this.remove(oldCenterComponent);
            }
        }

        this.add(lblImagen, BorderLayout.CENTER);
        this.revalidate(); // Actualizar el layout del contenedor
        this.repaint();    // Repintar el contenedor
    }

    /**
     * Carga y muestra el nombre del tamaño en la parte inferior del panel.
     *
     * @param nombre El nombre del tamaño a mostrar.
     */
    private void cargarNombreTamanio(String nombre) {
        JLabel lblNombre = new JLabel(nombre, SwingConstants.CENTER);
        this.add(lblNombre, BorderLayout.SOUTH);
    }
}
