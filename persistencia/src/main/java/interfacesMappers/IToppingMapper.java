/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.ToppingDTO;
import entidades.Topping;
import excepciones.PersistenciaException;

/**
 *
 * @author Jp
 */
public interface IToppingMapper {

    public ToppingDTO toDTO(Topping topping);

    public Topping toEntity(ToppingDTO toppingDto) throws PersistenciaException;
}
