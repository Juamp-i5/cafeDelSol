package DTOs.entradas;

import entidades.DetalleEntrada;
import java.util.List;

/**
 *
 * @author pablo Esta interfaz define el contrato para un **mapeador (mapper)**
 * de objetos `DetalleEntrada` y `DetalleEntradaDTOPersistencia`. Su propósito
 * principal es establecer los métodos necesarios para la conversión
 * bidireccional entre las entidades del dominio (`DetalleEntrada`) y los
 * objetos de transferencia de datos específicos para la capa de persistencia
 * (`DetalleEntradaDTOPersistencia`). Esto permite desacoplar la lógica de
 * negocio de los detalles de almacenamiento y facilita el flujo de datos entre
 * las distintas capas de la aplicación.
 */
public interface IDetalleEntradaMapperPersistencia {

    /**
     * Convierte un objeto `DetalleEntradaDTOPersistencia` (DTO) a su
     * correspondiente entidad de dominio `DetalleEntrada`. Este método es
     * fundamental para transformar los datos que llegan de la capa de
     * persistencia (o que se preparan para ir a ella) en el formato que maneja
     * la lógica de negocio.
     *
     * @param detallesDTO El DTO de detalle de entrada a convertir.
     * @return La entidad `DetalleEntrada` resultante.
     */
    public DetalleEntrada toEntity(DetalleEntradaDTOPersistencia detallesDTO);

    /**
     * Convierte una entidad de dominio `DetalleEntrada` a su correspondiente
     * `DetalleEntradaDTOPersistencia` (DTO). Este método se utiliza para
     * preparar los datos de las entidades antes de que sean enviados a la capa
     * de persistencia o a otras capas de la aplicación que esperan el formato
     * DTO.
     *
     * @param detalles La entidad `DetalleEntrada` a convertir.
     * @return El DTO `DetalleEntradaDTOPersistencia` resultante.
     */
    public DetalleEntradaDTOPersistencia todtoPersistencia(DetalleEntrada detalles);

    /**
     * Convierte una lista de objetos `DetalleEntradaDTOPersistencia` (DTOs) a
     * una lista de entidades de dominio `DetalleEntrada`. Esta operación es
     * útil para manejar colecciones de datos en una sola llamada, transformando
     * múltiples DTOs a entidades simultáneamente.
     *
     * @param detallesDTOList La lista de DTOs de detalle de entrada a
     * convertir.
     * @return Una `List` de entidades `DetalleEntrada`.
     */
    public List<DetalleEntrada> toEntityList(List<DetalleEntradaDTOPersistencia> detallesDTOList);

    /**
     * Convierte una lista de entidades de dominio `DetalleEntrada` a una lista
     * de `DetalleEntradaDTOPersistencia` (DTOs). Este método es esencial para
     * preparar colecciones completas de entidades para su transferencia o
     * persistencia, asegurando que todos los elementos se conviertan al formato
     * DTO.
     *
     * @param detallesList La lista de entidades `DetalleEntrada` a convertir.
     * @return Una `List` de DTOs `DetalleEntradaDTOPersistencia`.
     */
    public List<DetalleEntradaDTOPersistencia> todtoPersistenciaList(List<DetalleEntrada> detallesList);
}
