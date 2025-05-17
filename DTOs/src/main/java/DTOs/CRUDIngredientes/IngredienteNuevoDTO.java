/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.CRUDIngredientes;

/**
 *
 * @author norma
 */
public class IngredienteNuevoDTO {

    private String nombre;
    private Double cantidadDisponible;
    private Double cantidadMinima;
    private UnidadMedida unidadMedida;
    private NivelStock nivelStock;
    private String idProveedor;

    public IngredienteNuevoDTO() {
    }

    public IngredienteNuevoDTO(String nombre, Double cantidadDisponible, Double cantidadMinima, UnidadMedida unidadMedida, NivelStock nivelStock, String idProveedor) {
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadMinima = cantidadMinima;
        this.unidadMedida = unidadMedida;
        this.nivelStock = nivelStock;
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Double cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Double getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(Double cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public NivelStock getNivelStock() {
        return nivelStock;
    }

    public void setNivelStock(NivelStock nivelStock) {
        this.nivelStock = nivelStock;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public String toString() {
        return "IngredienteNuevoDTO{" + "nombre=" + nombre + ", cantidadDisponible=" + cantidadDisponible + ", cantidadMinima=" + cantidadMinima + ", unidadMedida=" + unidadMedida + ", nivelStock=" + nivelStock + ", idProveedor=" + idProveedor + '}';
    }
    
}
