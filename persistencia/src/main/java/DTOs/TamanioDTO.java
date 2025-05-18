/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.List;

/**
 *
 * @author Jp
 */
public class TamanioDTO {

    private String id;
    private String nombre;
    private double precioAdicional;
    private byte[] imagenData;
    private List<IngredienteDTOPersistencia> ingredientes;

    public TamanioDTO() {
    }

    public TamanioDTO(String id, String nombre, double precioAdicional, byte[] imagenData, List<IngredienteDTOPersistencia> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.precioAdicional = precioAdicional;
        this.imagenData = imagenData;
        this.ingredientes = ingredientes;
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

    public double getPrecioAdicional() {
        return precioAdicional;
    }

    public void setPrecioAdicional(double precioAdicional) {
        this.precioAdicional = precioAdicional;
    }

    public byte[] getImagenData() {
        return imagenData;
    }

    public void setImagenData(byte[] imagenData) {
        this.imagenData = imagenData;
    }

    public List<IngredienteDTOPersistencia> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteDTOPersistencia> ingredientes) {
        this.ingredientes = ingredientes;
    }

}
