/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaProductoPedidoDTO;
import entidades.ProductoPedido;

/**
 * * Interfaz que define las operaciones de mapeo entre la entidad
 * {@link ProductoPedido} y su correspondiente DTO de persistencia
 * {@link PersistenciaProductoPedidoDTO}.
 * <p>
 * Esta interfaz establece el contrato para la conversión bidireccional de datos
 * relacionados con los productos incluidos en un pedido, facilitando la
 * transferencia de información entre la capa de persistencia y otras capas de
 * la aplicación.
 * </p>
 *
 * @author Jp
 */
public interface IProductoPedidoMapper {

    /**
     * Convierte un objeto {@link ProductoPedido} (entidad de persistencia) a
     * un {@link PersistenciaProductoPedidoDTO} (objeto de transferencia de
     * datos).
     *
     * @param entidad El objeto {@link ProductoPedido} a convertir. Puede ser
     * {@code null}.
     * @return Un {@link PersistenciaProductoPedidoDTO} con los datos
     * mapeados, o {@code null} si la entidad de entrada es {@code null}.
     */
    public PersistenciaProductoPedidoDTO toDTO(ProductoPedido entidad);

    /**
     * Convierte un objeto {@link PersistenciaProductoPedidoDTO} (objeto de
     * transferencia de datos) a un {@link ProductoPedido} (entidad de
     * persistencia).
     *
     * @param dto El objeto {@link PersistenciaProductoPedidoDTO} a convertir.
     * Puede ser {@code null}.
     * @return Un {@link ProductoPedido} con los datos mapeados, o
     * {@code null} si el DTO de entrada es {@code null}.
     */
    public ProductoPedido toEntity(PersistenciaProductoPedidoDTO dto);
}
