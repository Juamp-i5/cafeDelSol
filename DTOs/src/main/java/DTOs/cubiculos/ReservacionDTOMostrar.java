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
public class ReservacionDTOMostrar {
    
    private Integer numReservacion;
    private String nombre;
    private String nombreCubiculo;
    private String estado;
    private LocalDate fechaInicio;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public ReservacionDTOMostrar() {
    }

    public ReservacionDTOMostrar(Integer numReservacion, String nombre, String nombreCubiculo, String estado, LocalDate fechaInicio, LocalTime horaInicio, LocalTime horaFin) {
        this.numReservacion = numReservacion;
        this.nombre = nombre;
        this.nombreCubiculo = nombreCubiculo;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Integer getNumReservacion() {
        return numReservacion;
    }

    public void setNumReservacion(Integer numReservacion) {
        this.numReservacion = numReservacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCubiculo() {
        return nombreCubiculo;
    }

    public void setNombreCubiculo(String nombreCubiculo) {
        this.nombreCubiculo = nombreCubiculo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return "ReservacionDTOMostrar{" + "numReservacion=" + numReservacion + ", nombre=" + nombre + ", nombreCubiculo=" + nombreCubiculo + ", estado=" + estado + ", fechaInicio=" + fechaInicio + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + '}';
    }
    
}
