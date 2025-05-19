/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.PersistenciaToppingDTO;
import DTOs.ToppingMostrarDTO;
import interfacesMapper.IToppingMapper;

/**
 *
 * @author norma
 */
public class ToppingMapper implements IToppingMapper {

    @Override
    public ToppingMostrarDTO toToppingsMostrarDTO(PersistenciaToppingDTO toppingDTO) {
        if (toppingDTO == null) {
            return null;
        }

        ToppingMostrarDTO dto = new ToppingMostrarDTO();
        dto.setNombre(toppingDTO.getNombre());
        dto.setImagenData(toppingDTO.getImagenData());
        return dto;
    }

}
