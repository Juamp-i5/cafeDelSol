/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.CRUDIngredientes;

/**
 *
 * @author Jp
 */
public class IngredienteListDTO {

    private String id;
    private String nombre;
    private Double cantidad;
    private UnidadMedida unidadMedida;
    private EstadoIngrediente estado;

    public IngredienteListDTO() {
    }

    public IngredienteListDTO(String id) {
        this.id = id;
    }

    public IngredienteListDTO(String id, String nombre, Double cantidad, UnidadMedida unidadMedida, EstadoIngrediente estado) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.estado = estado;
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

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public EstadoIngrediente getEstado() {
        return estado;
    }

    public void setEstado(EstadoIngrediente estado) {
        this.estado = estado;
    }

}
