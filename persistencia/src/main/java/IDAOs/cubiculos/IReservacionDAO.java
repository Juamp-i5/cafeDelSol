/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs.cubiculos;

import DTOs.cubiculos.ReservacionDTOPersistencia;
import entidades.Reservacion;
import enumCubiculos.Estado;
import excepciones.PersistenciaCubiculoEsception;
import java.time.LocalDate;
import java.util.List;
import org.bson.conversions.Bson;

/**
 *
 * @author rodri
 */
public interface IReservacionDAO {
    
    public ReservacionDTOPersistencia agregarReservacion(ReservacionDTOPersistencia reservacion) throws PersistenciaCubiculoEsception;
    
    public boolean actualizarEstadoReservacion(Integer numReservacion, Estado estado) throws PersistenciaCubiculoEsception;
    
    public List<ReservacionDTOPersistencia> buscarTodasReservaciones() throws PersistenciaCubiculoEsception;
    
    public ReservacionDTOPersistencia buscarPorId(Long id) throws PersistenciaCubiculoEsception;
    
    public List<ReservacionDTOPersistencia> buscarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaCubiculoEsception;
}
