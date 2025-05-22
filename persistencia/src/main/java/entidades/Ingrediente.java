package entidades;

import org.bson.types.ObjectId;

/**
 * Clase que representa un ingrediente en la base de datos.
 * @author Jp
 */
public class Ingrediente {

    private ObjectId id;
    private String nombre;
    private Double cantidadDisponible;
    private Double cantidadMinima;
    private String unidadMedida;
    private String nivelStock;
    private ObjectId idProveedor;

    /**
     * Constructor por defecto
     */
    public Ingrediente() {
    }

    /**
     * Obtiene el id del ingrediente.
     * @return id del ingrediente.
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Establece el id del ingrediente.
     * @param id id del ingrediente.
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del ingrediente.
     * @return nombre del ingrediente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del ingrediente.
     * @param nombre nombre del ingrediente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la cantidad disponible del ingrediente.
     * @return cantidad disponible del ingrediente.
     */
    public Double getCantidadDisponible() {
        return cantidadDisponible;
    }

    /**
     * Establece la cantidad disponible del ingrediente.
     * @param cantidadDisponible antidad disponible del ingrediente.
     */
    public void setCantidadDisponible(Double cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    /**
     * Obtiene la cantidad minima del ingrediente.
     * @return cantidad minima del ingrediente.
     */
    public Double getCantidadMinima() {
        return cantidadMinima;
    }

    /**
     * Establece la cantidad minima del ingrediente.
     * @param cantidadMinima cantidad minima del ingrediente.
     */
    public void setCantidadMinima(Double cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    /**
     * Obtiene la unidad de medida del ingrediente.
     * @return unidad de medida del ingrediente.
     */
    public String getUnidadMedida() {
        return unidadMedida;
    }

    /**
     * Establece la unidad de medida del ingrediente.
     * @param unidadMedida unidad de medida del ingrediente.
     */
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    /**
     * Obtiene el nivel de stock del ingrediente.
     * @return nivel de stock del ingrediente.
     */
    public String getNivelStock() {
        return nivelStock;
    }

    /**
     * Establece el nivel de stock del ingrediente.
     * @param nivelStock nivel de stock del ingrediente.
     */
    public void setNivelStock(String nivelStock) {
        this.nivelStock = nivelStock;
    }

    /**
     * Obtiene el id del proveedor del ingrediente.
     * @return id del proveedor del ingrediente.
     */
    public ObjectId getIdProveedor() {
        return idProveedor;
    }

    /**
     * Establece el id del proveedor del ingrediente.
     * @param idProveedor id del proveedor del ingrediente.
     */
    public void setIdProveedor(ObjectId idProveedor) {
        this.idProveedor = idProveedor;
    }

    /**
     * Regresa una cadena de texto de los datos de un ingrediente.
     * @return cadena de texto con los datos de un ingrediente.
     */
    @Override
    public String toString() {
        return "Ingrediente{" + "id=" + id + ", nombre=" + nombre + ", cantidadDisponible=" + cantidadDisponible + ", cantidadMinima=" + cantidadMinima + ", unidadMedida=" + unidadMedida + ", nivelStock=" + nivelStock + ", idProveedor=" + idProveedor + '}';
    }

}
