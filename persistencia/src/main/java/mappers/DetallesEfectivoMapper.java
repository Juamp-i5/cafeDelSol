/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaDetallesEfectivoDTO;
import entidades.DetallesEfectivo;
import interfacesMappers.IDetallesEfectivoMapper;

/**
 * * Implementación de la interfaz {@link IDetallesEfectivoMapper} para la
 * conversión entre entidades {@link DetallesEfectivo} y DTOs
 * {@link PersistenciaDetallesEfectivoDTO}.
 * <p>
 * Esta clase se encarga de mapear los datos entre la representación de la
 * entidad de persistencia y el objeto de transferencia de datos utilizado en
 * otras capas de la aplicación, específicamente para los detalles de pagos en
 * efectivo.
 * </p>
 *
 * @author Jp
 */
public class DetallesEfectivoMapper implements IDetallesEfectivoMapper {

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
    @Override
    public PersistenciaDetallesEfectivoDTO toDTO(DetallesEfectivo entidad) {
        if (entidad == null) {
            return null;
        }
        PersistenciaDetallesEfectivoDTO dto = new PersistenciaDetallesEfectivoDTO();
        dto.setMontoRecibido(entidad.getMontoRecibido());
        dto.setCambio(entidad.getCambio());
        return dto;
    }

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
    @Override
    public DetallesEfectivo toEntity(PersistenciaDetallesEfectivoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new DetallesEfectivo(
                dto.getMontoRecibido(),
                dto.getCambio()
        );
    }
}
