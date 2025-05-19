/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import interfacesMappers.IIngredienteMapper;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import entidades.Ingrediente;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class IngredienteMapper implements IIngredienteMapper {

    @Override
    public IngredienteDTOPersistencia toDTO(Ingrediente entidad) {
        if (entidad == null) {
            return null;
        }

        IngredienteDTOPersistencia dto = new IngredienteDTOPersistencia();
        dto.setId(entidad.getId() != null ? entidad.getId().toHexString() : null);
        dto.setNombre(entidad.getNombre());
        dto.setCantidadDisponible(entidad.getCantidadDisponible());
        dto.setCantidadMinima(entidad.getCantidadMinima());
        dto.setUnidadMedida(entidad.getUnidadMedida());
        dto.setNivelStock(entidad.getNivelStock());
        dto.setIdProveedor(entidad.getIdProveedor() != null ? entidad.getIdProveedor().toHexString() : null);
        return dto;
    }

    @Override
    public Ingrediente toEntity(IngredienteDTOPersistencia dto) {
        if (dto == null) {
            return null;
        }
        Ingrediente ingrediente = new Ingrediente();
        if (dto.getId() != null && ObjectId.isValid(dto.getId())) {
            ingrediente.setId(new ObjectId(dto.getId()));
        }
        ingrediente.setNombre(dto.getNombre());
        ingrediente.setCantidadDisponible(dto.getCantidadDisponible());
        ingrediente.setCantidadMinima(dto.getCantidadMinima());
        ingrediente.setUnidadMedida(dto.getUnidadMedida());
        ingrediente.setNivelStock(dto.getNivelStock());
        if (dto.getIdProveedor() != null && ObjectId.isValid(dto.getIdProveedor())) {
            ingrediente.setIdProveedor(new ObjectId(dto.getIdProveedor()));
        }
        return ingrediente;
    }

}
