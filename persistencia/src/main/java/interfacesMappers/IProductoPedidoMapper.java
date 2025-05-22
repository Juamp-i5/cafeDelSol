/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaProductoPedidoDTO;
import entidades.ProductoPedido;

/**
 *
 * @author Jp
 */
public interface IProductoPedidoMapper {

    public PersistenciaProductoPedidoDTO toDTO(ProductoPedido entidad);

    public ProductoPedido toEntity(PersistenciaProductoPedidoDTO dto);
}
