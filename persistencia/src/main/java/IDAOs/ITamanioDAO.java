/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import DTOs.PersistenciaTamanioDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface ITamanioDAO {

    public List<PersistenciaTamanioDTO> buscarTodos() throws PersistenciaException;

    public PersistenciaTamanioDTO buscarPorNombre(String nombre) throws PersistenciaException;

    public void guardar(PersistenciaTamanioDTO tamanioDTO) throws PersistenciaException;
}
