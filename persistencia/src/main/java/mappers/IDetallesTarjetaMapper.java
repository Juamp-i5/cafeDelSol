/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mappers;

import DTOs.PersistenciaDetallesTarjetaDTO;
import entidades.DetallesTarjeta;

/**
 *
 * @author Jp
 */
public interface IDetallesTarjetaMapper {

    public PersistenciaDetallesTarjetaDTO toDTO(DetallesTarjeta entidad);

    public DetallesTarjeta toEntity(PersistenciaDetallesTarjetaDTO dto);
}
