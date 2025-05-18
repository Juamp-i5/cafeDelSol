package validaciones;

import DTOs.CRUDEntradas.EntradaNuevaDTO;
import java.time.LocalDateTime;

/**
 *
 * @author pablo
 */
public class ValidadorGestorCRUDEntradas implements IValidadorGestorEntradas {
    
    @Override
    public void validarFechasFiltradas(LocalDateTime fechaFin, LocalDateTime fechaInicio) {
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha inicio no puede pasar despues de la fecha final.");
        }
    }
    
    @Override
    public void validarEntrada(EntradaNuevaDTO entrada) {
        if (entrada.getFechaHora()==null) {
            throw new IllegalArgumentException("La fecha y hora se requieren para el registro de la entrada.");
        }else if (entrada.getDetallesEntrada()==null||entrada.getDetallesEntrada().isEmpty()) {
            throw new IllegalArgumentException("La entrada no cuenta con sus detalles.");
        }else if (entrada.getPrecioTotal()==null) {
            throw new IllegalArgumentException("La entrada no cuenta con precio totsl.");
        }else if (entrada.getProveedor()==null) {
            throw new IllegalArgumentException("No puede haber entradas sin proveedor.");
        }
    }    
}
