/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Jp
 */
public class PersistenciaPedidoDTO {

    private String id;
    private List<PersistenciaProductoPedidoDTO> productos;
    private Double precioTotal;
    private String estado;
    private String baristaId;
    private PersistenciaPagoDTO pago;
    private LocalDateTime fechaHora;

    public PersistenciaPedidoDTO() {
    }

    public PersistenciaPedidoDTO(String id, List<PersistenciaProductoPedidoDTO> productos, String estado, String baristaId, PersistenciaPagoDTO pago, LocalDateTime fechaHora) {
        this.id = id;
        this.productos = productos;
        this.estado = estado;
        this.baristaId = baristaId;
        this.pago = pago;
        this.fechaHora = fechaHora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PersistenciaProductoPedidoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<PersistenciaProductoPedidoDTO> productos) {
        this.productos = productos;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBaristaId() {
        return baristaId;
    }

    public void setBaristaId(String baristaId) {
        this.baristaId = baristaId;
    }

    public PersistenciaPagoDTO getPago() {
        return pago;
    }

    public void setPago(PersistenciaPagoDTO pago) {
        this.pago = pago;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

}
