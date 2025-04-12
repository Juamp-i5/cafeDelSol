/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMapper;

import DTOs.PedidoDTO;
import entidades.Pedido;
import exception.NegocioException;

/**
 *
 * @author rodri
 */
public interface IPedidoMapper {
    
    public Pedido toEntity (PedidoDTO pedidoDTO) throws NegocioException;
    
    
}
