/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaDetallesEfectivoDTO;
import entidades.DetallesEfectivo;
import interfacesMappers.IDetallesEfectivoMapper;

/**
 *
 * @author Jp
 */
public class DetallesEfectivoMapper implements IDetallesEfectivoMapper {

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
