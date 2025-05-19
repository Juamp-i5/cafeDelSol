/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaProductoTamanioDTO;
import entidades.ProductoTamanio;
import interfacesMappers.IProductoTamanioIngredienteMapper;
import interfacesMappers.IProductoTamanioMapper;
import interfacesMappers.ITamanioMapper;
import java.util.stream.Collectors;

/**
 *
 * @author Jp
 */
public class ProductoTamanioMapper implements IProductoTamanioMapper {

    private final ITamanioMapper tamanioMapper;
    private final IProductoTamanioIngredienteMapper productoTamanioIngredienteMapper;

    public ProductoTamanioMapper(ITamanioMapper tamanioMapper, IProductoTamanioIngredienteMapper productoTamanioIngredienteMapper) {
        this.tamanioMapper = tamanioMapper;
        this.productoTamanioIngredienteMapper = productoTamanioIngredienteMapper;
    }

    @Override
    public PersistenciaProductoTamanioDTO toDTO(ProductoTamanio productoTamanio) {
        if (productoTamanio == null) {
            return null;
        }
        PersistenciaProductoTamanioDTO dto = new PersistenciaProductoTamanioDTO();
        dto.setTamanio(tamanioMapper.toDTO(productoTamanio.getTamanio()));
        if (productoTamanio.getIngredientes() != null) {
            dto.setIngredientes(
                    productoTamanio.getIngredientes().stream()
                            .map(productoTamanioIngredienteMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    @Override
    public ProductoTamanio toEntity(PersistenciaProductoTamanioDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductoTamanio entity = new ProductoTamanio();
        entity.setTamanio(tamanioMapper.toEntity(dto.getTamanio()));
        if (dto.getIngredientes() != null) {
            entity.setIngredientes(
                    dto.getIngredientes().stream()
                            .map(productoTamanioIngredienteMapper::toEntity)
                            .collect(Collectors.toList())
            );
        }
        return entity;
    }
}
