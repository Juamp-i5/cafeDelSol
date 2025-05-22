/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO que representa la estructura de datos de un pedido para su persistencia.
 * Contiene información sobre los productos pedidos, el barista asignado, el
 * estado del pedido, el pago realizado y la fecha y hora en que se generó el
 * pedido.
 *
 * Este DTO facilita la transferencia de datos entre capas sin exponer
 * directamente la entidad de negocio.
 *
 * @author Jp
 */
public class PersistenciaPedidoDTO {

    /**
     * Identificador único del pedido.
     */
    private String id;

    /**
     * Lista de productos incluidos en el pedido.
     */
    private List<PersistenciaProductoPedidoDTO> productos;

    /**
     * Precio total del pedido.
     */
    private Double precioTotal;

    /**
     * Estado actual del pedido (por ejemplo: "Pendiente", "Preparando",
     * "Entregado").
     */
    private String estado;

    /**
     * Identificador del barista responsable de preparar el pedido.
     */
    private String baristaId;

    /**
     * Información del pago asociado al pedido.
     */
    private PersistenciaPagoDTO pago;

    /**
     * Fecha y hora en que se generó el pedido.
     */
    private LocalDateTime fechaHora;

    /**
     * Constructor vacío.
     */
    public PersistenciaPedidoDTO() {
    }

    /**
     * Constructor con parámetros para inicializar los campos principales del
     * pedido.
     *
     * @param id identificador del pedido
     * @param productos lista de productos del pedido
     * @param estado estado actual del pedido
     * @param baristaId identificador del barista asignado
     * @param pago objeto que contiene la información del pago
     * @param fechaHora fecha y hora de creación del pedido
     */
    public PersistenciaPedidoDTO(String id, List<PersistenciaProductoPedidoDTO> productos, String estado, String baristaId, PersistenciaPagoDTO pago, LocalDateTime fechaHora) {
        this.id = id;
        this.productos = productos;
        this.estado = estado;
        this.baristaId = baristaId;
        this.pago = pago;
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el identificador del pedido.
     *
     * @return el ID del pedido
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del pedido.
     *
     * @param id el ID a establecer
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene la lista de productos del pedido.
     *
     * @return lista de productos
     */
    public List<PersistenciaProductoPedidoDTO> getProductos() {
        return productos;
    }

    /**
     * Establece la lista de productos del pedido.
     *
     * @param productos lista de productos a establecer
     */
    public void setProductos(List<PersistenciaProductoPedidoDTO> productos) {
        this.productos = productos;
    }

    /**
     * Obtiene el precio total del pedido.
     *
     * @return precio total
     */
    public Double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Establece el precio total del pedido.
     *
     * @param precioTotal precio total a establecer
     */
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * Obtiene el estado del pedido.
     *
     * @return estado del pedido
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del pedido.
     *
     * @param estado estado a establecer
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el ID del barista asignado al pedido.
     *
     * @return ID del barista
     */
    public String getBaristaId() {
        return baristaId;
    }

    /**
     * Establece el ID del barista asignado al pedido.
     *
     * @param baristaId ID del barista a establecer
     */
    public void setBaristaId(String baristaId) {
        this.baristaId = baristaId;
    }

    /**
     * Obtiene el objeto de pago del pedido.
     *
     * @return objeto de tipo {@code PersistenciaPagoDTO}
     */
    public PersistenciaPagoDTO getPago() {
        return pago;
    }

    /**
     * Establece el objeto de pago del pedido.
     *
     * @param pago objeto de tipo {@code PersistenciaPagoDTO} a establecer
     */
    public void setPago(PersistenciaPagoDTO pago) {
        this.pago = pago;
    }

    /**
     * Obtiene la fecha y hora del pedido.
     *
     * @return fecha y hora del pedido
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora del pedido.
     *
     * @param fechaHora fecha y hora a establecer
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

}
