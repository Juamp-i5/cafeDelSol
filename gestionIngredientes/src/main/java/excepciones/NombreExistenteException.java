package excepciones;

/**
 * Excepción para indicar que ya existe un ingrediente con ese nombre.
 *
 * @author norma
 */
public class NombreExistenteException extends GestionCRUDIngredientesException {

    /**
     * Constructor con mensaje específico.
     */
    public NombreExistenteException() {
        super("Ya existe un ingrediente ese nombre");
    }

    /**
     * Constructor con mensaje.
     *
     * @param message mensaje.
     */
    public NombreExistenteException(String message) {
        super(message);
    }
}
