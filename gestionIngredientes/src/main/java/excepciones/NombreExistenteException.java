/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author norma
 */
public class NombreExistenteException extends GestionCRUDIngredientesException {

    public NombreExistenteException() {
        super("Ya existe un ingrediente ese nombre");
    }

    public NombreExistenteException(String message) {
        super(message);
    }
}
