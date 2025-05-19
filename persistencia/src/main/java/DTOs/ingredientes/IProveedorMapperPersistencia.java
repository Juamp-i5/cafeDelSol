package DTOs.ingredientes;

import entidades.Proveedor;
import java.util.List;

/**
 *
 * @author Jp
 */
public interface IProveedorMapperPersistencia {

    public ProveedorDTOPersistencia toDTO(Proveedor entidadMongo);

    public List<ProveedorDTOPersistencia> toDTOList(List<Proveedor> entidadesMongo);

}
