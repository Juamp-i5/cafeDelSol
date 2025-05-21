/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.ReservacionDetalleDTO;
import DTOs.cubiculos.ReservacionDetalleDTOPersistencia;
import entidades.Reservacion;

/**
 *
 * @author rodri
 */
public interface IReservacionDetalleMapper {
    
    public ReservacionDetalleDTO toDTOBO (ReservacionDetalleDTOPersistencia dtoPers);
    
}
