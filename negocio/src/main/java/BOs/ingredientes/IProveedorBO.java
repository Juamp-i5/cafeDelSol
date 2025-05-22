package BOs.ingredientes;

import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Interfaz de la clase ProveedorBO.
 * @author norma
 */
public interface IProveedorBO {
    
    /**
     * Obtiene todos los proveedores.
     * @return Lista de proveedores.
     * @throws NegocioException en caso de error.
     */
    public List<ProveedorViejoDTO> obtenerProveedores() throws NegocioException;

}
