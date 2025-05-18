/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDateTime;

/**
 *
 * @author rodri
 */
public class Cancelacion {
    
    private String motivo;
    private LocalDateTime fechaCancelacion;
    private String numReservacion;

    public Cancelacion() {
    }

    public Cancelacion(String motivo, LocalDateTime fechaCancelacion, String numReservacion) {
        this.motivo = motivo;
        this.fechaCancelacion = fechaCancelacion;
        this.numReservacion = numReservacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDateTime fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getReservacion() {
        return numReservacion;
    }

    public void setReservacion(String numReservacion) {
        this.numReservacion = numReservacion;
    }

    @Override
    public String toString() {
        return "Cancelacion{" + "motivo=" + motivo + ", fechaCancelacion=" + fechaCancelacion + ", reservacion=" + numReservacion + '}';
    }
    
    
}
