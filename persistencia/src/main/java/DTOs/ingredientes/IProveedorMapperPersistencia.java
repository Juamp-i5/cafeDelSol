package DTOs.ingredientes;

import entidades.Proveedor;
import java.util.List;

/**
 * Interfaz de la clase IProveedorMapperPersistencia
 *
 * @author Jp
 */
public interface IProveedorMapperPersistencia {

    /**
     * Convierte un objeto de tipo Proveedor en un objeto de tipo
     * ProveedorDTOPersistencia.
     *
     * @param entidadMongo Objeto de tipo Proveedor a convertir.
     * @return Objeto convertido de tipo ProveedorDTOPersistencia.
     */
    public ProveedorDTOPersistencia toDTO(Proveedor entidadMongo);

    /**
     * Convierte una lista de objetos de tipo Proveedor en una lista de objetos
     * de tipo ProveedorDTOPersistencia.
     *
     * @param entidadesMongo Lista de objetos Proveedor a convertir.
     * @return Lista de objetos convertidos de tipo ProveedorDTOPersistencia.
     */
    public List<ProveedorDTOPersistencia> toDTOList(List<Proveedor> entidadesMongo);

    /**
     * Convierte un objeto de tipo ProveedorDTOPersistencia en un objeto de tipo
     * Proveedor.
     *
     * @param dto Objeto de tipo ProveedorDTOPersistencia a convertir.
     * @return Objeto convertido de tipo Proveedor.
     */
    public Proveedor toEntity(ProveedorDTOPersistencia dto);
}
