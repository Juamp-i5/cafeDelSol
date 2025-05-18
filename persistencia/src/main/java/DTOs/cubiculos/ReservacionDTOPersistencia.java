/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.cubiculos;

import enumCubiculos.Estado;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author rodri
 */
public class ReservacionDTOPersistencia {

    private String id;
    private Integer numReservacion;
    private String nombre;
    private String telefono;
    private LocalDate fechaReserva;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Estado estado;

    public ReservacionDTOPersistencia() {
    }

    public ReservacionDTOPersistencia(Integer numReservacion, String nombre, String telefono, LocalDate fechaReserva, LocalTime horaInicio, LocalTime horaFin, Estado estado) {
        this.id = id;
        this.numReservacion = numReservacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
    }

    public ReservacionDTOPersistencia(String id, Integer numReservacion, String nombre, String telefono, LocalDate fechaReserva, LocalTime horaInicio, LocalTime horaFin, Estado estado) {
        this.id = id;
        this.numReservacion = numReservacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ReservacionDTOPersistencia{" + "id=" + id + ", numReservacion=" + numReservacion + ", nombre=" + nombre + ", telefono=" + telefono + ", fechaReserva=" + fechaReserva + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", estado=" + estado + '}';
    }
    
}
