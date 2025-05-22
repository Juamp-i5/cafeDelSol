package BOs.ingredientes;

import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import DTOs.ingredientes.ProveedorDTOPersistencia;
import IDAOs.ingredientes.IProveedorDAOMongo;
import acceso.AccesoDatos;
import excepciones.NegocioException;
import excepciones.PersistenciaIngredientesException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase ProveedorBO que usa los métodos de ProveedorDAO y mapea las
 * entidades o DTOs.
 * @author norma
 */
public class ProveedorBO implements IProveedorBO {

    IProveedorDAOMongo proveedorDAO = AccesoDatos.getProveedorDAO();
    IProveedorMapper proveedorMapper = ProveedorMapper.getInstance();

    private static ProveedorBO instanceBO;

    /**
     * Constructor por defecto.
     */
    public ProveedorBO() {
    }

    /**
     * Obtiene la instancia de la clase.
     *
     * @return instanciaBO.
     */
    public static ProveedorBO getInstance() {
        if (instanceBO == null) {
            instanceBO = new ProveedorBO();
        }
        return instanceBO;
    }

    /**
     * Obtiene todos los proveedores.
     *
     * @return Lista de proveedores.
     * @throws NegocioException en caso de error.
     */
    @Override
    public List<ProveedorViejoDTO> obtenerProveedores() throws NegocioException {
        try {
            List<ProveedorDTOPersistencia> proveedores = proveedorDAO.obtenerProveedores();
            return proveedorMapper.toDTOList(proveedores);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al obtener los proveedores.");
        }
    }

}
