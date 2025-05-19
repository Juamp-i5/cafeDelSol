package DTOs.entradas;

import entidades.DetalleEntrada;
import java.util.List;

/**
 *
 * @author pablo
 */
public interface IDetalleEntradaMapperPersistencia {

    public DetalleEntrada toEntity(DetalleEntradaDTOPersistencia detallesDTO);

    public DetalleEntradaDTOPersistencia todto(DetalleEntrada detalles);
    
    public List<DetalleEntrada> toEntityList(List<DetalleEntradaDTOPersistencia> detallesDTOList);
    
    public List<DetalleEntradaDTOPersistencia> todtoList(List<DetalleEntrada> detallesList);
}
