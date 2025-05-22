/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import interfacesMappers.IDetallesTarjetaMapper;
import DTOs.PersistenciaDetallesTarjetaDTO;
import entidades.DetallesTarjeta;

/**
 * * Implementación de la interfaz {@link IDetallesTarjetaMapper} para la
 * conversión entre entidades {@link DetallesTarjeta} y DTOs
 * {@link PersistenciaDetallesTarjetaDTO}.
 * <p>
 * Esta clase se encarga de mapear los datos relacionados con los detalles de
 * pagos con tarjeta, facilitando la transferencia de información entre la capa
 * de persistencia y otras capas de la aplicación.
 * </p>
 *
 * @author Jp
 */
public class DetallesTarjetaMapper implements IDetallesTarjetaMapper {

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
    @Override
    public PersistenciaDetallesTarjetaDTO toDTO(DetallesTarjeta entidad) {
        if (entidad == null) {
            return null;
        }
        PersistenciaDetallesTarjetaDTO dto = new PersistenciaDetallesTarjetaDTO();
        dto.setTipoTarjeta(entidad.getTipoTarjeta());
        dto.setUltimosDigitos(entidad.getUltimosDigitos());
        dto.setMarca(entidad.getMarca());
        dto.setBanco(entidad.getBanco());
        dto.setTitular(entidad.getTitular());
        dto.setNoAutorizacion(entidad.getNoAutorizacion());
        return dto;
    }

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
    @Override
    public DetallesTarjeta toEntity(PersistenciaDetallesTarjetaDTO dto) {
        if (dto == null) {
            return null;
        }
        return new DetallesTarjeta(
                dto.getTipoTarjeta(),
                dto.getUltimosDigitos(),
                dto.getMarca(),
                dto.getBanco(),
                dto.getTitular(),
                dto.getNoAutorizacion()
        );
    }
}
