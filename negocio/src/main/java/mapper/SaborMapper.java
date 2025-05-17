/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.SaborDTO;
import DTOs.SaborMostrarDTO;
import interfacesMapper.ISaborMapper;

/**
 *
 * @author norma
 */
public class SaborMapper implements ISaborMapper {

    @Override
    public SaborMostrarDTO toSaboresMostrarDTO(SaborDTO sabor) {
        if (sabor == null) {
            return null;
        }

        SaborMostrarDTO dto = new SaborMostrarDTO();
        dto.setNombre(sabor.getNombre());
        dto.setImagenData(sabor.getImagenData());
        return dto;
    }
}
