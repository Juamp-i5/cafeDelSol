package mapper;

import DTOs.CRUDEntradas.DetalleEntradaDTO;
import entidades.DetalleEntrada;
import interfacesMapper.IDetalleEntradaMapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pablo
 */
public class DetalleEntradaMapper implements IDetalleEntradaMapper {

    private static DetalleEntradaMapper instanceMapper;

    public static DetalleEntradaMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new DetalleEntradaMapper();
        }
        return instanceMapper;
    }

    @Override
    public DetalleEntrada toEntity(DetalleEntradaDTO detallesDTO) {
        DetalleEntrada detalles = new DetalleEntrada();
        detalles.setNombreIngrediente(detallesDTO.getNombreIngrediente());
        detalles.setPrecioUnitario(detallesDTO.getPrecioUnitario());
        detalles.setCantidad(detallesDTO.getCantidadIngrediente());
        detalles.setIngrediente(detalles.getIngrediente());
        detalles.setPrecioTotal(detallesDTO.getPrecioTotal());
        return detalles;
    }

    @Override
    public DetalleEntradaDTO todto(DetalleEntrada detalles) {
        DetalleEntradaDTO detallesDTO = new DetalleEntradaDTO();
        detallesDTO.setNombreIngrediente(detalles.getNombreIngrediente());
        detallesDTO.setPrecioUnitario(detalles.getPrecioUnitario());
        detallesDTO.setCantidadIngrediente(detalles.getCantidad());
        detallesDTO.setIngrediente(detallesDTO.getIngrediente());
        detallesDTO.setPrecioTotal(detalles.getPrecioTotal());
        return detallesDTO;
    }

    @Override
    public List<DetalleEntrada> toEntityList(List<DetalleEntradaDTO> detallesDTOList) {
        List<DetalleEntrada> detallesList = new ArrayList<>();
        for (DetalleEntradaDTO dto : detallesDTOList) {
            detallesList.add(toEntity(dto));
        }
        return detallesList;
    }

    @Override
    public List<DetalleEntradaDTO> todtoList(List<DetalleEntrada> detallesList) {
        List<DetalleEntradaDTO> detallesDTOList = new ArrayList<>();
        for (DetalleEntrada detalle : detallesList) {
            detallesDTOList.add(todto(detalle));
        }
        return detallesDTOList;
    }
}
