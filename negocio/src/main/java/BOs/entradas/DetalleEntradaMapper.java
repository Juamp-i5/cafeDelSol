package BOs.entradas;

import DTOs.CRUDEntradas.DetalleEntradaDTO;
import entidades.DetalleEntrada;
import BOs.entradas.IDetalleEntradaMapper;
import DTOs.entradas.DetalleEntradaDTOPersistencia;
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
    public DetalleEntradaDTO todto(DetalleEntradaDTOPersistencia detallesDTO) {
        DetalleEntradaDTO detalles = new DetalleEntradaDTO();
        detalles.setNombreIngrediente(detallesDTO.getNombreIngrediente());
        detalles.setPrecioUnitario(detallesDTO.getPrecioUnitario());
        detalles.setCantidadIngrediente(detallesDTO.getCantidadIngrediente());
        detalles.setIngrediente(detalles.getIngrediente());
        detalles.setPrecioTotal(detallesDTO.getPrecioTotal());
        return detalles;
    }

    @Override
    public DetalleEntradaDTOPersistencia todtoPersistencia(DetalleEntradaDTO detalles) {
        DetalleEntradaDTOPersistencia detallesDTO = new DetalleEntradaDTOPersistencia();
        detallesDTO.setNombreIngrediente(detalles.getNombreIngrediente());
        detallesDTO.setPrecioUnitario(detalles.getPrecioUnitario());
        detallesDTO.setCantidadIngrediente(detalles.getCantidadIngrediente());
        detallesDTO.setIngrediente(detallesDTO.getIngrediente());
        detallesDTO.setPrecioTotal(detalles.getPrecioTotal());
        return detallesDTO;
    }

    @Override
    public List<DetalleEntradaDTO> todtoList(List<DetalleEntradaDTOPersistencia> detallesDTOList) {
        List<DetalleEntradaDTO> detallesList = new ArrayList<>();
        for (DetalleEntradaDTOPersistencia dto : detallesDTOList) {
            detallesList.add(todto(dto));
        }
        return detallesList;
    }

    @Override
    public List<DetalleEntradaDTOPersistencia> todtoPersistenciaList(List<DetalleEntradaDTO> detallesList) {
        List<DetalleEntradaDTOPersistencia> detallesDTOList = new ArrayList<>();
        for (DetalleEntradaDTO detalle : detallesList) {
            detallesDTOList.add(todtoPersistencia(detalle));
        }
        return detallesDTOList;
    }
}
