/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package validadores;

import DTOs.CRUDSalidas.SalidaNuevaDTO;
import excepciones.GestionCRUDSalidasException;

/**
 *
 * @author katia
 */
public interface IValidadorGestorCRUDSalidas {
    public void validarSalida(SalidaNuevaDTO salida) throws GestionCRUDSalidasException;
}
