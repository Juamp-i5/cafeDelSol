/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaToppingDTO;
import entidades.Topping;

/**
 *
 * @author Jp
 */
public interface IToppingMapper {

    public PersistenciaToppingDTO toDTO(Topping topping);

    public Topping toEntity(PersistenciaToppingDTO toppingDto);
}
