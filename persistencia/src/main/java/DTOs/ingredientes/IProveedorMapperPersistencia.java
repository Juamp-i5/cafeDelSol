package DTOs.ingredientes;

import entidades.Proveedor;
import excepciones.PersistenciaIngredientesException;
import java.util.List;

/**
 *
 * @author Jp
 */
public interface IProveedorMapperPersistencia {

    public ProveedorDTOPersistencia toDTO(Proveedor entidadMongo);

    public Proveedor toMongo(ProveedorDTOPersistencia dto) throws PersistenciaIngredientesException;

    public List<ProveedorDTOPersistencia> toDTOList(List<Proveedor> entidadesMongo);

    public List<Proveedor> toMongoList(List<ProveedorDTOPersistencia> dtos) throws PersistenciaIngredientesException;
}
