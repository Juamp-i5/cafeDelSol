package DTOs.ingredientes;

/**
 * Clase DetallesIngredienteViejoDTOPersistencia que representa todos los
 * detalles que tiene un ingrediente.
 *
 * @author norma
 */
public class DetallesIngredienteViejoDTOPersistencia {

    private String id;
    private String nombre;
    private Double cantidadDisponible;
    private Double cantidadMinima;
    private String unidadMedida;
    private String nivelStock;
    private String nombreProveedor;

    /**
     * Constructor por defecto.
     */
    public DetallesIngredienteViejoDTOPersistencia() {
    }

    /**
     * Constructor que inicializa todos los atributos del objeto.
     *
     * @param id Identificador del ingrediente.
     * @param nombre Nombre del ingrediente.
     * @param cantidadDisponible Cantidad disponible del ingrediente.
     * @param cantidadMinima Cantidad mínima necesaria del ingrediente.
     * @param unidadMedida Unidad de medida del ingrediente.
     * @param nivelStock Nivel de stock actual del ingrediente.
     * @param nombreProveedor Nombre del proveedor asociado al ingrediente.
     */
    public DetallesIngredienteViejoDTOPersistencia(String id, String nombre, Double cantidadDisponible, Double cantidadMinima, String unidadMedida, String nivelStock, String nombreProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadMinima = cantidadMinima;
        this.unidadMedida = unidadMedida;
        this.nivelStock = nivelStock;
        this.nombreProveedor = nombreProveedor;
    }

    /**
     * Obtiene el id del ingrediente.
     *
     * @return id del ingrediente.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el id del ingrediente.
     *
     * @param id id del ingrediente.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del ingrediente.
     *
     * @return Nombre del ingrediente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del ingrediente.
     *
     * @param nombre Nombre del ingrediente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la cantidad disponible del ingrediente.
     *
     * @return Cantidad disponible.
     */
    public Double getCantidadDisponible() {
        return cantidadDisponible;
    }

    /**
     * Establece la cantidad disponible del ingrediente.
     *
     * @param cantidadDisponible Cantidad disponible.
     */
    public void setCantidadDisponible(Double cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    /**
     * Obtiene la cantidad mínima necesaria del ingrediente.
     *
     * @return Cantidad mínima requerida.
     */
    public Double getCantidadMinima() {
        return cantidadMinima;
    }

    /**
     * Establece la cantidad mínima necesaria del ingrediente.
     *
     * @param cantidadMinima Cantidad mínima requerida.
     */
    public void setCantidadMinima(Double cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    /**
     * Obtiene la unidad de medida del ingrediente.
     *
     * @return Unidad de medida.
     */
    public String getUnidadMedida() {
        return unidadMedida;
    }

    /**
     * Establece la unidad de medida del ingrediente.
     *
     * @param unidadMedida Unidad de medida.
     */
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    /**
     * Obtiene el nivel de stock del ingrediente.
     *
     * @return Nivel de stock.
     */
    public String getNivelStock() {
        return nivelStock;
    }

    /**
     * Establece el nivel de stock del ingrediente.
     *
     * @param nivelStock Nivel de stock.
     */
    public void setNivelStock(String nivelStock) {
        this.nivelStock = nivelStock;
    }

    /**
     * Obtiene el nombre del proveedor del ingrediente.
     *
     * @return Nombre del proveedor.
     */
    public String getNombreProveedor() {
        return nombreProveedor;
    }

    /**
     * Establece el nombre del proveedor del ingrediente.
     *
     * @param idProveedor Nombre del proveedor.
     */
    public void setNombreProveedor(String idProveedor) {
        this.nombreProveedor = idProveedor;
    }

    /**
     * Devuelve una cadena de texto con todos los detalles del ingrediente.
     *
     * @return Cadena con todos los atributos del objeto.
     */
    @Override
    public String toString() {
        return "DetallesIngredienteViejoDTOPersistencia{"
                + "id=" + id
                + ", nombre=" + nombre
                + ", cantidadDisponible=" + cantidadDisponible
                + ", cantidadMinima=" + cantidadMinima
                + ", unidadMedida=" + unidadMedida
                + ", nivelStock=" + nivelStock
                + ", nombreProveedor=" + nombreProveedor
                + '}';
    }
}
