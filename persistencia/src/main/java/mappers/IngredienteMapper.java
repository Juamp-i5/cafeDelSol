/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import interfacesMappers.IProveedorMapper;
import interfacesMappers.IIngredienteMapper;
import DTOs.IngredienteDTO;
import entidades.Ingrediente;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class IngredienteMapper implements IIngredienteMapper {

    private final IProveedorMapper proveedorMapper;

    public IngredienteMapper(IProveedorMapper proveedorMapper) {
        this.proveedorMapper = proveedorMapper;
    }

    @Override
    public IngredienteDTO toDTO(Ingrediente entidad) {
        if (entidad == null) {
            return null;
        }
        IngredienteDTO dto = new IngredienteDTO();
        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toHexString());
        }
        dto.setNombre(entidad.getNombre());
        dto.setCantidadDisponible(entidad.getCantidadDisponible());
        dto.setCantidadMinima(entidad.getCantidadMinima());
        dto.setUnidadMedida(entidad.getUnidadMedida());
        dto.setNivelStock(entidad.getNivelStock());
        if (entidad.getProveedor() != null) {
            dto.setProveedor(this.proveedorMapper.toDTO(entidad.getProveedor()));
        }
        return dto;
    }

    @Override
    public Ingrediente toMongo(IngredienteDTO dto) throws PersistenciaException {
        if (dto == null) {
            return null;
        }
        Ingrediente entidad = new Ingrediente();
        if (dto.getId() != null && ObjectId.isValid(dto.getId())) {
            entidad.setId(new ObjectId(dto.getId()));
        } else if (dto.getId() != null && !dto.getId().isEmpty() && !ObjectId.isValid(dto.getId())) {
            throw new PersistenciaException("Error al mapear el ingredienteId de IngredienteDTO de String a ObjectId");
        }
        entidad.setNombre(dto.getNombre());
        entidad.setCantidadDisponible(dto.getCantidadDisponible());
        entidad.setCantidadMinima(dto.getCantidadMinima());
        entidad.setUnidadMedida(dto.getUnidadMedida());
        entidad.setNivelStock(dto.getNivelStock());
        if (dto.getProveedor() != null) {
            entidad.setProveedor(this.proveedorMapper.toMongo(dto.getProveedor()));
        }
        return entidad;
    }

    @Override
    public List<IngredienteDTO> toDTOList(List<Ingrediente> entidades) {
        if (entidades == null) {
            return null;
        }
        List<IngredienteDTO> dtos = new ArrayList<>();
        for (Ingrediente entidad : entidades) {
            dtos.add(toDTO(entidad));
        }
        return dtos;
    }

    @Override
    public List<Ingrediente> toMongoList(List<IngredienteDTO> dtos) throws PersistenciaException {
        if (dtos == null) {
            return null;
        }
        List<Ingrediente> entidades = new ArrayList<>();
        for (IngredienteDTO dto : dtos) {
            entidades.add(toMongo(dto));
        }
        return entidades;
    }
}
