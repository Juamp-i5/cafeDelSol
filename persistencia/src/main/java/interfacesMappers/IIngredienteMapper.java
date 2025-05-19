/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.ingredientes.IngredienteDTOPersistencia;
import entidades.Ingrediente;

/**
 *
 * @author Jp
 */
public interface IIngredienteMapper {

    public IngredienteDTOPersistencia toDTO(Ingrediente entidad);

    public Ingrediente toEntity(IngredienteDTOPersistencia dto);

}
