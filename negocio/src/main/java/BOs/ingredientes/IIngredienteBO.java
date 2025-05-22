package BOs.ingredientes;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Interfaz de la clase IngredienteBO.
 *
 * @author norma
 */
public interface IIngredienteBO {

    /**
     * Agrega un ingrediente.
     *
     * @param ingrediente ingrediente a agregar.
     * @return true si se agregó correctamente.
     * @throws NegocioException en caso de error.
     */
    public boolean agregarIngrediente(IngredienteNuevoDTO ingrediente) throws NegocioException;

    /**
     * Edita el nombre de un ingrediente.
     *
     * @param idIngrediente ID del ingrediente a editar.
     * @param nombreNuevo Nuevo nombre.
     * @return Detalles actualizados.
     * @throws NegocioException en caso de error.
     */
    public DetallesIngredienteViejoDTO editarIngrediente(String idIngrediente, String nombreNuevo) throws NegocioException;

    /**
     * Busca ingredientes por nombre y stock.
     *
     * @param filtroNombre Nombre parcial.
     * @param filtroNivelStock Nivel de stock.
     * @return Lista de ingredientes encontrados.
     * @throws NegocioException en caso de error.
     */
    public List<IngredienteViejoListDTO> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) throws NegocioException;

    /**
     * Obtiene detalles de un ingrediente.
     *
     * @param idIngrediente ID del ingrediente que se quiere obtener.
     * @return Detalles del ingrediente.
     * @throws NegocioException en caso de error.
     */
    public DetallesIngredienteViejoDTO obtenerDetallesIngrediente(String idIngrediente) throws NegocioException;

    /**
     * Aumenta el stock de un ingrediente.
     *
     * @param idIngrediente ID del ingrediente.
     * @param cantidad Cantidad a aumentar.
     * @return true si se actualizó.
     * @throws NegocioException en caso de error.
     */
    public boolean aumentarStock(String idIngrediente, Double cantidad) throws NegocioException;

    /**
     * Reduce el stock de un ingrediente.
     *
     * @param idIngrediente ID del ingrediente.
     * @param cantidad Cantidad a reducir.
     * @return true si se actualizó.
     * @throws NegocioException en caso de error.
     */
    public boolean reducirStock(String idIngrediente, Double cantidad) throws NegocioException;

    /**
     * Verifica si existe un ingrediente por nombre.
     *
     * @param nombre Nombre a buscar.
     * @return true si existe.
     * @throws NegocioException en caso de error.
     */
    public boolean obtenerIngredientePorNombre(String nombre) throws NegocioException;

    /**
     * Actualiza el nivel de stock.
     *
     * @param idIngrediente ID del ingrediente.
     * @throws NegocioException en caso de error.
     */
    public void actualizarNivelStock(String idIngrediente) throws NegocioException;
}
