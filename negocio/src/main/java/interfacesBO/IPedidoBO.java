/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesBO;

import DTOs.PedidoDTO;
import exception.NegocioException;

/**
 *
 * @author rodri
 */
public interface IPedidoBO {
    
    public PedidoDTO registrarPedido (PedidoDTO pedidoDTO) throws NegocioException;
    
}
