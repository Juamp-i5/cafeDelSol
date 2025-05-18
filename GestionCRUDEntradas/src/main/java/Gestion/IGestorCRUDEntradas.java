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
    
    public List<EntradaViejaDTO> obtenerEntradasPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws GestorCRUDEntradasException;
}
