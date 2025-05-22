/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaPagoDTO;
import entidades.Pago;
import interfacesMappers.IDetallesEfectivoMapper;
import interfacesMappers.IPagoMapper;

/**
 *
 * @author Jp
 */
public class PagoMapper implements IPagoMapper {

    private IDetallesEfectivoMapper detallesEfectivoMapper;
    private IDetallesTarjetaMapper detallesTarjetaMapper;

    public PagoMapper(IDetallesEfectivoMapper detallesEfectivoMapper, IDetallesTarjetaMapper detallesTarjetaMapper) {
        this.detallesEfectivoMapper = detallesEfectivoMapper;
        this.detallesTarjetaMapper = detallesTarjetaMapper;
    }

    @Override
    public PersistenciaPagoDTO toDTO(Pago entidad) {
        if (entidad == null) {
            return null;
        }
        PersistenciaPagoDTO dto = new PersistenciaPagoDTO();
        dto.setMetodoPago(entidad.getMetodoPago());
        dto.setMoneda(entidad.getMoneda());
        dto.setDetallesEfectivo(detallesEfectivoMapper.toDTO(entidad.getDetallesEfectivo()));
        dto.setDetallesTarjeta(detallesTarjetaMapper.toDTO(entidad.getDetallesTarjeta()));
        return dto;
    }

    @Override
    public Pago toEntity(PersistenciaPagoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Pago(
                dto.getMetodoPago(),
                dto.getMoneda(),
                detallesEfectivoMapper.toEntity(dto.getDetallesEfectivo()),
                detallesTarjetaMapper.toEntity(dto.getDetallesTarjeta())
        );
    }
}
