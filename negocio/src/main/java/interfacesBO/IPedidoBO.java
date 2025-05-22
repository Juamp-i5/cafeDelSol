/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesBO;

import DTOs.PedidoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IPedidoBO {

    public void registrarPedido(PedidoDTO pedidoDTO) throws NegocioException;
    
    public List<PedidoDTO> obtenerPedidosDelivery() throws NegocioException;
    
    public void actualizarEstado(String idPedido) throws NegocioException;

}
