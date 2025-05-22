/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Jp
 */
public class PersistenciaProductoPedidoDTO {

    private PersistenciaProductoDTO producto;
    private PersistenciaSaborDTO sabor;
    private PersistenciaTamanioDTO tamanio;
    private PersistenciaToppingDTO topping;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

    public PersistenciaProductoPedidoDTO() {
    }

    public PersistenciaProductoPedidoDTO(PersistenciaProductoDTO producto, PersistenciaSaborDTO sabor, PersistenciaTamanioDTO tamanio, PersistenciaToppingDTO topping, Integer cantidad, Double precioUnitario) {
        this.producto = producto;
        this.sabor = sabor;
        this.tamanio = tamanio;
        this.topping = topping;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public PersistenciaProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(PersistenciaProductoDTO producto) {
        this.producto = producto;
    }

    public PersistenciaSaborDTO getSabor() {
        return sabor;
    }

    public void setSabor(PersistenciaSaborDTO sabor) {
        this.sabor = sabor;
    }

    public PersistenciaTamanioDTO getTamanio() {
        return tamanio;
    }

    public void setTamanio(PersistenciaTamanioDTO tamanio) {
        this.tamanio = tamanio;
    }

    public PersistenciaToppingDTO getTopping() {
        return topping;
    }

    public void setTopping(PersistenciaToppingDTO topping) {
        this.topping = topping;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

}
