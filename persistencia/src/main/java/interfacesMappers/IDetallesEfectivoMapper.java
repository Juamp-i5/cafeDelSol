/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaDetallesEfectivoDTO;
import entidades.DetallesEfectivo;

/**
 * * Interfaz que define las operaciones de mapeo entre la entidad
 * {@link DetallesEfectivo} y su correspondiente DTO de persistencia
 * {@link PersistenciaDetallesEfectivoDTO}.
 * <p>
 * Esta interfaz establece el contrato para la conversión bidireccional de datos
 * relacionados con los detalles de pagos en efectivo, facilitando la
 * transferencia de información entre la capa de persistencia y otras capas de
 * la aplicación.
 * </p>
 *
 * @author Jp
 */
public interface IDetallesEfectivoMapper {

    /**
     * Convierte un objeto {@link DetallesEfectivo} (entidad de persistencia) a
     * un {@link PersistenciaDetallesEfectivoDTO} (objeto de transferencia de
     * datos).
     *
     * @param entidad El objeto {@link DetallesEfectivo} a convertir. Puede ser
     * {@code null}.
     * @return Un {@link PersistenciaDetallesEfectivoDTO} con los datos
     * mapeados, o {@code null} si la entidad de entrada es {@code null}.
     */
    public PersistenciaDetallesEfectivoDTO toDTO(DetallesEfectivo entidad);

    /**
     * Convierte un objeto {@link PersistenciaDetallesEfectivoDTO} (objeto de
     * transferencia de datos) a un {@link DetallesEfectivo} (entidad de
     * persistencia).
     *
     * @param dto El objeto {@link PersistenciaDetallesEfectivoDTO} a convertir.
     * Puede ser {@code null}.
     * @return Un {@link DetallesEfectivo} con los datos mapeados, o
     * {@code null} si el DTO de entrada es {@code null}.
     */
    public DetallesEfectivo toEntity(PersistenciaDetallesEfectivoDTO dto);
}
