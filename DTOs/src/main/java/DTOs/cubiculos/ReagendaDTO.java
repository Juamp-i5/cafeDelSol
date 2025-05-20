/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.cubiculos;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author rodri
 */
public class ReagendaDTO {
    
    private Integer numReservacion;
    private LocalDate fechaNueva;
    private LocalTime horaInicio;
    private String motivo;
    private String nombreCubiculo;

    public ReagendaDTO() {
    }

    public ReagendaDTO(Integer numReservacion, LocalDate fechaNueva, LocalTime horaInicio, String motivo, String nombreCubiculo) {
        this.numReservacion = numReservacion;
        this.fechaNueva = fechaNueva;
        this.horaInicio = horaInicio;
        this.motivo = motivo;
        this.nombreCubiculo = nombreCubiculo;
    }

    public Integer getNumReservacion() {
        return numReservacion;
    }

    public void setNumReservacion(Integer numReservacion) {
        this.numReservacion = numReservacion;
    }

    public LocalDate getFechaNueva() {
        return fechaNueva;
    }

    public void setFechaNueva(LocalDate fechaNueva) {
        this.fechaNueva = fechaNueva;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNombreCubiculo() {
        return nombreCubiculo;
    }

    public void setNombreCubiculo(String nombreCubiculo) {
        this.nombreCubiculo = nombreCubiculo;
    }

    @Override
    public String toString() {
        return "ReagendaDTO{" + "numReservacion=" + numReservacion + ", fechaNueva=" + fechaNueva + ", horaInicio=" + horaInicio + ", motivo=" + motivo + ", nombreCubiculo=" + nombreCubiculo + '}';
    }
    
    
    
}
