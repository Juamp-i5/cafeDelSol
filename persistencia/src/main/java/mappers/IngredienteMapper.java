/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import interfacesMappers.IIngredienteMapper;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import entidades.Ingrediente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jp
 */
public class IngredienteMapper implements IIngredienteMapper {

    @Override
    public IngredienteDTOPersistencia toDTO(Ingrediente entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    @Override
//    public IngredienteDTOPersistencia toDTO(Ingrediente entidad) {
//        if (entidad == null) {
//            return null;
//        }
//        IngredienteDTOPersistencia dto = new IngredienteDTOPersistencia();
//        if (entidad.getId() != null) {
//            dto.setId(entidad.getId().toHexString());
//        }
//        dto.setNombre(entidad.getNombre());
//        dto.setCantidadDisponible(entidad.getCantidadDisponible());
//        dto.setCantidadMinima(entidad.getCantidadMinima());
//        dto.setUnidadMedida(entidad.getUnidadMedida());
//        dto.setNivelStock(entidad.getNivelStock());
//        if (entidad.getIdProveedor() != null) {
//            dto.setIdProveedor(entidad.getIdProveedor().toHexString());
//        }
//        return dto;
//    }
//
//    @Override
//    public Ingrediente toMongo(IngredienteDTOPersistencia dto) throws PersistenciaException {
//        if (dto == null) {
//            return null;
//        }
//        Ingrediente entidad = new Ingrediente();
//        if (dto.getId() != null && ObjectId.isValid(dto.getId())) {
//            entidad.setId(new ObjectId(dto.getId()));
//        } else if (dto.getId() != null && !dto.getId().isEmpty() && !ObjectId.isValid(dto.getId())) {
//            throw new PersistenciaException("Error al mapear el ingredienteId de IngredienteDTO de String a ObjectId");
//        }
//        entidad.setNombre(dto.getNombre());
//        entidad.setCantidadDisponible(dto.getCantidadDisponible());
//        entidad.setCantidadMinima(dto.getCantidadMinima());
//        entidad.setUnidadMedida(dto.getUnidadMedida());
//        entidad.setNivelStock(dto.getNivelStock());
//        if (dto.getIdProveedor() != null && ObjectId.isValid(dto.getIdProveedor())) {
//            entidad.setIdProveedor(new ObjectId(dto.getIdProveedor()));
//        } else if (dto.getIdProveedor() != null && !dto.getIdProveedor().isEmpty() && !ObjectId.isValid(dto.getIdProveedor())) {
//            throw new PersistenciaException("Error al mapear el ingredienteId de IngredienteDTO de String a ObjectId");
//        }
//        return entidad;
//    }
//
//    @Override
//    public List<IngredienteDTOPersistencia> toDTOList(List<Ingrediente> entidades) {
//        if (entidades == null) {
//            return null;
//        }
//        List<IngredienteDTOPersistencia> dtos = new ArrayList<>();
//        for (Ingrediente entidad : entidades) {
//            dtos.add(toDTO(entidad));
//        }
//        return dtos;
//    }
//
//    @Override
//    public List<Ingrediente> toMongoList(List<IngredienteDTOPersistencia> dtos) throws PersistenciaException {
//        if (dtos == null) {
//            return null;
//        }
//        List<Ingrediente> entidades = new ArrayList<>();
//        for (IngredienteDTOPersistencia dto : dtos) {
//            entidades.add(toMongo(dto));
//        }
//        return entidades;
//    }

    @Override
    public Ingrediente toMongo(IngredienteDTOPersistencia dto) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<IngredienteDTOPersistencia> toDTOList(List<Ingrediente> entidades) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Ingrediente> toMongoList(List<IngredienteDTOPersistencia> dtos) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
