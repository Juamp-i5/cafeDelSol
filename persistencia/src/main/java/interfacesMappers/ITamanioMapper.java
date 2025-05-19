/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaTamanioDTO;
import entidades.Tamanio;

/**
 *
 * @author Jp
 */
public interface ITamanioMapper {

    public PersistenciaTamanioDTO toDTO(Tamanio entidad);

    public Tamanio toEntity(PersistenciaTamanioDTO dto);

}
