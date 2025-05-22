/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jp
 */
public class PedidoDeliveryDTO {

    private String idPedido;
    private List<ProductoPedidoDeliveryDTO> productos = new ArrayList<>();
    private double costoTotal;
    private boolean terminado;

    public PedidoDeliveryDTO() {
    }

    public PedidoDeliveryDTO(String idPedido, double costoTotal, boolean terminado) {
        this.idPedido = idPedido;
        this.costoTotal = costoTotal;
        this.terminado = terminado;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public List<ProductoPedidoDeliveryDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoPedidoDeliveryDTO> productos) {
        this.productos = productos;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

}
