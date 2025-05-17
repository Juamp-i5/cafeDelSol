/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import DTOs.ProductoDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IProductoDAO {

    public List<ProductoDTO> buscarTodos() throws PersistenciaException;

    public List<ProductoDTO> buscarTodosHabilitados() throws PersistenciaException;

    public List<ProductoDTO> buscarPorNombreYCategoria(String filtroNombre, String filtroCategoria) throws PersistenciaException;

    public ProductoDTO buscarPorId(String id) throws PersistenciaException;

    public ProductoDTO buscarPorNombre(String nombre) throws PersistenciaException;

    public void guardarProducto(ProductoDTO producto) throws PersistenciaException;

    public void actualizarProducto(ProductoDTO producto) throws PersistenciaException;
}
