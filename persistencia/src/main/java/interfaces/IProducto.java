/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Producto;
import exception.persistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IProducto {
    
    public List<Producto> buscarTodos () throws persistenciaException;
    
    public Producto buscarPorNombre(String nombre) throws persistenciaException;
    
}
