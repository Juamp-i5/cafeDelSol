/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas.cubiculos;

import pantallas.*;
import DTOs.TarjetaDTO;
import control.ControlNavegacion;
import java.awt.*;
import javax.swing.*;

/**
 * Clase que representa la pantalla para realizar pagos con tarjeta. 
 *
 * @author pablo
 */
public class PagoTarjetaCubiculos extends javax.swing.JFrame {

    private TarjetaDTO tarjeta;

    final int CANTIDAD_FILAS = 4;
    final int CANTIDAD_COLUMNAS = 2;
    final int ESPACIO_ENTRE_BOTONES_HORIZONTAL = 10;
    final int ESPACIO_ENTRE_BOTONES_VERTICAL = 10;
    final int MARGEN_PANEL_ARRIBA = 20;
    final int MARGEN_PANEL_IZQUIERDA = 40;
    final int MARGEN_PANEL_ABAJO = 20;
    final int MARGEN_PANEL_DERECHA = 20;
    final int DIMENSION_BOTON_X = 200;
    final int DIMENSION_BOTON_Y = 80;
    final int TAMANIO_TEXTO = 24;
    final int TAMANIO_BTN_ATRAS = 48;

    private JTextField txtNumero;
    private JTextField txtBanco;
    private JTextField txtFecha;
    private JPasswordField txtCVV;

    private JPanel panelPrincipal;
    private JPanel panelFormulario;
    private JPanel panelInferior;

    /**
     * Constructor de la clase PagoTarjeta. Configura la ventana y sus
     * componentes.
     */
    public PagoTarjetaCubiculos() {
        setTitle("Pago con Tarjeta");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    /**
     * Inicializa los componentes de la interfaz gráfica.
     */
    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.add(PanelFormulario(), BorderLayout.CENTER);
        panelPrincipal.add(PanelInferior(), BorderLayout.SOUTH);
        setContentPane(panelPrincipal);
    }

    /**
     * Crea y configura el panel del formulario.
     *
     * @return JPanel con los campos de entrada.
     */
    private JPanel PanelFormulario() {
        panelFormulario = new JPanel(new GridLayout(CANTIDAD_FILAS, CANTIDAD_COLUMNAS, ESPACIO_ENTRE_BOTONES_HORIZONTAL, ESPACIO_ENTRE_BOTONES_VERTICAL));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(MARGEN_PANEL_ARRIBA, MARGEN_PANEL_IZQUIERDA, MARGEN_PANEL_ABAJO, MARGEN_PANEL_DERECHA));

        txtNumero = new JTextField();
        txtBanco = new JTextField();
        txtFecha = new JTextField();
        txtCVV = new JPasswordField();

        agregarCampoFormulario("16 dígitos de la tarjeta", txtNumero);
        agregarCampoFormulario("Nombre de banco", txtBanco);
        agregarCampoFormulario("Fecha exp", txtFecha);
        agregarCampoFormulario("CVV", txtCVV);

        return panelFormulario;
    }

    /**
     * Crea y configura el panel inferior con los botones de acción.
     *
     * @return JPanel con los botones.
     */
    private JPanel PanelInferior() {
        panelInferior = new JPanel(new BorderLayout());
        JButton btnAtras = configurarBotonAtras();
        JButton btnConfirmar = configurarBotonConfirmar();
        panelInferior.add(btnAtras, BorderLayout.WEST);
        panelInferior.add(btnConfirmar, BorderLayout.EAST);
        return panelInferior;
    }

    /**
     * Configura el botón de retroceso.
     *
     * @return JButton con la configuración de "Atrás".
     */
    private JButton configurarBotonAtras() {
        JButton btnAtras = new JButton("<--");
        btnAtras.setBackground(Color.YELLOW);
        btnAtras.setPreferredSize(new Dimension(DIMENSION_BOTON_X, DIMENSION_BOTON_Y));
        btnAtras.setFont(new Font("Segoe UI", Font.PLAIN, TAMANIO_BTN_ATRAS));
        btnAtras.addActionListener(e -> BtnAtrasSeleccionado());
        return btnAtras;
    }

    /**
     * Configura el botón de confirmación de pago.
     *
     * @return JButton con la configuración de "Confirmar".
     */
    private JButton configurarBotonConfirmar() {
        JButton btnConfirmar = new JButton("Confirmar pago");
        btnConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, TAMANIO_TEXTO));
        btnConfirmar.addActionListener(e -> BtnConfirmarSeleccionado());
        return btnConfirmar;
    }

    /**
     * Agrega un campo de formulario con su etiqueta correspondiente.
     *
     * @param etiqueta Texto de la etiqueta.
     * @param campo Campo de entrada de texto.
     */
    private void agregarCampoFormulario(String etiqueta, JTextField campo) {
        JLabel label = new JLabel(etiqueta);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        panelFormulario.add(label);
        panelFormulario.add(campo);
    }

    /**
     * Maneja la acción de retroceder a la pantalla anterior.
     */
    private void BtnAtrasSeleccionado() {
        ControlNavegacion.volverPantallaAnterior();
        ControlNavegacion.setReservacionNueva(null);
        dispose();
    }

    /**
     * Maneja la acción de confirmar el pago.
     */
    private void BtnConfirmarSeleccionado() {
        validarYProcesarPago();
    }

    /**
     * Valida los datos ingresados y procesa el pago.
     */
    private void validarYProcesarPago() {
        TarjetaDTO tarjetaIngresada = new TarjetaDTO(
                txtNumero.getText(),
                txtBanco.getText(),
                txtFecha.getText(),
                new String(txtCVV.getPassword())
        );
        try {
            if (ControlNavegacion.validarTarjetaPresentacion(tarjetaIngresada)) {
                ControlNavegacion.mostrarPantallaReservacionExitosa();
                ControlNavegacion.realizarReservacion();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Datos de la tarjeta inválidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Ocurrió un error al procesar la tarjeta: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
