/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.ReservacionCompletaDTO;
import DTOs.cubiculos.ReservacionDTOMostrar;
import DTOs.cubiculos.ReservacionDetalleDTO;
import DTOs.cubiculos.ReservacionNuevaDTO;
import excepciones.NegocioCubiculoException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IReservacionBO {
    
    public Integer agregarReservacion (ReservacionCompletaDTO reservacion) throws NegocioCubiculoException;
    
    public ReservacionCompletaDTO buscarPorId (Integer id) throws NegocioCubiculoException;
    
    public boolean modificarReservacion (Integer numReservacion, Integer numReservacionNueva, String motivo, LocalDateTime fechaHora) throws NegocioCubiculoException;
    
    public List<ReservacionDTOMostrar> obtenerReservacionesPendientes(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioCubiculoException;
    
    public List<ReservacionDTOMostrar> obtenerReservacionesHistorial(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioCubiculoException;
    
    public Integer actualizarEstado (Integer numReservacion, String estado) throws NegocioCubiculoException;
    
    public ReservacionDetalleDTO getDetalleReservacion (Integer numReservacion) throws NegocioCubiculoException;
}
