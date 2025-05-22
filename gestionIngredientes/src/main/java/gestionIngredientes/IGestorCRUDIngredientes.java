package gestionIngredientes;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDIngredientes.NivelStock;
import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import excepciones.GestionCRUDIngredientesException;
import java.util.List;

/**
 * Interfaz para la gestión ingredientes.
 * @author norma
 */
public interface IGestorCRUDIngredientes {

     /**
     * Agrega un nuevo ingrediente.
     * @param ingrediente ingrediente a agregar.
     * @return true si se agregó correctamente.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    public boolean agregarIngrediente(IngredienteNuevoDTO ingrediente) throws GestionCRUDIngredientesException;

    /**
     * Edita el nombre de un ingrediente existente.
     * @param idIngrediente id del ingrediente.
     * @param nombreNuevo nuevo nombre.
     * @return detalles del ingrediente actualizado.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    public DetallesIngredienteViejoDTO editarIngrediente(String idIngrediente, String nombreNuevo) throws GestionCRUDIngredientesException;

     /**
     * Obtiene los detalles de un ingrediente por su ID.
     * @param idIngrediente id del ingrediente.
     * @return detalles del ingrediente.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    public DetallesIngredienteViejoDTO obtenerDetallesIngrediente(String idIngrediente) throws GestionCRUDIngredientesException;

     /**
     * Busca ingredientes usando filtros.
     * @param filtroNombre filtro por nombre.
     * @param filtroNivelStock filtro por nivel de stock.
     * @return lista de ingredientes encontrados.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    public List<IngredienteViejoListDTO> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) throws GestionCRUDIngredientesException;

     /**
     * Obtiene la lista de proveedores.
     * @return lista de proveedores.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    public List<ProveedorViejoDTO> obtenerProveedores() throws GestionCRUDIngredientesException;

     /**
     * Aumenta el stock de un ingrediente.
     * @param idIngrediente id del ingrediente.
     * @param cantidad cantidad a aumentar.
     * @return true si se aumentó correctamente.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    public boolean aumentarStock(String idIngrediente, Double cantidad) throws GestionCRUDIngredientesException;

    /**
     * Reduce el stock de un ingrediente.
     * @param idIngrediente id del ingrediente.
     * @param cantidad cantidad a reducir.
     * @return true si se redujo correctamente.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    public boolean reducirStock(String idIngrediente, Double cantidad) throws GestionCRUDIngredientesException;

      /**
     * Actualiza el nivel de stock de un ingrediente.
     * @param idIngrediente id del ingrediente.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    public void actualizarNivelStock(String idIngrediente) throws GestionCRUDIngredientesException;
    
    /**
     * Obtiene un ingrediente por su nombre.
     * @param nombre nombre del ingrediente.
     * @return detalles del ingrediente.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    public DetallesIngredienteViejoDTO obtenerIngredientePorNombre(String nombre) throws GestionCRUDIngredientesException;
    
    public String obtenerIdIngredientePorNombre(String nombre) throws GestionCRUDIngredientesException;

}
