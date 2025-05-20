/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.ReservacionCompletaDTO;
import DTOs.cubiculos.ReservacionDTOCompletaPersistencia;

/**
 *
 * @author rodri
 */
public interface IReservacionMapper {
    
    public ReservacionDTOCompletaPersistencia toDTOPersistencia(ReservacionCompletaDTO dtoNuevo);
    
    public ReservacionCompletaDTO toDTO (ReservacionDTOCompletaPersistencia dtoPersistencia);
    
}
