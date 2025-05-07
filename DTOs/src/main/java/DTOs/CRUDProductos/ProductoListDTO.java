/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.CRUDProductos;

import java.util.Map;

/**
 *
 * @author Jp
 */
public class ProductoListDTO {

    private String id;
    private String nombre;
    private CategoriaProducto categoria;
    private EstadoProducto estadoProducto;
    private Map<TamanioProducto, Double> precios;

    public ProductoListDTO() {
    }

    public ProductoListDTO(String id, String nombre, CategoriaProducto categoria, EstadoProducto estadoProducto, Map<TamanioProducto, Double> precios) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.estadoProducto = estadoProducto;
        this.precios = precios;
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

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
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

}
