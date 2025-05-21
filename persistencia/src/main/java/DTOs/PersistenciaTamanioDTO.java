/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Jp
 */
public class PersistenciaTamanioDTO {

    private String id;
    private String nombre;
    private Double precioAdicional;
    private byte[] imagenData;

    public PersistenciaTamanioDTO() {
    }

    public PersistenciaTamanioDTO(String id, String nombre, Double precioAdicional, byte[] imagenData) {
        this.id = id;
        this.nombre = nombre;
        this.precioAdicional = precioAdicional;
        this.imagenData = imagenData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecioAdicional() {
        return precioAdicional;
    }

    public void setPrecioAdicional(Double precioAdicional) {
        this.precioAdicional = precioAdicional;
    }

    public byte[] getImagenData() {
        return imagenData;
    }

    public void setImagenData(byte[] imagenData) {
        this.imagenData = imagenData;
    }

}
