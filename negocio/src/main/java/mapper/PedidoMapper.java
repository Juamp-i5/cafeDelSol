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

}
