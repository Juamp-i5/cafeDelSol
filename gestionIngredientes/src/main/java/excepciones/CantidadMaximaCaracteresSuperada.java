/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author norma
 */
public class CantidadMaximaCaracteresSuperada extends GestionCRUDIngredientesException{

    public CantidadMaximaCaracteresSuperada() {
        super("El nombre del ingrediente no puede tener más de 15 carácteres");
    }

    public CantidadMaximaCaracteresSuperada(String message) {
        super(message);
    }
  
}
