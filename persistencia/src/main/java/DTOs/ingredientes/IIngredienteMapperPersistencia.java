package DTOs.ingredientes;

import entidades.Ingrediente;
import excepciones.PersistenciaIngredientesException;
import java.util.List;


/**
 *
 * @author Jp
 */
public interface IIngredienteMapperPersistencia {

    public IngredienteViejoListDTOPersistencia toDTO(Ingrediente entidad);

    public Ingrediente toMongo(IngredienteViejoListDTOPersistencia dto) throws PersistenciaIngredientesException;

    public List<IngredienteViejoListDTOPersistencia> toDTOList(List<Ingrediente> entidades);

    public List<Ingrediente> toMongoList(List<IngredienteViejoListDTOPersistencia> dtos) throws PersistenciaIngredientesException;
}
