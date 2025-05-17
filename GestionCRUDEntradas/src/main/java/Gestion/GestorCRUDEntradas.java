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

/**
 *
 * @author pablo
 */
public class GestorCRUDEntradas implements IGestorCRUDEntradas{
    private static GestorCRUDEntradas instance;
    IEntradaBO entradaBO = EntradaBO.getInstance(); 

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
        try {
            return entradaBO.registrarEntrada(entrada);
        } catch (PersistenciaEntradasException | NegocioExceptionNegocio ex) {
            Logger.getLogger(GestorCRUDEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public List<EntradaViejaDTO> obtenerListaEntradasPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws GestorCRUDEntradasException{
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
        try {
            return entradaBO.obtenerEntradasHastaFecha(fechaFin);
        } catch (PersistenciaEntradasException | NegocioExceptionNegocio ex) {
            Logger.getLogger(GestorCRUDEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<EntradaViejaDTO> obtenerEntradasDesdeFecha(LocalDateTime fechaInicio) throws GestorCRUDEntradasException{
        try {
            return entradaBO.obtenerEntradasDesdeFecha(fechaInicio);
        } catch (PersistenciaEntradasException | NegocioExceptionNegocio ex) {
            Logger.getLogger(GestorCRUDEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
