/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaPedidoDTO;
import DTOs.PersistenciaProductoPedidoDTO;
import entidades.Pedido;
import entidades.ProductoPedido;
import interfacesMappers.IPagoMapper;
import interfacesMappers.IPedidoMapper;
import interfacesMappers.IProductoPedidoMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;

/**
 *  * Implementación de la interfaz {@link IPedidoMapper} para la conversión entre
 * entidades {@link Pedido} y DTOs {@link PersistenciaPedidoDTO}.
 * <p>
 * Esta clase se encarga de mapear los datos entre la representación de la
 * entidad de persistencia y el objeto de transferencia de datos utilizado en
 * otras capas de la aplicación, específicamente para los pedidos. Utiliza
 * mappers para sus sub-objetos como {@link ProductoPedido} y {@link entidades.Pago}.
 * </p>
 *
 * @author Jp
 */
public class PedidoMapper implements IPedidoMapper {

    /**
     * Mapper para convertir entre entidades {@link ProductoPedido} y sus DTOs.
     */
    IProductoPedidoMapper productoPedidoMapper;

    /**
     * Mapper para convertir entre entidades {@link entidades.Pago} y sus DTOs.
     */
    IPagoMapper pagoMapper;

    /**
     * Constructor que inyecta las dependencias de los mappers necesarios.
     *
     * @param productoPedidoMapper Mapper para {@link ProductoPedido}.
     * @param pagoMapper Mapper para {@link entidades.Pago}.
     */
    public PedidoMapper(IProductoPedidoMapper productoPedidoMapper, IPagoMapper pagoMapper) {
        this.productoPedidoMapper = productoPedidoMapper;
        this.pagoMapper = pagoMapper;
    }

    /**
     * Convierte un objeto {@link Pedido} (entidad de persistencia) a un
     * {@link PersistenciaPedidoDTO} (objeto de transferencia de datos).
     * <p>
     * Este método mapea los campos de la entidad {@code Pedido} a los campos
     * correspondientes del DTO {@code PersistenciaPedidoDTO}. Incluye la
     * conversión de las listas de {@link ProductoPedido} y el objeto
     * {@link entidades.Pago} utilizando los mappers inyectados.
     * </p>
     *
     * @param entidad El objeto {@link Pedido} a convertir. Puede ser
     * {@code null}.
     * @return Un {@link PersistenciaPedidoDTO} con los datos mapeados, o
     * {@code null} si la entidad de entrada es {@code null}.
     */
    @Override
    public PersistenciaPedidoDTO toPersistenciaProductoTamanioDTO(Pedido entidad) {
        if (entidad == null) {
            return null;
        }
        PersistenciaPedidoDTO dto = new PersistenciaPedidoDTO();
        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toString());
        }

        if (entidad.getProductos() != null) {
            List<PersistenciaProductoPedidoDTO> productosDTO = entidad.getProductos().stream()
                    .map(productoPedidoMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setProductos(productosDTO);
        } else {
            dto.setProductos(new ArrayList<>());
        }

        dto.setPrecioTotal(entidad.getPrecioTotal());
        dto.setEstado(entidad.getEstado());
        if (entidad.getBaristaId() != null) {
            dto.setBaristaId(entidad.getBaristaId().toString());
        }
        dto.setPago(pagoMapper.toDTO(entidad.getPago()));
        dto.setFechaHora(entidad.getFechaHora());
        return dto;
    }

    /**
     * Convierte un objeto {@link PersistenciaPedidoDTO} (objeto de transferencia
     * de datos) a un {@link Pedido} (entidad de persistencia).
     * <p>
     * Este método mapea los campos del DTO {@code PersistenciaPedidoDTO} a los
     * campos correspondientes de la entidad {@code Pedido}. Incluye la
     * conversión de las listas de {@link PersistenciaProductoPedidoDTO} y el
     * DTO de {@link entidades.Pago} utilizando los mappers inyectados.
     * </p>
     *
     * @param dto El objeto {@link PersistenciaPedidoDTO} a convertir. Puede ser
     * {@code null}.
     * @return Un {@link Pedido} con los datos mapeados, o {@code null} si el
     * DTO de entrada es {@code null}.
     */
    @Override
    public Pedido toEntity(PersistenciaPedidoDTO dto) {
        if (dto == null) {
            return null;
        }
        ObjectId id = null;
        if (dto.getId() != null && !dto.getId().isEmpty()) {
            try {
                id = new ObjectId(dto.getId());
            } catch (IllegalArgumentException e) {
                System.err.println("ID de Pedido inválido: " + dto.getId());
            }
        }

        ObjectId baristaId = null;
        if (dto.getBaristaId() != null && !dto.getBaristaId().isEmpty()) {
            try {
                baristaId = new ObjectId(dto.getBaristaId());
            } catch (IllegalArgumentException e) {
                System.err.println("ID de Barista inválido: " + dto.getBaristaId());
            }
        }

        List<ProductoPedido> productos = new ArrayList<>();
        if (dto.getProductos() != null) {
            productos = dto.getProductos().stream()
                    .map(productoPedidoMapper::toEntity)
                    .collect(Collectors.toList());
        }

        return new Pedido(
                id,
                productos,
                dto.getPrecioTotal(),
                dto.getEstado(),
                baristaId,
                pagoMapper.toEntity(dto.getPago()),
                dto.getFechaHora()
        );
    }

}
