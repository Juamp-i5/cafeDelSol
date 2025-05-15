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
public class Reagenda {
    
    private String motivo;
    private LocalDateTime fechaReagenda;
    private Reservacion viejaReservacion;
    private Reservacion nuevaReservacion;

    public Reagenda() {
    }

    public Reagenda(String motivo, Reservacion viejaReservacion, Reservacion nuevaReservacion) {
        this.motivo = motivo;
        this.fechaReagenda = LocalDateTime.now();
        this.viejaReservacion = viejaReservacion;
        this.nuevaReservacion = nuevaReservacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFechaReagenda() {
        return fechaReagenda;
    }

    public void setFechaReagenda(LocalDateTime fechaReagenda) {
        this.fechaReagenda = fechaReagenda;
    }

    public Reservacion getViejaReservacion() {
        return viejaReservacion;
    }

    public void setViejaReservacion(Reservacion viejaReservacion) {
        this.viejaReservacion = viejaReservacion;
    }

    public Reservacion getNuevaReservacion() {
        return nuevaReservacion;
    }

    public void setNuevaReservacion(Reservacion nuevaReservacion) {
        this.nuevaReservacion = nuevaReservacion;
    }

    @Override
    public String toString() {
        return "Reagenda{" + "motivo=" + motivo + ", fechaReagenda=" + fechaReagenda + ", viejaReservacion=" + viejaReservacion + ", nuevaReservacion=" + nuevaReservacion + '}';
    }
    
    
    
}
