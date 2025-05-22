/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs.cubiculos;

import DTOs.cubiculos.ReservacionDTOCompletaPersistencia;
import DTOs.cubiculos.ReservacionDetalleDTOPersistencia;
import enumCubiculos.Estado;
import excepciones.PersistenciaCubiculoEsception;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IReservacionDAO {

    public ReservacionDTOCompletaPersistencia agregarReservacion(ReservacionDTOCompletaPersistencia reservacion) throws PersistenciaCubiculoEsception;

    public boolean actualizarEstadoReservacion(Integer numReservacion, Estado estado) throws PersistenciaCubiculoEsception;

    public ReservacionDTOCompletaPersistencia buscarPorId(Integer numReservacion) throws PersistenciaCubiculoEsception;

    public List<ReservacionDTOCompletaPersistencia> buscarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaCubiculoEsception;

    public List<ReservacionDTOCompletaPersistencia> buscarPendientesPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaCubiculoEsception;

    public boolean modificarReservacion(Integer numReservacion, Integer numReservacionNuevo, String motivo, LocalDateTime fechaHora) throws PersistenciaCubiculoEsception;

    public ReservacionDetalleDTOPersistencia getDetalleReservacion(Integer numReservacion) throws PersistenciaCubiculoEsception;

}
