/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs.ingredientes;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.ingredientes.DetallesIngredienteViejoDTOPersistencia;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import entidades.Ingrediente;
import java.util.List;

/**
 *
 * @author norma
 */
public interface IIngredienteMapper {

    public IngredienteDTOPersistencia toDTOPersistencia(IngredienteNuevoDTO dtoNuevo);

    public DetallesIngredienteViejoDTO toDTOIngredienteDetalles(DetallesIngredienteViejoDTOPersistencia dtoPersistencia);

    public List<IngredienteViejoListDTO> toDTOIngredienteList(List<IngredienteDTOPersistencia> listaPersistencia);

}
