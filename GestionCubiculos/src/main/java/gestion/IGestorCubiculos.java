/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestion;

import DTOs.cubiculos.CubiculoCompletoDTO;
import DTOs.cubiculos.EfectivoDTOCubiculo;
import DTOs.cubiculos.ReservacionNuevaDTO;
import excepciones.GestionCubiculosException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IGestorCubiculos {
    
    public List<String> obtenerCubiculos() throws GestionCubiculosException;
    
    public CubiculoCompletoDTO obtenerPorNombre(String nombre) throws GestionCubiculosException;
    
    public ReservacionNuevaDTO getReservacionNueva();
    
    public void setReservacionNueva(ReservacionNuevaDTO reservacionNueva);
    
    public double calcularCambio(EfectivoDTOCubiculo efectivoDTO);
    
    public Integer realizarReservacion() throws GestionCubiculosException;
    
}
