package DTOs.entradas;

import entidades.DetalleEntrada;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pablo
 */
public class DetalleEntradaMapperPersistencia implements IDetalleEntradaMapperPersistencia {

    private static DetalleEntradaMapperPersistencia instanceMapper;

    public static DetalleEntradaMapperPersistencia getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new DetalleEntradaMapperPersistencia();
        }
        return instanceMapper;
    }

    @Override
    public DetalleEntrada toEntity(DetalleEntradaDTOPersistencia detallesDTO) {
        DetalleEntrada detalles = new DetalleEntrada();
        detalles.setNombreIngrediente(detallesDTO.getNombreIngrediente());
        detalles.setPrecioUnitario(detallesDTO.getPrecioUnitario());
        detalles.setCantidad(detallesDTO.getCantidadIngrediente());
        detalles.setIngrediente(detalles.getIngrediente());
        detalles.setPrecioTotal(detallesDTO.getPrecioTotal());
        return detalles;
    }

    @Override
    public DetalleEntradaDTOPersistencia todto(DetalleEntrada detalles) {
        DetalleEntradaDTOPersistencia detallesDTO = new DetalleEntradaDTOPersistencia();
        detallesDTO.setNombreIngrediente(detalles.getNombreIngrediente());
        detallesDTO.setPrecioUnitario(detalles.getPrecioUnitario());
        detallesDTO.setCantidadIngrediente(detalles.getCantidad());
        detallesDTO.setIngrediente(detallesDTO.getIngrediente());
        detallesDTO.setPrecioTotal(detalles.getPrecioTotal());
        return detallesDTO;
    }

    @Override
    public List<DetalleEntrada> toEntityList(List<DetalleEntradaDTOPersistencia> detallesDTOList) {
        List<DetalleEntrada> detallesList = new ArrayList<>();
        for (DetalleEntradaDTOPersistencia dto : detallesDTOList) {
            detallesList.add(toEntity(dto));
        }
        return detallesList;
    }

    @Override
    public List<DetalleEntradaDTOPersistencia> todtoList(List<DetalleEntrada> detallesList) {
        List<DetalleEntradaDTOPersistencia> detallesDTOList = new ArrayList<>();
        for (DetalleEntrada detalle : detallesList) {
            detallesDTOList.add(todto(detalle));
        }
        return detallesDTOList;
    }
}
