/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.SaborDTO;
import entidades.Sabor;
import excepciones.PersistenciaException;

/**
 *
 * @author Jp
 */
public interface ISaborMapper {

    public SaborDTO toDTO(Sabor sabor);

    public Sabor toEntity(SaborDTO saborDto) throws PersistenciaException;
}
