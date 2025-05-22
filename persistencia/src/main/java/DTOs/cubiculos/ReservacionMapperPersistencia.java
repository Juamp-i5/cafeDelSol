/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.cubiculos;

import entidades.Reservacion;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author rodri
 */
public class ReservacionMapperPersistencia implements IReservacionMapperPersistencia {

    public ReservacionMapperPersistencia() {
    }

    @Override
    public ReservacionDTOCompletaPersistencia toDTO(Reservacion entidad) {
        if (entidad == null) {
            return null;
        }
        ReservacionDTOCompletaPersistencia dto = new ReservacionDTOCompletaPersistencia();
        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toHexString());
        }

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

        return dto;
    }

    @Override
    public Reservacion toMongo(ReservacionDTOCompletaPersistencia dto) {
        if (dto == null) {
            return null;
        }
        Reservacion entidad = new Reservacion();

        entidad.setNumReservacion(dto.getNumReservacion());
        entidad.setNombre(dto.getNombre());
        entidad.setTelefono(dto.getTelefono());
        entidad.setFechaReserva(dto.getFechaReserva());
        entidad.setHoraInicio(dto.getHoraInicio());
        entidad.setHoraFin(dto.getHoraFin());
        entidad.setEstado(dto.getEstado());
        entidad.setIdCubiculo(new ObjectId(dto.getIdCubiculo()));
        entidad.setNombreCubiculo(dto.getNombreCubiculo());
        entidad.setPrecioHora(dto.getPrecioHora());
        entidad.setPrecioReservacion(dto.getPrecioReservacion());

        return entidad;
    }

    @Override
    public List<ReservacionDTOCompletaPersistencia> toDTOList(List<Reservacion> entidades) {
        if (entidades == null) {
            return null;
        }
        List<ReservacionDTOCompletaPersistencia> dtos = new ArrayList<>();
        for (Reservacion entidad : entidades) {
            dtos.add(toDTO(entidad));
        }
        return dtos;
    }

}
