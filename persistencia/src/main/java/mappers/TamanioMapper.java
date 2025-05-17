/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import interfacesMappers.ITamanioMapper;
import interfacesMappers.IIngredienteMapper;
import DTOs.TamanioDTO;
import entidades.Tamanio;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class TamanioMapper implements ITamanioMapper {
    
    private final IIngredienteMapper ingredienteMapper;
    
    public TamanioMapper(IIngredienteMapper ingredienteMapper) {
        this.ingredienteMapper = ingredienteMapper;
    }
    
    @Override
    public TamanioDTO toDTO(Tamanio entidad) {
        if (entidad == null) {
            return null;
        }
        TamanioDTO dto = new TamanioDTO();
        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toHexString());
        }
        dto.setNombre(entidad.getNombre());
        dto.setPrecioAdicional(entidad.getPrecioAdicional());
        dto.setImagenData(entidad.getImagenData());
        if (entidad.getIngredientes() != null) {
            dto.setIngredientes(this.ingredienteMapper.toDTOList(entidad.getIngredientes()));
        }
        return dto;
    }
    
    @Override
    public Tamanio toMongo(TamanioDTO dto) throws PersistenciaException {
        if (dto == null) {
            return null;
        }
        Tamanio entidad = new Tamanio();
        if (dto.getId() != null && ObjectId.isValid(dto.getId())) {
            entidad.setId(new ObjectId(dto.getId()));
        } else if (dto.getId() != null && !dto.getId().isEmpty() && !ObjectId.isValid(dto.getId())) {
            throw new PersistenciaException("Error al mapear el id de TamanioDTO de String a ObjectId");
        }
        entidad.setNombre(dto.getNombre());
        entidad.setPrecioAdicional(dto.getPrecioAdicional());
        entidad.setImagenData(dto.getImagenData());
        if (dto.getIngredientes() != null) {
            entidad.setIngredientes(this.ingredienteMapper.toMongoList(dto.getIngredientes()));
        }
        return entidad;
    }
    
    @Override
    public List<TamanioDTO> toDTOList(List<Tamanio> entidades) {
        if (entidades == null) {
            return null;
        }
        List<TamanioDTO> dtos = new ArrayList<>();
        for (Tamanio entidad : entidades) {
            dtos.add(toDTO(entidad));
        }
        return dtos;
    }
    
    @Override
    public List<Tamanio> toMongoList(List<TamanioDTO> dtos) throws PersistenciaException {
        if (dtos == null) {
            return null;
        }
        List<Tamanio> entidades = new ArrayList<>();
        for (TamanioDTO dto : dtos) {
            entidades.add(toMongo(dto));
        }
        return entidades;
    }
}
