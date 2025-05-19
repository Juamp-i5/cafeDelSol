/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.ReservacionNuevaDTO;
import excepciones.NegocioCubiculoException;

/**
 *
 * @author rodri
 */
public interface IReservacionBO {
    
    public boolean agregarReservacion (ReservacionNuevaDTO reservacionNueva) throws NegocioCubiculoException;
    
    
    
}
