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
    public Proveedor toMongo(ProveedorDTOPersistencia dto) throws PersistenciaIngredientesException {
        if (dto == null) {
            return null;
        }
        Proveedor entidadMongo = new Proveedor();
        if (dto.getId() != null && ObjectId.isValid(dto.getId())) {
            entidadMongo.setId(new ObjectId(dto.getId()));
        } else if (dto.getId() != null && !dto.getId().isEmpty() && !ObjectId.isValid(dto.getId())) {
            throw new PersistenciaIngredientesException("Error al mappear el id de Producto de tipo String a tipo ObjectId");
        }
        entidadMongo.setNombre(dto.getNombre());
        return entidadMongo;
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

    @Override
    public List<Proveedor> toMongoList(List<ProveedorDTOPersistencia> dtos) throws PersistenciaIngredientesException {
        if (dtos == null) {
            return null;
        }
        List<Proveedor> proveedores = new ArrayList<>();
        for (ProveedorDTOPersistencia dto : dtos) {
            proveedores.add(toMongo(dto));
        }
        return proveedores;
    }
}
