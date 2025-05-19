/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import DTOs.PersistenciaToppingDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IToppingDAO {

    public List<PersistenciaToppingDTO> buscarTodos() throws PersistenciaException;

    public PersistenciaToppingDTO buscarPorNombre(String nombre) throws PersistenciaException;

    public void guardar(PersistenciaToppingDTO toppingDTO) throws PersistenciaException;
}
