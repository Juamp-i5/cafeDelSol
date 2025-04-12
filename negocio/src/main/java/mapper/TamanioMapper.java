/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.TamanioMostrarDTO;
import entidades.Tamanio;
import interfacesMapper.ITamanioMapper;

/**
 *
 * @author norma
 */
public class TamanioMapper implements ITamanioMapper {

    private static TamanioMapper instanceMapper;

    public TamanioMapper() {
    }
    
    public static TamanioMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new TamanioMapper();
        }
        return instanceMapper;
    }
    
    @Override
    public TamanioMostrarDTO aDTO(Tamanio tamanio) {
        if (tamanio == null) {
            return null;
        }

        TamanioMostrarDTO dto = new TamanioMostrarDTO();
        dto.setNombre(tamanio.getNombre());
        dto.setUrlImagen(tamanio.getUrlImagen());
        dto.setPrecio(tamanio.getPrecio());
        return dto;
    }
}
