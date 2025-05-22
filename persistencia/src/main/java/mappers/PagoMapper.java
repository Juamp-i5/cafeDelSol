/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import interfacesMappers.IDetallesTarjetaMapper;
import DTOs.PersistenciaPagoDTO;
import entidades.Pago;
import interfacesMappers.IDetallesEfectivoMapper;
import interfacesMappers.IPagoMapper;

/**
 * * Implementación de la interfaz {@link IPagoMapper} para la conversión entre
 * entidades {@link Pago} y DTOs {@link PersistenciaPagoDTO}.
 * <p>
 * Esta clase se encarga de mapear los datos entre la representación de la
 * entidad de persistencia y el objeto de transferencia de datos utilizado en
 * otras capas de la aplicación, específicamente para los pagos. Utiliza
 * mappers para sus sub-objetos como {@link entidades.DetallesEfectivo} y
 * {@link entidades.DetallesTarjeta}.
 * </p>
 *
 * @author Jp
 */
public class PagoMapper implements IPagoMapper {

    /**
     * Mapper para convertir entre entidades {@link entidades.DetallesEfectivo} y sus DTOs.
     */
    private IDetallesEfectivoMapper detallesEfectivoMapper;

    /**
     * Mapper para convertir entre entidades {@link entidades.DetallesTarjeta} y sus DTOs.
     */
    private IDetallesTarjetaMapper detallesTarjetaMapper;

    /**
     * Constructor que inyecta las dependencias de los mappers necesarios.
     *
     * @param detallesEfectivoMapper Mapper para {@link entidades.DetallesEfectivo}.
     * @param detallesTarjetaMapper Mapper para {@link entidades.DetallesTarjeta}.
     */
    public PagoMapper(IDetallesEfectivoMapper detallesEfectivoMapper, IDetallesTarjetaMapper detallesTarjetaMapper) {
        this.detallesEfectivoMapper = detallesEfectivoMapper;
        this.detallesTarjetaMapper = detallesTarjetaMapper;
    }

    /**
     * Convierte un objeto {@link Pago} (entidad de persistencia) a un
     * {@link PersistenciaPagoDTO} (objeto de transferencia de datos).
     * <p>
     * Este método mapea los campos de la entidad {@code Pago} a los campos
     * correspondientes del DTO {@code PersistenciaPagoDTO}. Incluye la
     * conversión de los detalles específicos del pago (efectivo o tarjeta)
     * utilizando los mappers correspondientes.
     * </p>
     *
     * @param entidad El objeto {@link Pago} a convertir. Puede ser {@code null}.
     * @return Un {@link PersistenciaPagoDTO} con los datos mapeados, o
     * {@code null} si la entidad de entrada es {@code null}.
     */
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

    /**
     * Convierte un objeto {@link PersistenciaPagoDTO} (objeto de transferencia
     * de datos) a un {@link Pago} (entidad de persistencia).
     * <p>
     * Este método mapea los campos del DTO {@code PersistenciaPagoDTO} a los
     * campos correspondientes de la entidad {@code Pago}. Incluye la
     * conversión de los detalles específicos del pago (efectivo o tarjeta)
     * utilizando los mappers correspondientes.
     * </p>
     *
     * @param dto El objeto {@link PersistenciaPagoDTO} a convertir. Puede ser
     * {@code null}.
     * @return Un {@link Pago} con los datos mapeados, o {@code null} si el
     * DTO de entrada es {@code null}.
     */
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
