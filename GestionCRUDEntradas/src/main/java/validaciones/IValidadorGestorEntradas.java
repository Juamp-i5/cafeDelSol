package validaciones;

import DTOs.CRUDEntradas.EntradaNuevaDTO;
import java.time.LocalDateTime;

/**
 *
 * @author pablo
 */
public interface IValidadorGestorEntradas {

    public void validarEntrada(EntradaNuevaDTO entrada);
}
