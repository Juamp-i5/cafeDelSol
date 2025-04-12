/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pago;

import DTOs.DetallesCobroTarjetaDTO;
import exception.PagoException;
import interfaces.IFachadaPago;
import interfaces.IPagoTarjetaAPI;
import interfaces.IValidadorPago;

/**
 *
 * @author norma
 */
public class FachadaPago implements IFachadaPago {

    private IPagoTarjetaAPI pagoTarjetaAPI;
    private IValidadorPago validadorPago;

    public FachadaPago(IPagoTarjetaAPI pagoTarjetaAPI, IValidadorPago validadorPago) {
        this.pagoTarjetaAPI = pagoTarjetaAPI;
        this.validadorPago = validadorPago;
    }

    @Override
    public ResultadoPago procesarPago(DetallesCobroTarjetaDTO detallesCobroTarjeta) {
        try {
            if (validadorPago.validarTarjeta(detallesCobroTarjeta)) {
                return pagoTarjetaAPI.cobrar(detallesCobroTarjeta);
            }
        } catch (PagoException ex) {
            return new ResultadoPago(false, ex.getMessage(), "VALIDACION", null);
        }

        return new ResultadoPago(false, "Error desconocido", "ERROR", null);
    }
}
