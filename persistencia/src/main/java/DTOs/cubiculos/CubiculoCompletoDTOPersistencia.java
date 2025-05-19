/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.cubiculos;

/**
 *
 * @author rodri
 */
public class CubiculoCompletoDTOPersistencia {
    
    private String id;
    private String nombre;
    private Double precioHora;

    public CubiculoCompletoDTOPersistencia() {
    }

    public CubiculoCompletoDTOPersistencia(String id, String nombre, Double precioHora) {
        this.id = id;
        this.nombre = nombre;
        this.precioHora = precioHora;
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

    public Double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(Double precioHora) {
        this.precioHora = precioHora;
    }

    @Override
    public String toString() {
        return "CubiculoCompletoDTO{" + "id=" + id + ", nombre=" + nombre + ", precioHora=" + precioHora + '}';
    }
    
    
    
}
