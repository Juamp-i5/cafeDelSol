/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.PersistenciaUsuarioDTO;
import DTOs.UsuarioDTO;
import interfacesMapper.IUsuarioMapper;

/**
 *
 * @author Jp
 */
public class UsuarioMapper implements IUsuarioMapper {

    @Override
    public UsuarioDTO toUsuarioDTO(PersistenciaUsuarioDTO persistenciaDTO) {
        if (persistenciaDTO == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(persistenciaDTO.getId());
        dto.setNombresPila(persistenciaDTO.getNombresPila());
        dto.setApellidoPaterno(persistenciaDTO.getApellidoPaterno());
        dto.setApellidoMaterno(persistenciaDTO.getApellidoMaterno());
        dto.setTipoEmpleado(persistenciaDTO.getTipoEmpleado());
        dto.setUsuario(persistenciaDTO.getUsuario());
        return dto;
    }

}
