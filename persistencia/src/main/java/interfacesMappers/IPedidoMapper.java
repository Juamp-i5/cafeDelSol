/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaPedidoDTO;
import entidades.Pedido;

/**
 *
 * @author Jp
 */
public interface IPedidoMapper {

    public PersistenciaPedidoDTO toPersistenciaProductoTamanioDTO(Pedido entidad);

    public Pedido toEntity(PersistenciaPedidoDTO dto);
}
