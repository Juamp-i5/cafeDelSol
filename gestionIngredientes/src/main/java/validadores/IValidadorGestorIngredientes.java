package validadores;

import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import excepciones.GestionCRUDIngredientesException;

/**
 * Interfaz para validadores de gestión de ingredientes.
 *
 * @author norma
 */
public interface IValidadorGestorIngredientes {

    /**
     * Valida el agregar de un nuevo ingrediente.
     *
     * @param ingrediente ingrediente a validar.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    public void validarAgregarIngrediente(IngredienteNuevoDTO ingrediente) throws GestionCRUDIngredientesException;

    /**
     * Valida que el nombre no supere la cantidad máxima de caracteres.
     *
     * @param nombre nombre a validar.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    public void validadCantidadMaximaCaracteres(String nombre) throws GestionCRUDIngredientesException;

    /**
     * Valida que la cantidad no supere el máximo permitido.
     *
     * @param cantidad cantidad a validar.
     * @param unidadMedida unidad de medida asociada.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    public void validadCantidadMaximaDisponibleSuperada(Double cantidad, String unidadMedida) throws GestionCRUDIngredientesException;

    /**
     * Valida que la cantidad no esté por debajo del mínimo permitido.
     *
     * @param cantidad cantidad a validar.
     * @param unidadMedida unidad de medida asociada.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    public void validadCantidadMinimaSuperada(Double cantidad, String unidadMedida) throws GestionCRUDIngredientesException;

    /**
     * Valida la edición de un ingrediente por nombre.
     *
     * @param nombre nombre a validar.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    public void validarEditarIngrediente(String nombre) throws GestionCRUDIngredientesException;

    /**
     * Valida el identificador de un ingrediente.
     *
     * @param idIngrediente id a validar.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    public void validarIdIngrediente(String idIngrediente) throws GestionCRUDIngredientesException;

    /**
     * Valida que la cantidad no sea negativa.
     *
     * @param cantidad cantidad a validar.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    public void validarCantidadNegativa(double cantidad) throws GestionCRUDIngredientesException;
}
