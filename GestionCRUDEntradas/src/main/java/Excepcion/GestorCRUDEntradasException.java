package Excepcion;

/**
 *
 * @author pablo
 */
public class GestorCRUDEntradasException extends Exception {

    public GestorCRUDEntradasException() {
    }

    public GestorCRUDEntradasException(String message) {
        super(message);
    }

    public GestorCRUDEntradasException(String message, Throwable cause) {
        super(message, cause);
    }
}
