/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import interfacesMappers.IProductoMapper;
import DTOs.PersistenciaProductoDTO;
import entidades.Producto;
import entidades.ProductoTamanio;
import interfacesMappers.IProductoTamanioMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;

/**
 * Implementación de la interfaz {@link IProductoMapper} para convertir entre
 * entidades {@link Producto} y objetos de transferencia de datos
 * {@link PersistenciaProductoDTO}.
 * <p>
 * Esta clase utiliza una instancia de {@link IProductoTamanioMapper} para
 * manejar la conversión de las listas anidadas de tamaños de producto.
 * </p>
 *
 * @author Jp
 */
public class ProductoMapper implements IProductoMapper {

    /**
     * Mapper utilizado para la conversión de objetos {@link ProductoTamanio} y
     * sus correspondientes DTOs.
     */
    private final IProductoTamanioMapper productoTamanioMapper;

    /**
     * Construye una nueva instancia de {@code ProductoMapper}.
     *
     * @param productoTamanioMapper Una implementación de
     * {@link IProductoTamanioMapper} para ser utilizada en el mapeo de las
     * configuraciones de tamaño del producto. No debe ser {@code null}.
     */
    public ProductoMapper(IProductoTamanioMapper productoTamanioMapper) {
        if (productoTamanioMapper == null) {
            throw new IllegalArgumentException("productoTamanioMapper no puede ser nulo.");
        }
        this.productoTamanioMapper = productoTamanioMapper;
    }

    /**
     * Convierte una entidad {@link Producto} a un objeto
     * {@link PersistenciaProductoDTO}.
     * <p>
     * Realiza una conversión campo por campo, incluyendo la transformación del
     * {@link ObjectId} del producto a su representación en {@link String}. La
     * lista de {@code tamanios} se convierte utilizando el
     * {@link IProductoTamanioMapper} inyectado.
     * </p>
     *
     * @param producto La entidad {@link Producto} a convertir. Puede ser
     * {@code null}.
     * @return Un objeto {@link PersistenciaProductoDTO} correspondiente a la
     * entidad, o {@code null} si la entidad de entrada es {@code null}.
     */
    @Override
    public PersistenciaProductoDTO toDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        PersistenciaProductoDTO dto = new PersistenciaProductoDTO();
        dto.setId(producto.getId() != null ? producto.getId().toHexString() : null);
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setCategoria(producto.getCategoria());
        dto.setEstado(producto.getEstado());
        dto.setPrecioBase(producto.getPrecioBase());
        dto.setImageData(producto.getImageData());

        if (producto.getTamanios() != null) {
            List<DTOs.PersistenciaProductoTamanioDTO> tamaniosDTO = producto.getTamanios().stream()
                    .map(productoTamanioMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setTamanios(tamaniosDTO);
        } else {
            dto.setTamanios(new ArrayList<>());
        }
        return dto;
    }

    /**
     * Convierte un objeto {@link PersistenciaProductoDTO} a una entidad
     * {@link Producto}.
     * <p>
     * Realiza una conversión campo por campo. El ID en formato {@link String}
     * del DTO se convierte a {@link ObjectId}, validando su formato. La lista
     * de DTOs de tamaños se convierte a entidades {@link ProductoTamanio}
     * utilizando el {@link IProductoTamanioMapper} inyectado.
     * </p>
     *
     * @param dto El objeto {@link PersistenciaProductoDTO} a convertir. Puede
     * ser {@code null}.
     * @return Una entidad {@link Producto} correspondiente al DTO, o
     * {@code null} si el DTO de entrada es {@code null}. Si el ID del DTO es
     * una cadena inválida para {@link ObjectId}, el ID de la entidad resultante
     * podría quedar {@code null} o se podría optar por lanzar una excepción
     * dependiendo de la lógica de negocio (aquí se deja como {@code null} si es
     * inválido).
     */
    @Override
    public Producto toEntity(PersistenciaProductoDTO dto) {
        if (dto == null) {
            return null;
        }
        Producto producto = new Producto();
        if (dto.getId() != null && ObjectId.isValid(dto.getId())) {
            producto.setId(new ObjectId(dto.getId()));
        } else if (dto.getId() != null) {
            System.err.println("Advertencia: ID de producto en DTO no es un ObjectId válido: " + dto.getId());
        }

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCategoria(dto.getCategoria());
        producto.setEstado(dto.getEstado());
        producto.setPrecioBase(dto.getPrecioBase());
        producto.setImageData(dto.getImageData());

        if (dto.getTamanios() != null) {
            List<ProductoTamanio> tamaniosEntidad = dto.getTamanios().stream()
                    .map(productoTamanioMapper::toEntity)
                    .collect(Collectors.toList());
            producto.setTamanios(tamaniosEntidad);
        } else {
            producto.setTamanios(new ArrayList<>());
        }
        return producto;
    }

}
