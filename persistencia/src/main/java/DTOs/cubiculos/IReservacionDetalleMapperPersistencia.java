/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DTOs.cubiculos;

import entidades.Reservacion;

/**
 *
 * @author rodri
 */
public interface IReservacionDetalleMapperPersistencia {

    public ReservacionDetalleDTOPersistencia toDTO(Reservacion entidad);
}
