package DTOs.ingredientes;

/**
 * Clase ProveedorDTOPersistencia que representa los datos de un proveedor.
 *
 * @author Jp
 */
public class ProveedorDTOPersistencia {

    private String id;
    private String nombre;

    /**
     * Constructor por defecto.
     */
    public ProveedorDTOPersistencia() {
    }

    /**
     * Constructor que inicializa todos los atributos del objeto.
     *
     * @param id Identificador del ingrediente.
     * @param nombre Nombre del proveedor.
     */
    public ProveedorDTOPersistencia(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el id del proveedor.
     *
     * @return id del proveedor.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el id del proveedor.
     *
     * @param id id del proveedor.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del proveedor.
     *
     * @return Nombre del proveedor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del proveedor.
     *
     * @param nombre Nombre del proveedor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
