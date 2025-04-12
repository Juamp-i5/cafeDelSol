/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pago;

import DTOs.DetallesCobroTarjetaDTO;
import exception.PagoException;
import interfaces.IValidadorPago;

/**
 *
 * @author norma
 */
public class ValidarPago implements IValidadorPago {

    @Override
    public boolean validarTarjeta(DetallesCobroTarjetaDTO tarjeta) throws PagoException {
        if (tarjeta == null
                || tarjeta.getNumeroTarjeta() == null || tarjeta.getNumeroTarjeta().isEmpty()
                || tarjeta.getCvv() == null || tarjeta.getCvv().isEmpty()
                || tarjeta.getNombreBanco() == null || tarjeta.getNombreBanco().isEmpty()
                || tarjeta.getFechaExp() == null || tarjeta.getFechaExp().isEmpty()) {
            throw new PagoException("Se tiene que llenar todos los campos.");
        }

        if (!tarjeta.getNumeroTarjeta().matches("\\d{16}")) {
            throw new PagoException("Número de tarjeta inválido. Tiene que tener 16 dígitos.");
        }

        if (!tarjeta.getCvv().matches("\\d{3,4}")) {
            throw new PagoException("CVV inválido. Tiene que tener 3 o 4 dígitos.");
        }

        if (!tarjeta.getFechaExp().matches("\\d{2}/\\d{2}")) {
            throw new PagoException("Fecha de expiración inválida. Tiene que tener formato MM/YY.");
        }

        return true;
    }
}
