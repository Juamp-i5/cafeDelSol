/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author rodri
 */
public class ProductoPedido {

    private Producto producto;
    private Tamanio tamanio;
    private Sabor sabor;
    private Topping topping;
    private int cantidad = 1;
    private double costo;

    public ProductoPedido() {
        this.cantidad = 1;
    }

    public ProductoPedido(Producto producto, Tamanio tamanio, Sabor sabor) {
        this.producto = producto;
        this.tamanio = tamanio;
        this.sabor = sabor;
        this.cantidad = 1;
    }

    public ProductoPedido(Producto producto, Tamanio tamanio, Sabor sabor, Topping topping) {
        this.producto = producto;
        this.tamanio = tamanio;
        this.sabor = sabor;
        this.topping = topping;
        this.cantidad = 1;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Tamanio getTamanio() {
        return tamanio;
    }

    public void setTamanio(Tamanio tamanio) {
        this.tamanio = tamanio;
    }

    public Sabor getSabor() {
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
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
