/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import DTOs.SaborDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface ISaborDAO {

    public List<SaborDTO> buscarTodos() throws PersistenciaException;

    public SaborDTO buscarPorNombre(String nombre) throws PersistenciaException;

}
