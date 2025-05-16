/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package validadores;

import DTOs.CRUDProductos.DetallesProductoDTO;
import DTOs.CRUDProductos.ProductoCreateDTO;
import excepciones.GestionCRUDProductosException;

/**
 *
 * @author oliva
 */
public interface IValidadorGestorCRUDProductos {

    public void validarGuardarProducto(ProductoCreateDTO productoDTO) throws GestionCRUDProductosException;

    public void validarActualizarProducto(DetallesProductoDTO productoDTO) throws GestionCRUDProductosException;

    public void validarIdProducto(String idProducto) throws GestionCRUDProductosException;

    public void validarFiltrosProductos(String filtroNombre, String filtroCategoria) throws GestionCRUDProductosException;
}
