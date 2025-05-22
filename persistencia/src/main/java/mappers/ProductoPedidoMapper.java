/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaProductoPedidoDTO;
import entidades.ProductoPedido;
import interfacesMappers.IProductoMapper;
import interfacesMappers.IProductoPedidoMapper;
import interfacesMappers.ISaborMapper;
import interfacesMappers.ITamanioMapper;
import interfacesMappers.IToppingMapper;

/**
 *  * Implementación de la interfaz {@link IProductoPedidoMapper} para la
 * conversión entre entidades {@link ProductoPedido} y DTOs
 * {@link PersistenciaProductoPedidoDTO}.
 * <p>
 * Esta clase se encarga de mapear los datos entre la representación de la
 * entidad de persistencia y el objeto de transferencia de datos utilizado en
 * otras capas de la aplicación, específicamente para los productos dentro de
 * un pedido. Utiliza mappers para sus sub-objetos como {@link entidades.Producto},
 * {@link entidades.Sabor}, {@link entidades.Tamanio}, y {@link entidades.Topping}.
 * </p>
 *
 * @author Jp
 */
public class ProductoPedidoMapper implements IProductoPedidoMapper {

    /**
     * Mapper para convertir entre entidades {@link entidades.Producto} y sus DTOs.
     */
    IProductoMapper productoMapper;

    /**
     * Mapper para convertir entre entidades {@link entidades.Sabor} y sus DTOs.
     */
    ISaborMapper saborMapper;

    /**
     * Mapper para convertir entre entidades {@link entidades.Tamanio} y sus DTOs.
     */
    ITamanioMapper tamanioMapper;

    /**
     * Mapper para convertir entre entidades {@link entidades.Topping} y sus DTOs.
     */
    IToppingMapper toppingMapper;

    /**
     * Constructor que inyecta las dependencias de los mappers necesarios.
     *
     * @param productoMapper Mapper para {@link entidades.Producto}.
     * @param saborMapper Mapper para {@link entidades.Sabor}.
     * @param tamanioMapper Mapper para {@link entidades.Tamanio}.
     * @param toppingMapper Mapper para {@link entidades.Topping}.
     */
    public ProductoPedidoMapper(IProductoMapper productoMapper, ISaborMapper saborMapper, ITamanioMapper tamanioMapper, IToppingMapper toppingMapper) {
        this.productoMapper = productoMapper;
        this.saborMapper = saborMapper;
        this.tamanioMapper = tamanioMapper;
        this.toppingMapper = toppingMapper;
    }

    /**
     * Convierte un objeto {@link ProductoPedido} (entidad de persistencia) a
     * un {@link PersistenciaProductoPedidoDTO} (objeto de transferencia de
     * datos).
     * <p>
     * Este método mapea los campos de la entidad {@code ProductoPedido} a los
     * campos correspondientes del DTO {@code PersistenciaProductoPedidoDTO}.
     * Incluye la conversión de los sub-objetos como {@link entidades.Producto},
     * {@link entidades.Sabor}, {@link entidades.Tamanio}, y {@link entidades.Topping}
     * utilizando los mappers inyectados.
     * </p>
     *
     * @param entidad El objeto {@link ProductoPedido} a convertir. Puede ser
     * {@code null}.
     * @return Un {@link PersistenciaProductoPedidoDTO} con los datos
     * mapeados, o {@code null} si la entidad de entrada es {@code null}.
     */
    @Override
    public PersistenciaProductoPedidoDTO toDTO(ProductoPedido entidad) {
        if (entidad == null) {
            return null;
        }
        PersistenciaProductoPedidoDTO dto = new PersistenciaProductoPedidoDTO();
        dto.setProducto(productoMapper.toDTO(entidad.getProducto()));
        dto.setSabor(saborMapper.toDTO(entidad.getSabor()));
        dto.setTamanio(tamanioMapper.toDTO(entidad.getTamanio()));
        dto.setTopping(toppingMapper.toDTO(entidad.getTopping()));
        dto.setCantidad(entidad.getCantidad());
        dto.setPrecioUnitario(entidad.getPrecioUnitario());
        dto.setSubtotal(entidad.getSubtotal());
        return dto;
    }

    /**
     * Convierte un objeto {@link PersistenciaProductoPedidoDTO} (objeto de
     * transferencia de datos) a un {@link ProductoPedido} (entidad de
     * persistencia).
     * <p>
     * Este método mapea los campos del DTO {@code PersistenciaProductoPedidoDTO}
     * a los campos correspondientes de la entidad {@code ProductoPedido}.
     * Incluye la conversión de los DTOs de sub-objetos como
     * {@link entidades.Producto}, {@link entidades.Sabor}, {@link entidades.Tamanio},
     * y {@link entidades.Topping} utilizando los mappers inyectados.
     * </p>
     *
     * @param dto El objeto {@link PersistenciaProductoPedidoDTO} a convertir.
     * Puede ser {@code null}.
     * @return Un {@link ProductoPedido} con los datos mapeados, o
     * {@code null} si el DTO de entrada es {@code null}.
     */
    @Override
    public ProductoPedido toEntity(PersistenciaProductoPedidoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new ProductoPedido(
                productoMapper.toEntity(dto.getProducto()),
                saborMapper.toEntity(dto.getSabor()),
                tamanioMapper.toEntity(dto.getTamanio()),
                toppingMapper.toEntity(dto.getTopping()),
                dto.getCantidad(),
                dto.getPrecioUnitario(),
                dto.getSubtotal()
        );
    }
}
