/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import interfacesMappers.ITamanioMapper;
import DTOs.PersistenciaTamanioDTO;
import entidades.Tamanio;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class TamanioMapper implements ITamanioMapper {

    @Override
    public PersistenciaTamanioDTO toDTO(Tamanio entidad) {
        if (entidad == null) {
            return null;
        }
        PersistenciaTamanioDTO dto = new PersistenciaTamanioDTO();
        dto.setId(entidad.getId() != null ? entidad.getId().toHexString() : null);
        dto.setNombre(entidad.getNombre());
        dto.setPrecioAdicional(entidad.getPrecioAdicional());
        dto.setImagenData(entidad.getImagenData());
        return dto;
    }

    @Override
    public Tamanio toEntity(PersistenciaTamanioDTO dto) {
        if (dto == null) {
            return null;
        }
        Tamanio tamanio = new Tamanio();
        if (dto.getId() != null && ObjectId.isValid(dto.getId())) {
            tamanio.setId(new ObjectId(dto.getId()));
        }
        tamanio.setNombre(dto.getNombre());
        tamanio.setPrecioAdicional(dto.getPrecioAdicional());
        tamanio.setImagenData(dto.getImagenData());
        return tamanio;
    }

}
