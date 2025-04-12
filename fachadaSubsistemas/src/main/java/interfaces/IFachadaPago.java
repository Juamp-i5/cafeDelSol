/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOs.DetallesCobroTarjetaDTO;
import pago.ResultadoPago;

/**
 *
 * @author norma
 */
public interface IFachadaPago {

    ResultadoPago procesarPago(DetallesCobroTarjetaDTO detallesCobroTarjeta);
}
