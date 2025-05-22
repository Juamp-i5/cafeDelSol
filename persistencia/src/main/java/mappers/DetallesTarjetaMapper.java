/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaDetallesTarjetaDTO;
import entidades.DetallesTarjeta;

/**
 *
 * @author Jp
 */
public class DetallesTarjetaMapper implements IDetallesTarjetaMapper {

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
