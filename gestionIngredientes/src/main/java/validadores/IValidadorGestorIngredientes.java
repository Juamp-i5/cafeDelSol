package validadores;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import excepciones.GestionIngredientesException;

/**
 *
 * @author norma
 */
public interface IValidadorGestorIngredientes {

    public void validarAgregarIngrediente(IngredienteNuevoDTO ingrediente) throws GestionIngredientesException;

    public void validadCantidadMaximaCaracteres(String nombre) throws GestionIngredientesException;

    public void validadCantidadMaximaDisponibleSuperada(Double cantidad, String unidadMedida) throws GestionIngredientesException;

    public void validadCantidadMinimaSuperada(Double cantidad, String unidadMedida) throws GestionIngredientesException;
    
    public void validarEditarIngrediente(String nombre) throws GestionIngredientesException;

    public void validarIdIngrediente(String idIngrediente) throws GestionIngredientesException;
    
    public void validarCantidadNegativa(double cantidad);
}
