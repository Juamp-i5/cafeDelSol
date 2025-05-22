/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.cubiculos;

import entidades.Reservacion;
import enumCubiculos.Estado;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author rodri
 */
public class ReservacionDetalleMapperPersistencia implements IReservacionDetalleMapperPersistencia{

    public ReservacionDetalleMapperPersistencia() {
    }
    
    /**
     * Transforma una entidad Reservación a ReservacionDetalleDTOPersistencia
     * @param entidad Reservacion a transformar
     * @return Entidad transformada a ReservacionDetalleDTOPersistencia
     */
    @Override
    public ReservacionDetalleDTOPersistencia toDTO(Reservacion entidad) {
        if (entidad == null) {
            return null;
        }
        ReservacionDetalleDTOPersistencia dto = new ReservacionDetalleDTOPersistencia();
        dto.setNumReservacion(entidad.getNumReservacion());
        dto.setNombre(entidad.getNombre());
        dto.setTelefono(entidad.getTelefono());
        dto.setFechaReserva(entidad.getFechaReserva());
        dto.setHoraInicio(entidad.getHoraInicio());
        dto.setHoraFin(entidad.getHoraFin());
        dto.setEstado(entidad.getEstado());
        dto.setIdCubiculo(entidad.getIdCubiculo().toHexString());
        dto.setNombreCubiculo(entidad.getNombreCubiculo());
        dto.setPrecioHora(entidad.getPrecioHora());
        dto.setPrecioReservacion(entidad.getPrecioReservacion());
        dto.setMotivo(entidad.getMotivo());
        dto.setNumReservacionNuevo(entidad.getNumReservacionNuevo());
        dto.setFechaModificacion(entidad.getFechaModificacion());
        return dto;
    }
    
}
