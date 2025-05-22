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

    private String producto;
    private String tamanio;
    private String sabor;
    private String topping;
    private Integer cantidad;
    private Double costo;

    public ProductoPedidoDTO() {
        cantidad = 1;
    }

    public ProductoPedidoDTO(String producto, String tamanio, String sabor, String topping, Double costo) {
        this.producto = producto;
        this.tamanio = tamanio;
        this.sabor = sabor;
        this.topping = topping;
        cantidad = 1;
        this.costo = costo;
    }

    public ProductoPedidoDTO(String producto, String tamanio, String sabor, String topping, Integer cantidad, Double costo) {
        this.producto = producto;
        this.tamanio = tamanio;
        this.sabor = sabor;
        this.topping = topping;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

}
