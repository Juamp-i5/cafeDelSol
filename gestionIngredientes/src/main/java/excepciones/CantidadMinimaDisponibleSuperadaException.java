/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author norma
 */
public class CantidadMinimaDisponibleSuperadaException extends GestionCRUDIngredientesException {

    public CantidadMinimaDisponibleSuperadaException() {
        super("No se puede agregar un ingrediente con una cantidad disponible tan baja");
    }
   
    public CantidadMinimaDisponibleSuperadaException(String message) {
        super(message);
    }
    
}
