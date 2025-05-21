package gestionIngredientes;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDIngredientes.NivelStock;
import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import excepciones.GestionCRUDIngredientesException;
import java.util.List;

/**
 *
 * @author norma
 */
public interface IGestorCRUDIngredientes {

    public boolean agregarIngrediente(IngredienteNuevoDTO ingrediente) throws GestionCRUDIngredientesException;

    public DetallesIngredienteViejoDTO editarIngrediente(String idIngrediente, String nombreNuevo) throws GestionCRUDIngredientesException;

    public DetallesIngredienteViejoDTO obtenerDetallesIngrediente(String idIngrediente) throws GestionCRUDIngredientesException;

    public List<IngredienteViejoListDTO> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) throws GestionCRUDIngredientesException;

    public List<ProveedorViejoDTO> obtenerProveedores() throws GestionCRUDIngredientesException;

    public boolean aumentarStock(String idIngrediente, Double cantidad) throws GestionCRUDIngredientesException;

    public boolean reducirStock(String idIngrediente, Double cantidad) throws GestionCRUDIngredientesException;

    public void actualizarNivelStock(String idIngrediente) throws GestionCRUDIngredientesException;
    
    public DetallesIngredienteViejoDTO obtenerIngredientePorNombre(String nombre) throws GestionCRUDIngredientesException;
    
    public String obtenerIdIngredientePorNombre(String nombre) throws GestionCRUDIngredientesException;

}
