package DTOs.ingredientes;

import entidades.Proveedor;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class ProveedorMapperPersistencia implements IProveedorMapperPersistencia {

    /**
     * Convierte un objeto de tipo Proveedor en un objeto de tipo
     * ProveedorDTOPersistencia.
     *
     * @param entidadMongo Objeto de tipo Proveedor a convertir.
     * @return Objeto convertido de tipo ProveedorDTOPersistencia.
     */
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

    /**
     * Convierte un objeto de tipo ProveedorDTOPersistencia en un objeto de tipo
     * Proveedor.
     *
     * @param dto Objeto de tipo ProveedorDTOPersistencia a convertir.
     * @return Objeto convertido de tipo Proveedor.
     */
    @Override
    public Proveedor toEntity(ProveedorDTOPersistencia dto) {
        if (dto == null) {
            return null;
        }
        Proveedor proveedor = new Proveedor();
        proveedor.setId(new ObjectId(dto.getId()));
        proveedor.setNombre(dto.getNombre());
        return proveedor;
    }

    /**
     * Convierte una lista de objetos de tipo Proveedor en una lista de objetos
     * de tipo ProveedorDTOPersistencia.
     *
     * @param entidadesMongo Lista de objetos Proveedor a convertir.
     * @return Lista de objetos convertidos de tipo ProveedorDTOPersistencia.
     */
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
