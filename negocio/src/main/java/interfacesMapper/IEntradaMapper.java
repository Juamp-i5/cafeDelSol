package interfacesMapper;

import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import entidades.Entrada;
import java.util.List;

/**
 *
 * @author pablo
 */
public interface IEntradaMapper {

    public Entrada toEntityNuevo(EntradaNuevaDTO entradaDTO);

    public EntradaNuevaDTO todtoNuevo(Entrada entrada);

    public List<Entrada> toEntityNuevoList(List<EntradaNuevaDTO> entradasDTO);

    public List<EntradaNuevaDTO> todtoNuevoList(List<Entrada> entradas);

    public Entrada toEntityViejo(EntradaViejaDTO entradaDTO);

    public EntradaViejaDTO todtoViejo(Entrada entrada);

    public List<Entrada> toEntityViejoList(List<EntradaViejaDTO> entradasDTO);

    public List<EntradaViejaDTO> todtoViejoList(List<Entrada> entradas);
}
