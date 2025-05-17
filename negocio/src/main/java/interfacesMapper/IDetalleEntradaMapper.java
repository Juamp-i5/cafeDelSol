package interfacesMapper;

import DTOs.CRUDEntradas.DetalleEntradaDTO;
import entidades.DetalleEntrada;
import java.util.List;

/**
 *
 * @author pablo
 */
public interface IDetalleEntradaMapper {

    public DetalleEntrada toEntity(DetalleEntradaDTO detallesDTO);

    public DetalleEntradaDTO todto(DetalleEntrada detalles);
    
    public List<DetalleEntrada> toEntityList(List<DetalleEntradaDTO> detallesDTOList);
    
    public List<DetalleEntradaDTO> todtoList(List<DetalleEntrada> detallesList);
}
