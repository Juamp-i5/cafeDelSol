package excepciones;

/**
 * Excepción para errores en la persistencia general.
 *
 * @author rodri
 */
public class PersistenciaException extends Exception {

    /**
     * Constructor por defecto.
     */
    public PersistenciaException() {
    }

    /**
     * Constructor con mensaje.
     *
     * @param message mensaje.
     */
    public PersistenciaException(String message) {
        super(message);
    }

    /**
     * Constructor con mensaje y causa.
     *
     * @param message mensaje.
     * @param cause causa.
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }

}
