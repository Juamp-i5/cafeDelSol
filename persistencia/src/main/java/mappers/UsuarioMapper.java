/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaUsuarioDTO;
import entidades.Usuario;
import interfacesMappers.IUsuarioMapper;

/**
 *
 * @author Jp
 */
public class UsuarioMapper implements IUsuarioMapper {

    @Override
    public Usuario toEntity(PersistenciaUsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario entidad = new Usuario();
        entidad.setNombresPila(dto.getNombresPila());
        entidad.setApellidoPaterno(dto.getApellidoPaterno());
        entidad.setApellidoMaterno(dto.getApellidoMaterno());
        entidad.setTipoEmpleado(dto.getTipoEmpleado());
        entidad.setUsuario(dto.getUsuario());
        entidad.setContrasenia(new String(dto.getContrasenia()));
        return entidad;
    }

    @Override
    public PersistenciaUsuarioDTO toDTO(Usuario entidad) {
        if (entidad == null) {
            return null;
        }
        PersistenciaUsuarioDTO dto = new PersistenciaUsuarioDTO();
        dto.setId(entidad.getId().toHexString());
        dto.setNombresPila(entidad.getNombresPila());
        dto.setApellidoPaterno(entidad.getApellidoPaterno());
        dto.setApellidoMaterno(entidad.getApellidoMaterno());
        dto.setTipoEmpleado(entidad.getTipoEmpleado());
        dto.setUsuario(entidad.getUsuario());
        return dto;
    }
}
