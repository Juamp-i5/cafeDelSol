/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.PedidoDTO;
import entidades.Pedido;
import exception.NegocioException;
import interfacesMapper.IPedidoMapper;

/**
 *
 * @author rodri
 */
public class PedidoMapper implements IPedidoMapper{

    @Override
    public Pedido toEntity(PedidoDTO pedidoDTO) throws NegocioException {
        return new Pedido(
                pedidoDTO.getCostoTotal(), 
                pedidoDTO.isTerminado()
        );
    }
    
}
