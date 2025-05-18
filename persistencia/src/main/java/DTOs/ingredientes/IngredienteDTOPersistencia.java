/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import enumIngredientes.NivelStock;
import enumIngredientes.UnidadMedida;

/**
 *
 * @author Jp
 */
public class IngredienteDTOPersistencia {

    private String id;
    private String nombre;
    private double cantidadDisponible;
    private double cantidadMinima;
    private UnidadMedida unidadMedida;
    private NivelStock nivelStock;
    private String idProveedor;

    public IngredienteDTOPersistencia() {
    }

    public IngredienteDTOPersistencia(String id, String nombre, double cantidadDisponible, double cantidadMinima, UnidadMedida unidadMedida, NivelStock nivelStock, String idProveedor) {
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

    public double getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(double cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public double getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(double cantidadMinima) {
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

    public void setIdProveedor(String proveedor) {
        this.idProveedor = proveedor;
    }

}
