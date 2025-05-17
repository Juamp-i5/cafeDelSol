/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import DTOs.TamanioDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface ITamanioDAO {

    public List<TamanioDTO> buscarTodos() throws PersistenciaException;

    public TamanioDTO buscarPorNombre(String nombre) throws PersistenciaException;
}
