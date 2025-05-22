/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Jp
 */
public class Pago {

    private String metodoPago;
    private String moneda;
    private DetallesEfectivo detallesEfectivo;
    private DetallesTarjeta detallesTarjeta;

    public Pago() {
    }

    public Pago(String metodoPago, String moneda, DetallesEfectivo detallesEfectivo, DetallesTarjeta detallesTarjeta) {
        this.metodoPago = metodoPago;
        this.moneda = moneda;
        this.detallesEfectivo = detallesEfectivo;
        this.detallesTarjeta = detallesTarjeta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public DetallesEfectivo getDetallesEfectivo() {
        return detallesEfectivo;
    }

    public void setDetallesEfectivo(DetallesEfectivo detallesEfectivo) {
        this.detallesEfectivo = detallesEfectivo;
    }

    public DetallesTarjeta getDetallesTarjeta() {
        return detallesTarjeta;
    }

    public void setDetallesTarjeta(DetallesTarjeta detallesTarjeta) {
        this.detallesTarjeta = detallesTarjeta;
    }

}
