/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion;

import DTOs.CRUDProductos.DetallesProductoDTO;
import DTOs.CRUDProductos.ProductoCreateDTO;
import DTOs.CRUDProductos.ProductoListDTO;
import excepciones.GestionCRUDProductosException;
import java.util.List;

/**
 *
 * @author oliva
 */
public interface IGestorCRUDProductos {

    public List<ProductoListDTO> obtenerProductosFiltrados(String filtroNombre, String filtroCategoria) throws GestionCRUDProductosException;

    public DetallesProductoDTO obtenerDetallesProducto(String idProducto) throws GestionCRUDProductosException;

    public void guardarProducto(ProductoCreateDTO productoDTO) throws GestionCRUDProductosException;

    public void actualizarProducto(DetallesProductoDTO productoDTO) throws GestionCRUDProductosException;
}
