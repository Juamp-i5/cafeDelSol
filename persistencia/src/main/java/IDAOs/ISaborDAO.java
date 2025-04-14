/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import entidades.Sabor;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface ISaborDAO {
    
    public List<Sabor> buscarTodos () throws PersistenciaException;
    
    public Sabor buscarPorNombre(String nombre) throws PersistenciaException;
    
}
