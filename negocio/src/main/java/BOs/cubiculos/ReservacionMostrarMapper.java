/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.ReservacionDTOCompletaPersistencia;
import DTOs.cubiculos.ReservacionDTOMostrar;

/**
 *
 * @author rodri
 */
public class ReservacionMostrarMapper implements IReservacionMostrarMapper{
    
    private static ReservacionMostrarMapper instanceMapper;

    public ReservacionMostrarMapper() {
    }

    public static ReservacionMostrarMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new ReservacionMostrarMapper();
        }
        return instanceMapper;
    }

    @Override
    public ReservacionDTOMostrar toDTO(ReservacionDTOCompletaPersistencia dtoPers) {
        if (dtoPers == null) {
            return null;
        }
        ReservacionDTOMostrar dto = new ReservacionDTOMostrar();
        dto.setNumReservacion(dtoPers.getNumReservacion());
        dto.setNombre(dtoPers.getNombre());
        dto.setNombreCubiculo(dtoPers.getNombreCubiculo());
        dto.setEstado(dtoPers.getEstado().toString());
        dto.setFechaInicio(dtoPers.getFechaReserva());
        dto.setHoraInicio(dtoPers.getHoraInicio());
        dto.setHoraFin(dtoPers.getHoraFin());
        return dto;
    }
    
}
