/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import DTOs.SaboresMostrarDTO;
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
 * Clase que representa un panel personalizado para mostrar un sabor de producto
 * con su imagen y nombre. Este panel contiene una imagen del sabor, ajustada a
 * un tamaño específico, y el nombre del sabor debajo de la imagen. La imagen se
 * carga a partir de una URL proporcionada en el objeto SaboresMostrarDTO.
 *
 * @author pablo
 */
public class PanelSabor extends JPanel {

    private final int ANCHO_PANEL = 250;
    private final int ALTO_PANEL = 300;
    private final int ANCHO_IMAGEN = 150;
    private final int ALTO_IMAGEN = 150;
    private final Border BORDE_PANEL = BorderFactory.createLineBorder(Color.GRAY, 1);

    /**
     * Constructor de la clase PanelSabor. Inicializa el panel con una imagen y
     * un nombre de sabor.
     *
     * @param sabor El objeto SaboresMostrarDTO que contiene los datos del
     * sabor, como la URL de la imagen y el nombre.
     */
    public PanelSabor(SaboresMostrarDTO sabor) {
        this.setLayout(new BorderLayout());
        this.setBorder(BORDE_PANEL);
        this.setPreferredSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));

        cargarImagenSabor(sabor.getUrlImagen());
        cargarNombreSabor(sabor.getNombre());
    }

    /**
     * Carga la imagen del sabor en el panel, con un tamaño fijo.
     *
     * @param urlImagen La URL de la imagen del sabor.
     */
    private void cargarImagenSabor(String urlImagen) {
        JLabel lblImagen = new JLabel();
        ImageIcon icono = new ImageIcon(urlImagen);
        Image img = icono.getImage().getScaledInstance(ANCHO_IMAGEN, ALTO_IMAGEN, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(img));
        lblImagen.setHorizontalAlignment(JLabel.CENTER);

        this.add(lblImagen, BorderLayout.CENTER);
    }

    /**
     * Carga y muestra el nombre del sabor en la parte inferior del panel.
     *
     * @param nombre El nombre del sabor a mostrar.
     */
    private void cargarNombreSabor(String nombre) {
        JLabel lblNombre = new JLabel(nombre, SwingConstants.CENTER);
        this.add(lblNombre, BorderLayout.SOUTH);
    }
}
