/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaProductoTamanioIngredienteDTO;
import entidades.ProductoTamanioIngrediente;
import interfacesMappers.IIngredienteMapper;
import interfacesMappers.IProductoTamanioIngredienteMapper;

/**
 *
 * @author Jp
 */
public class ProductoTamanioIngredienteMapper implements IProductoTamanioIngredienteMapper {

    private final IIngredienteMapper ingredienteMapper;

    public ProductoTamanioIngredienteMapper(IIngredienteMapper ingredienteMapper) {
        this.ingredienteMapper = ingredienteMapper;
    }

    @Override
    public PersistenciaProductoTamanioIngredienteDTO toDTO(ProductoTamanioIngrediente productoTamanioIngrediente) {
        if (productoTamanioIngrediente == null) {
            return null;
        }
        PersistenciaProductoTamanioIngredienteDTO dto = new PersistenciaProductoTamanioIngredienteDTO();
        dto.setIngrediente(ingredienteMapper.toDTO(productoTamanioIngrediente.getIngrediente()));
        dto.setCantidad(productoTamanioIngrediente.getCantidad());
        return dto;
    }

    @Override
    public ProductoTamanioIngrediente toEntity(PersistenciaProductoTamanioIngredienteDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductoTamanioIngrediente entity = new ProductoTamanioIngrediente();
        entity.setIngrediente(ingredienteMapper.toEntity(dto.getIngrediente()));
        entity.setCantidad(dto.getCantidad());
        return entity;
    }
}
