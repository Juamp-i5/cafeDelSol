/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaTamanioDTO;
import entidades.Tamanio;

/**
 * * Interfaz que define las operaciones de mapeo entre la entidad {@link Tamanio} y
 * su correspondiente DTO de persistencia {@link PersistenciaTamanioDTO}.
 * <p>
 * Esta interfaz establece el contrato para la conversión bidireccional de datos
 * relacionados con los tamaños, facilitando la transferencia de información
 * entre la capa de persistencia y otras capas de la aplicación.
 * </p>
 *
 * @author Jp
 */
public interface ITamanioMapper {

    /**
     * Convierte un objeto {@link Tamanio} (entidad de persistencia) a un
     * {@link PersistenciaTamanioDTO} (objeto de transferencia de datos).
     *
     * @param entidad El objeto {@link Tamanio} a convertir. Puede ser
     * {@code null}.
     * @return Un {@link PersistenciaTamanioDTO} con los datos mapeados, o
     * {@code null} si la entidad de entrada es {@code null}.
     */
    public PersistenciaTamanioDTO toDTO(Tamanio entidad);

    /**
     * Convierte un objeto {@link PersistenciaTamanioDTO} (objeto de
     * transferencia de datos) a un {@link Tamanio} (entidad de persistencia).
     *
     * @param dto El objeto {@link PersistenciaTamanioDTO} a convertir. Puede
     * ser {@code null}.
     * @return Un {@link Tamanio} con los datos mapeados, o {@code null} si el
     * DTO de entrada es {@code null}.
     */
    public Tamanio toEntity(PersistenciaTamanioDTO dto);

}
