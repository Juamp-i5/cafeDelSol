/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.IngredienteDTOPersistencia;
import entidades.Ingrediente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jp
 */
public interface IIngredienteMapper {

    public IngredienteDTOPersistencia toDTO(Ingrediente entidad);

    public Ingrediente toMongo(IngredienteDTOPersistencia dto) throws PersistenciaException;

    public List<IngredienteDTOPersistencia> toDTOList(List<Ingrediente> entidades);

    public List<Ingrediente> toMongoList(List<IngredienteDTOPersistencia> dtos) throws PersistenciaException;
}
