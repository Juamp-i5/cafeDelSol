package BOs.ingredientes;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.ingredientes.DetallesIngredienteViejoDTOPersistencia;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import IDAOs.ingredientes.IIngredienteDAOMongo;
import acceso.AccesoDatos;
import excepciones.NegocioException;
import excepciones.PersistenciaIngredientesException;
import java.util.List;

/**
 * Clase IngredienteBO que usa los métodos de IngredienteDAO y mapea las
 * entidades o DTOs.
 *
 * @author norma
 */
public class IngredienteBO implements IIngredienteBO {

    IIngredienteDAOMongo ingredienteDAO = AccesoDatos.getIngredienteDAO();
    IIngredienteMapper ingredienteMapper = IngredienteMapper.getInstance();

    private static IngredienteBO instanceBO;

    /**
     * Construcor por defecto.
     */
    public IngredienteBO() {
    }

    /**
     * Obtiene la instancia de la clase.
     *
     * @return instanciaBO.
     */
    public static IngredienteBO getInstance() {
        if (instanceBO == null) {
            instanceBO = new IngredienteBO();
        }
        return instanceBO;
    }

    /**
     * Agrega un ingrediente.
     *
     * @param ingredienteDTO ingrediente a agregar.
     * @return true si se agregó correctamente.
     * @throws NegocioException en caso de error.
     */
    @Override
    public boolean agregarIngrediente(IngredienteNuevoDTO ingredienteDTO) throws NegocioException {
        try {
            return ingredienteDAO.agregarIngrediente(ingredienteMapper.toDTOPersistencia(ingredienteDTO));
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al agregar el ingrediente.");
        }
    }

    /**
     * Edita el nombre de un ingrediente.
     *
     * @param idIngrediente ID del ingrediente a editar.
     * @param nombreNuevo Nuevo nombre.
     * @return Detalles actualizados.
     * @throws NegocioException en caso de error.
     */
    @Override
    public DetallesIngredienteViejoDTO editarIngrediente(String idIngrediente, String nombreNuevo) throws NegocioException {
        try {
            DetallesIngredienteViejoDTOPersistencia ingrediente = ingredienteDAO.editarIngrediente(idIngrediente, nombreNuevo);
            return ingredienteMapper.toDTOIngredienteDetalles(ingrediente);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al editar el ingrediente.");
        }
    }

    /**
     * Busca ingredientes por nombre y stock.
     *
     * @param filtroNombre Nombre parcial.
     * @param filtroNivelStock Nivel de stock.
     * @return Lista de ingredientes encontrados.
     * @throws NegocioException en caso de error.
     */
    @Override
    public List<IngredienteViejoListDTO> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) throws NegocioException {
        try {
            if (filtroNombre != null) {
                filtroNombre = filtroNombre.trim();
            }
            List<IngredienteDTOPersistencia> ingredientes = ingredienteDAO.buscarIngredientesPorFiltros(filtroNombre, filtroNivelStock);
            return ingredienteMapper.toDTOIngredienteList(ingredientes);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al buscar los ingredientes por filtros.");
        }
    }

    /**
     * Obtiene detalles de un ingrediente.
     *
     * @param idIngrediente ID del ingrediente que se quiere obtener.
     * @return Detalles del ingrediente.
     * @throws NegocioException en caso de error.
     */
    @Override
    public DetallesIngredienteViejoDTO obtenerDetallesIngrediente(String idIngrediente) throws NegocioException {
        try {
            DetallesIngredienteViejoDTOPersistencia ingrediente = ingredienteDAO.obtenerDetallesIngrediente(idIngrediente);
            return ingredienteMapper.toDTOIngredienteDetalles(ingrediente);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al buscar los detalles del ingrediente.");
        }
    }

    /**
     * Aumenta el stock de un ingrediente.
     *
     * @param idIngrediente ID del ingrediente.
     * @param cantidad Cantidad a aumentar.
     * @return true si se actualizó.
     * @throws NegocioException en caso de error.
     */
    @Override
    public boolean aumentarStock(String idIngrediente, Double cantidad) throws NegocioException {
        try {
            return ingredienteDAO.aumentarStock(idIngrediente, cantidad);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al aumentar el stock del ingrediente.");
        }
    }

    /**
     * Reduce el stock de un ingrediente.
     *
     * @param idIngrediente ID del ingrediente.
     * @param cantidad Cantidad a reducir.
     * @return true si se actualizó.
     * @throws NegocioException en caso de error.
     */
    @Override
    public boolean reducirStock(String idIngrediente, Double cantidad) throws NegocioException {
        try {
            return ingredienteDAO.reducirStock(idIngrediente, cantidad);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al reducir el stock del ingrediente.");
        }
    }

    /**
     * Verifica si existe un ingrediente por nombre.
     *
     * @param nombre Nombre a buscar.
     * @return true si existe.
     * @throws NegocioException en caso de error.
     */
    @Override
    public boolean obtenerIngredientePorNombre(String nombre) throws NegocioException {
        try {
            return ingredienteDAO.obtenerIngredientePorNombre(nombre);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al obtener el ingrediente por nombre.");
        }
    }

    /**
     * Actualiza el nivel de stock.
     *
     * @param idIngrediente ID del ingrediente.
     * @throws NegocioException en caso de error.
     */
    @Override
    public void actualizarNivelStock(String idIngrediente) throws NegocioException {
        try {
            ingredienteDAO.actualizarNivelStock(idIngrediente);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al actualizar el nivel de stock.");
        }
    }
}
