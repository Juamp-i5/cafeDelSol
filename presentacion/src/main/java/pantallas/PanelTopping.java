/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import DTOs.ToppingsMostrarDTO;
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
 * Clase que representa un panel personalizado para mostrar un topping de
 * producto con su imagen y nombre. Este panel contiene una imagen del topping,
 * ajustada a un tamaño específico, y el nombre del topping debajo de la imagen.
 * La imagen se carga a partir de una URL proporcionada en el objeto
 * ToppingsMostrarDTO.
 *
 * @author pablo
 */
public class PanelTopping extends JPanel {

    private final int ANCHO_PANEL = 250;
    private final int ALTO_PANEL = 300;
    private final int ANCHO_IMAGEN = 250;
    private final int ALTO_IMAGEN = 250;
    private final Border BORDE_PANEL = BorderFactory.createLineBorder(Color.GRAY, 1);

    /**
     * Constructor de la clase PanelTopping. Inicializa el panel con una imagen
     * y un nombre de topping.
     *
     * @param topping El objeto ToppingsMostrarDTO que contiene los datos del
     * topping, como la URL de la imagen y el nombre.
     */
    public PanelTopping(ToppingsMostrarDTO topping) {
        this.setLayout(new BorderLayout());
        this.setBorder(BORDE_PANEL);
        this.setPreferredSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));

        cargarImagenTopping(topping.getUrlImagen());
        cargarNombreTopping(topping.getNombre());
    }

    /**
     * Carga la imagen del topping en el panel, con un tamaño fijo.
     *
     * @param urlImagen La URL de la imagen del topping.
     */
    private void cargarImagenTopping(String urlImagen) {
        JLabel lblImagen = new JLabel();
        ImageIcon icono = new ImageIcon(urlImagen);
        Image img = icono.getImage().getScaledInstance(ANCHO_IMAGEN, ALTO_IMAGEN, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(img));
        lblImagen.setHorizontalAlignment(JLabel.CENTER);

        this.add(lblImagen, BorderLayout.CENTER);
    }

    /**
     * Carga y muestra el nombre del topping en la parte inferior del panel.
     *
     * @param nombre El nombre del topping a mostrar.
     */
    private void cargarNombreTopping(String nombre) {
        JLabel lblNombre = new JLabel(nombre, SwingConstants.CENTER);
        this.add(lblNombre, BorderLayout.SOUTH);
    }
}
