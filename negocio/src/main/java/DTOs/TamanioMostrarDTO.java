/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author rodri
 */
public class TamanioMostrarDTO {
    
    private String nombre;
    private String urlImagen;
    private int precio;

    public TamanioMostrarDTO() {
    }

    public TamanioMostrarDTO(String nombre, String urlImagen, int precio) {
        this.nombre = nombre;
        this.urlImagen = urlImagen;
        this.precio = precio;
    }   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "TamanioMostrarDTO{nombre=" + nombre + ", urlImagen=" + urlImagen + ", precio=" + precio + '}';
    }
    
    
}
