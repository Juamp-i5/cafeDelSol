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
public class PedidoDTO {

    List<ProductoPedidoDTO> pedido = new ArrayList<>();
    private double costoTotal;
    private boolean terminado;

    public PedidoDTO() {
    }

    public PedidoDTO(boolean terminado) {
        this.terminado = terminado;
    }
    
    public PedidoDTO(List<ProductoPedidoDTO> pedido) {
        this.pedido = pedido;
    }

    public List<ProductoPedidoDTO> getPedido() {
        return pedido;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public void setPedido(List<ProductoPedidoDTO> pedido) {
        this.pedido = pedido;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
    
    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder();
        for (ProductoPedidoDTO producto : pedido) {
            salida.append(producto.toString());
        }
        return salida.toString();
    }
}
