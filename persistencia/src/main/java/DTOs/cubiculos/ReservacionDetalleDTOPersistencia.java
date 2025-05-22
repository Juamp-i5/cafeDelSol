/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.cubiculos;

import enumCubiculos.Estado;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author rodri
 */
public class ReservacionDetalleDTOPersistencia {

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
    private String motivo;
    private Integer numReservacionNuevo;
    private LocalDateTime fechaModificacion;

    public ReservacionDetalleDTOPersistencia() {
    }

    public ReservacionDetalleDTOPersistencia(Integer numReservacion, String nombre, String telefono, LocalDate fechaReserva, LocalTime horaInicio, LocalTime horaFin, Estado estado, String idCubiculo, String nombreCubiculo, Double precioHora, Double precioReservacion, String motivo, Integer numReservacionNuevo, LocalDateTime fechModificacion) {
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
        this.motivo = motivo;
        this.numReservacionNuevo = numReservacionNuevo;
        this.fechaModificacion = fechModificacion;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getNumReservacionNuevo() {
        return numReservacionNuevo;
    }

    public void setNumReservacionNuevo(Integer numReservacionNuevo) {
        this.numReservacionNuevo = numReservacionNuevo;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public String toString() {
        return "ReservacionDetalleDTOPersistencia{" + "numReservacion=" + numReservacion + ", nombre=" + nombre + ", telefono=" + telefono + ", fechaReserva=" + fechaReserva + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", estado=" + estado + ", idCubiculo=" + idCubiculo + ", nombreCubiculo=" + nombreCubiculo + ", precioHora=" + precioHora + ", precioReservacion=" + precioReservacion + ", motivo=" + motivo + ", numReservacionNuevo=" + numReservacionNuevo + ", fechModificacion=" + fechaModificacion + '}';
    }
}
