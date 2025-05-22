/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Clase que representa un pedido dentro del sistema. Contiene información
 * relacionada con los productos incluidos, el barista asignado, el pago
 * realizado, el estado del pedido y la fecha/hora en que fue generado.
 *
 * Esta entidad es utilizada principalmente en la lógica de negocio y se
 * relaciona con objetos persistentes en la base de datos.
 *
 * @author rodri
 */
public class Pedido {

    /**
     * Identificador único del pedido en formato BSON (MongoDB).
     */
    private ObjectId id;

    /**
     * Lista de productos asociados al pedido.
     */
    private List<ProductoPedido> productos;

    /**
     * Precio total del pedido, calculado a partir de los productos.
     */
    private Double precioTotal;

    /**
     * Estado actual del pedido (ej. "Pendiente", "En preparación",
     * "Entregado").
     */
    private String estado;

    /**
     * Identificador del barista encargado del pedido.
     */
    private ObjectId baristaId;

    /**
     * Información sobre el pago realizado del pedido.
     */
    private Pago pago;

    /**
     * Fecha y hora en que se generó el pedido.
     */
    private LocalDateTime fechaHora;

    /**
     * Constructor vacío necesario para frameworks y serialización.
     */
    public Pedido() {
    }

    /**
     * Constructor completo para crear un objeto Pedido con todos sus campos.
     *
     * @param id identificador del pedido
     * @param productos lista de productos del pedido
     * @param precioTotal precio total del pedido
     * @param estado estado actual del pedido
     * @param baristaId identificador del barista asignado
     * @param pago objeto que contiene la información del pago
     * @param fechaHora fecha y hora en que se generó el pedido
     */
    public Pedido(ObjectId id, List<ProductoPedido> productos, Double precioTotal, String estado, ObjectId baristaId, Pago pago, LocalDateTime fechaHora) {
        this.id = id;
        this.productos = productos;
        this.precioTotal = precioTotal;
        this.estado = estado;
        this.baristaId = baristaId;
        this.pago = pago;
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el identificador del pedido.
     *
     * @return objeto {@code ObjectId} que representa el ID del pedido
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Establece el identificador del pedido.
     *
     * @param id objeto {@code ObjectId} a establecer
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Obtiene la lista de productos del pedido.
     *
     * @return lista de objetos {@code ProductoPedido}
     */
    public List<ProductoPedido> getProductos() {
        return productos;
    }

    /**
     * Establece la lista de productos del pedido.
     *
     * @param productos lista de objetos {@code ProductoPedido} a establecer
     */
    public void setProductos(List<ProductoPedido> productos) {
        this.productos = productos;
    }

    /**
     * Obtiene el precio total del pedido.
     *
     * @return precio total del pedido
     */
    public Double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Establece el precio total del pedido.
     *
     * @param precioTotal valor del precio total a establecer
     */
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * Obtiene el estado actual del pedido.
     *
     * @return estado del pedido como cadena de texto
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual del pedido.
     *
     * @param estado nuevo estado del pedido
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el identificador del barista asignado al pedido.
     *
     * @return objeto {@code ObjectId} del barist
     a
