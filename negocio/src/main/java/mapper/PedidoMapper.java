/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.PedidoDTO;
import entidades.Pedido;
import interfacesMapper.IPedidoMapper;

/**
 *
 * @author rodri
 */
public class PedidoMapper implements IPedidoMapper {

    private static PedidoMapper instanceMapper;

    public PedidoMapper() {
    }

    public static PedidoMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new PedidoMapper();
        }
        return instanceMapper;
    }

    @Override
    public Pedido toEntity(PedidoDTO pedidoDTO) {
        return new Pedido(
                pedidoDTO.getCostoTotal(),
                pedidoDTO.isTerminado()
        );
    }
    
    @Override
    public PersistenciaPedidoDTO toPersistenciaProductoTamanioDTO(PedidoDTO entidad) {
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

}
