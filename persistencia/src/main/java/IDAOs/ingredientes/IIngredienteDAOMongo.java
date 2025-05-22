package IDAOs.ingredientes;

import DTOs.ingredientes.DetallesIngredienteViejoDTOPersistencia;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import excepciones.PersistenciaIngredientesException;
import java.util.List;

/**
 * Interfaz de la clase IngredienteDAOMongo
 *
 * @author norma
 */
public interface IIngredienteDAOMongo {

    /**
     * Agrega un nuevo ingrediente a la colección.
     *
     * @param ingrediente ingrediente a guardar.
     * @return true si se agregó correctamente.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * operación.
     */
    public boolean agregarIngrediente(IngredienteDTOPersistencia ingrediente) throws PersistenciaIngredientesException;

    /**
     * Edita el nombre de un ingrediente existente.
     *
     * @param idIngrediente ID del ingrediente a editar.
     * @param nombreNuevo Nuevo nombre del ingrediente.
     * @return ingrediente con todos sus detalles actualizados.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * operación.
     */
    public DetallesIngredienteViejoDTOPersistencia editarIngrediente(String idIngrediente, String nombreNuevo) throws PersistenciaIngredientesException;

    /**
     * Busca ingredientes que coincidan con los filtros de nombre y nivel de
     * stock.
     *
     * @param filtroNombre Filtro por nombre (expresión regular).
     * @param filtroNivelStock Filtro por nivel de stock.
     * @return lista de ingredientes buscados.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * búsqueda.
     */
    public List<IngredienteDTOPersistencia> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) throws PersistenciaIngredientesException;

    /**
     * Obtiene los detalles de un ingrediente, incluyendo información del
     * proveedor.
     *
     * @param idIngrediente ID del ingrediente a consultar.
     * @return ingrediente con todos sus detalles.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * operación.
     */
    public DetallesIngredienteViejoDTOPersistencia obtenerDetallesIngrediente(String idIngrediente) throws PersistenciaIngredientesException;

    /**
     * Aumenta la cantidad disponible de un ingrediente y actualiza el nivel de
     * stock.
     *
     * @param idIngrediente ID del ingrediente.
     * @param cantidad Cantidad a aumentar.
     * @return true si se actualizó correctamente.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * operación.
     */
    public boolean aumentarStock(String idIngrediente, Double cantidad) throws PersistenciaIngredientesException;

    /**
     * Reduce la cantidad disponible de un ingrediente, validando que no sea
     * negativa.
     *
     * @param idIngrediente ID del ingrediente.
     * @param cantidad Cantidad a reducir.
     * @return true si se redujo correctamente.
     * @throws PersistenciaIngredientesException Si la cantidad es insuficiente
     * o hay un error.
     */
    public boolean reducirStock(String idIngrediente, Double cantidad) throws PersistenciaIngredientesException;

    /**
     * Actualiza el campo "nivelStock" de un ingrediente dependiendo de su
     * cantidad disponible actual.
     *
     * @param idIngrediente ID del ingrediente a actualizar.
     * @throws PersistenciaIngredientesException Si ocurre un error en la
     * actualización.
     */
    public void actualizarNivelStock(String idIngrediente) throws PersistenciaIngredientesException;

    /**
     * Verifica si existe un ingrediente por nombre exacto.
     *
     * @param nombre Nombre del ingrediente a buscar.
     * @return true si existe, false en caso contrario.
     * @throws PersistenciaIngredientesException Si ocurre un error en la
     * búsqueda.
     */
    public boolean obtenerIngredientePorNombre(String nombre) throws PersistenciaIngredientesException;

    /**
     * Descuenta directamente una cantidad del stock de un ingrediente.
     *
     * @param idIngrediente ID del ingrediente.
     * @param cantidad Cantidad a descontar.
     */
    public void descontarStock(String idIngrediente, double cantidad);
}
