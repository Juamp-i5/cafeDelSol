package validaciones;

import DTOs.CRUDEntradas.EntradaNuevaDTO;
import java.time.LocalDateTime;

/**
 *
 * @author pablo
 */
public interface IValidadorGestorEntradas {

    public void validarFechasFiltradas(LocalDateTime fechaFin, LocalDateTime fechaInicio);

    public void validarEntrada(EntradaNuevaDTO entrada);
}
