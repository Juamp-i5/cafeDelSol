/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author katia
 */
public class PersistenciaSalidasException extends Exception{

    public PersistenciaSalidasException() {
    }

    public PersistenciaSalidasException(String message) {
        super(message);
    }

    public PersistenciaSalidasException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
