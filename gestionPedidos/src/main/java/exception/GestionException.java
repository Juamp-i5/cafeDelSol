/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 * Clase que representa una excepción personalizada para la gestión de pedidos o
 * productos en el sistema.
 *
 * @author rodri
 */
public class GestionException extends Exception {

    /**
     * Constructor que crea una nueva excepción con un mensaje específico.
     * @param message El mensaje que describe el error.
     */
    public GestionException(String message) {
        super(message);
    }

    /**
     * Constructor que crea una nueva excepción con un mensaje específico y su causa.
     * @param message El mensaje que describe el error.
     * @param cause La causa de la excepción.
     */
    public GestionException(String message, Throwable cause) {
        super(message, cause);
    }

}
