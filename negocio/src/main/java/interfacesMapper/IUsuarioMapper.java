/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacesMapper;

import DTOs.PersistenciaUsuarioDTO;
import DTOs.UsuarioDTO;

/**
 *
 * @author Jp
 */
public interface IUsuarioMapper {

    public UsuarioDTO toUsuarioDTO(PersistenciaUsuarioDTO persistenciaUsuarioDTO);
}
