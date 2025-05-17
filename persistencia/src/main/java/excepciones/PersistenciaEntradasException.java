package excepciones;

/**
 *
 * @author pablo
 */
public class PersistenciaEntradasException extends Exception{

    public PersistenciaEntradasException() {
    }

    public PersistenciaEntradasException(String message) {
        super(message);
    }
    
    public PersistenciaEntradasException(String message, Throwable cause) {
        super(message, cause);
    } 
}
