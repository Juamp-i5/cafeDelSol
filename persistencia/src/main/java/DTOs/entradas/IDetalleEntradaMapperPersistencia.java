package DTOs.entradas;

import entidades.DetalleEntrada;
import java.util.List;

/**
 *
 * @author pablo
 */
public interface IDetalleEntradaMapperPersistencia {

    public DetalleEntrada toEntity(DetalleEntradaDTOPersistencia detallesDTO);

    public DetalleEntradaDTOPersistencia todtoPersistencia(DetalleEntrada detalles);
    
    public List<DetalleEntrada> toEntityList(List<DetalleEntradaDTOPersistencia> detallesDTOList);
    
    public List<DetalleEntradaDTOPersistencia> todtoPersistenciaList(List<DetalleEntrada> detallesList);
}
