/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOs.DetallesCobroTarjetaDTO;
import exception.PagoException;

/**
 *
 * @author norma
 */
public interface IValidadorPago {

    boolean validarTarjeta(DetallesCobroTarjetaDTO detallesCobroTarjeta) throws PagoException;

}
