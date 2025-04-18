/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author rodri
 */
public class Producto {

    private Long id;
    private String nombre;
    private double precio;
    private String urlImagen;

    public Producto() {
    }

    public Producto(Long id, String nombre, String urlImagen) {
        this.id = id;
        this.nombre = nombre;
        this.urlImagen = urlImagen;
    }

    public Producto(Long id, String nombre, double precio, String urlImagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Override
    public String toString() {
        return "ProductoMostrarDTO{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", urlImagen=" + urlImagen + '}';
    }

}
