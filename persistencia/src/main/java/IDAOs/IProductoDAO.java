/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import DTOs.PersistenciaProductoDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IProductoDAO {

    public List<PersistenciaProductoDTO> buscarTodos() throws PersistenciaException;

    public List<PersistenciaProductoDTO> buscarTodosHabilitadosConStock() throws PersistenciaException;

    public List<PersistenciaProductoDTO> buscarPorNombreYCategoria(String filtroNombre, String filtroCategoria) throws PersistenciaException;

    public PersistenciaProductoDTO buscarPorId(String id) throws PersistenciaException;

    public PersistenciaProductoDTO buscarPorNombre(String nombre) throws PersistenciaException;

    public void guardarProducto(PersistenciaProductoDTO producto) throws PersistenciaException;

    public void actualizarProducto(PersistenciaProductoDTO producto) throws PersistenciaException;

    public void restarIngredientes(String idProducto, String idTamanio) throws PersistenciaException;
}
