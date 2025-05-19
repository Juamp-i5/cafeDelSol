/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaSaborDTO;
import entidades.Sabor;
import excepciones.PersistenciaException;
import interfacesMappers.ISaborMapper;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class SaborMapper implements ISaborMapper {

    @Override
    public PersistenciaSaborDTO toDTO(Sabor sabor) {
        if (sabor == null) {
            return null;
        }
        PersistenciaSaborDTO dto = new PersistenciaSaborDTO();
        if (sabor.getId() != null) {
            dto.setId(sabor.getId().toHexString());
        }
        dto.setNombre(sabor.getNombre());
        dto.setImagenData(sabor.getImagenData());
        return dto;
    }

    @Override
    public Sabor toEntity(PersistenciaSaborDTO saborDTO) {
        if (saborDTO == null) {
            return null;
        }
        Sabor sabor = new Sabor();
        if (saborDTO.getId() != null && ObjectId.isValid(saborDTO.getId())) {
            sabor.setId(new ObjectId(saborDTO.getId()));
        }
        sabor.setNombre(saborDTO.getNombre());
        sabor.setImagenData(saborDTO.getImagenData());
        return sabor;
    }

}
