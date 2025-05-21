package BOs.entradas;

import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import DTOs.entradas.EntradaViejaDTOPersistencia;
import excepciones.NegocioException;
import excepciones.PersistenciaEntradasException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author pablo
 */
public interface IEntradaBO {

    public boolean registrarEntrada(EntradaNuevaDTO entrada) throws NegocioException;
    
    public List<EntradaViejaDTO> obtenerEntradasPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException;
}
