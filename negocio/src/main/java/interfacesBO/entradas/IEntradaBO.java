package interfacesBO.entradas;

import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import excepciones.NegocioExceptionNegocio;
import excepciones.PersistenciaEntradasException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author pablo
 */
public interface IEntradaBO {
    public boolean registrarEntrada(EntradaNuevaDTO entrada) throws PersistenciaEntradasException, NegocioExceptionNegocio ;

    public List<EntradaViejaDTO> obtenerListaPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaEntradasException, NegocioExceptionNegocio;
    
    public List<EntradaViejaDTO> obtenerTodasLasEntradas() throws PersistenciaEntradasException, NegocioExceptionNegocio;
    
    public List<EntradaViejaDTO> obtenerEntradasHastaFecha(LocalDateTime fechaFin) throws PersistenciaEntradasException, NegocioExceptionNegocio;
    
    public List<EntradaViejaDTO> obtenerEntradasDesdeFecha(LocalDateTime fechaInicio) throws PersistenciaEntradasException, NegocioExceptionNegocio;
}
