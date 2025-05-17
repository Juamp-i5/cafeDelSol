package Gestion;

import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import Excepcion.GestorCRUDEntradasException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author pablo
 */
public interface IGestorCRUDEntradas {
    
    public boolean registrarEntrada(EntradaNuevaDTO entrada) throws GestorCRUDEntradasException;

    public List<EntradaViejaDTO> obtenerListaEntradasPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws GestorCRUDEntradasException;
    
    public List<EntradaViejaDTO> obtenerTodasLasEntradas() throws GestorCRUDEntradasException;
    
    public List<EntradaViejaDTO> obtenerEntradasHastaFecha(LocalDateTime fechaFin) throws GestorCRUDEntradasException;
    
    public List<EntradaViejaDTO> obtenerEntradasDesdeFecha(LocalDateTime fechaInicio) throws GestorCRUDEntradasException;
}
