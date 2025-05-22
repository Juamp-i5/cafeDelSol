/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pago;

import DTOs.DetallesCobroTarjetaDTO;
import interfaces.IPagoTarjetaAPI;

/**
 *
 * @author norma
 */
public class PagoTarjetaAPI implements IPagoTarjetaAPI {

    @Override
    public ResultadoPago cobrar(DetallesCobroTarjetaDTO tarjeta) {
        // Simulación de cobro
        return new ResultadoPago(true, "Pago realizado exitosamente", null, "TX123456", "Débito", "MasterCard", "BBVA México", "Jose Luis Beltrán Fraijo");
    }
}
