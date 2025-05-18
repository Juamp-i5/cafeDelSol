package DTOs.ingredientes;

import DTOs.IngredienteDTO;
import entidades.Ingrediente;
import excepciones.PersistenciaIngredientesException;
import java.util.List;

/**
 *
 * @author Jp
 */
public interface IIngredienteMapperPersistencia {

    public IngredienteViejoListDTOPersistencia toDTOIngredienteViejoListDTOPersistencia(Ingrediente entidad);

    public List<IngredienteViejoListDTOPersistencia> toDTOList(List<Ingrediente> entidades);

    public Ingrediente toMongo(IngredienteDTO dto) throws PersistenciaIngredientesException;
}
