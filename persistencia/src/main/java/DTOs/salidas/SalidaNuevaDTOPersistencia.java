/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.salidas;

import enumSalidas.MotivoEnum;
import java.time.LocalDate;

/**
 *
 * @author katia
 */
public class SalidaNuevaDTOPersistencia {
    private LocalDate fecha;
    private String idIngrediente;
    private Double cantidad;
    private MotivoEnum motivo;

    public SalidaNuevaDTOPersistencia() {
    }

    public SalidaNuevaDTOPersistencia(LocalDate fecha, String idIngrediente, Double cantidad, MotivoEnum motivo) {
        this.fecha = fecha;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
        this.motivo = motivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getIdIngrediente() {
        return idIngrediente;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public MotivoEnum getMotivo() {
        return motivo;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setIdIngrediente(String idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public void setMotivo(MotivoEnum motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "SalidaNuevaDTOPersistencia{" + "fecha=" + fecha + ", idIngrediente=" + idIngrediente + ", cantidad=" + cantidad + ", motivo=" + motivo + '}';
    }
    
    
}
