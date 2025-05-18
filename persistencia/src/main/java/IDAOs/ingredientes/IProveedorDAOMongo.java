package IDAOs.ingredientes;

import entidades.Proveedor;
import excepciones.PersistenciaIngredientesException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author norma
 */
public interface IProveedorDAOMongo {
    
    public List<Proveedor> obtenerProveedores() throws PersistenciaIngredientesException;

    public void agregarProveedor(Proveedor proveedor) throws PersistenciaIngredientesException;
}
