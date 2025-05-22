/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package validaciones;

import DTOs.cubiculos.ReagendaDTO;
import DTOs.cubiculos.ReservacionCompletaDTO;
import DTOs.cubiculos.ReservacionNuevaDTO;
import excepciones.GestionCubiculosException;
import java.time.LocalTime;

/**
 *
 * @author rodri
 */
public interface IValidadorCubiculos {
    
    public void ValidarGuardarReservacion(ReservacionNuevaDTO reservacionNueva) throws GestionCubiculosException;
    
    public void ValidarReagendarReservacionCampos(ReagendaDTO reagenda) throws GestionCubiculosException;
    
    public void ValidarChoqueHorarios(ReagendaDTO reagenda, LocalTime horaFinNueva) throws GestionCubiculosException;
    
    public void ValidarChoqueHorariosResNueva(ReservacionNuevaDTO reservacionNueva) throws GestionCubiculosException;
    
    public void ValidarIniciarReservacion(Integer numReservacion) throws GestionCubiculosException;
}
