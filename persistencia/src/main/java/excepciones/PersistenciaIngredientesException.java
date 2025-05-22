package excepciones;

/**
 *
 * @author norma
 */
public class PersistenciaIngredientesException extends Exception {

    public PersistenciaIngredientesException() {
    }

    public PersistenciaIngredientesException(String message) {
        super(message);
    }

    public PersistenciaIngredientesException(String message, Throwable cause) {
        super(message, cause);
    }

}
