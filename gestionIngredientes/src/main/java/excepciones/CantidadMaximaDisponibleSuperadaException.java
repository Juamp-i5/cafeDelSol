package excepciones;

/**
 * Excepción para indicar que la cantidad máxima disponible ha sido superada.
 * @author norma
 */
public class CantidadMaximaDisponibleSuperadaException extends GestionCRUDIngredientesException {

    /**
     * Constructor con mensaje específico.
     */
    public CantidadMaximaDisponibleSuperadaException() {
        super("No se puede agregar un ingrediente con una cantidad disponible tan alta");
    }

    /**
     * Constructor con mensaje.
     * @param message mensaje.
     */
    public CantidadMaximaDisponibleSuperadaException(String message) {
        super(message);
    }
}
