/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import interfacesMappers.ITamanioMapper;
import interfacesMappers.IProductoMapper;
import DTOs.ProductoDTO;
import entidades.Producto;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class ProductoMapper implements IProductoMapper {

    private final ITamanioMapper tamanioMapper;

    public ProductoMapper(ITamanioMapper tamanioMapper) {
        this.tamanioMapper = tamanioMapper;
    }

    @Override
    public ProductoDTO toDTO(Producto entidad) {
        if (entidad == null) {
            return null;
        }
        ProductoDTO dto = new ProductoDTO();
        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toHexString());
        }
        dto.setNombre(entidad.getNombre());
        dto.setCategoria(entidad.getCategoria());
        dto.setPrecioBase(entidad.getPrecioBase());
        dto.setImageData(entidad.getImageData());
        dto.setEstado(entidad.getEstado());
        if (entidad.getTamanios() != null) {
            dto.setTamanios(this.tamanioMapper.toDTOList(entidad.getTamanios()));
        }
        return dto;
    }

    @Override
    public Producto toMongo(ProductoDTO dto) throws PersistenciaException {
        if (dto == null) {
            return null;
        }
        Producto entidad = new Producto();
        if (dto.getId() != null && ObjectId.isValid(dto.getId())) {
            entidad.setId(new ObjectId(dto.getId()));
        } else if (dto.getId() != null && !dto.getId().isEmpty() && !ObjectId.isValid(dto.getId())) {
            throw new PersistenciaException("Error al mapear el id de ProductoDTO de String a ObjectId");
        }
        entidad.setNombre(dto.getNombre());
        entidad.setCategoria(dto.getCategoria());
        entidad.setPrecioBase(dto.getPrecioBase());
        entidad.setImageData(dto.getImageData());
        entidad.setEstado(dto.getEstado());
        if (dto.getTamanios() != null) {
            entidad.setTamanios(this.tamanioMapper.toMongoList(dto.getTamanios()));
        }
        return entidad;
    }

    @Override
    public List<ProductoDTO> toDTOList(List<Producto> entidades) {
        if (entidades == null) {
            return null;
        }
        List<ProductoDTO> dtos = new ArrayList<>();
        for (Producto entidad : entidades) {
            dtos.add(toDTO(entidad));
        }
        return dtos;
    }

    @Override
    public List<Producto> toMongoList(List<ProductoDTO> dtos) throws PersistenciaException {
        if (dtos == null) {
            return null;
        }
        List<Producto> entidades = new ArrayList<>();
        for (ProductoDTO dto : dtos) {
            entidades.add(toMongo(dto));
        }
        return entidades;
    }
}
