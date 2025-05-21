/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.CRUDProductos.DetallesProductoDTO;
import DTOs.CRUDProductos.ProductoCreateDTO;
import DTOs.CRUDProductos.ProductoListDTO;
import DTOs.PersistenciaProductoDTO;
import DTOs.PersistenciaProductoTamanioDTO;
import DTOs.ProductoMostrarDTO;
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
import IDAOs.ITamanioDAO;
import acceso.AccesoDatos;
import java.util.stream.Collectors;

/**
 *
 * @author rodri
 */
public class ProductoBO implements IProductoBO {

    IProductoDAO productoDAO = AccesoDatos.getProductoDAO();
    ITamanioDAO tamanioDAO = AccesoDatos.getTamanioDAO();
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
            List<PersistenciaProductoDTO> productos = productoDAO.buscarTodosHabilitados();
            List<ProductoMostrarDTO> productosDTO = new ArrayList<>();

            for (PersistenciaProductoDTO producto : productos) {
                ProductoMostrarDTO productoDTO = productoMapper.toProductoMostrarDTO(producto);
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
        List<PersistenciaProductoDTO> productos;
        try {
            productos = productoDAO.buscarPorNombreYCategoria(filtroNombre, filtroCategoria);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar los productos filtrados: " + e.getMessage(), e);
        }
        if (productos == null) {
            productos = new ArrayList<>();
        }
        try {
            List<ProductoListDTO> resultado = productos.stream()
                    .map(productoMapper::toProductoListDTO)
                    .filter(dto -> dto != null)
                    .collect(Collectors.toList());
            return resultado;
        } catch (Exception e) {
            throw new NegocioException("Error al procesar los datos de los productos filtrados", e);
        }
    }

    @Override
    public DetallesProductoDTO obtenerDetallesProducto(String idProducto) throws NegocioException {
        DetallesProductoDTO producto;
        PersistenciaProductoDTO productoPersistencia;
        try {
            productoPersistencia = productoDAO.buscarPorId(idProducto);
            producto = productoMapper.toDetallesProductoDTO(productoPersistencia);
            return producto;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al intentar consultar detalles producto", e);
        }
    }

    @Override
    public void guardarProducto(ProductoCreateDTO productoDTO) throws NegocioException {
        try {
            PersistenciaProductoDTO producto;
            producto = productoMapper.toPersistenciaProductoDTO(productoDTO);
            for (PersistenciaProductoTamanioDTO tamanioDTO : producto.getTamanios()) {
                String nombre = tamanioDTO.getTamanio().getNombre();
                String id = tamanioDAO.buscarPorNombre(nombre).getId();
                tamanioDTO.getTamanio().setId(id);
                tamanioDTO.getTamanio().setNombre(null);
            }
        } catch (PersistenciaException e) {
        }
    }

    @Override
    public void actualizarProducto(DetallesProductoDTO productoDTO) throws NegocioException {
        try {
            PersistenciaProductoDTO producto;

            producto = productoMapper.toPersistenciaProductoDTO(productoDTO);
            for (PersistenciaProductoTamanioDTO tamanioDTO : producto.getTamanios()) {
                String nombre = tamanioDTO.getTamanio().getNombre();
                String id = tamanioDAO.buscarPorNombre(nombre).getId();
                tamanioDTO.getTamanio().setId(id);
                tamanioDTO.getTamanio().setNombre(null);
            }

            productoDAO.actualizarProducto(producto);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al actualizar producto en negocio", ex);
        }
    }

}
