/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMapper;

import DTOs.ProductoPedidoDTO;
import entidades.ProductoPedido;
import excepciones.NegocioException;

/**
 *
 * @author rodri
 */
public interface IProductoPedidoMapper {

    public ProductoPedido toEntity(ProductoPedidoDTO productoPedidoDTO) throws NegocioException;

    public ProductoPedido toEntitySinTopping(ProductoPedidoDTO productoPedidoDTO) throws NegocioException;
}
