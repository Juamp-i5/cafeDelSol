package excepciones;

/**
 *
 * @author pablo
 */
public class NegocioExceptionNegocio extends Exception{

    public NegocioExceptionNegocio() {
    }

    public NegocioExceptionNegocio(String message) {
        super(message);
    }

    public NegocioExceptionNegocio(String message, Throwable cause) {
        super(message, cause);
    }
}
