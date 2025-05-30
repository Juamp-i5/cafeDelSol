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
public class ReservacionDTOCompletaPersistencia {

    private String id;
    private Integer numReservacion;
    private String nombre;
    private String telefono;
    private LocalDate fechaReserva;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Estado estado;
    private String idCubiculo;
    private String nombreCubiculo;
    private Double precioHora;
    private Double precioReservacion;

    public ReservacionDTOCompletaPersistencia() {
    }

    public ReservacionDTOCompletaPersistencia(Integer numReservacion, String nombre, String telefono, LocalDate fechaReserva, LocalTime horaInicio, LocalTime horaFin, Estado estado, String idCubiculo, String nombreCubiculo, Double precioHora, Double precioReservacion) {
        this.numReservacion = numReservacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.idCubiculo = idCubiculo;
        this.nombreCubiculo = nombreCubiculo;
        this.precioHora = precioHora;
        this.precioReservacion = precioReservacion;
    }

    public ReservacionDTOCompletaPersistencia(String id, Integer numReservacion, String nombre, String telefono, LocalDate fechaReserva, LocalTime horaInicio, LocalTime horaFin, Estado estado, String idCubiculo, String nombreCubiculo, Double precioHora, Double precioReservacion) {
        this.id = id;
        this.numReservacion = numReservacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.idCubiculo = idCubiculo;
        this.nombreCubiculo = nombreCubiculo;
        this.precioHora = precioHora;
        this.precioReservacion = precioReservacion;
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

    public String getIdCubiculo() {
        return idCubiculo;
    }

    public void setIdCubiculo(String idCubiculo) {
        this.idCubiculo = idCubiculo;
    }

    public String getNombreCubiculo() {
        return nombreCubiculo;
    }

    public void setNombreCubiculo(String nombreCubiculo) {
        this.nombreCubiculo = nombreCubiculo;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ReservacionDTOCompletaPersistencia{" + "id=" + id + ", numReservacion=" + numReservacion + ", nombre=" + nombre + ", telefono=" + telefono + ", fechaReserva=" + fechaReserva + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", estado=" + estado + ", idCubiculo=" + idCubiculo + ", nombreCubiculo=" + nombreCubiculo + ", precioHora=" + precioHora + ", precioReservacion=" + precioReservacion + '}';
    }

}
