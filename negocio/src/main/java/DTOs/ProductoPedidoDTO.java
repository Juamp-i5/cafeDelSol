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
    private SaboresMostrarDTO sabor;
    private ToppingsMostrarDTO topping;
    private int cantidad = 1;
    private double costo;

    public ProductoPedidoDTO() {
        this.cantidad = 1;
    }

    public ProductoPedidoDTO(ProductoMostrarDTO producto, TamanioMostrarDTO tamanio, SaboresMostrarDTO sabor, ToppingsMostrarDTO topping) {
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

    public SaboresMostrarDTO getSabor() {
        return sabor;
    }

    public void setSabor(SaboresMostrarDTO sabor) {
        this.sabor = sabor;
    }

    public ToppingsMostrarDTO getTopping() {
        return topping;
    }

    public void setTopping(ToppingsMostrarDTO topping) {
        this.topping = topping;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "ProductoPedidoDTO{" + "producto=" + producto + ", tamanio=" + tamanio + ", sabor=" + sabor + ", topping=" + topping + ", cantidad=" + cantidad + ", costo=" + costo + '}';
    }
    
    
}
