/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.ReservacionCompletaDTO;
import DTOs.cubiculos.ReservacionDTOCompletaPersistencia;

/**
 *
 * @author rodri
 */
public class ReservacionMapper implements IReservacionMapper{

    private static ReservacionMapper instanceMapper;

    public ReservacionMapper() {
    }

    public static ReservacionMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new ReservacionMapper();
        }
        return instanceMapper;
    }
    
    @Override
    public ReservacionDTOCompletaPersistencia toDTOPersistencia(ReservacionCompletaDTO dtoNuevo) {
        if (dtoNuevo == null) {
            return null;
        }
        ReservacionDTOCompletaPersistencia dtoPers = new ReservacionDTOCompletaPersistencia();
        dtoPers.setNumReservacion(dtoNuevo.getNumReservacion());
        dtoPers.setNombre(dtoNuevo.getNombre());
        dtoPers.setTelefono(dtoNuevo.getTelefono());
        dtoPers.setFechaReserva(dtoNuevo.getFechaReserva());
        dtoPers.setHoraInicio(dtoNuevo.getHoraInicio());
        dtoPers.setHoraFin(dtoNuevo.getHoraFin());
        dtoPers.setIdCubiculo(dtoNuevo.getIdCubiculo());
        dtoPers.setNombreCubiculo(dtoNuevo.getNombreCubiculo());
        dtoPers.setPrecioHora(dtoNuevo.getPrecioHora());
        dtoPers.setPrecioReservacion(dtoNuevo.getPrecioReservacion());
        return dtoPers;
        
    }
    
    
    
}
