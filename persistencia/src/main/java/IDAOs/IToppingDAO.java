/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import DTOs.ToppingDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IToppingDAO {

    public List<ToppingDTO> buscarTodos() throws PersistenciaException;

    public ToppingDTO buscarPorNombre(String nombre) throws PersistenciaException;

}
