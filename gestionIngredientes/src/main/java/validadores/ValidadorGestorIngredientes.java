package validadores;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import excepciones.CantidadMaximaCaracteresSuperada;
import excepciones.CantidadMaximaDisponibleSuperadaException;
import excepciones.CantidadMinimaDisponibleSuperadaException;
import excepciones.GestionIngredientesException;

/**
 *
 * @author norma
 */
public class ValidadorGestorIngredientes implements IValidadorGestorIngredientes {

    private static final int CANTIDAD_MAX_CARACTERES = 30;
    private static final Double CANTIDAD_MAX_DISPONIBLE = 10000.0;
    private static final Double CANTIDAD_MAX_MINIMA = 10.0;

    @Override
    public void validarAgregarIngrediente(IngredienteNuevoDTO ingrediente) throws GestionIngredientesException {
        if (ingrediente == null) {
            throw new IllegalArgumentException("El DTO del ingrediente no puede ser nulo.");
        }

        if (ingrediente.getNombre() == null || ingrediente.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del ingrediente es obligatorio.");
        }
        validadCantidadMaximaCaracteres(ingrediente.getNombre());
        if (ingrediente.getCantidadDisponible() == null) {
            throw new IllegalArgumentException("La cantidad disponible del ingrediente es obligatoria.");
        }
        validadCantidadMaximaDisponibleSuperada(ingrediente.getCantidadDisponible(), ingrediente.getUnidadMedida().toString());
        if (ingrediente.getCantidadDisponible() < 0) {
            throw new IllegalArgumentException("La cantidad disponible no puede ser negativa.");
        }

        if (ingrediente.getCantidadMinima() == null) {
            throw new IllegalArgumentException("La cantidad mínima del ingrediente es obligatoria.");
        }
        validadCantidadMinimaSuperada(ingrediente.getCantidadDisponible(), ingrediente.getUnidadMedida().toString());

        if (ingrediente.getUnidadMedida() == null) {
            throw new IllegalArgumentException("La unidad de medida del ingrediente es obligatoria.");
        }

//        if (ingrediente.getProveedor() == null) {
//            throw new IllegalArgumentException("El proveedor del ingrediente es obligatorio.");
//        }
    }

    @Override
    public void validadCantidadMaximaCaracteres(String nombre) throws GestionIngredientesException {
        if (nombre.length() > CANTIDAD_MAX_CARACTERES) {
            throw new CantidadMaximaCaracteresSuperada("El nombre del ingrediente no puede exceder los " + CANTIDAD_MAX_CARACTERES + " caracteres.");
        }
    }

    @Override
    public void validadCantidadMaximaDisponibleSuperada(Double cantidad, String unidadMedida) throws GestionIngredientesException {
        if (cantidad > CANTIDAD_MAX_DISPONIBLE) {
            throw new CantidadMaximaDisponibleSuperadaException("La cantidad disponible del ingrediente no puede exceder los " + CANTIDAD_MAX_DISPONIBLE + " " + unidadMedida + ".");
        }
    }

    @Override
    public void validadCantidadMinimaSuperada(Double cantidad, String unidadMedida) throws GestionIngredientesException {
        if (cantidad < CANTIDAD_MAX_MINIMA) {
            throw new CantidadMinimaDisponibleSuperadaException("La cantidad mínima del ingrediente no puede ser menor que " + CANTIDAD_MAX_MINIMA + " " + unidadMedida + ".");
        }
    }

    @Override
    public void validarEditarIngrediente(String nombre) throws GestionIngredientesException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del ingrediente es obligatorio.");
        }
        validadCantidadMaximaCaracteres(nombre);
        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            throw new IllegalArgumentException("El nombre del ingrediente solo puede contener letras.");
        }
    }

    @Override
    public void validarIdIngrediente(String idIngrediente) throws GestionIngredientesException {
        if (idIngrediente == null || idIngrediente.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del ingrediente no puede ser nulo o vacío.");
        }
    }

    @Override
    public void validarCantidadNegativa(double cantidad) {
        if(cantidad < 0){
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
    }

}
