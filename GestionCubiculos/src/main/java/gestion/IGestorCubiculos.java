/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestion;

import DTOs.cubiculos.CubiculoCompletoDTO;
import DTOs.cubiculos.EfectivoDTOCubiculo;
import DTOs.cubiculos.ReagendaDTO;
import DTOs.cubiculos.ReservacionDTOMostrar;
import DTOs.cubiculos.ReservacionDetalleDTO;
import DTOs.cubiculos.ReservacionNuevaDTO;
import excepciones.GestionCubiculosException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IGestorCubiculos {
    
    public List<String> obtenerCubiculos() throws GestionCubiculosException;
    
    public CubiculoCompletoDTO obtenerPorNombre(String nombre) throws GestionCubiculosException;
    
    public ReservacionNuevaDTO getReservacionNueva();
    
    public boolean setReservacionNueva(ReservacionNuevaDTO reservacionNueva) throws GestionCubiculosException;
    
    public LocalTime setReagendaNueva(ReagendaDTO reagenda) throws GestionCubiculosException;
    
    public double calcularCambio(EfectivoDTOCubiculo efectivoDTO);
    
    public Integer realizarReservacion() throws GestionCubiculosException;
    
    public Integer realizarReagenda(LocalTime horaFinNueva) throws GestionCubiculosException;
    
    public boolean modificarReservacion(Integer numReservacion, Integer numReservacionNueva, String motivo) throws GestionCubiculosException;
        
    public List<ReservacionDTOMostrar> obtenerReservacionesPendientes(LocalDate fechaInicio, LocalDate fechaFin) throws GestionCubiculosException;
    
    public List<ReservacionDTOMostrar> obtenerReservacionesHistorial(LocalDate fechaInicio, LocalDate fechaFin) throws GestionCubiculosException;

    public boolean actualizarEstado(Integer numReservacion, String estado) throws GestionCubiculosException;
    
    public ReservacionDetalleDTO getDetalleReservacion(Integer numReservacion) throws GestionCubiculosException;
}
