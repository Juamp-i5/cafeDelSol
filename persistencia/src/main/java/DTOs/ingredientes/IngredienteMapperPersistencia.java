package DTOs.ingredientes;

import entidades.Ingrediente;
import excepciones.PersistenciaIngredientesException;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Clase IngredienteMapperPersistencia que sirve para mapear objectos a
 * entidades o a DTOs
 *
 * @author Jp
 */
public class IngredienteMapperPersistencia implements IIngredienteMapperPersistencia {

    /**
     * Constructor por defecto.
     */
    public IngredienteMapperPersistencia() {
    }

    /**
     * Convierte un objeto de tipo Ingrediente en un objeto de tipo
     * IngredienteDTOPersistencia.
     *
     * @param entidad Objeto Ingrediente que se desea convertir.
     * @return Objeto convertido de tipo IngredienteDTOPersistencia.
     */
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

    /**
     * Convierte una lista de objetos de tipo Ingrediente en una lista de
     * objetos de tipo IngredienteDTOPersistencia.
     *
     * @param entidades Lista de ingredientes a convertir.
     * @return Lista de objetos IngredienteDTOPersistencia.
     */
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

    /**
     * Convierte un objeto de tipo IngredienteDTOPersistencia en un objeto de
     * tipo Ingrediente.
     *
     * @param dto Objeto DTO que se desea convertir a entidad.
     * @return Objeto convertido de tipo Ingrediente.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * conversión.
     */
    @Override
    public Ingrediente toMongo(IngredienteDTOPersistencia dto) throws PersistenciaIngredientesException {
        if (dto == null) {
            return null;
        }
        Ingrediente entidad = new Ingrediente();
        entidad.setNombre(dto.getNombre());
        entidad.setCantidadDisponible(dto.getCantidadDisponible());
        entidad.setCantidadMinima(dto.getCantidadMinima());
        entidad.setUnidadMedida(dto.getUnidadMedida());
        entidad.setNivelStock(dto.getNivelStock());
        entidad.setIdProveedor(new ObjectId(dto.getIdProveedor()));
        return entidad;
    }
}
