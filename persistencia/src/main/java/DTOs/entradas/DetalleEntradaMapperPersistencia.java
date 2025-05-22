package DTOs.entradas;

import DTOs.ingredientes.IIngredienteMapperPersistencia;
import DTOs.ingredientes.IngredienteMapperPersistencia;
import entidades.DetalleEntrada;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo
 */
public class DetalleEntradaMapperPersistencia implements IDetalleEntradaMapperPersistencia {

    private static DetalleEntradaMapperPersistencia instanceMapper;
    
    IIngredienteMapperPersistencia ingredienteMapper = new IngredienteMapperPersistencia();

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

        String idHex = detallesDTO.getIdIngrediente();
        ObjectId objectId = new ObjectId(idHex);
        detalles.setIdIngrediente(objectId);

        detalles.setPrecioTotal(detallesDTO.getPrecioTotal());
        detalles.setNivelStock(detallesDTO.getNivelStock());
        return detalles;
    }

    @Override
    public DetalleEntradaDTOPersistencia todtoPersistencia(DetalleEntrada detalles) {
        DetalleEntradaDTOPersistencia detallesDTO = new DetalleEntradaDTOPersistencia();
        detallesDTO.setNombreIngrediente(detalles.getNombreIngrediente());
        detallesDTO.setPrecioUnitario(detalles.getPrecioUnitario());
        detallesDTO.setCantidadIngrediente(detalles.getCantidad());
        detallesDTO.setIdIngrediente(
                detalles.getIdIngrediente().toHexString()
        );
        detallesDTO.setPrecioTotal(detalles.getPrecioTotal());
        detallesDTO.setNivelStock(detalles.getNivelStock());
        detallesDTO.setIngredienteInfo(ingredienteMapper.toDTOList(detalles.getIngredienteInfo()));
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
    public List<DetalleEntradaDTOPersistencia> todtoPersistenciaList(List<DetalleEntrada> detallesList) {
        if (detallesList == null) {
            return Collections.emptyList(); // evita NullPointerException
        }
        List<DetalleEntradaDTOPersistencia> detallesDTOList = new ArrayList<>();
        for (DetalleEntrada detalle : detallesList) {
            detallesDTOList.add(todtoPersistencia(detalle));
        }
        return detallesDTOList;
    }
}
