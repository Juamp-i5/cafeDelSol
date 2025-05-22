package IDAOs.entradas;

import DTOs.entradas.EntradaNuevaDTOPersistencia;
import DTOs.entradas.EntradaViejaDTOPersistencia;
import excepciones.PersistenciaEntradasException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author pablo
 * Interfaz que define el contrato para las operaciones de acceso a datos (DAO)
 * relacionadas con las 'Entradas' en el sistema.
 * Un DAO (Data Access Object) es un patrón de diseño que abstrae y encapsula
 * todo el acceso a la fuente de datos.
 * Esta interfaz asegura que cualquier clase que la implemente proporcionará
 * los métodos definidos aquí para interactuar con los datos de las entradas.
 */
public interface IEntradaDAO {

    /**
     * Registra una nueva entrada en la base de datos.
     *
     * @param entrada Objeto EntradaNuevaDTOPersistencia que contiene los datos de la nueva entrada a registrar.
     * @return true si la entrada se registró exitosamente, false en caso contrario.
     * @throws PersistenciaEntradasException Si ocurre un error durante el proceso de persistencia (ej. problema de conexión a la BD, datos inválidos).
     */
    public boolean registrarEntrada(EntradaNuevaDTOPersistencia entrada) throws PersistenciaEntradasException;

    /**
     * Obtiene los detalles de una entrada existente, incluyendo la información de sus ingredientes,
     * basándose en su identificador único.
     *
     * @param entradaId El ID único (String) de la entrada que se desea consultar.
     * @return Un objeto EntradaViejaDTOPersistencia que contiene todos los detalles de la entrada y sus ingredientes asociados.
     * @throws PersistenciaEntradasException Si la entrada no se encuentra o si ocurre un error durante la consulta.
     */
    public EntradaViejaDTOPersistencia obtenerDetallesConIngredientes(String entradaId) throws PersistenciaEntradasException;

    /**
     * Obtiene una lista de entradas que se encuentran dentro de un rango de fechas y horas específico.
     * Esto es útil para generar reportes o historiales de entradas en un período dado.
     *
     * @param fechaInicio La fecha y hora de inicio del rango de búsqueda (inclusive).
     * @param fechaFin La fecha y hora de fin del rango de búsqueda (inclusive).
     * @return Una List de objetos EntradaViejaDTOPersistencia que cumplen con el criterio de fecha.
     * @throws PersistenciaEntradasException Si ocurre un error durante la consulta de las entradas.
     */
    public List<EntradaViejaDTOPersistencia> obtenerEntradasPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaEntradasException;
}
