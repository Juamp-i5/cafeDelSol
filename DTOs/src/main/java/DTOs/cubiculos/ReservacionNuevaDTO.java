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
public class ReservacionNuevaDTO {
    
    private String nombre;
    private String telefono;
    private String nombreCubiculo;
    private LocalDate fechaReserva;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Double precioHora;
    private Double precioReservacion;

    public ReservacionNuevaDTO() {
    }

    public ReservacionNuevaDTO(String nombre, String telefono, String nombreCubiculo, LocalDate fechaReserva, LocalTime horaInicio, LocalTime horaFin, Double precioHora, Double precioReservacion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.nombreCubiculo = nombreCubiculo;
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.precioHora = precioHora;
        this.precioReservacion = precioReservacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreCubiculo() {
        return nombreCubiculo;
    }

    public void setNombreCubiculo(String nombreCubiculo) {
        this.nombreCubiculo = nombreCubiculo;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
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

    public Double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(Double precioHora) {
        this.precioHora = precioHora;
    }

    public Double getPrecioReservacion() {
        return precioReservacion;
    }

    public void setPrecioReservacion(Double precioReservacion) {
        this.precioReservacion = precioReservacion;
    }

    @Override
    public String toString() {
        return "ReservacionNuevaDTO{" + "nombre=" + nombre + ", telefono=" + telefono + ", nombreCubiculo=" + nombreCubiculo + ", fechaReserva=" + fechaReserva + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", precioHora=" + precioHora + ", precioReservacion=" + precioReservacion + '}';
    }
    
    
}
