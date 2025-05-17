/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author katia
 */
public class ToppingMostrarDTO {

    private String nombre;
    private byte[] imagenData;

    public ToppingMostrarDTO() {
    }

    public ToppingMostrarDTO(String nombre, byte[] imagenData) {
        this.nombre = nombre;
        this.imagenData = imagenData;
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

}
