/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaPagoDTO;
import entidades.Pago;

/**
 *
 * @author Jp
 */
public interface IPagoMapper {

    public PersistenciaPagoDTO toDTO(Pago entidad);

    public Pago toEntity(PersistenciaPagoDTO dto);

}
