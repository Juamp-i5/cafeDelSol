/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.ingredientes;

/**
 * Clase IngredienteDTOPersistencia que representa los datos de un ingrediente.
 *
 * @author norma
 */
public class IngredienteDTOPersistencia {

    private String id;
    private String nombre;
    private Double cantidadDisponible;
    private Double cantidadMinima;
    private String unidadMedida;
    private String nivelStock;
    private String idProveedor;

    /**
     * Constructor por defecto.
     */
    public IngredienteDTOPersistencia() {
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
     * @param idProveedor id del proveedor asociado al ingrediente.
     */
    public IngredienteDTOPersistencia(String id, String nombre, Double cantidadDisponible, Double cantidadMinima, String unidadMedida, String nivelStock, String idProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadMinima = cantidadMinima;
        this.unidadMedida = unidadMedida;
        this.nivelStock = nivelStock;
        this.idProveedor = idProveedor;
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
     * Obtiene el id del proveedor.
     *
     * @return id del proveedor.
     */
    public String getIdProveedor() {
        return idProveedor;
    }

    /**
     * Establece el id del proveedor.
     *
     * @param idProveedor id del proveedor.
     */
    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    /**
     * Devuelve una cadena de texto con los datos de un ingrediente.
     *
     * @return Cadena con todos los atributos del objeto.
     */
    @Override
    public String toString() {
        return "IngredienteDTOPersistencia{"
                + "id=" + id
                + ", nombre=" + nombre
                + ", cantidadDisponible=" + cantidadDisponible
                + ", cantidadMinima=" + cantidadMinima
                + ", unidadMedida=" + unidadMedida
                + ", nivelStock=" + nivelStock
                + ", idProveedor=" + idProveedor + '}';
    }

}
