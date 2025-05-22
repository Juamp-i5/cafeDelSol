/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaProductoPedidoDTO;
import entidades.ProductoPedido;
import interfacesMappers.IProductoMapper;
import interfacesMappers.IProductoPedidoMapper;
import interfacesMappers.ISaborMapper;
import interfacesMappers.ITamanioMapper;
import interfacesMappers.IToppingMapper;

/**
 *
 * @author Jp
 */
public class ProductoPedidoMapper implements IProductoPedidoMapper {

    IProductoMapper productoMapper;
    ISaborMapper saborMapper;
    ITamanioMapper tamanioMapper;
    IToppingMapper toppingMapper;

    public ProductoPedidoMapper(IProductoMapper productoMapper, ISaborMapper saborMapper, ITamanioMapper tamanioMapper, IToppingMapper toppingMapper) {
        this.productoMapper = productoMapper;
        this.saborMapper = saborMapper;
        this.tamanioMapper = tamanioMapper;
        this.toppingMapper = toppingMapper;
    }

    @Override
    public PersistenciaProductoPedidoDTO toDTO(ProductoPedido entidad) {
        if (entidad == null) {
            return null;
        }
        PersistenciaProductoPedidoDTO dto = new PersistenciaProductoPedidoDTO();
        dto.setProducto(productoMapper.toDTO(entidad.getProducto()));
        dto.setSabor(saborMapper.toDTO(entidad.getSabor()));
        dto.setTamanio(tamanioMapper.toDTO(entidad.getTamanio()));
        dto.setTopping(toppingMapper.toDTO(entidad.getTopping()));
        dto.setCantidad(entidad.getCantidad());
        dto.setPrecioUnitario(entidad.getPrecioUnitario());
        dto.setSubtotal(entidad.getSubtotal());
        return dto;
    }

    @Override
    public ProductoPedido toEntity(PersistenciaProductoPedidoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new ProductoPedido(
                productoMapper.toEntity(dto.getProducto()),
                saborMapper.toEntity(dto.getSabor()),
                tamanioMapper.toEntity(dto.getTamanio()),
                toppingMapper.toEntity(dto.getTopping()),
                dto.getCantidad(),
                dto.getPrecioUnitario(),
                dto.getSubtotal()
        );
    }
}
