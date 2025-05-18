package DTOs.ingredientes;

import entidades.Ingrediente;
import excepciones.PersistenciaIngredientesException;
import java.util.List;

/**
 *
 * @author Jp
 */
public interface IIngredienteMapperPersistencia {

    public IngredienteDTOPersistencia toDTOIngredienteViejoListDTOPersistencia(Ingrediente entidad);

    public List<IngredienteDTOPersistencia> toDTOList(List<Ingrediente> entidades);

    public Ingrediente toMongo(IngredienteDTOPersistencia dto) throws PersistenciaIngredientesException;
}
