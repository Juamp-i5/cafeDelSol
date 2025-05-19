/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import interfacesMappers.IProductoMapper;
import DTOs.PersistenciaProductoDTO;
import entidades.Producto;
import interfacesMappers.IProductoTamanioMapper;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class ProductoMapper implements IProductoMapper {

    private final IProductoTamanioMapper productoTamanioMapper;

    public ProductoMapper(IProductoTamanioMapper productoTamanioMapper) {
        this.productoTamanioMapper = productoTamanioMapper;
    }

    @Override
    public PersistenciaProductoDTO toDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        PersistenciaProductoDTO dto = new PersistenciaProductoDTO();
        dto.setId(producto.getId() != null ? producto.getId().toHexString() : null);
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setCategoria(producto.getCategoria());
        dto.setEstado(producto.getEstado());
        dto.setPrecioBase(producto.getPrecioBase());
        dto.setImageData(producto.getImageData());
        if (producto.getTamanios() != null) {
            dto.setTamanios(
                    producto.getTamanios().stream()
                            .map(productoTamanioMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    @Override
    public Producto toEntity(PersistenciaProductoDTO dto) {
        if (dto == null) {
            return null;
        }
        Producto producto = new Producto();
        if (dto.getId() != null && ObjectId.isValid(dto.getId())) {
            producto.setId(new ObjectId(dto.getId()));
        }
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCategoria(dto.getCategoria());
        producto.setEstado(dto.getEstado());
        producto.setPrecioBase(dto.getPrecioBase());
        producto.setImageData(dto.getImageData());
        if (dto.getTamanios() != null) {
            producto.setTamanios(
                    dto.getTamanios().stream()
                            .map(productoTamanioMapper::toEntity)
                            .collect(Collectors.toList())
            );
        }
        return producto;
    }

}
