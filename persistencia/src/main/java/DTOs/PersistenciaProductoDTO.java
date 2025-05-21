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
public class PersistenciaProductoDTO {

    private String id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String estado;
    private Double precioBase;
    private byte[] imageData;
    private List<PersistenciaProductoTamanioDTO> tamanios;

    public PersistenciaProductoDTO() {
    }

    public PersistenciaProductoDTO(String id, String nombre, String descripcion, String categoria, String estado, double precioBase, byte[] imageData, List<PersistenciaProductoTamanioDTO> tamanios) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.estado = estado;
        this.precioBase = precioBase;
        this.imageData = imageData;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public List<PersistenciaProductoTamanioDTO> getTamanios() {
        return tamanios;
    }

    public void setTamanios(List<PersistenciaProductoTamanioDTO> tamanios) {
        this.tamanios = tamanios;
    }

}
