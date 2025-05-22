/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaProductoTamanioIngredienteDTO;
import entidades.ProductoTamanioIngrediente;
import interfacesMappers.IIngredienteMapper;
import interfacesMappers.IProductoTamanioIngredienteMapper;

/**
 *
 * @author Jp
 * representa la estructura de datos en la base de datos) y el DTO
 * `PersistenciaProductoTamanioIngredienteDTO` (utilizado para la transferencia
 * de datos con la capa de negocio). Este mapeador también utiliza la inyección
 * de dependencia de `IIngredienteMapper` para manejar el mapeo de los ingredientes
 * anidados, manteniendo la coherencia y la modularidad.
 */
public class ProductoTamanioIngredienteMapper implements IProductoTamanioIngredienteMapper {

    private final IIngredienteMapper ingredienteMapper; // Inyección de dependencia del mapeador de Ingrediente.

    /**
     * Constructor de `ProductoTamanioIngredienteMapper`.
     * Recibe una instancia de `IIngredienteMapper` para delegar el mapeo de los objetos `Ingrediente`
     * anidados dentro de `ProductoTamanioIngrediente`.
     *
     * @param ingredienteMapper El mapeador de ingredientes a utilizar.
     */
    public ProductoTamanioIngredienteMapper(IIngredienteMapper ingredienteMapper) {
        this.ingredienteMapper = ingredienteMapper;
    }

    /**
     * Convierte una entidad `ProductoTamanioIngrediente` a su DTO de persistencia,
     * `PersistenciaProductoTamanioIngredienteDTO`.
     * Este método es crucial para transformar los datos recuperados de la base de datos
     * a un formato que la capa de negocio pueda consumir de forma desacoplada.
     * Realiza un mapeo profundo, delegando la conversión del ingrediente anidado
     * al `ingredienteMapper`.
     *
     * @param productoTamanioIngrediente La entidad `ProductoTamanioIngrediente` a convertir.
     * @return El `PersistenciaProductoTamanioIngredienteDTO` resultante, o `null` si la entidad de entrada es nula.
     */
    @Override
    public PersistenciaProductoTamanioIngredienteDTO toDTO(ProductoTamanioIngrediente productoTamanioIngrediente) {
        if (productoTamanioIngrediente == null) {
            return null;
        }
        PersistenciaProductoTamanioIngredienteDTO dto = new PersistenciaProductoTamanioIngredienteDTO();
        dto.setIngrediente(ingredienteMapper.toDTO(productoTamanioIngrediente.getIngrediente())); // Mapeo del ingrediente.
        dto.setCantidad(productoTamanioIngrediente.getCantidad());
        return dto;
    }

    /**
     * Convierte un `PersistenciaProductoTamanioIngredienteDTO` a su entidad `ProductoTamanioIngrediente`.
     * Este método es esencial para transformar los datos recibidos de la capa de negocio
     * a un formato que pueda ser persistido en la base de datos.
     * Realiza un mapeo profundo, delegando la conversión del ingrediente anidado
     * al `ingredienteMapper`.
     *
     * @param dto El `PersistenciaProductoTamanioIngredienteDTO` a convertir.
     * @return La entidad `ProductoTamanioIngrediente` resultante, o `null` si el DTO de entrada es nulo.
     */
    @Override
    public ProductoTamanioIngrediente toEntity(PersistenciaProductoTamanioIngredienteDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductoTamanioIngrediente entity = new ProductoTamanioIngrediente();
        entity.setIngrediente(ingredienteMapper.toEntity(dto.getIngrediente())); // Mapeo del ingrediente.
        entity.setCantidad(dto.getCantidad());
        return entity;
    }
}
