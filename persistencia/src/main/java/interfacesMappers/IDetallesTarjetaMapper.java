/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaDetallesTarjetaDTO;
import entidades.DetallesTarjeta;

/**
 * * Interfaz que define las operaciones de mapeo entre la entidad
 * {@link DetallesTarjeta} y su correspondiente DTO de persistencia
 * {@link PersistenciaDetallesTarjetaDTO}.
 * <p>
 * Esta interfaz establece el contrato para la conversión bidireccional de datos
 * relacionados con los detalles de pagos con tarjeta, facilitando la
 * transferencia de información entre la capa de persistencia y otras capas de
 * la aplicación.
 * </p>
 *
 * @author Jp
 */
public interface IDetallesTarjetaMapper {

    /**
     * Convierte un objeto {@link DetallesTarjeta} (entidad de persistencia) a
     * un {@link PersistenciaDetallesTarjetaDTO} (objeto de transferencia de
     * datos).
     *
     * @param entidad El objeto {@link DetallesTarjeta} a convertir. Puede ser
     * {@code null}.
     * @return Un {@link PersistenciaDetallesTarjetaDTO} con los datos
     * mapeados, o {@code null} si la entidad de entrada es {@code null}.
     */
    public PersistenciaDetallesTarjetaDTO toDTO(DetallesTarjeta entidad);

    /**
     * Convierte un objeto {@link PersistenciaDetallesTarjetaDTO} (objeto de
     * transferencia de datos) a un {@link DetallesTarjeta} (entidad de
     * persistencia).
     *
     * @param dto El objeto {@link PersistenciaDetallesTarjetaDTO} a convertir.
     * Puede ser {@code null}.
     * @return Un {@link DetallesTarjeta} con los datos mapeados, o
     * {@code null} si el DTO de entrada es {@code null}.
     */
    public DetallesTarjeta toEntity(PersistenciaDetallesTarjetaDTO dto);
}
