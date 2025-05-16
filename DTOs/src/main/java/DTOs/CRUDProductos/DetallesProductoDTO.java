/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.CRUDProductos;

import DTOs.CRUDIngredientesSimulados.IngredienteListDTO;
import java.util.Map;

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
    Map<IngredienteListDTO, Map<TamanioProducto, Double>> ingredientes;

    public DetallesProductoDTO() {
    }

    public DetallesProductoDTO(String id, String nombre, String descipcion, CategoriaProducto categoria, byte[] imagenData, EstadoProducto estadoProducto, Map<TamanioProducto, Double> precios, Map<IngredienteListDTO, Map<TamanioProducto, Double>> ingredientes) {
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

    public Map<IngredienteListDTO, Map<TamanioProducto, Double>> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Map<IngredienteListDTO, Map<TamanioProducto, Double>> ingredientes) {
        this.ingredientes = ingredientes;
    }

}
