/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMapper;

import DTOs.PedidoDTO;
import DTOs.PersistenciaPedidoDTO;
import entidades.Pedido;

/**
 *
 * @author rodri
 */
public interface IPedidoMapper {

    public Pedido toEntity(PedidoDTO pedidoDTO);

    public PersistenciaPedidoDTO toPersistenciaPedidoDTO(PedidoDTO fuentePedido);
    
    public PedidoDTO toPedidoDTO(PersistenciaPedidoDTO persistenciaPedido);
}
