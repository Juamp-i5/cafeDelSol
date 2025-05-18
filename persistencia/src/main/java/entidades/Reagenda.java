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
    private String numReservacionVieja;
    private String numReservacion;

    public Reagenda() {
    }

    public Reagenda(String motivo, String viejaReservacion, String nuevaReservacion) {
        this.motivo = motivo;
        this.fechaReagenda = LocalDateTime.now();
        this.numReservacionVieja = viejaReservacion;
        this.numReservacion = nuevaReservacion;
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

    public String getNumReservacionVieja() {
        return numReservacionVieja;
    }

    public void setNumReservacionVieja(String numReservacionVieja) {
        this.numReservacionVieja = numReservacionVieja;
    }

    public String getNumReservacion() {
        return numReservacion;
    }

    public void setNumReservacion(String numReservacion) {
        this.numReservacion = numReservacion;
    }

    @Override
    public String toString() {
        return "Reagenda{" + "motivo=" + motivo + ", fechaReagenda=" + fechaReagenda + ", viejaReservacion=" + numReservacionVieja + ", nuevaReservacion=" + numReservacion + '}';
    }
    
    
    
}
