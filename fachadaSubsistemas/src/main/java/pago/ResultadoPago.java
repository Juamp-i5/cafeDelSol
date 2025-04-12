/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pago;

/**
 *
 * @author norma
 */
public class ResultadoPago {

    private boolean exito;
    private String mensajeError;
    private String codigoError;
    private String idTransaccion;

    public ResultadoPago() {
    }

    public ResultadoPago(boolean exito, String mensajeError, String codigoError, String idTransaccion) {
        this.exito = exito;
        this.mensajeError = mensajeError;
        this.codigoError = codigoError;
        this.idTransaccion = idTransaccion;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    @Override
    public String toString() {
        return "ResultadoPago{" + "exito=" + exito + ", mensajeError=" + mensajeError + ", codigoError=" + codigoError + ", idTransaccion=" + idTransaccion + '}';
    }

}
