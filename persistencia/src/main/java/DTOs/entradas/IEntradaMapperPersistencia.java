package DTOs.entradas;

import entidades.Entrada;
import java.util.List;

/**
 *
 * @author pablo
 * Esta interfaz define el contrato para un **mapeador (mapper)** de objetos
 * `Entrada`. Su propósito es establecer los métodos necesarios para la conversión
 * bidireccional entre la entidad de dominio `Entrada` y sus diferentes
 * Objetos de Transferencia de Datos (DTOs) específicos para la capa de persistencia:
 * `EntradaNuevaDTOPersistencia` (para creaciones) y `EntradaViejaDTOPersistencia`
 * (para lecturas/actualizaciones). Al separar estas responsabilidades,
 * se mantiene la limpieza en el código y se facilita el flujo de datos
 * entre las capas de la aplicación.
 */
public interface IEntradaMapperPersistencia {

    /**
     * Convierte un `EntradaNuevaDTOPersistencia` a una entidad `Entrada`.
     * Este método se utiliza cuando se va a persistir una nueva entrada en la base de datos,
     * transformando los datos del DTO a la estructura que la entidad de dominio requiere.
     *
     * @param entradaDTO El DTO para una nueva entrada.
     * @return La entidad `Entrada` lista para su persistencia.
     */
    public Entrada toEntityNuevo(EntradaNuevaDTOPersistencia entradaDTO);

    /**
     * Convierte una entidad `Entrada` a un `EntradaNuevaDTOPersistencia`.
     * Este método es útil para transformar una entidad de dominio a un formato
     * que representa una "nueva entrada" para la transferencia de datos, por ejemplo,
     * para casos donde una entidad se procesa internamente antes de su creación formal.
     *
     * @param entrada La entidad `Entrada` a convertir.
     * @return El DTO `EntradaNuevaDTOPersistencia`.
     */
    public EntradaNuevaDTOPersistencia todtoNuevoPersistencia(Entrada entrada);

    /**
     * Convierte una lista de `EntradaNuevaDTOPersistencia` a una lista de entidades `Entrada`.
     * Permite el mapeo por lotes de múltiples DTOs de nuevas entradas a sus correspondientes
     * entidades de dominio, facilitando operaciones de persistencia masivas.
     *
     * @param entradasDTO La lista de DTOs de nuevas entradas.
     * @return Una lista de entidades `Entrada`.
     */
    public List<Entrada> toEntityNuevoList(List<EntradaNuevaDTOPersistencia> entradasDTO);

    /**
     * Convierte una lista de entidades `Entrada` a una lista de `EntradaNuevaDTOPersistencia`.
     * Es útil para transformar una colección de entidades de dominio a un formato de DTO
     * que represente "nuevas entradas" para su transferencia o procesamiento posterior.
     *
     * @param entradas La lista de entidades `Entrada`.
     * @return Una lista de DTOs `EntradaNuevaDTOPersistencia`.
     */
    public List<EntradaNuevaDTOPersistencia> todtoNuevoPersistenciaList(List<Entrada> entradas);

    /**
     * Convierte un `EntradaViejaDTOPersistencia` (que incluye un ID) a una entidad `Entrada`.
     * Este método es fundamental cuando se recuperan registros existentes de la base de datos,
     * ya que transforma el DTO con ID a la entidad de dominio completa.
     *
     * @param entradaDTO El DTO de una entrada existente.
     * @return La entidad `Entrada` completa, incluyendo su ID.
     */
    public Entrada toEntityViejo(EntradaViejaDTOPersistencia entradaDTO);

    /**
     * Convierte una entidad `Entrada` a un `EntradaViejaDTOPersistencia` (que incluye un ID).
     * Este método es esencial para preparar los datos de una entrada existente (con su ID)
     * para su transferencia a otras capas de la aplicación o para ser devuelta como resultado de una consulta.
     *
     * @param entrada La entidad `Entrada` a convertir.
     * @return El DTO `EntradaViejaDTOPersistencia`.
     */
    public EntradaViejaDTOPersistencia todtoViejoPersistencia(Entrada entrada);

    /**
     * Convierte una lista de `EntradaViejaDTOPersistencia` a una lista de entidades `Entrada`.
     * Este método facilita el mapeo por lotes de DTOs de entradas existentes (con IDs)
     * a sus entidades de dominio, ideal para cargar múltiples registros de la base de datos.
     *
     * @param entradasDTO La lista de DTOs de entradas existentes.
     * @return Una lista de entidades `Entrada`.
     */
    public List<Entrada> toEntityViejoList(List<EntradaViejaDTOPersistencia> entradasDTO);

    /**
     * Convierte una lista de entidades `Entrada` a una lista de `EntradaViejaDTOPersistencia`.
     * Se utiliza para transformar colecciones completas de entidades de dominio (con IDs)
     * a un formato de DTO adecuado para su transferencia, como la respuesta a una consulta.
     *
     * @param entradas La lista de entidades `Entrada`.
     * @return Una lista de DTOs `EntradaViejaDTOPersistencia`.
     */
    public List<EntradaViejaDTOPersistencia> todtoViejoPersistenciaList(List<Entrada> entradas);
}
