package excepciones;

/**
 * Excepción para indicar que la cantidad mínima disponible ha sido superada.
 * @author norma
 */
public class CantidadMinimaDisponibleSuperadaException extends GestionCRUDIngredientesException {

    /**
     * Constructor con mensaje específico.
     */
    public CantidadMinimaDisponibleSuperadaException() {
        super("No se puede agregar un ingrediente con una cantidad disponible tan baja");
    }

    /**
     * Constructor con mensaje.
     * @param message mensaje.
     */
    public CantidadMinimaDisponibleSuperadaException(String message) {
        super(message);
    }
}

