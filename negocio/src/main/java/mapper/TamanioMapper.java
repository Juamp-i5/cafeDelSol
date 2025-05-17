/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.TamanioDTO;
import DTOs.TamanioMostrarDTO;
import interfacesMapper.ITamanioMapper;

/**
 *
 * @author norma
 */
public class TamanioMapper implements ITamanioMapper {

    @Override
    public TamanioMostrarDTO toTamanioMostrarDTO(TamanioDTO tamanioDTO) {
        if (tamanioDTO == null) {
            return null;
        }

        TamanioMostrarDTO dto = new TamanioMostrarDTO();
        dto.setNombre(tamanioDTO.getNombre());
        dto.setImagenData(tamanioDTO.getImagenData());
        dto.setPrecioAdicional(tamanioDTO.getPrecioAdicional());
        return dto;
    }
}
