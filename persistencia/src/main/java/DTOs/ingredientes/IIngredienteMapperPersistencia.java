package DTOs.ingredientes;

import entidades.Ingrediente;
import excepciones.PersistenciaIngredientesException;
import java.util.List;

/**
 * Interfaz de la clase IngredienteMapperPersistencia
 *
 * @author Jp
 */
public interface IIngredienteMapperPersistencia {

    /**
     * Convierte un objeto de tipo Ingrediente en un objeto de tipo
     * IngredienteDTOPersistencia.
     *
     * @param entidad Objeto Ingrediente que se desea convertir.
     * @return Objeto convertido de tipo IngredienteDTOPersistencia.
     */
    public IngredienteDTOPersistencia toDTOIngredienteViejoListDTOPersistencia(Ingrediente entidad);

    /**
     * Convierte una lista de objetos de tipo Ingrediente en una lista de
     * objetos de tipo IngredienteDTOPersistencia.
     *
     * @param entidades Lista de ingredientes a convertir.
     * @return Lista de objetos IngredienteDTOPersistencia.
     */
    public List<IngredienteDTOPersistencia> toDTOList(List<Ingrediente> entidades);

    /**
     * Convierte un objeto de tipo IngredienteDTOPersistencia en un objeto de
     * tipo Ingrediente.
     *
     * @param dto Objeto DTO a convertir.
     * @return Objeto convertido de tipo Ingrediente.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * conversión.
     */
    public Ingrediente toMongo(IngredienteDTOPersistencia dto) throws PersistenciaIngredientesException;
}
