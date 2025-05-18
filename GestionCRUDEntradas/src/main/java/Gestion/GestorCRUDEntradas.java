/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestion;

import BOs.entradas.EntradaBO;
import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import Excepcion.GestorCRUDEntradasException;
import excepciones.NegocioExceptionNegocio;
import excepciones.PersistenciaEntradasException;
import interfacesBO.entradas.IEntradaBO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import validaciones.IValidadorGestorEntradas;
import validaciones.ValidadorGestorCRUDEntradas;

/**
 *
 * @author pablo
 */
public class GestorCRUDEntradas implements IGestorCRUDEntradas{
    private static GestorCRUDEntradas instance;
    private final IEntradaBO entradaBO = EntradaBO.getInstance();
    IValidadorGestorEntradas validador = new ValidadorGestorCRUDEntradas();

    public GestorCRUDEntradas() {
    }
    
    public static GestorCRUDEntradas getInstance() {
        if (instance == null) {
            instance = new GestorCRUDEntradas();
        }

        return instance;
    }

    @Override
    public boolean registrarEntrada(EntradaNuevaDTO entrada) throws GestorCRUDEntradasException{
        validador.validarEntrada(entrada);
        try {
            return entradaBO.registrarEntrada(entrada);
        } catch (PersistenciaEntradasException | NegocioExceptionNegocio ex) {
            Logger.getLogger(GestorCRUDEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public List<EntradaViejaDTO> obtenerListaEntradasPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws GestorCRUDEntradasException{
        validador.validarFechasFiltradas(fechaFin, fechaInicio);
        try {
            return entradaBO.obtenerListaPorRangoFechas(fechaInicio, fechaFin);
        } catch (PersistenciaEntradasException | NegocioExceptionNegocio ex) {
            Logger.getLogger(GestorCRUDEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<EntradaViejaDTO> obtenerTodasLasEntradas() throws GestorCRUDEntradasException{
        try {
            return entradaBO.obtenerTodasLasEntradas();
        } catch (PersistenciaEntradasException | NegocioExceptionNegocio ex) {
            Logger.getLogger(GestorCRUDEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<EntradaViejaDTO> obtenerEntradasHastaFecha(LocalDateTime fechaFin) throws GestorCRUDEntradasException{
        validador.validarFechasFiltradas(fechaFin, fechaFin);
        try {
            return entradaBO.obtenerEntradasHastaFecha(fechaFin);
        } catch (PersistenciaEntradasException | NegocioExceptionNegocio ex) {
            Logger.getLogger(GestorCRUDEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<EntradaViejaDTO> obtenerEntradasDesdeFecha(LocalDateTime fechaInicio) throws GestorCRUDEntradasException{
        validador.validarFechasFiltradas(fechaInicio, fechaInicio);
        try {
            return entradaBO.obtenerEntradasDesdeFecha(fechaInicio);
        } catch (PersistenciaEntradasException | NegocioExceptionNegocio ex) {
            Logger.getLogger(GestorCRUDEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
