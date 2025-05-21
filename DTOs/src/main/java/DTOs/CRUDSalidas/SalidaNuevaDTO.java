/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.CRUDSalidas;

import enums.MotivoEnum;
import java.time.LocalDate;

/**
 *
 * @author katia
 */
public class SalidaNuevaDTO {
    private LocalDate fecha;
    private String idIngrediente;
    private Double cantidad;
    private MotivoEnum motivo;

    public SalidaNuevaDTO() {}

    public SalidaNuevaDTO(LocalDate fecha, String idIngrediente, Double cantidad, MotivoEnum motivo) {
        this.fecha = fecha;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
        this.motivo = motivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(String idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public MotivoEnum getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoEnum motivo) {
        this.motivo = motivo;
    }

}
