/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs.cubiculos;

import DTOs.cubiculos.CancelacionDTOPersistencia;
import DTOs.cubiculos.ReagendaDTOPersistencia;
import DTOs.cubiculos.ReservacionDTOPersistencia;
import entidades.Reservacion;
import enumCubiculos.Estado;
import excepciones.PersistenciaCubiculoEsception;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.conversions.Bson;

/**
 *
 * @author rodri
 */
public interface IReservacionDAO {
    
    public ReservacionDTOPersistencia agregarReservacion(ReservacionDTOPersistencia reservacion) throws PersistenciaCubiculoEsception;
    
    public boolean actualizarEstadoReservacion(Integer numReservacion, Estado estado) throws PersistenciaCubiculoEsception;
    
    public ReservacionDTOPersistencia buscarPorId(Integer numReservacion) throws PersistenciaCubiculoEsception;
    
    public List<ReservacionDTOPersistencia> buscarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaCubiculoEsception;

    public boolean cancelacionReservacion(Integer numReservacion, String motivo, LocalDateTime fechaHora) throws PersistenciaCubiculoEsception;
    
    public boolean reagendaReservacion(Integer numReservacion, Integer numReservacionNuevo, String motivo, LocalDateTime fechaHora) throws PersistenciaCubiculoEsception;

}
