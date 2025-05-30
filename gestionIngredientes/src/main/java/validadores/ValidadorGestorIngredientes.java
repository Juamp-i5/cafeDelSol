package validadores;

import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import excepciones.CantidadMaximaCaracteresSuperada;
import excepciones.CantidadMaximaDisponibleSuperadaException;
import excepciones.CantidadMinimaDisponibleSuperadaException;
import excepciones.GestionCRUDIngredientesException;

/**
 * Validador de gestión ingredientes.
 *
 * @author norma
 */
public class ValidadorGestorIngredientes implements IValidadorGestorIngredientes {

    private static final int CANTIDAD_MAX_CARACTERES = 30;
    private static final Double CANTIDAD_MAX_DISPONIBLE = 10000.0;
    private static final Double CANTIDAD_MAX_MINIMA = 10.0;

    /**
     * Valida el agregar de un nuevo ingrediente.
     *
     * @param ingrediente ingrediente a validar.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    @Override
    public void validarAgregarIngrediente(IngredienteNuevoDTO ingrediente) throws GestionCRUDIngredientesException {
        if (ingrediente == null) {
            throw new GestionCRUDIngredientesException("El DTO del ingrediente no puede ser nulo.");
        }

        if (ingrediente.getNombre() == null || ingrediente.getNombre().trim().isEmpty()) {
            throw new GestionCRUDIngredientesException("El nombre del ingrediente es obligatorio.");
        }
        if (!ingrediente.getNombre().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new GestionCRUDIngredientesException("El nombre del ingrediente solo puede contener letras y espacios.");
        }
        validadCantidadMaximaCaracteres(ingrediente.getNombre());
        if (ingrediente.getCantidadDisponible() == null) {
            throw new GestionCRUDIngredientesException("La cantidad disponible del ingrediente es obligatoria.");
        }
        validadCantidadMaximaDisponibleSuperada(ingrediente.getCantidadDisponible(), ingrediente.getUnidadMedida().toString());
        if (ingrediente.getCantidadDisponible() < 0) {
            throw new GestionCRUDIngredientesException("La cantidad disponible no puede ser negativa.");
        }

        if (ingrediente.getCantidadMinima() == null) {
            throw new GestionCRUDIngredientesException("La cantidad mínima del ingrediente es obligatoria.");
        }
        validadCantidadMinimaSuperada(ingrediente.getCantidadMinima(), ingrediente.getUnidadMedida().toString());
        if (ingrediente.getCantidadMinima() < 0) {
            throw new GestionCRUDIngredientesException("La cantidad mínima no puede ser negativa.");
        }

        if (ingrediente.getUnidadMedida() == null) {
            throw new GestionCRUDIngredientesException("La unidad de medida del ingrediente es obligatoria.");
        }

//        if (ingrediente.getProveedor() == null) {
//            throw new IllegalArgumentException("El proveedor del ingrediente es obligatorio.");
//        }
    }

    /**
     * Valida que el nombre no supere la cantidad máxima de caracteres.
     *
     * @param nombre nombre a validar.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    @Override
    public void validadCantidadMaximaCaracteres(String nombre) throws GestionCRUDIngredientesException {
        if (nombre.length() > CANTIDAD_MAX_CARACTERES) {
            throw new CantidadMaximaCaracteresSuperada("El nombre del ingrediente no puede exceder los " + CANTIDAD_MAX_CARACTERES + " caracteres.");
        }
    }

    /**
     * Valida que la cantidad no supere el máximo permitido.
     *
     * @param cantidad cantidad a validar.
     * @param unidadMedida unidad de medida asociada.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    @Override
    public void validadCantidadMaximaDisponibleSuperada(Double cantidad, String unidadMedida) throws GestionCRUDIngredientesException {
        if (cantidad > CANTIDAD_MAX_DISPONIBLE) {
            throw new CantidadMaximaDisponibleSuperadaException("La cantidad disponible del ingrediente no puede exceder los " + CANTIDAD_MAX_DISPONIBLE + " " + unidadMedida + ".");
        }
    }

    /**
     * Valida que la cantidad no esté por debajo del mínimo permitido.
     *
     * @param cantidad cantidad a validar.
     * @param unidadMedida unidad de medida asociada.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    @Override
    public void validadCantidadMinimaSuperada(Double cantidad, String unidadMedida) throws GestionCRUDIngredientesException {
        if (cantidad < CANTIDAD_MAX_MINIMA) {
            throw new CantidadMinimaDisponibleSuperadaException("La cantidad mínima del ingrediente no puede ser menor que " + CANTIDAD_MAX_MINIMA + " " + unidadMedida + ".");
        }
    }

    /**
     * Valida la edición de un ingrediente por nombre.
     *
     * @param nombre nombre a validar.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    @Override
    public void validarEditarIngrediente(String nombre) throws GestionCRUDIngredientesException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new GestionCRUDIngredientesException("El nombre del ingrediente es obligatorio.");
        }
        validadCantidadMaximaCaracteres(nombre);
        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            throw new GestionCRUDIngredientesException("El nombre del ingrediente solo puede contener letras.");
        }
    }

    /**
     * Valida el identificador de un ingrediente.
     *
     * @param idIngrediente id a validar.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    @Override
    public void validarIdIngrediente(String idIngrediente) throws GestionCRUDIngredientesException {
        if (idIngrediente == null || idIngrediente.trim().isEmpty()) {
            throw new GestionCRUDIngredientesException("El ID del ingrediente no puede ser nulo o vacío.");
        }
    }

    /**
     * Valida que la cantidad no sea negativa.
     *
     * @param cantidad cantidad a validar.
     * @throws GestionCRUDIngredientesException si falla la validación.
     */
    @Override
    public void validarCantidadNegativa(double cantidad) throws GestionCRUDIngredientesException {
        if (cantidad < 0) {
            throw new GestionCRUDIngredientesException("La cantidad no puede ser negativa");
        }
    }

}
