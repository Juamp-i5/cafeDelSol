package excepciones;

/**
 * Excepción para indicar que se ha superado la cantidad máxima de caracteres permitidos en el nombre del ingrediente.
 * @author norma
 */
public class CantidadMaximaCaracteresSuperada extends GestionCRUDIngredientesException {

    /**
     * Constructor con mensaje específico.
     */
    public CantidadMaximaCaracteresSuperada() {
        super("El nombre del ingrediente no puede tener más de 15 carácteres");
    }

    /**
     * Constructor con mensaje.
     * @param message mensaje.
     */
    public CantidadMaximaCaracteresSuperada(String message) {
        super(message);
    }
}
