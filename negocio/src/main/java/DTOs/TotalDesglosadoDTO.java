/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.List;

/**
 *
 * @author norma
 */
public class TotalDesglosadoDTO {

    List<ProductoPedidoDTO> listaProductosPedido;
    private double total;

    public TotalDesglosadoDTO() {
    }

    public TotalDesglosadoDTO(List<ProductoPedidoDTO> listaProductosPedido, double total) {
        this.listaProductosPedido = listaProductosPedido;
        this.total = total;
    }

    public List<ProductoPedidoDTO> getListaProductosPedido() {
        return listaProductosPedido;
    }

    public void setListaProductosPedido(List<ProductoPedidoDTO> listaProductosPedido) {
        this.listaProductosPedido = listaProductosPedido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
