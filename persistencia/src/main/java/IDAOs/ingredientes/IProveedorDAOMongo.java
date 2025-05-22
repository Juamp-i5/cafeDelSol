package IDAOs.ingredientes;

import DTOs.ingredientes.ProveedorDTOPersistencia;
import excepciones.PersistenciaException;
import excepciones.PersistenciaIngredientesException;
import java.util.List;

/**
 * Interfaz de la clase ProveedorDAOMongo
 *
 * @author norma
 */
public interface IProveedorDAOMongo {

    /**
     * Obtiene todos los proveedores exisitentes.
     *
     * @return Lista de proveedores.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * operación.
     */
    public List<ProveedorDTOPersistencia> obtenerProveedores() throws PersistenciaIngredientesException;

    /**
     * Guarda un nuevo proveedor a la colección (No se usa en el sistema, pero
     * sirve para insertar manualmente proveedores ya que no se puede hacer eso
     * en el sistema ejecutado).
     *
     * @param proveedorDTO proveedor a guardar.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public void guardarProveedor(ProveedorDTOPersistencia proveedorDTO) throws PersistenciaException;
}
