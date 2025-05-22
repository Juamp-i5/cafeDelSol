package excepciones;

/**
 * Excepción personalizada que indica un error relacionado con la persistencia
 * de datos. Esta excepción puede utilizarse para señalar fallos al guardar,
 * cargar o acceder a datos persistentes.
 *
 * @author rodri
 */
public class PersistenciaException extends Exception {

    /**
     * Crea una nueva instancia de {@code PersistenciaException} sin mensaje de
     * detalle.
     */
    public PersistenciaException() {
    }

    /**
     * Crea una nueva instancia de {@code PersistenciaException} con el mensaje
     * de detalle especificado.
     *
     * @param message el mensaje que describe el error.
     */
    public PersistenciaException(String message) {
        super(message);
    }

    /**
     * Crea una nueva instancia de {@code PersistenciaException} con el mensaje
     * de detalle y la causa especificados.
     *
     * @param message el mensaje que describe el error.
     * @param cause la causa original del error (puede ser {@code null}).
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
