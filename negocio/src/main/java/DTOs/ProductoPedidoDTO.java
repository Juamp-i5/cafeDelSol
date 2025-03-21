/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Jp
 */
public class ProductoPedidoDTO {

    private ProductoMostrarDTO producto;

    public ProductoPedidoDTO() {
    }

    public ProductoPedidoDTO(ProductoMostrarDTO producto) {
        this.producto = producto;
    }

    public ProductoMostrarDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoMostrarDTO producto) {
        this.producto = producto;
    }

}
