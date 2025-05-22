/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaProductoTamanioDTO;
import entidades.ProductoTamanio;
import interfacesMappers.IProductoTamanioIngredienteMapper;
import interfacesMappers.IProductoTamanioMapper;
import interfacesMappers.ITamanioMapper;
import java.util.stream.Collectors;

/**
 *
 * @author Jp
 * Su particularidad y complejidad residen en que `ProductoTamanio` es una entidad
 * compuesta que incluye un `Tamanio` y una lista de `ProductoTamanioIngrediente`.
 * Para manejar estas entidades anidadas, este mapeador hace uso de la inyección
 * de dependencia de otros mapeadores (`ITamanioMapper` y `IProductoTamanioIngredienteMapper`),
 * lo que demuestra una buena práctica de diseño para mapear estructuras de datos complejas.
 */
public class ProductoTamanioMapper implements IProductoTamanioMapper {

    private final ITamanioMapper tamanioMapper; // Mapeador para la entidad Tamanio.
    private final IProductoTamanioIngredienteMapper productoTamanioIngredienteMapper; // Mapeador para la entidad ProductoTamanioIngrediente.

    /**
     * Constructor de `ProductoTamanioMapper`.
     * Este constructor recibe instancias de `ITamanioMapper` y `IProductoTamanioIngredienteMapper`
     * a través de inyección de dependencia. Esto permite que `ProductoTamanioMapper`
     * delegue el mapeo de sus componentes anidados a los mapeadores especializados correspondientes.
     *
     * @param tamanioMapper El mapeador para la entidad `Tamanio`.
     * @param productoTamanioIngredienteMapper El mapeador para la entidad `ProductoTamanioIngrediente`.
     */
    public ProductoTamanioMapper(ITamanioMapper tamanioMapper, IProductoTamanioIngredienteMapper productoTamanioIngredienteMapper) {
        this.tamanioMapper = tamanioMapper;
        this.productoTamanioIngredienteMapper = productoTamanioIngredienteMapper;
    }

    /**
     * Convierte una entidad `ProductoTamanio` (modelo de la base de datos) a su
     * `PersistenciaProductoTamanioDTO` (DTO para la capa de persistencia).
     * Este método es vital para transformar los datos recuperados de la base de datos
     * en un formato adecuado para la capa de negocio, manejando las relaciones anidadas.
     * Utiliza los mapeadores inyectados para convertir recursivamente el `Tamanio`
     * y la lista de `ProductoTamanioIngrediente` a sus respectivos DTOs.
     *
     * @param productoTamanio La entidad `ProductoTamanio` a convertir.
     * @return Un `PersistenciaProductoTamanioDTO` con los datos mapeados, o `null` si la entidad de entrada es nula.
     */
    @Override
    public PersistenciaProductoTamanioDTO toDTO(ProductoTamanio productoTamanio) {
        if (productoTamanio == null) {
            return null;
        }
        PersistenciaProductoTamanioDTO dto = new PersistenciaProductoTamanioDTO();
        dto.setTamanio(tamanioMapper.toDTO(productoTamanio.getTamanio())); // Mapea el objeto Tamanio anidado.
        
        // Mapea la lista de ingredientes anidados si no es nula, usando Streams API para concisión.
        if (productoTamanio.getIngredientes() != null) {
            dto.setIngredientes(
                    productoTamanio.getIngredientes().stream()
                            .map(productoTamanioIngredienteMapper::toDTO) // Delega el mapeo de cada ingrediente.
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    /**
     * Convierte un `PersistenciaProductoTamanioDTO` (DTO de la capa de persistencia) a su
     * entidad `ProductoTamanio` (modelo de la base de datos).
     * Este método es esencial para transformar los datos recibidos de la capa de negocio
     * a un formato que pueda ser persistido en la base de datos, manejando también
     * las relaciones anidadas. Utiliza los mapeadores inyectados para convertir
     * recursivamente el `TamanioDTO` y la lista de `ProductoTamanioIngredienteDTO`
     * a sus respectivas entidades.
     *
     * @param dto El `PersistenciaProductoTamanioDTO` a convertir.
     * @return La entidad `ProductoTamanio` resultante, o `null` si el DTO de entrada es nulo.
     */
    @Override
    public ProductoTamanio toEntity(PersistenciaProductoTamanioDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductoTamanio entity = new ProductoTamanio();
        entity.setTamanio(tamanioMapper.toEntity(dto.getTamanio())); // Mapea el objeto Tamanio anidado.
        
        // Mapea la lista de ingredientes anidados si no es nula, usando Streams API.
        if (dto.getIngredientes() != null) {
            entity.setIngredientes(
                    dto.getIngredientes().stream()
                            .map(productoTamanioIngredienteMapper::toEntity) // Delega el mapeo de cada ingrediente.
                            .collect(Collectors.toList())
            );
        }
        return entity;
    }
}