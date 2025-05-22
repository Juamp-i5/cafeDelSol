/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaSaborDTO;
import entidades.Sabor;

/**
 * * Interfaz que define las operaciones de mapeo entre la entidad {@link Sabor} y
 * su correspondiente DTO de persistencia {@link PersistenciaSaborDTO}.
 * <p>
 * Esta interfaz establece el contrato para la conversión bidireccional de datos
 * relacionados con los sabores, facilitando la transferencia de información
 * entre la capa de persistencia y otras capas de la aplicación.
 * </p>
 *
 * @author Jp
 */
public interface ISaborMapper {

    /**
     * Convierte una entidad {@link Sabor} a su representación como
     * {@link PersistenciaSaborDTO}.
     *
     * @param sabor La entidad {@link Sabor} a convertir.
     * @return Un objeto {@link PersistenciaSaborDTO} que representa los datos
     * de la entidad.
     */
    public PersistenciaSaborDTO toDTO(Sabor sabor);

    /**
     * Convierte un objeto {@link PersistenciaSaborDTO} a su representación como
     * entidad {@link Sabor}.
     *
     * @param saborDto El objeto {@link PersistenciaSaborDTO} a convertir.
     * @return Una entidad {@link Sabor} que representa los datos del DTO.
     */
    public Sabor toEntity(PersistenciaSaborDTO saborDto);
}
