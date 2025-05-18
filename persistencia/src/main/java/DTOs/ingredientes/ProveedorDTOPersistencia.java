package DTOs.ingredientes;

/**
 *
 * @author Jp
 */
public class ProveedorDTOPersistencia {

    private String id;
    private String nombre;

    public ProveedorDTOPersistencia() {
    }

    public ProveedorDTOPersistencia(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
