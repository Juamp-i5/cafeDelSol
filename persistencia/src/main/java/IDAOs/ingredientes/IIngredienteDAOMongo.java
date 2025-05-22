package IDAOs.ingredientes;

import DTOs.ingredientes.IngredienteDTOPersistencia;
import DTOs.ingredientes.DetallesIngredienteViejoDTOPersistencia;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import excepciones.PersistenciaIngredientesException;
import java.util.List;

/**
 *
 * @author norma
 */
public interface IIngredienteDAOMongo {
    
    public boolean agregarIngrediente(IngredienteDTOPersistencia ingrediente) throws PersistenciaIngredientesException;
        
    public DetallesIngredienteViejoDTOPersistencia editarIngrediente(String idIngrediente, String nombreNuevo) throws PersistenciaIngredientesException;
    
    public List<IngredienteDTOPersistencia> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) throws PersistenciaIngredientesException;
    
    public DetallesIngredienteViejoDTOPersistencia obtenerDetallesIngrediente(String idIngrediente) throws PersistenciaIngredientesException;
        
    public boolean aumentarStock(String idIngrediente, Double cantidad) throws PersistenciaIngredientesException;
    
    public boolean reducirStock(String idIngrediente, Double cantidad) throws PersistenciaIngredientesException;
    
    public void actualizarNivelStock(String idIngrediente) throws PersistenciaIngredientesException;
    
    public boolean obtenerIngredientePorNombre(String nombre) throws PersistenciaIngredientesException;
   
    public void descontarStock(String idIngrediente, double cantidad);
}
