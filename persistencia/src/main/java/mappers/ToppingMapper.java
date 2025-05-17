/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.ToppingDTO;
import entidades.Topping;
import excepciones.PersistenciaException;
import interfacesMappers.IToppingMapper;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class ToppingMapper implements IToppingMapper {

    @Override
    public ToppingDTO toDTO(Topping topping) {
        if (topping == null) {
            return null;
        }
        ToppingDTO dto = new ToppingDTO();
        if (topping.getId() != null) {
            dto.setId(topping.getId().toHexString());
        }
        dto.setNombre(topping.getNombre());
        dto.setImagenData(topping.getImagenData());
        return dto;
    }

    @Override
    public Topping toEntity(ToppingDTO toppingDTO) throws PersistenciaException {
        if (toppingDTO == null) {
            return null;
        }
        Topping topping = new Topping();
        if (toppingDTO.getId() != null && ObjectId.isValid(toppingDTO.getId())) {
            topping.setId(new ObjectId(toppingDTO.getId()));
        } else {
            throw new PersistenciaException("Error al mapear el id de toppingDTO de String a ObjectId");
        }
        topping.setNombre(toppingDTO.getNombre());
        topping.setImagenData(toppingDTO.getImagenData());
        return topping;
    }

}
