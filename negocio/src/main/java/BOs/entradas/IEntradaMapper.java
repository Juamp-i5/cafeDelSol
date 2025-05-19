package BOs.entradas;

import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import DTOs.entradas.EntradaNuevaDTOPersistencia;
import DTOs.entradas.EntradaViejaDTOPersistencia;
import entidades.Entrada;
import java.util.List;

/**
 *
 * @author pablo
 */
public interface IEntradaMapper {

    public EntradaNuevaDTO toDtoNuevo(EntradaNuevaDTOPersistencia entradaDTO);

    public EntradaNuevaDTOPersistencia toDtoNuevoPersistencia(EntradaNuevaDTO entrada);

    public List<EntradaNuevaDTO> toDtoNuevoList(List<EntradaNuevaDTOPersistencia> entradasDTO);

    public List<EntradaNuevaDTOPersistencia> toDtoNuevoPersistenciaList(List<EntradaNuevaDTO> entradas);

    public EntradaViejaDTO todtoViejo(EntradaViejaDTOPersistencia entradaDTO);

    public EntradaViejaDTOPersistencia todtoViejoPersistencia(EntradaViejaDTO entrada) ;

    public List<EntradaViejaDTO> todtoViejoList(List<EntradaViejaDTOPersistencia> entradasDTO);

    public List<EntradaViejaDTOPersistencia> todtoViejoPersistenciaList(List<EntradaViejaDTO> entradas);
}
