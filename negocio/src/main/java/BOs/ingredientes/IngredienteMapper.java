package BOs.ingredientes;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDIngredientes.NivelStock;
import DTOs.CRUDIngredientes.UnidadMedida;
import DTOs.ingredientes.DetallesIngredienteViejoDTOPersistencia;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase IngredienteMapper que sirve para mapear objectos a entidades o a DTOs
 *
 * @author norma
 */
public class IngredienteMapper implements IIngredienteMapper {

    private static IngredienteMapper instanceMapper;

    /**
     * Constructor por defecto.
     */
    public IngredienteMapper() {
    }

    /**
     * Obtiene la instancia de la clase.
     *
     * @return instanceMapper.
     */
    public static IngredienteMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new IngredienteMapper();
        }
        return instanceMapper;
    }

    /**
     * Convierte un objeto de tipo IngredienteNuevoDTO en un objeto de tipo
     * IngredienteDTOPersistencia.
     *
     * @param dtoNuevo Objeto IngredienteNuevoDTO que se desea convertir.
     * @return Objeto convertido de tipo IngredienteDTOPersistencia.
     */
    @Override
    public IngredienteDTOPersistencia toDTOPersistencia(IngredienteNuevoDTO dtoNuevo) {
        if (dtoNuevo == null) {
            return null;
        }

        IngredienteDTOPersistencia dtoPersistencia = new IngredienteDTOPersistencia();

        dtoPersistencia.setNombre(dtoNuevo.getNombre());
        dtoPersistencia.setCantidadDisponible(dtoNuevo.getCantidadDisponible());
        dtoPersistencia.setCantidadMinima(dtoNuevo.getCantidadMinima());

        dtoPersistencia.setUnidadMedida(dtoNuevo.getUnidadMedida() != null ? dtoNuevo.getUnidadMedida().name() : null);
        dtoPersistencia.setNivelStock(dtoNuevo.getNivelStock() != null ? dtoNuevo.getNivelStock().name() : null);

        dtoPersistencia.setIdProveedor(dtoNuevo.getIdProveedor());

        return dtoPersistencia;
    }

    /**
     * Convierte un objeto de tipo DetallesIngredienteViejoDTOPersistencia en un
     * objeto de tipo DetallesIngredienteViejoDTO.
     *
     * @param dtoPersistencia Objeto DetallesIngredienteViejoDTOPersistencia que
     * se desea convertir.
     * @return Objeto convertido de tipo DetallesIngredienteViejoDTO.
     */
    @Override
    public DetallesIngredienteViejoDTO toDTOIngredienteDetalles(DetallesIngredienteViejoDTOPersistencia dtoPersistencia) {
        if (dtoPersistencia == null) {
            return null;
        }

        DetallesIngredienteViejoDTO dto = new DetallesIngredienteViejoDTO();

        dto.setId(dtoPersistencia.getId());
        dto.setNombre(dtoPersistencia.getNombre());
        dto.setCantidadDisponible(dtoPersistencia.getCantidadDisponible());
        dto.setCantidadMinima(dtoPersistencia.getCantidadMinima());

        dto.setUnidadMedida(dtoPersistencia.getUnidadMedida() != null
                ? UnidadMedida.valueOf(dtoPersistencia.getUnidadMedida())
                : null);

        dto.setNivelStock(dtoPersistencia.getNivelStock() != null
                ? NivelStock.valueOf(dtoPersistencia.getNivelStock())
                : null);

        dto.setNombreProveedor(dtoPersistencia.getNombreProveedor());

        return dto;
    }

    /**
     * Convierte una lista de objetos de tipo IngredienteDTOPersistencia en una
     * lista de objetos de tipo IngredienteViejoListDTO.
     *
     * @param listaPersistencia Objeto DetallesIngredienteViejoDTOPersistencia
     * que se desea convertir.
     * @return Objeto convertido de tipo IngredienteViejoListDTO.
     */
    @Override
    public List<IngredienteViejoListDTO> toDTOIngredienteList(List<IngredienteDTOPersistencia> listaPersistencia) {
        if (listaPersistencia == null) {
            return null;
        }

        List<IngredienteViejoListDTO> listaDTO = new ArrayList<>();
        for (IngredienteDTOPersistencia dtoPersistencia : listaPersistencia) {
            IngredienteViejoListDTO dto = new IngredienteViejoListDTO();

            dto.setId(dtoPersistencia.getId());
            dto.setNombre(dtoPersistencia.getNombre());
            dto.setCantidadDisponible(dtoPersistencia.getCantidadDisponible());

            dto.setUnidadMedida(dtoPersistencia.getUnidadMedida() != null
                    ? UnidadMedida.valueOf(dtoPersistencia.getUnidadMedida())
                    : null);

            dto.setNivelStock(dtoPersistencia.getNivelStock() != null
                    ? NivelStock.valueOf(dtoPersistencia.getNivelStock())
                    : null);

            listaDTO.add(dto);
        }

        return listaDTO;
    }

}
