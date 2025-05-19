/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import DTOs.ingredientes.IngredienteDTOPersistencia;

/**
 *
 * @author Jp
 */
public class PersistenciaProductoTamanioIngredienteDTO {

    private IngredienteDTOPersistencia ingrediente;
    private double cantidad;

    public PersistenciaProductoTamanioIngredienteDTO() {
    }

    public PersistenciaProductoTamanioIngredienteDTO(IngredienteDTOPersistencia ingrediente, double cantidad) {
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
    }

    public IngredienteDTOPersistencia getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(IngredienteDTOPersistencia ingrediente) {
        this.ingrediente = ingrediente;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

}
