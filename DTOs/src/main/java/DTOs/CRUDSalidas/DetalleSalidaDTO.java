/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.CRUDSalidas;

import java.time.LocalDate;

/**
 *
 * @author katia
 */
public class DetalleSalidaDTO {
    private String nombreIngrediente;
    private Double cantidad;
    private MotivoEnum motivo;
    private LocalDate fecha;
    
    public DetalleSalidaDTO() {
    }

    public DetalleSalidaDTO(LocalDate fecha, Double cantidad, MotivoEnum motivo, String nombreIngrediente) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.nombreIngrediente = nombreIngrediente;
    }

    public DetalleSalidaDTO(String nombreIngrediente, Double cantidad, MotivoEnum motivo, LocalDate fechaHora) {
        this.nombreIngrediente = nombreIngrediente;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.fecha = fechaHora;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getCantidad() {
        return cantidad;
    }

    public MotivoEnum getMotivo() {
        return motivo;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public void setMotivo(MotivoEnum motivo) {
        this.motivo = motivo;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    @Override
    public String toString() {
        return "DetalleSalidaDTO{" + "fecha=" + fecha + ", cantidad=" + cantidad + ", motivo=" + motivo + ", nombreIngrediente=" + nombreIngrediente + '}';
    }
    
    
}
