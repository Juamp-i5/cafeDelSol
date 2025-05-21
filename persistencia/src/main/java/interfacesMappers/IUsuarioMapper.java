/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaUsuarioDTO;
import entidades.Usuario;

/**
 *
 * @author Jp
 */
public interface IUsuarioMapper {

    public Usuario toEntity(PersistenciaUsuarioDTO dto);

    public PersistenciaUsuarioDTO toDTO(Usuario entidad);

}
