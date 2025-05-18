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

    public IngredienteMapperPersistencia() {
    }

    @Override
    public IngredienteDTOPersistencia toDTOIngredienteViejoListDTOPersistencia(Ingrediente entidad) {
        if (entidad == null) {
            return null;
        }
        IngredienteDTOPersistencia dto = new IngredienteDTOPersistencia();
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
    public List<IngredienteDTOPersistencia> toDTOList(List<Ingrediente> entidades) {
        if (entidades == null) {
            return null;
        }
        List<IngredienteDTOPersistencia> dtos = new ArrayList<>();
        for (Ingrediente entidad : entidades) {
            dtos.add(toDTOIngredienteViejoListDTOPersistencia(entidad));
        }
        return dtos;
    }

    @Override
    public Ingrediente toMongo(IngredienteDTOPersistencia dto) throws PersistenciaIngredientesException {
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
            throw new PersistenciaIngredientesException("Error al mapear el ingredienteId de IngredienteDTO de String a ObjectId");
        }
        return entidad;
    }
}
