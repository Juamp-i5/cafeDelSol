/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaDetallesEfectivoDTO;
import entidades.DetallesEfectivo;

/**
 *
 * @author Jp
 */
public interface IDetallesEfectivoMapper {

    public PersistenciaDetallesEfectivoDTO toDTO(DetallesEfectivo entidad);

    public DetallesEfectivo toEntity(PersistenciaDetallesEfectivoDTO dto);
}
