/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Tamanio;
import exception.persistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface ITamanio {
    
    public List<Tamanio> buscarTodos () throws persistenciaException;
    
}
