package DTOs.ingredientes;

import entidades.Ingrediente;
import excepciones.PersistenciaIngredientesException;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class IngredienteMapperPersistencia implements IIngredienteMapperPersistencia {

    private final IProveedorMapperPersistencia proveedorMapper;

    public IngredienteMapperPersistencia(IProveedorMapperPersistencia proveedorMapper) {
        this.proveedorMapper = proveedorMapper;
    }

    @Override
    public IngredienteViejoListDTOPersistencia toDTO(Ingrediente entidad) {
        if (entidad == null) {
            return null;
        }
        IngredienteViejoListDTOPersistencia dto = new IngredienteViejoListDTOPersistencia();
        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toHexString());
        }
        dto.setNombre(entidad.getNombre());
        dto.setCantidadDisponible(entidad.getCantidadDisponible());
        dto.setUnidadMedida(entidad.getUnidadMedida());
        dto.setNivelStock(entidad.getNivelStock());
        return dto;
    }

    @Override
    public Ingrediente toMongo(IngredienteViejoListDTOPersistencia dto) throws PersistenciaIngredientesException {
        if (dto == null) {
            return null;
        }
        Ingrediente entidad = new Ingrediente();
        if (dto.getId() != null && ObjectId.isValid(dto.getId())) {
            entidad.setId(new ObjectId(dto.getId()));
        } else if (dto.getId() != null && !dto.getId().isEmpty() && !ObjectId.isValid(dto.getId())) {
            throw new PersistenciaIngredientesException("Error al mapear el ingredienteId de IngredienteDTO de String a ObjectId");
        }
        entidad.setNombre(dto.getNombre());
        entidad.setCantidadDisponible(dto.getCantidadDisponible());
        entidad.setCantidadMinima(dto.getCantidadMinima());
        entidad.setUnidadMedida(dto.getUnidadMedida());
        entidad.setNivelStock(dto.getNivelStock());
        if (dto.getIdProveedor() != null && ObjectId.isValid(dto.getIdProveedor())) {
            entidad.setIdProveedor(new ObjectId(dto.getIdProveedor()));
        } else if (dto.getIdProveedor() != null && !dto.getIdProveedor().isEmpty() && !ObjectId.isValid(dto.getIdProveedor())) {
            throw new PersistenciaIngredientesException("Error al mapear el proveedorId de IngredienteDTO de String a ObjectId");
        }
        return entidad;
    }

    @Override
    public List<IngredienteViejoListDTOPersistencia> toDTOList(List<Ingrediente> entidades) {
        if (entidades == null) {
            return null;
        }
        List<IngredienteViejoListDTOPersistencia> dtos = new ArrayList<>();
        for (Ingrediente entidad : entidades) {
            dtos.add(toDTO(entidad));
        }
        return dtos;
    }

    @Override
    public List<Ingrediente> toMongoList(List<IngredienteViejoListDTOPersistencia> dtos) throws PersistenciaIngredientesException {
        if (dtos == null) {
            return null;
        }
        List<Ingrediente> entidades = new ArrayList<>();
        for (IngredienteViejoListDTOPersistencia dto : dtos) {
            entidades.add(toMongo(dto));
        }
        return entidades;
    }
}
