package BOs.entradas;

import DTOs.CRUDEntradas.DetalleEntradaDTO;
import DTOs.entradas.DetalleEntradaDTOPersistencia;
import entidades.DetalleEntrada;
import java.util.List;

/**
 *
 * @author pablo
 */
public interface IDetalleEntradaMapper {

    public DetalleEntradaDTO todto(DetalleEntradaDTOPersistencia detallesDTO);

    public DetalleEntradaDTOPersistencia todtoPersistencia(DetalleEntradaDTO detalles);
    
    public List<DetalleEntradaDTO> todtoList(List<DetalleEntradaDTOPersistencia> detallesDTOList);
    
    public List<DetalleEntradaDTOPersistencia> todtoPersistenciaList(List<DetalleEntradaDTO> detallesList);
}
