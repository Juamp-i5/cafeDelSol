/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.CRUDProductos;

import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author oliva
 */
public class DetallesProductoDTO {

    private String id;
    private String nombre;
    private String descipcion;
    private CategoriaProducto categoria;
    private byte[] imagenData;
    EstadoProducto estadoProducto;
    Map<TamanioProducto, Double> precios;
    Map<IngredienteViejoListDTO, Map<TamanioProducto, Double>> ingredientes;

    public DetallesProductoDTO() {
    }

    public DetallesProductoDTO(String id, String nombre, String descipcion, CategoriaProducto categoria, byte[] imagenData, EstadoProducto estadoProducto, Map<TamanioProducto, Double> precios, Map<IngredienteViejoListDTO, Map<TamanioProducto, Double>> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.descipcion = descipcion;
        this.categoria = categoria;
        this.imagenData = imagenData;
        this.estadoProducto = estadoProducto;
        this.precios = precios;
        this.ingredientes = ingredientes;
    }

    public DetallesProductoDTO(DetallesProductoDTO producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.descipcion = producto.getDescipcion();
        this.categoria = producto.getCategoria();
        this.imagenData = producto.getImagenData();
        this.estadoProducto = producto.getEstadoProducto();
        this.precios = producto.getPrecios();
        this.ingredientes = producto.getIngredientes();
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

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    public byte[] getImagenData() {
        return imagenData;
    }

    public void setImagenData(byte[] imagenData) {
        this.imagenData = imagenData;
    }

    public EstadoProducto getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(EstadoProducto estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public Map<TamanioProducto, Double> getPrecios() {
        return precios;
    }

    public void setPrecios(Map<TamanioProducto, Double> precios) {
        this.precios = precios;
    }

    public Map<IngredienteViejoListDTO, Map<TamanioProducto, Double>> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Map<IngredienteViejoListDTO, Map<TamanioProducto, Double>> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.descipcion);
        hash = 59 * hash + Objects.hashCode(this.categoria);
        hash = 59 * hash + Arrays.hashCode(this.imagenData);
        hash = 59 * hash + Objects.hashCode(this.estadoProducto);
        hash = 59 * hash + Objects.hashCode(this.precios);
        hash = 59 * hash + Objects.hashCode(this.ingredientes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetallesProductoDTO other = (DetallesProductoDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descipcion, other.descipcion)) {
            return false;
        }
        if (this.categoria != other.categoria) {
            return false;
        }
        if (!Arrays.equals(this.imagenData, other.imagenData)) {
            return false;
        }
        if (this.estadoProducto != other.estadoProducto) {
            return false;
        }
        if (!Objects.equals(this.precios, other.precios)) {
            return false;
        }
        return Objects.equals(this.ingredientes, other.ingredientes);
    }

}
