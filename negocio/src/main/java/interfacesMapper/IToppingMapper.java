/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMapper;

import DTOs.PersistenciaToppingDTO;
import DTOs.ToppingMostrarDTO;

/**
 *
 * @author norma
 */
public interface IToppingMapper {

    ToppingMostrarDTO toToppingsMostrarDTO(PersistenciaToppingDTO topping);
}
