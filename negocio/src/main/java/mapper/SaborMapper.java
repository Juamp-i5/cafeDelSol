/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.SaboresMostrarDTO;
import entidades.Sabor;
import interfacesMapper.ISaborMapper;

/**
 *
 * @author norma
 */
public class SaborMapper implements ISaborMapper {

    @Override
    public SaboresMostrarDTO aDTO(Sabor sabor) {
        if (sabor == null) {
            return null;
        }

        SaboresMostrarDTO dto = new SaboresMostrarDTO();
        dto.setNombre(sabor.getNombre());
        dto.setUrlImagen(sabor.getUrlImagen());
        return dto;
    }
}
