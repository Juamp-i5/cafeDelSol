package BOs.ingredientes;

import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import DTOs.ingredientes.ProveedorDTOPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase ProveedorMapper que sirve para mapear objectos a entidades o a DTOs
 *
 * @author norma
 */
public class ProveedorMapper implements IProveedorMapper {

    private static ProveedorMapper instanceMapper;

    /**
     * Constructor por defecto.
     */
    public ProveedorMapper() {
    }

    /**
     * Obtiene la instancia de la clase.
     *
     * @return instanceMapper.
     */
    public static ProveedorMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new ProveedorMapper();
        }
        return instanceMapper;
    }

    /**
     * Convierte una lista de objetos de tipo ProveedorDTOPersistencia en una
     * lista de objetos de tipo ProveedorViejoDTO.
     *
     * @param listaPersistencia Objeto ProveedorDTOPersistencia que se desea
     * convertir.
     * @return Objeto convertido de tipo ProveedorViejoDTO.
     */
    @Override
    public List<ProveedorViejoDTO> toDTOList(List<ProveedorDTOPersistencia> listaPersistencia) {
        if (listaPersistencia == null) {
            return null;
        }

        List<ProveedorViejoDTO> listaViejoDTO = new ArrayList<>();
        for (ProveedorDTOPersistencia persistencia : listaPersistencia) {
            ProveedorViejoDTO viejoDTO = new ProveedorViejoDTO();
            viejoDTO.setId(persistencia.getId());
            viejoDTO.setNombre(persistencia.getNombre());
            listaViejoDTO.add(viejoDTO);
        }

        return listaViejoDTO;
    }

}
