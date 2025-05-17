/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author rodri
 */
public class PersistenciaCubiculoEsception extends Exception {

    public PersistenciaCubiculoEsception() {
    }
    
    public PersistenciaCubiculoEsception(String message) {
        super(message);
    }

    public PersistenciaCubiculoEsception(String message, Throwable cause) {
        super(message, cause);
    }
    
}
