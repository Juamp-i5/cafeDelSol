/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAOs.ProductoDAOImp;
import DTOs.ProductoMostrarDTO;
import entidades.Producto;
import exception.NegocioException;
import exception.persistenciaException;
import interfaces.IProducto;
import interfacesBO.IProductoBO;
import interfacesMapper.IProductoMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.ProductoMapper;

/**
 *
 * @author rodri
 */
public class ProductoBO implements IProductoBO{

    IProducto productoDAO = ProductoDAOImp.getInstance();
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
        } catch (persistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al cargar los productos");
        }
        
    }
    
}
