/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionIngredientes;

import BOs.ingredientes.IIngredienteBO;
import BOs.ingredientes.IProveedorBO;
import BOs.ingredientes.IngredienteBO;
import BOs.ingredientes.ProveedorBO;
import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDIngredientes.NivelStock;
import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import excepciones.GestionCRUDIngredientesException;
import excepciones.NegocioException;
import excepciones.NombreExistenteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import validadores.IValidadorGestorIngredientes;
import validadores.ValidadorGestorIngredientes;

/**
 *
 * @author norma
 */
public class GestorCRUDIngredientes implements IGestorCRUDIngredientes {

    private static GestorCRUDIngredientes instance;
    private IValidadorGestorIngredientes validador = new ValidadorGestorIngredientes();
    private IIngredienteBO ingredienteBO = IngredienteBO.getInstance();
    private IProveedorBO proveedorBO = ProveedorBO.getInstance();

    private GestorCRUDIngredientes() {
    }

    public static GestorCRUDIngredientes getInstance() {
        if (instance == null) {
            instance = new GestorCRUDIngredientes();
        }
        return instance;
    }

    @Override
    public boolean agregarIngrediente(IngredienteNuevoDTO ingrediente) throws GestionCRUDIngredientesException {
        try {
            boolean existente = ingredienteBO.obtenerIngredientePorNombre(ingrediente.getNombre().trim());
            if (existente) {
                throw new NombreExistenteException("Ya existe un ingrediente con el nombre '" + ingrediente.getNombre() + "'.");
            }
            validador.validarAgregarIngrediente(ingrediente);
            ingrediente.setNivelStock(ingrediente.getCantidadDisponible() < ingrediente.getCantidadMinima() ? NivelStock.BAJOSTOCK : NivelStock.ENSTOCK);
            ingredienteBO.agregarIngrediente(ingrediente);
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDIngredientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public DetallesIngredienteViejoDTO editarIngrediente(String idIngrediente, String nombreNuevo) throws GestionCRUDIngredientesException {
        validador.validarEditarIngrediente(nombreNuevo);
        try {
            boolean existente = ingredienteBO.obtenerIngredientePorNombre(nombreNuevo);
            if (existente) {
                throw new NombreExistenteException("Ya existe un ingrediente con el nombre '" + nombreNuevo + "'.");
            }

            return ingredienteBO.editarIngrediente(idIngrediente, nombreNuevo);
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDIngredientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public DetallesIngredienteViejoDTO obtenerDetallesIngrediente(String idIngrediente) throws GestionCRUDIngredientesException {
        validador.validarIdIngrediente(idIngrediente);

        try {
            return ingredienteBO.obtenerDetallesIngrediente(idIngrediente);
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDIngredientes.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<IngredienteViejoListDTO> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) throws GestionCRUDIngredientesException {
        try {
            return ingredienteBO.buscarIngredientesPorFiltros(filtroNombre, filtroNivelStock);
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDIngredientes.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<ProveedorViejoDTO> obtenerProveedores() throws GestionCRUDIngredientesException {
        try {
            return proveedorBO.obtenerProveedores();
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDIngredientes.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean aumentarStock(String idIngrediente, Double cantidad) throws GestionCRUDIngredientesException {
        validador.validarCantidadNegativa(cantidad);
        try {
            return ingredienteBO.aumentarStock(idIngrediente, cantidad);
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDIngredientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean reducirStock(String idIngrediente, Double cantidad) throws GestionCRUDIngredientesException {
        validador.validarCantidadNegativa(cantidad);
        try {
            return ingredienteBO.reducirStock(idIngrediente, cantidad);
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDIngredientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @Override
    public void actualizarNivelStock(String idIngrediente) throws GestionCRUDIngredientesException {
        try {
            ingredienteBO.actualizarNivelStock(idIngrediente);
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDIngredientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public DetallesIngredienteViejoDTO obtenerIngredientePorNombre(String nombre) throws GestionCRUDIngredientesException {
        try {
            List<IngredienteViejoListDTO> lista = ingredienteBO.buscarIngredientesPorFiltros(nombre, null);
            if (lista == null || lista.isEmpty()) {
                throw new GestionCRUDIngredientesException("No se encontró ningún ingrediente con el nombre: " + nombre);
            }
            return obtenerDetallesIngrediente(lista.get(0).getId());
        } catch (NegocioException e) {
            throw new GestionCRUDIngredientesException("Error al buscar el ingrediente por nombre: " + nombre, e);
        }
    }
    
    @Override
    public String obtenerIdIngredientePorNombre(String nombre) throws GestionCRUDIngredientesException {
        DetallesIngredienteViejoDTO dto = obtenerIngredientePorNombre(nombre);
        return dto.getId();
    }

}
