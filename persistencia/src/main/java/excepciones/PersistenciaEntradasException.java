package excepciones;

/**
 *
 * @author pablo
 * Excepción personalizada para la capa de persistencia de entradas.
 * Sirve para manejar errores específicos al interactuar con el almacenamiento de datos de entradas.
 */
public class PersistenciaEntradasException extends Exception {

    /**
     * Constructor vacío.
     */
    public PersistenciaEntradasException() {
    }

    /**
     * Constructor con mensaje de error.
     *
     * @param message Mensaje descriptivo del error.
     */
    public PersistenciaEntradasException(String message) {
        super(message);
    }

    /**
     * Constructor con mensaje de error y la causa original.
     *
     * @param message Mensaje descriptivo del error.
     * @param cause La causa raíz de la excepción.
     */
    public PersistenciaEntradasException(String message, Throwable cause) {
        super(message, cause);
    }
}
