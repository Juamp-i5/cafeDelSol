/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Sabor;
import exception.persistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface ISabor {
    
    public List<Sabor> buscarTodos () throws persistenciaException;
    
}
