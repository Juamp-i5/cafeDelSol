/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import entidades.Producto;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IProductoDAO {
    
    public List<Producto> buscarTodos () throws PersistenciaException;
    
    public Producto buscarPorNombre(String nombre) throws PersistenciaException;
    
}
