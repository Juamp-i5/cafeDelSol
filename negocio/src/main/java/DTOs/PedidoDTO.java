/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.List;

/**
 *
 * @author Jp
 */
public class PedidoDTO {

    List<ProductoPedidoDTO> pedido;

    public PedidoDTO() {
    }

    public PedidoDTO(List<ProductoPedidoDTO> pedido) {
        this.pedido = pedido;
    }

    public List<ProductoPedidoDTO> getPedido() {
        return pedido;
    }

    public void setPedido(List<ProductoPedidoDTO> pedido) {
        this.pedido = pedido;
    }

}
