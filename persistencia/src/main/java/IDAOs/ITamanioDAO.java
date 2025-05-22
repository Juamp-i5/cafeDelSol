package IDAOs;

import DTOs.PersistenciaTamanioDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 * Esta interfaz, `ITamanioDAO`, define la interfaz para las
 * operaciones de **acceso a datos (DAO)** relacionadas con la entidad `Tamanio`.
 * Establece un conjunto de métodos que cualquier implementación de este DAO
 * debe proporcionar, sin especificar los detalles de cómo se realizan esas
 * operaciones (por ejemplo, si es con MongoDB, SQL, etc.). Esto es un pilar
 * fundamental para la **separación de preocupaciones** en una arquitectura
 * por capas, ya que la capa de negocio solo interactúa con esta interfaz,
 * no con la implementación concreta de la base de datos.
 */
public interface ITamanioDAO {

    /**
     * Recupera una lista de todos los objetos `Tamanio` disponibles en la fuente de datos.
     * Este método es crucial para obtener una visión general de todos los tamaños registrados.
     *
     * @return Una `List` de `PersistenciaTamanioDTO` que representan todos los tamaños.
     * @throws PersistenciaException Si ocurre un error inesperado al interactuar con la fuente de datos.
     */
    public List<PersistenciaTamanioDTO> buscarTodos() throws PersistenciaException;

    /**
     * Busca y recupera un objeto `Tamanio` específico utilizando su nombre.
     * Este método es útil para encontrar un tamaño en particular basándose en un identificador textual.
     *
     * @param nombre El nombre del tamaño a buscar.
     * @return Un `PersistenciaTamanioDTO` si se encuentra un tamaño con el nombre especificado,
     * o `null` si no existe.
     * @throws PersistenciaException Si ocurre un error inesperado al interactuar con la fuente de datos.
     */
    public PersistenciaTamanioDTO buscarPorNombre(String nombre) throws PersistenciaException;

    /**
     * Guarda un nuevo `Tamanio` en la fuente de datos. Este método se utiliza
     * para persistir la información de un tamaño nuevo.
     *
     * @param tamanioDTO El `PersistenciaTamanioDTO` que contiene los datos del tamaño a guardar.
     * @throws PersistenciaException Si ocurre un error inesperado al intentar guardar el tamaño.
     */
    public void guardar(PersistenciaTamanioDTO tamanioDTO) throws PersistenciaException;
}
