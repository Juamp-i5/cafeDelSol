/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import entidades.Topping;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IToppingDAO {
    
    public List<Topping> buscarTodos () throws PersistenciaException;
    
    public Topping buscarPorNombre(String nombre) throws PersistenciaException;
    
}
