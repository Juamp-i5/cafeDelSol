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
 * Clase gestor ingredientes.
 *
 * @author norma
 */
public class GestorCRUDIngredientes implements IGestorCRUDIngredientes {

    private static GestorCRUDIngredientes instance;
    private IValidadorGestorIngredientes validador = new ValidadorGestorIngredientes();
    private IIngredienteBO ingredienteBO = IngredienteBO.getInstance();
    private IProveedorBO proveedorBO = ProveedorBO.getInstance();

    /**
     * Constructor por defecto.
     */
    private GestorCRUDIngredientes() {
    }

    /**
     * Obtiene la instancia de la clase.
     *
     * @return instance.
     */
    public static GestorCRUDIngredientes getInstance() {
        if (instance == null) {
            instance = new GestorCRUDIngredientes();
        }
        return instance;
    }

    /**
     * Agrega un nuevo ingrediente.
     *
     * @param ingrediente ingrediente a agregar.
     * @return true si se agregó correctamente.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
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

    /**
     * Edita el nombre de un ingrediente existente.
     *
     * @param idIngrediente id del ingrediente.
     * @param nombreNuevo nuevo nombre.
     * @return detalles del ingrediente actualizado.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
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

    /**
     * Obtiene los detalles de un ingrediente por su ID.
     *
     * @param idIngrediente id del ingrediente.
     * @return detalles del ingrediente.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
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

    /**
     * Busca ingredientes usando filtros.
     *
     * @param filtroNombre filtro por nombre.
     * @param filtroNivelStock filtro por nivel de stock.
     * @return lista de ingredientes encontrados.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    @Override
    public List<IngredienteViejoListDTO> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) throws GestionCRUDIngredientesException {
        try {
            return ingredienteBO.buscarIngredientesPorFiltros(filtroNombre, filtroNivelStock);
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDIngredientes.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene la lista de proveedores.
     *
     * @return lista de proveedores.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    @Override
    public List<ProveedorViejoDTO> obtenerProveedores() throws GestionCRUDIngredientesException {
        try {
            return proveedorBO.obtenerProveedores();
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDIngredientes.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Aumenta el stock de un ingrediente.
     *
     * @param idIngrediente id del ingrediente.
     * @param cantidad cantidad a aumentar.
     * @return true si se aumentó correctamente.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
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

    /**
     * Reduce el stock de un ingrediente.
     *
     * @param idIngrediente id del ingrediente.
     * @param cantidad cantidad a reducir.
     * @return true si se redujo correctamente.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
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

    /**
     * Actualiza el nivel de stock de un ingrediente.
     *
     * @param idIngrediente id del ingrediente.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    @Override
    public void actualizarNivelStock(String idIngrediente) throws GestionCRUDIngredientesException {
        try {
            ingredienteBO.actualizarNivelStock(idIngrediente);
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDIngredientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene un ingrediente por su nombre.
     *
     * @param nombre nombre del ingrediente.
     * @return detalles del ingrediente.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    @Override
    public DetallesIngredienteViejoDTO obtenerIngredientePorNombre(String nombre) throws GestionCRUDIngredientesException {
        try {
            List<IngredienteViejoListDTO> lista = ingredienteBO.buscarIngredientesPorFiltros(nombre.trim(), null);
            if (lista == null || lista.isEmpty()) {
                throw new GestionCRUDIngredientesException("No se encontró ningún ingrediente con el nombre: " + nombre);
            }
            DetallesIngredienteViejoDTO dto = obtenerDetallesIngrediente(lista.get(0).getId());
            if (dto == null) {
                throw new GestionCRUDIngredientesException("No se pudieron obtener los detalles del ingrediente con nombre: " + nombre);
            }
            return dto;
        } catch (NegocioException e) {
            throw new GestionCRUDIngredientesException("Error al buscar el ingrediente por nombre: " + nombre, e);
        }
    }

    /**
     * Obtiene el ID de un ingrediente por su nombre.
     *
     * @param nombre nombre del ingrediente.
     * @return id del ingrediente.
     * @throws GestionCRUDIngredientesException si ocurre un error.
     */
    @Override
    public String obtenerIdIngredientePorNombre(String nombre) throws GestionCRUDIngredientesException {
        DetallesIngredienteViejoDTO dto = obtenerIngredientePorNombre(nombre);
        return dto.getId();
    }

}
