/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.CRUDProductos.DetallesProductoDTO;
import DTOs.CRUDProductos.ProductoCreateDTO;
import DTOs.CRUDProductos.ProductoListDTO;
import DTOs.ProductoMostrarDTO;
import entidades.Producto;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfacesBO.IProductoBO;
import interfacesMapper.IProductoMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.ProductoMapper;
import IDAOs.IProductoDAO;
import acceso.AccesoDatos;

/**
 *
 * @author rodri
 */
public class ProductoBO implements IProductoBO {

    IProductoDAO productoDAO = AccesoDatos.getProductoDAO();
    IProductoMapper productoMapper = ProductoMapper.getInstance();

    private static ProductoBO instanceBO;

    public ProductoBO() {
    }

    public static ProductoBO getInstance() {
        if (instanceBO == null) {
            instanceBO = new ProductoBO();
        }
        return instanceBO;
    }

    @Override
    public List<ProductoMostrarDTO> cargarProductos() throws NegocioException {
        try {

            List<Producto> productos = productoDAO.buscarTodos();
            List<ProductoMostrarDTO> productosDTO = new ArrayList<>();

            for (Producto producto : productos) {
                ProductoMostrarDTO productoDTO = productoMapper.aDTO(producto);
                productosDTO.add(productoDTO);
            }

            return productosDTO;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al cargar los productos");
        }

    }

    @Override
    public List<ProductoListDTO> obtenerProductosFiltrados(String filtroNombre, String filtroCategoria) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DetallesProductoDTO obtenerDetallesProducto(String idProducto) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardarProducto(ProductoCreateDTO productoDTO) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarProducto(DetallesProductoDTO productoDTO) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
