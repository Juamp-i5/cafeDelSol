/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DTOs.cubiculos;

import entidades.Reservacion;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IReservacionMapperPersistencia {

    public ReservacionDTOCompletaPersistencia toDTO(Reservacion entidad);

    public Reservacion toMongo(ReservacionDTOCompletaPersistencia dto);
    
    public List<ReservacionDTOCompletaPersistencia> toDTOList(List<Reservacion> entidades);

}
