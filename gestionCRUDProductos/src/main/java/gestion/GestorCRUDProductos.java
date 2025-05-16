/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion;

import BOs.ProductoBO;
import DTOs.CRUDProductos.DetallesProductoDTO;
import DTOs.CRUDProductos.ProductoCreateDTO;
import DTOs.CRUDProductos.ProductoListDTO;
import excepciones.GestionCRUDProductosException;
import excepciones.NegocioException;
import interfacesBO.IProductoBO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import validadores.IValidadorGestorCRUDProductos;
import validadores.ValidadorGestorCRUDProductos;

/**
 *
 * @author oliva
 */
public class GestorCRUDProductos implements IGestorCRUDProductos {

    private static GestorCRUDProductos instance;
    private IProductoBO productoBO = ProductoBO.getInstance();
    private IValidadorGestorCRUDProductos validador = new ValidadorGestorCRUDProductos();

    private GestorCRUDProductos() {
    }

    public static GestorCRUDProductos getInstance() {
        if (instance == null) {
            instance = new GestorCRUDProductos();
        }

        return instance;
    }

    @Override
    public List<ProductoListDTO> obtenerProductosFiltrados(String filtroNombre, String filtroCategoria) throws GestionCRUDProductosException {
        validador.validarFiltrosProductos(filtroNombre, filtroCategoria);

        try {
            return productoBO.obtenerProductosFiltrados(filtroNombre, filtroCategoria);
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDProductos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public DetallesProductoDTO obtenerDetallesProducto(String idProducto) throws GestionCRUDProductosException {
        validador.validarIdProducto(idProducto);

        try {
            return productoBO.obtenerDetallesProducto(idProducto);
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDProductos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void guardarProducto(ProductoCreateDTO productoDTO) throws GestionCRUDProductosException {
        validador.validarGuardarProducto(productoDTO);

        try {
            productoBO.guardarProducto(productoDTO);
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizarProducto(DetallesProductoDTO productoDTO) throws GestionCRUDProductosException {
        validador.validarActualizarProducto(productoDTO);

        try {
            productoBO.actualizarProducto(productoDTO);
        } catch (NegocioException ex) {
            Logger.getLogger(GestorCRUDProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
