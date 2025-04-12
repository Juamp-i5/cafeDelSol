/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.ToppingsMostrarDTO;
import entidades.Topping;
import interfacesMapper.IToppingMapper;

/**
 *
 * @author norma
 */
public class ToppingMapper implements IToppingMapper {

    @Override
    public ToppingsMostrarDTO aDTO(Topping topping) {
        if (topping == null) {
            return null;
        }

        ToppingsMostrarDTO dto = new ToppingsMostrarDTO();
        dto.setNombre(topping.getNombre());
        dto.setUrlImagen(topping.getUrlImagen());
        return dto;
    }

}
