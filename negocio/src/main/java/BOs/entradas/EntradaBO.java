/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.entradas;

import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import excepciones.NegocioExceptionNegocio;
import excepciones.PersistenciaEntradasException;
import interfacesBO.entradas.IEntradaBO;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author pablo
 */
public class EntradaBO implements IEntradaBO {

    private static EntradaBO instanciaBO;
    
    public static EntradaBO getInstance(){
        if (instanciaBO==null) {
            instanciaBO = new EntradaBO();
        }
        return instanciaBO;
    }
    
    @Override
    public boolean registrarEntrada(EntradaNuevaDTO entrada) throws PersistenciaEntradasException, NegocioExceptionNegocio {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<EntradaViejaDTO> obtenerListaPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaEntradasException, NegocioExceptionNegocio {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<EntradaViejaDTO> obtenerTodasLasEntradas() throws PersistenciaEntradasException, NegocioExceptionNegocio {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<EntradaViejaDTO> obtenerEntradasHastaFecha(LocalDateTime fechaFin) throws PersistenciaEntradasException, NegocioExceptionNegocio {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<EntradaViejaDTO> obtenerEntradasDesdeFecha(LocalDateTime fechaInicio) throws PersistenciaEntradasException, NegocioExceptionNegocio {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
