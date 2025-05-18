package validadores;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import excepciones.GestionCRUDIngredientesException;

/**
 *
 * @author norma
 */
public interface IValidadorGestorIngredientes {

    public void validarAgregarIngrediente(IngredienteNuevoDTO ingrediente) throws GestionCRUDIngredientesException;

    public void validadCantidadMaximaCaracteres(String nombre) throws GestionCRUDIngredientesException;

    public void validadCantidadMaximaDisponibleSuperada(Double cantidad, String unidadMedida) throws GestionCRUDIngredientesException;

    public void validadCantidadMinimaSuperada(Double cantidad, String unidadMedida) throws GestionCRUDIngredientesException;
    
    public void validarEditarIngrediente(String nombre) throws GestionCRUDIngredientesException;

    public void validarIdIngrediente(String idIngrediente) throws GestionCRUDIngredientesException;
    
    public void validarCantidadNegativa(double cantidad) throws GestionCRUDIngredientesException;
}
