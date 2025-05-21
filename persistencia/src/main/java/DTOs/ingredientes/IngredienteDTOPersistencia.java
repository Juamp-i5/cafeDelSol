/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.ingredientes;

/**
 *
 * @author Jp
 */
public class IngredienteDTOPersistencia {

    private String id;
    private String nombre;
    private Double cantidadDisponible;
    private Double cantidadMinima;
    private String unidadMedida;
    private String nivelStock;
    private String idProveedor;

    public IngredienteDTOPersistencia() {
    }

    public IngredienteDTOPersistencia(String id, String nombre, Double cantidadDisponible, Double cantidadMinima, String unidadMedida, String nivelStock, String idProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadMinima = cantidadMinima;
        this.unidadMedida = unidadMedida;
        this.nivelStock = nivelStock;
        this.idProveedor = idProveedor;
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

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getNivelStock() {
        return nivelStock;
    }

    public void setNivelStock(String nivelStock) {
        this.nivelStock = nivelStock;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String proveedor) {
        this.idProveedor = proveedor;
    }

}
