/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Topping;
import exception.persistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface ITopping {
    
    public List<Topping> buscarTodos () throws persistenciaException;
    
    public Topping buscarPorNombre(String nombre) throws persistenciaException;
    
}
