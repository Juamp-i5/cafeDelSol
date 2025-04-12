/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.ProductoPedidoDTO;
import entidades.ProductoPedido;
import exception.NegocioException;
import interfacesMapper.IProductoPedidoMapper;

/**
 *
 * @author rodri
 */
public class ProductoPedidoMapper implements IProductoPedidoMapper{

    @Override
    public ProductoPedido toEntity(ProductoPedidoDTO productoPedidoDTO) throws NegocioException {
        return new ProductoPedido(productoPedidoDTO., tamanio, sabor, topping)
    }

    @Override
    public ProductoPedido toEntitySinTopping(ProductoPedidoDTO productoPedidoDTO) throws NegocioException {
        
    }
    
    
    
}
