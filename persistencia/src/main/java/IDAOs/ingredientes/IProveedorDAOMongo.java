package IDAOs.ingredientes;

import DTOs.ingredientes.ProveedorDTOPersistencia;
import excepciones.PersistenciaException;
import excepciones.PersistenciaIngredientesException;
import java.util.List;

/**
 *
 * @author norma
 */
public interface IProveedorDAOMongo {

    public List<ProveedorDTOPersistencia> obtenerProveedores() throws PersistenciaIngredientesException;

    public void guardarProveedor(ProveedorDTOPersistencia proveedorDTO) throws PersistenciaException;
}
