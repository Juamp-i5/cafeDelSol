/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas.CRUDProductos;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jp
 */
public class JOptionPanePersonalizado {

    public static Double solicitarDoublePositivo(String mensaje, Double valorInicial) {
        JTextField inputField = new JTextField(10);
        inputField.setDocument(new PositiveDoubleDocument());
        inputField.setText(valorInicial != null ? valorInicial.toString() : "");

        int result = JOptionPane.showConfirmDialog(
                null,
                new Object[]{mensaje, inputField},
                "Entrada requerida",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String texto = inputField.getText().trim();
            if (!texto.isEmpty()) {
                try {
                    return Double.parseDouble(texto);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Número inválido.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Campo vacío.");
            }
        }

        return null;
    }
}
