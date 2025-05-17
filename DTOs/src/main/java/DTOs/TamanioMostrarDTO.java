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
    private byte[] imagenData;
    private double precioAdicional;

    public TamanioMostrarDTO() {
    }

    public TamanioMostrarDTO(String nombre, byte[] imagenData, double precioAdicional) {
        this.nombre = nombre;
        this.imagenData = imagenData;
        this.precioAdicional = precioAdicional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getImagenData() {
        return imagenData;
    }

    public void setImagenData(byte[] imagenData) {
        this.imagenData = imagenData;
    }

    public double getPrecioAdicional() {
        return precioAdicional;
    }

    public void setPrecioAdicional(double precioAdicional) {
        this.precioAdicional = precioAdicional;
    }

}
