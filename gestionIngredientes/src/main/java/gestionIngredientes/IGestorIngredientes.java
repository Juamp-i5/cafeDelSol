package gestionIngredientes;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDIngredientes.NivelStock;
import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import excepciones.GestionIngredientesException;
import java.util.List;

/**
 *
 * @author norma
 */
public interface IGestorIngredientes {

    public boolean agregarIngrediente(IngredienteNuevoDTO ingrediente) throws GestionIngredientesException;

    public DetallesIngredienteViejoDTO editarIngrediente(String idIngrediente, String nombreNuevo) throws GestionIngredientesException;

    public DetallesIngredienteViejoDTO obtenerDetallesIngrediente(String idIngrediente) throws GestionIngredientesException;

    public List<IngredienteViejoListDTO> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) throws GestionIngredientesException;

    public List<ProveedorViejoDTO> obtenerProveedores() throws GestionIngredientesException;

    public boolean aumentarStock(String idIngrediente, Double cantidad) throws GestionIngredientesException;

    public boolean reducirStock(String idIngrediente, Double cantidad) throws GestionIngredientesException;

}
