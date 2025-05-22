/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;
import org.bson.types.ObjectId;

/**
 * Representa un producto ofrecido en el sistema. Esta clase almacena
 * información detallada sobre un producto, incluyendo su identificación,
 * nombre, descripción, categoría, estado (por ejemplo, habilitado o
 * deshabilitado), precio base, imagen asociada y las diferentes configuraciones
 * de tamaño disponibles.
 *
 * @author rodri
 */
public class Producto {

    /**
     * Identificador único del producto, generalmente asignado por la base de
     * datos (MongoDB ObjectId).
     */
    private ObjectId id;
    /**
     * Nombre comercial del producto.
     */
    private String nombre;
    /**
     * Descripción detallada del producto.
     */
    private String descripcion;
    /**
     * Categoría a la que pertenece el producto (ej. "BEBIDASFRIAS", "POSTRES").
     */
    private String categoria;
    /**
     * Estado actual del producto en el sistema (ej. "HABILITADO",
     * "DESHABILITADO").
     */
    private String estado;
    /**
     * Precio base del producto, sin considerar variaciones por tamaño u otros
     * modificadores.
     */
    private Double precioBase;
    /**
     * Datos binarios de la imagen representativa del producto.
     */
    private byte[] imageData;
    /**
     * Lista de las diferentes configuraciones de tamaño disponibles para este
     * producto. Cada configuración puede incluir ingredientes específicos y
     * variaciones de precio.
     *
     * @see ProductoTamanio
     */
    private List<ProductoTamanio> tamanios;

    /**
     * Constructor por defecto. Crea una nueva instancia de {@code Producto} sin
     * inicializar sus campos.
     */
    public Producto() {
    }

    /**
     * Obtiene el identificador único del producto.
     *
     * @return El {@link ObjectId} que representa el ID del producto.
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     *
     * @param id El {@link ObjectId} a establecer como ID del producto.
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre El nuevo nombre para el producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del producto.
     *
     * @return La descripción detallada del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     *
     * @param descripcion La nueva descripción detallada para el producto.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la categoría a la que pertenece el producto.
     *
     * @return La categoría del producto.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del producto.
     *
     * @param categoria La nueva categoría para el producto.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene el estado actual del producto (ej. "HABILITADO",
     * "DESHABILITADO").
     *
     * @return El estado del producto.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del producto.
     *
     * @param estado El nuevo estado para el producto.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el precio base del producto.
     *
     * @return El precio base del producto. Puede ser {@code null} si no está
     * establecido.
     */
    public Double getPrecioBase() {
        return precioBase;
    }

    /**
     * Establece el precio base del producto.
     *
     * @param precioBase El nuevo precio base para el producto.
     */
    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    /**
     * Obtiene los datos binarios de la imagen del producto.
     *
     * @return Un arreglo de bytes que representa la imagen del producto. Puede
     * ser {@code null}.
     */
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * Establece los datos binarios de la imagen del producto.
     *
     * @param imageData Un arreglo de bytes con los datos de la nueva imagen
     * para el producto.
     */
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    /**
     * Obtiene la lista de configuraciones de tamaño disponibles para este
     * producto. Cada elemento de la lista es un objeto {@link ProductoTamanio}
     * que detalla los ingredientes y posiblemente el precio para un tamaño
     * específico.
     *
     * @return Una lista de {@link ProductoTamanio}. Puede ser {@code null} si
     * no se han establecido tamaños.
     */
    public List<ProductoTamanio> getTamanios() {
        return tamanios;
    }

    /**
     * Establece la lista de configuraciones de tamaño para este producto.
     *
     * @param tamanios Una lista de {@link ProductoTamanio} con las nuevas
     * configuraciones de tamaño para el producto.
     */
    public void setTamanios(List<ProductoTamanio> tamanios) {
        this.tamanios = tamanios;
    }

}
