/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.ReservacionDetalleDTO;
import DTOs.cubiculos.ReservacionDetalleDTOPersistencia;

/**
 *
 * @author rodri
 */
public class ReservacionDetalleMapper implements IReservacionDetalleMapper{
    
    private static ReservacionDetalleMapper instanceMapper;

    public ReservacionDetalleMapper() {
    }

    public static ReservacionDetalleMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new ReservacionDetalleMapper();
        }
        return instanceMapper;
    }

    @Override
    public ReservacionDetalleDTO toDTOBO(ReservacionDetalleDTOPersistencia dtoPers) {
        if (dtoPers == null) {
            return null;
        }
        ReservacionDetalleDTO dto = new ReservacionDetalleDTO();
        dto.setNumReservacion(dtoPers.getNumReservacion());
        dto.setNombre(dtoPers.getNombre());
        dto.setTelefono(dtoPers.getTelefono());
        dto.setFechaReserva(dtoPers.getFechaReserva());
        dto.setHoraInicio(dtoPers.getHoraInicio());
        dto.setHoraFin(dtoPers.getHoraFin());
        dto.setEstado(dtoPers.getEstado().toString());
        dto.setNombreCubiculo(dtoPers.getNombreCubiculo());
        dto.setPrecioHora(dtoPers.getPrecioHora());
        dto.setPrecioReservacion(dtoPers.getPrecioReservacion());
        dto.setMotivo(dtoPers.getMotivo());
        dto.setNumReservacionNuevo(dtoPers.getNumReservacionNuevo());
        dto.setFechaModificacion(dtoPers.getFechaModificacion());
        return dto;
    }
    
}
