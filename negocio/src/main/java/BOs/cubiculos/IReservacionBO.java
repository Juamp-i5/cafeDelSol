/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.ReservacionCompletaDTO;
import DTOs.cubiculos.ReservacionNuevaDTO;
import excepciones.NegocioCubiculoException;
import java.time.LocalDateTime;

/**
 *
 * @author rodri
 */
public interface IReservacionBO {
    
    public Integer agregarReservacion (ReservacionCompletaDTO reservacion) throws NegocioCubiculoException;
    
    public ReservacionCompletaDTO buscarPorId (Integer id) throws NegocioCubiculoException;
    
    public boolean modificarReservacion (Integer numReservacion, Integer numReservacionNueva, String motivo, LocalDateTime fechaHora) throws NegocioCubiculoException;
    
}
