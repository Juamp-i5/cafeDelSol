/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaPagoDTO;
import entidades.Pago;

/**
 * * Interfaz que define las operaciones de mapeo entre la entidad {@link Pago} y
 * su correspondiente DTO de persistencia {@link PersistenciaPagoDTO}.
 * <p>
 * Esta interfaz establece el contrato para la conversión bidireccional de datos
 * relacionados con los pagos, facilitando la transferencia de información entre
 * la capa de persistencia y otras capas de la aplicación. Incluye métodos para
 * convertir entre la entidad {@code Pago} y su DTO, manejando los detalles
 * específicos del tipo de pago (efectivo o tarjeta).
 * </p>
 *
 * @author Jp
 */
public interface IPagoMapper {

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
    public PersistenciaPagoDTO toDTO(Pago entidad);

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
    public Pago toEntity(PersistenciaPagoDTO dto);

}
