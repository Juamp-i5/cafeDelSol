/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaPedidoDTO;
import DTOs.PersistenciaProductoPedidoDTO;
import entidades.Pedido;
import entidades.ProductoPedido;
import interfacesMappers.IPagoMapper;
import interfacesMappers.IPedidoMapper;
import interfacesMappers.IProductoPedidoMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class PedidoMapper implements IPedidoMapper {

    IProductoPedidoMapper productoPedidoMapper;
    IPagoMapper pagoMapper;

    public PedidoMapper(IProductoPedidoMapper productoPedidoMapper, IPagoMapper pagoMapper) {
        this.productoPedidoMapper = productoPedidoMapper;
        this.pagoMapper = pagoMapper;
    }

    @Override
    public PersistenciaPedidoDTO toPersistenciaProductoTamanioDTO(Pedido entidad) {
        if (entidad == null) {
            return null;
        }
        PersistenciaPedidoDTO dto = new PersistenciaPedidoDTO();
        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toString());
        }

        if (entidad.getProductos() != null) {
            List<PersistenciaProductoPedidoDTO> productosDTO = entidad.getProductos().stream()
                    .map(productoPedidoMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setProductos(productosDTO);
        } else {
            dto.setProductos(new ArrayList<>());
        }

        dto.setPrecioTotal(entidad.getPrecioTotal());
        dto.setEstado(entidad.getEstado());
        if (entidad.getBaristaId() != null) {
            dto.setBaristaId(entidad.getBaristaId().toString());
        }
        dto.setPago(pagoMapper.toDTO(entidad.getPago()));
        dto.setFechaHora(entidad.getFechaHora());
        return dto;
    }

    @Override
    public Pedido toEntity(PersistenciaPedidoDTO dto) {
        if (dto == null) {
            return null;
        }
        ObjectId id = null;
        if (dto.getId() != null && !dto.getId().isEmpty()) {
            try {
                id = new ObjectId(dto.getId());
            } catch (IllegalArgumentException e) {
                System.err.println("ID de Pedido inválido: " + dto.getId());
            }
        }

        ObjectId baristaId = null;
        if (dto.getBaristaId() != null && !dto.getBaristaId().isEmpty()) {
            try {
                baristaId = new ObjectId(dto.getBaristaId());
            } catch (IllegalArgumentException e) {
                System.err.println("ID de Barista inválido: " + dto.getBaristaId());
            }
        }

        List<ProductoPedido> productos = new ArrayList<>();
        if (dto.getProductos() != null) {
            productos = dto.getProductos().stream()
                    .map(productoPedidoMapper::toEntity)
                    .collect(Collectors.toList());
        }

        return new Pedido(
                id,
                productos,
                dto.getPrecioTotal(),
                dto.getEstado(),
                baristaId,
                pagoMapper.toEntity(dto.getPago()),
                dto.getFechaHora()
        );
    }

}
