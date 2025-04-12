/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author rodri
 */
public class SaboresMostrarDTO {
    
    private String nombre;
    private String urlImagen;

    public SaboresMostrarDTO() {
    }

    public SaboresMostrarDTO(String nombre, String urlImagen) {
        this.nombre = nombre;
        this.urlImagen = urlImagen;
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

    @Override
    public String toString() {
        return "SaboresMostrarDTO{nombre=" + nombre + ", urlImagen=" + urlImagen + '}';
    }
    
    
}
