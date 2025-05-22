/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaProductoDTO;
import entidades.Producto;

/**
 * Define el contrato para los mappers que convierten entre entidades
 * {@link Producto} y objetos de transferencia de datos
 * {@link PersistenciaProductoDTO}.
 * <p>
 * Esta interfaz permite desacoplar la lógica de negocio y presentación de los
 * detalles específicos de la entidad de persistencia, facilitando la conversión
 * bidireccional entre estas dos representaciones del producto.
 * </p>
 *
 * @author Jp
 */
public interface IProductoMapper {

    /**
     * Convierte una entidad {@link Producto} a su representación como
     * {@link PersistenciaProductoDTO}.
     * <p>
     * Este método es útil para transferir datos de la capa de dominio o
     * persistencia hacia capas superiores (ej. presentación o servicios) que
     * utilizan DTOs.
     * </p>
     *
     * @param entidad La entidad {@link Producto} que se desea convertir. Se
     * espera que no sea {@code null} si se desea una conversión válida, aunque
     * las implementaciones deben manejar el caso de entrada {@code null}.
     * @return Un objeto {@link PersistenciaProductoDTO} que representa los
     * datos de la entidad, o {@code null} si la entidad de entrada es
     * {@code null}.
     */
    public PersistenciaProductoDTO toDTO(Producto entidad);

    /**
     * Convierte un objeto {@link PersistenciaProductoDTO} a su representación
     * como entidad {@link Producto}.
     * <p>
     * Este método se utiliza típicamente cuando se reciben datos desde una capa
     * externa (ej. una solicitud web) y se necesitan transformar en una entidad
     * de dominio para su procesamiento o persistencia.
     * </p>
     *
     * @param dto El objeto {@link PersistenciaProductoDTO} que se desea
     * convertir. Se espera que no sea {@code null} si se desea una conversión
     * válida, aunque las implementaciones deben manejar el caso de entrada
     * {@code null}.
     * @return Una entidad {@link Producto} que representa los datos del DTO, o
     * {@code null} si el DTO de entrada es {@code null}.
     */
    public Producto toEntity(PersistenciaProductoDTO dto);

}
