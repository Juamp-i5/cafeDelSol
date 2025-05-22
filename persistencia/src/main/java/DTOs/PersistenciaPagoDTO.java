/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Jp
 */
public class PersistenciaPagoDTO {

    private String metodoPago;
    private String moneda;
    private PersistenciaDetallesEfectivoDTO detallesEfectivo;
    private PersistenciaDetallesTarjetaDTO detallesTarjeta;

    public PersistenciaPagoDTO() {
    }

    public PersistenciaPagoDTO(String metodoPago, String moneda, PersistenciaDetallesEfectivoDTO detallesEfectivo, PersistenciaDetallesTarjetaDTO detallesTarjeta) {
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

    public PersistenciaDetallesEfectivoDTO getDetallesEfectivo() {
        return detallesEfectivo;
    }

    public void setDetallesEfectivo(PersistenciaDetallesEfectivoDTO detallesEfectivo) {
        this.detallesEfectivo = detallesEfectivo;
    }

    public PersistenciaDetallesTarjetaDTO getDetallesTarjeta() {
        return detallesTarjeta;
    }

    public void setDetallesTarjeta(PersistenciaDetallesTarjetaDTO detallesTarjeta) {
        this.detallesTarjeta = detallesTarjeta;
    }

}
