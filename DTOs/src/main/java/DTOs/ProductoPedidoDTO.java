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
    private TamanioMostrarDTO tamanio;
    private SaborMostrarDTO sabor;
    private ToppingMostrarDTO topping;
    private Integer cantidad = 1;
    private Double costo;

    public ProductoPedidoDTO() {
        this.cantidad = 1;
    }

    public ProductoPedidoDTO(ProductoMostrarDTO producto, TamanioMostrarDTO tamanio, SaborMostrarDTO sabor) {
        this.producto = producto;
        this.tamanio = tamanio;
        this.sabor = sabor;
        this.cantidad = 1;
    }

    public ProductoPedidoDTO(ProductoMostrarDTO producto, TamanioMostrarDTO tamanio, SaborMostrarDTO sabor, ToppingMostrarDTO topping) {
        this.producto = producto;
        this.tamanio = tamanio;
        this.sabor = sabor;
        this.topping = topping;
        this.cantidad = 1;
    }

    public ProductoMostrarDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoMostrarDTO producto) {
        this.producto = producto;
    }

    public TamanioMostrarDTO getTamanio() {
        return tamanio;
    }

    public void setTamanio(TamanioMostrarDTO tamanio) {
        this.tamanio = tamanio;
    }

    public SaborMostrarDTO getSabor() {
        return sabor;
    }

    public void setSabor(SaborMostrarDTO sabor) {
        this.sabor = sabor;
    }

    public ToppingMostrarDTO getTopping() {
        return topping;
    }

    public void setTopping(ToppingMostrarDTO topping) {
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

    @Override
    public String toString() {
        return "{\n"
                + "  \"producto\": \"" + producto + "\",\n"
                + "  \"tamanio\": \"" + tamanio + "\",\n"
                + "  \"sabor\": \"" + sabor + "\",\n"
                + "  \"topping\": \"" + topping + "\",\n"
                + "  \"cantidad\": " + cantidad + ",\n"
                + "  \"costo\": " + costo + "\n"
                + "}";
    }

}
