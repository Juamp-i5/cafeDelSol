/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaProductoTamanioIngredienteDTO;
import entidades.ProductoTamanioIngrediente;

/**
 *
 * @author Jp
 * Su propósito es establecer las operaciones de mapeo esenciales para transformar
 * los datos de la relación entre un producto, su tamaño y un ingrediente específico.
 * Esto es crucial para asegurar el **desacoplamiento** y la **modularidad**
 * entre la representación de los datos en la base de datos y la que se utiliza
 * en la capa de negocio.
 */
public interface IProductoTamanioIngredienteMapper {

    /**
     * Convierte un objeto de la entidad `ProductoTamanioIngrediente` (modelo de la base de datos)
     * a un `PersistenciaProductoTamanioIngredienteDTO` (DTO para la capa de persistencia).
     * Este método es utilizado cuando se recuperan datos de la base de datos y se necesitan
     * transformar a un formato más genérico y manejable para ser pasados a la capa de negocio.
     *
     * @param productoTamanioIngrediente El objeto `ProductoTamanioIngrediente` (entidad) a convertir.
     * @return Un `PersistenciaProductoTamanioIngredienteDTO` con los datos mapeados, o `null` si la entidad de entrada es `null`.
     */
    public PersistenciaProductoTamanioIngredienteDTO toDTO(ProductoTamanioIngrediente productoTamanioIngrediente);

    /**
     * Convierte un `PersistenciaProductoTamanioIngredienteDTO` (DTO de la capa de persistencia)
     * a un objeto de la entidad `ProductoTamanioIngrediente` (modelo de la base de datos).
     * Este método se utiliza principalmente cuando la capa de negocio envía datos
     * de un ingrediente asociado a un producto y tamaño para ser guardados o actualizados
     * en la base de datos. Se encarga de cualquier transformación necesaria para que
     * el DTO sea compatible con la estructura de la entidad de la base de datos.
     *
     * @param dto El `PersistenciaProductoTamanioIngredienteDTO` a convertir.
     * @return Un objeto `ProductoTamanioIngrediente` (entidad) con los datos mapeados, o `null` si el DTO de entrada es `null`.
     */
    public ProductoTamanioIngrediente toEntity(PersistenciaProductoTamanioIngredienteDTO dto);
}
