package DTOs.entradas;

import entidades.Entrada;
import java.util.List;

/**
 *
 * @author pablo
 */
public interface IEntradaMapperPersistencia {

    public Entrada toEntityNuevo(EntradaNuevaDTOPersistencia entradaDTO);

    public EntradaNuevaDTOPersistencia todtoNuevoPersistencia(Entrada entrada);

    public List<Entrada> toEntityNuevoList(List<EntradaNuevaDTOPersistencia> entradasDTO);

    public List<EntradaNuevaDTOPersistencia> todtoNuevoPersistenciaList(List<Entrada> entradas);

    public Entrada toEntityViejo(EntradaViejaDTOPersistencia entradaDTO);

    public EntradaViejaDTOPersistencia todtoViejoPersistencia(Entrada entrada);

    public List<Entrada> toEntityViejoList(List<EntradaViejaDTOPersistencia> entradasDTO);

    public List<EntradaViejaDTOPersistencia> todtoViejoPersistenciaList(List<Entrada> entradas);
}
