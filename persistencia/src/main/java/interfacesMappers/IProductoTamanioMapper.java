/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaProductoTamanioDTO;
import entidades.ProductoTamanio;

/**
 *
 * @author Jp
 * Transforma los datos complejos de `ProductoTamanio` (que incluye `Tamanio`
 * y una lista de `ProductoTamanioIngrediente`) entre el formato utilizado por
 * la base de datos y el formato utilizado por la capa de negocio, asegurando
 * así el desacoplamiento y la modularidad de las capas de la aplicación.
 */
public interface IProductoTamanioMapper {

    /**
     * Convierte un objeto de la entidad `ProductoTamanio` (modelo de la base de datos)
     * a un `PersistenciaProductoTamanioDTO` (DTO para la capa de persistencia).
     * Este método es utilizado cuando se recuperan datos complejos de la base de datos
     * y se necesitan transformar a un formato más manejable y desacoplado para ser
     * pasados a la capa de negocio.
     *
     * @param productoTamanio El objeto `ProductoTamanio` (entidad) a convertir.
     * @return Un `PersistenciaProductoTamanioDTO` con los datos mapeados, o `null` si la entidad de entrada es `null`.
     */
    public PersistenciaProductoTamanioDTO toDTO(ProductoTamanio productoTamanio);

    /**
     * Convierte un `PersistenciaProductoTamanioDTO` (DTO de la capa de persistencia)
     * a un objeto de la entidad `ProductoTamanio` (modelo de la base de datos).
     * Este método se utiliza principalmente cuando la capa de negocio envía
     * datos de un producto-tamaño-ingrediente para ser guardados o actualizados
     * en la base de datos. Se encarga de cualquier transformación necesaria para que
     * el DTO sea compatible con la estructura compleja de la entidad de la base de datos.
     *
     * @param dto El `PersistenciaProductoTamanioDTO` a convertir.
     * @return Un objeto `ProductoTamanio` (entidad) con los datos mapeados, o `null` si el DTO de entrada es `null`.
     */
    public ProductoTamanio toEntity(PersistenciaProductoTamanioDTO dto);
}
