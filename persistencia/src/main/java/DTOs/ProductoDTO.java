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
public class ProductoDTO {

    private String id;
    private String nombre;
    private String categoria;
    private double precioBase;
    private byte[] imageData;
    private String estado;
    private List<TamanioDTO> tamanios;

    public ProductoDTO() {
    }

    public ProductoDTO(String id, String nombre, String categoria, double precioBase, byte[] imageData, String estado, List<TamanioDTO> tamanios) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioBase = precioBase;
        this.imageData = imageData;
        this.estado = estado;
        this.tamanios = tamanios;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<TamanioDTO> getTamanios() {
        return tamanios;
    }

    public void setTamanios(List<TamanioDTO> tamanios) {
        this.tamanios = tamanios;
    }

}
