/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesBO;

import DTOs.CRUDProductos.DetallesProductoDTO;
import DTOs.CRUDProductos.ProductoCreateDTO;
import DTOs.CRUDProductos.ProductoListDTO;
import DTOs.ProductoMostrarDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IProductoBO {

    public List<ProductoMostrarDTO> cargarProductos() throws NegocioException;

    public List<ProductoListDTO> obtenerProductosFiltrados(String filtroNombre, String filtroCategoria) throws NegocioException;

    public DetallesProductoDTO obtenerDetallesProducto(String idProducto) throws NegocioException;

    public void guardarProducto(ProductoCreateDTO productoDTO) throws NegocioException;

    public void actualizarProducto(DetallesProductoDTO productoDTO) throws NegocioException;

}
