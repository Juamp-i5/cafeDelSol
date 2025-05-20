package BOs.ingredientes;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDIngredientes.NivelStock;
import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author norma
 */
public interface IIngredienteBO {
        
    public boolean agregarIngrediente(IngredienteNuevoDTO ingrediente) throws NegocioException;
        
    public DetallesIngredienteViejoDTO editarIngrediente(String idIngrediente, String nombreNuevo) throws NegocioException;
    
    public List<IngredienteViejoListDTO> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) throws NegocioException;
    
    public DetallesIngredienteViejoDTO obtenerDetallesIngrediente(String idIngrediente) throws NegocioException;
    
    public boolean aumentarStock(String idIngrediente, Double cantidad) throws NegocioException;
    
    public boolean reducirStock(String idIngrediente,Double cantidad) throws NegocioException;
        
    public boolean obtenerIngredientePorNombre(String nombre) throws NegocioException;
    
    public void actualizarNivelStock(String idIngrediente)throws NegocioException;
}
