package BOs.ingredientes;

import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import DTOs.ingredientes.ProveedorDTOPersistencia;
import java.util.List;

/**
 * Interfaz de la clase ProveedorMapper
 *
 * @author norma
 */
public interface IProveedorMapper {

    /**
     * Convierte una lista de objetos de tipo ProveedorDTOPersistencia en una
     * lista de objetos de tipo ProveedorViejoDTO.
     *
     * @param listaPersistencia Objeto ProveedorDTOPersistencia que se desea
     * convertir.
     * @return Objeto convertido de tipo ProveedorViejoDTO.
     */
    public List<ProveedorViejoDTO> toDTOList(List<ProveedorDTOPersistencia> listaPersistencia);
}
