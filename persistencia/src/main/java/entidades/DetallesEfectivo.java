/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Jp
 */
public class DetallesEfectivo {

    private Double montoRecibido;
    private Double cambio;

    public DetallesEfectivo() {
    }

    public DetallesEfectivo(Double montoRecibido, Double cambio) {
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
