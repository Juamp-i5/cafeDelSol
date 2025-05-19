package DTOs.ingredientes;

import entidades.Proveedor;
import excepciones.PersistenciaIngredientesException;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class ProveedorMapperPersistencia implements IProveedorMapperPersistencia {

    @Override
    public ProveedorDTOPersistencia toDTO(Proveedor entidadMongo) {
        if (entidadMongo == null) {
            return null;
        }
        ProveedorDTOPersistencia dto = new ProveedorDTOPersistencia();
        if (entidadMongo.getId() != null) {
            dto.setId(entidadMongo.getId().toHexString());
        }
        dto.setNombre(entidadMongo.getNombre());
        return dto;
    }

    @Override
    public List<ProveedorDTOPersistencia> toDTOList(List<Proveedor> entidadesMongo) {
        if (entidadesMongo == null) {
            return null;
        }
        List<ProveedorDTOPersistencia> proveedores = new ArrayList<>();
        for (Proveedor entidadMongo : entidadesMongo) {
            proveedores.add(toDTO(entidadMongo));
        }
        return proveedores;
    }

}
