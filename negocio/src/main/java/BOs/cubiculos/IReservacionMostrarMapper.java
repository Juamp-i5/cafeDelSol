/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.ReservacionDTOCompletaPersistencia;
import DTOs.cubiculos.ReservacionDTOMostrar;

/**
 *
 * @author rodri
 */
public interface IReservacionMostrarMapper {
    
    public ReservacionDTOMostrar toDTO (ReservacionDTOCompletaPersistencia dtoPers);
    
}
