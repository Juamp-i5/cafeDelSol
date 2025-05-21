package BOs.ingredientes;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDIngredientes.NivelStock;
import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import DTOs.ingredientes.DetallesIngredienteViejoDTOPersistencia;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import IDAOs.ingredientes.IIngredienteDAOMongo;
import acceso.AccesoDatos;
import excepciones.NegocioException;
import excepciones.PersistenciaIngredientesException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author norma
 */
public class IngredienteBO implements IIngredienteBO {

    IIngredienteDAOMongo ingredienteDAO = AccesoDatos.getIngredienteDAO();
    IIngredienteMapper ingredienteMapper = IngredienteMapper.getInstance();

    private static IngredienteBO instanceBO;

    public IngredienteBO() {
    }

    public static IngredienteBO getInstance() {
        if (instanceBO == null) {
            instanceBO = new IngredienteBO();
        }
        return instanceBO;
    }

    @Override
    public boolean agregarIngrediente(IngredienteNuevoDTO ingredienteDTO) throws NegocioException {
        try {
            return ingredienteDAO.agregarIngrediente(ingredienteMapper.toDTOPersistencia(ingredienteDTO));
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al agregar el ingrediente.");
        }
    }

    @Override
    public DetallesIngredienteViejoDTO editarIngrediente(String idIngrediente, String nombreNuevo) throws NegocioException {
        try {
            DetallesIngredienteViejoDTOPersistencia ingrediente = ingredienteDAO.editarIngrediente(idIngrediente, nombreNuevo);
            return ingredienteMapper.toDTOIngredienteDetalles(ingrediente);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al editar el ingrediente.");
        }
    }

    @Override
    public List<IngredienteViejoListDTO> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) throws NegocioException {
        try {
            List<IngredienteDTOPersistencia> ingredientes = ingredienteDAO.buscarIngredientesPorFiltros(filtroNombre, filtroNivelStock);
            return ingredienteMapper.toDTOIngredienteList(ingredientes);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al buscar los ingredientes por filtros.");
        }
    }

    @Override
    public DetallesIngredienteViejoDTO obtenerDetallesIngrediente(String idIngrediente) throws NegocioException {
        try {
            DetallesIngredienteViejoDTOPersistencia ingrediente = ingredienteDAO.obtenerDetallesIngrediente(idIngrediente);
            return ingredienteMapper.toDTOIngredienteDetalles(ingrediente);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al buscar los detalles del ingrediente.");
        }
    }

    @Override
    public boolean aumentarStock(String idIngrediente, Double cantidad) throws NegocioException {
        try {
            return ingredienteDAO.aumentarStock(idIngrediente, cantidad);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al aumentar el stock del ingrediente.");
        }
    }

    @Override
    public boolean reducirStock(String idIngrediente, Double cantidad) throws NegocioException {
        try {
            return ingredienteDAO.reducirStock(idIngrediente, cantidad);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al reducir el stock del ingrediente.");
        }
    }

    @Override
    public boolean obtenerIngredientePorNombre(String nombre) throws NegocioException {
        try {
            return ingredienteDAO.obtenerIngredientePorNombre(nombre);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al obtener el ingrediente por nombre.");
        }
    }

    @Override
    public void actualizarNivelStock(String idIngrediente) throws NegocioException {
        try {
            ingredienteDAO.actualizarNivelStock(idIngrediente);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al actualizar el nivel de stock.");
        }
    }
}
