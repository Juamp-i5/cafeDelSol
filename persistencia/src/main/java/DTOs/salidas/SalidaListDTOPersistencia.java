/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.salidas;

import enums.MotivoEnum;
import java.time.LocalDate;

/**
 *
 * @author katia
 */
public class SalidaListDTOPersistencia {
    private String id;
    private String nombreIngrediente;
    private Double cantidad;
    private MotivoEnum motivo;
    private LocalDate fecha;

    public SalidaListDTOPersistencia() {
    }

    public SalidaListDTOPersistencia(String id, String nombreIngrediente, Double cantidad, MotivoEnum motivo, LocalDate fecha) {
        this.id = id;
        this.nombreIngrediente = nombreIngrediente;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    public SalidaListDTOPersistencia(String nombreIngrediente, Double cantidad, MotivoEnum motivo, LocalDate fecha) {
        this.nombreIngrediente = nombreIngrediente;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public MotivoEnum getMotivo() {
        return motivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public void setMotivo(MotivoEnum motivo) {
        this.motivo = motivo;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "SalidaListDTOPersistencia{" + "id=" + id + ", nombreIngrediente=" + nombreIngrediente + ", cantidad=" + cantidad + ", motivo=" + motivo + ", fecha=" + fecha + '}';
    }
    
    
}
