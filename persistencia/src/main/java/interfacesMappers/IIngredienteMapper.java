/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.IngredienteDTO;
import entidades.Ingrediente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jp
 */
public interface IIngredienteMapper {

    public IngredienteDTO toDTO(Ingrediente entidad);

    public Ingrediente toMongo(IngredienteDTO dto) throws PersistenciaException;

    public List<IngredienteDTO> toDTOList(List<Ingrediente> entidades);

    public List<Ingrediente> toMongoList(List<IngredienteDTO> dtos) throws PersistenciaException;
}
