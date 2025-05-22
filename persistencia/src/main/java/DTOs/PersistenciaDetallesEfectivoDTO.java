/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Jp
 */
public class PersistenciaDetallesEfectivoDTO {

    private Double montoRecibido;
    private Double cambio;

    public PersistenciaDetallesEfectivoDTO() {
    }

    public PersistenciaDetallesEfectivoDTO(Double montoRecibido, Double cambio) {
        this.montoRecibido = montoRecibido;
        this.cambio = cambio;
    }

    public Double getMontoRecibido() {
        return montoRecibido;
    }

    public void setMontoRecibido(Double montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    public Double getCambio() {
        return cambio;
    }

    public void setCambio(Double cambio) {
        this.cambio = cambio;
    }

}
