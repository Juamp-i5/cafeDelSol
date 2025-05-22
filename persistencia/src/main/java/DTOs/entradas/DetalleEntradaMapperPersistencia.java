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
 * @author pablo Esta clase `DetalleEntradaMapperPersistencia` actúa como un
 * **mapeador** entre la entidad `DetalleEntrada` (el modelo de dominio) y el
 * DTO `DetalleEntradaDTOPersistencia` (el objeto de transferencia de datos para
 * persistencia). Su función principal es traducir datos de un formato a otro,
 * asegurando que la capa de persistencia trabaje con los DTOs, mientras que la
 * capa de negocio manipula las entidades. Implementa el patrón Singleton para
 * asegurar una única instancia del mapeador.
 */
public class DetalleEntradaMapperPersistencia implements IDetalleEntradaMapperPersistencia {

    private static DetalleEntradaMapperPersistencia instanceMapper;

    // Se instancia un mapeador de ingredientes para manejar la conversión de información de ingredientes anidada.
    IIngredienteMapperPersistencia ingredienteMapper = new IngredienteMapperPersistencia();

    /**
     * Implementa el patrón Singleton para proporcionar una única instancia de
     * este mapeador. Si la instancia aún no ha sido creada, la inicializa y la
     * devuelve; de lo contrario, retorna la instancia existente. Esto es útil
     * para optimizar recursos y asegurar que todos los componentes utilicen el
     * mismo objeto de mapeo.
     *
     * @return La única instancia de `DetalleEntradaMapperPersistencia`.
     */
    public static DetalleEntradaMapperPersistencia getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new DetalleEntradaMapperPersistencia();
        }
        return instanceMapper;
    }

    /**
     * Convierte un objeto `DetalleEntradaDTOPersistencia` (DTO de la capa de
     * persistencia) a su correspondiente entidad de dominio `DetalleEntrada`.
     * Este método se encarga de transferir los datos del DTO a la entidad,
     * incluyendo la conversión del ID del ingrediente de `String` a `ObjectId`,
     * que es el tipo utilizado en la entidad para interactuar con MongoDB.
     *
     * @param detallesDTO El DTO `DetalleEntradaDTOPersistencia` a convertir.
     * @return Un objeto `DetalleEntrada` que representa la entidad de dominio.
     */
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

    /**
     * Convierte un objeto de entidad `DetalleEntrada` a su DTO correspondiente
     * `DetalleEntradaDTOPersistencia`. Este método es crucial para preparar los
     * datos de las entidades para ser transferidos fuera de la capa de acceso a
     * datos, por ejemplo, a la capa de negocio o presentación. Incluye la
     * conversión del `ObjectId` del ingrediente a `String` y el mapeo de la
     * información del ingrediente anidada.
     *
     * @param detalles La entidad `DetalleEntrada` a convertir.
     * @return Un objeto `DetalleEntradaDTOPersistencia`.
     */
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

    /**
     * Convierte una lista de objetos `DetalleEntradaDTOPersistencia` a una
     * lista de entidades `DetalleEntrada`. Itera sobre la lista de DTOs,
     * aplicando el método `toEntity` a cada elemento para realizar la
     * conversión individual y construir la lista de entidades resultante.
     *
     * @param detallesDTOList La lista de DTOs a convertir.
     * @return Una `List` de objetos `DetalleEntrada`.
     */
    @Override
    public List<DetalleEntrada> toEntityList(List<DetalleEntradaDTOPersistencia> detallesDTOList) {
        List<DetalleEntrada> detallesList = new ArrayList<>();
        for (DetalleEntradaDTOPersistencia dto : detallesDTOList) {
            detallesList.add(toEntity(dto));
        }
        return detallesList;
    }

    /**
     * Convierte una lista de entidades `DetalleEntrada` a una lista de objetos
     * `DetalleEntradaDTOPersistencia`. Este método es vital para la
     * transferencia de colecciones de datos, asegurando que cada entidad se
     * transforme correctamente en su formato DTO antes de ser transferida.
     * Maneja el caso de una lista `null` para evitar `NullPointerException`.
     *
     * @param detallesList La lista de entidades `DetalleEntrada` a convertir.
     * @return Una `List` de objetos `DetalleEntradaDTOPersistencia`. Retorna
     * una lista vacía si la entrada es `null`.
     */
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
