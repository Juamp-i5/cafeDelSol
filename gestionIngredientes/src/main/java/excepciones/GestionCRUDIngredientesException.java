package excepciones;

/**
 * Excepción para errores en la gestión CRUD de ingredientes.
 * @author norma
 */
public class GestionCRUDIngredientesException extends Exception {

    /**
     * Constructor con mensaje.
     * @param message mensaje.
     */
    public GestionCRUDIngredientesException(String message) {
        super(message);
    }

    /**
     * Constructor con mensaje y causa.
     * @param message mensaje.
     * @param cause causa.
     */
    public GestionCRUDIngredientesException(String message, Throwable cause) {
        super(message, cause);
    }
}
