package DTOs.entradas;

import entidades.Entrada;
import java.util.List;

/**
 *
 * @author pablo
 */
public interface IEntradaMapperPersistencia {

    public Entrada toEntityNuevo(EntradaNuevaDTOPersistencia entradaDTO);

    public EntradaNuevaDTOPersistencia todtoNuevo(Entrada entrada);

    public List<Entrada> toEntityNuevoList(List<EntradaNuevaDTOPersistencia> entradasDTO);

    public List<EntradaNuevaDTOPersistencia> todtoNuevoList(List<Entrada> entradas);

    public Entrada toEntityViejo(EntradaViejaDTOPersistencia entradaDTO);

    public EntradaViejaDTOPersistencia todtoViejo(Entrada entrada);

    public List<Entrada> toEntityViejoList(List<EntradaViejaDTOPersistencia> entradasDTO);

    public List<EntradaViejaDTOPersistencia> todtoViejoList(List<Entrada> entradas);
}
