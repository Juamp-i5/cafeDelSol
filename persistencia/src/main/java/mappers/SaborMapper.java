/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.SaborDTO;
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
    public SaborDTO toDTO(Sabor sabor) {
        if (sabor == null) {
            return null;
        }
        SaborDTO dto = new SaborDTO();
        if (sabor.getId() != null) {
            dto.setId(sabor.getId().toHexString());
        }
        dto.setNombre(sabor.getNombre());
        dto.setImagenData(sabor.getImagenData());
        return dto;
    }

    @Override
    public Sabor toEntity(SaborDTO saborDTO) throws PersistenciaException {
        if (saborDTO == null) {
            return null;
        }
        Sabor sabor = new Sabor();
        if (saborDTO.getId() != null && ObjectId.isValid(saborDTO.getId())) {
            sabor.setId(new ObjectId(saborDTO.getId()));
        } else {
            throw new PersistenciaException("Error al mappear el id de saborDTO de String a ObjectId");
        }
        sabor.setNombre(saborDTO.getNombre());
        sabor.setImagenData(saborDTO.getImagenData());
        return sabor;
    }

}
