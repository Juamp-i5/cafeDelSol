/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas.CRUDProductos;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Jp
 */
public class PositiveDoubleDocument extends PlainDocument {

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null || str.contains(" ")) {
            return;
        }

        StringBuilder currentText = new StringBuilder(getText(0, getLength()));
        currentText.insert(offset, str);

        if (isValidDouble(currentText.toString())) {
            super.insertString(offset, str, attr);
        }
    }

    private boolean isValidDouble(String text) {
        if (text.isEmpty() || text.equals(".")) {
            return true;
        }

        if (!text.matches("\\d*\\.?\\d*")) {
            return false;
        }

        try {
            Double.valueOf(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
