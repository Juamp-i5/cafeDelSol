package excepciones;

/**
 * Excepción para errores en la persistncia de ingredientes
 * @author norma
 */
public class PersistenciaIngredientesException extends Exception {

    /**
     * Constructor por defecto.
     */
    public PersistenciaIngredientesException() {
    }

    /**
     * Constructor con mensaje.
     * @param message mensaje.
     */
    public PersistenciaIngredientesException(String message) {
        super(message);
    }

    /**
     * Constructor con mensaje y causa.
     * @param message mensaje.
     * @param cause causa.
     */
    public PersistenciaIngredientesException(String message, Throwable cause) {
        super(message, cause);
    }

}