/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaPedidoDTO;
import entidades.Pedido;

/**
 * Interfaz que define los métodos de mapeo entre la entidad {@link Pedido} y su
 * DTO correspondiente {@link PersistenciaPedidoDTO}.
 *
 * <p>
 * Esta interfaz se utiliza para convertir objetos de tipo entidad a DTO y
 * viceversa, facilitando la separación de capas en la arquitectura de la
 * aplicación.</p>
 *
 * @author Jp
 */
public interface IPedidoMapper {

    /**
     * Convierte una entidad {@link Pedido} a un objeto
     * {@link PersistenciaPedidoDTO}.
     *
     * @param entidad la entidad de tipo {@code Pedido} que se desea convertir.
     * @return un objeto {@code PersistenciaPedidoDTO} con los datos mapeados
     * desde la entidad, o {@code null} si la entidad es {@code null}.
     */
    public PersistenciaPedidoDTO toPersistenciaProductoTamanioDTO(Pedido entidad);

    /**
     * Convierte un objeto {@link PersistenciaPedidoDTO} a una entidad
     * {@link Pedido}.
     *
     * @param dto el DTO de tipo {@code PersistenciaPedidoDTO} que se desea
     * convertir.
     * @return una entidad {@code Pedido} con los datos mapeados desde el DTO, o
     * {@code null} si el DTO es {@code null}.
     */
    public Pedido toEntity(PersistenciaPedidoDTO dto);
}
