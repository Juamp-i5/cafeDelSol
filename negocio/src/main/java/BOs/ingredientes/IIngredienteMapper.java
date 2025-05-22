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
import java.util.List;

/**
 * Interfaz de la clase IngredienteMapper
 * @author norma
 */
public interface IIngredienteMapper {

    /**
     * Convierte un objeto de tipo IngredienteNuevoDTO en un objeto de tipo
     * IngredienteDTOPersistencia.
     *
     * @param dtoNuevo Objeto IngredienteNuevoDTO que se desea convertir.
     * @return Objeto convertido de tipo IngredienteDTOPersistencia.
     */
    public IngredienteDTOPersistencia toDTOPersistencia(IngredienteNuevoDTO dtoNuevo);

     /**
     * Convierte un objeto de tipo DetallesIngredienteViejoDTOPersistencia en un objeto de tipo
     * DetallesIngredienteViejoDTO.
     *
     * @param dtoPersistencia Objeto DetallesIngredienteViejoDTOPersistencia que se desea convertir.
     * @return Objeto convertido de tipo DetallesIngredienteViejoDTO.
     */
    public DetallesIngredienteViejoDTO toDTOIngredienteDetalles(DetallesIngredienteViejoDTOPersistencia dtoPersistencia);

     /**
     * Convierte una lista de objetos de tipo IngredienteDTOPersistencia en una lista de objetos de tipo
     * IngredienteViejoListDTO.
     *
     * @param listaPersistencia Objeto DetallesIngredienteViejoDTOPersistencia que se desea convertir.
     * @return Objeto convertido de tipo IngredienteViejoListDTO.
     */
    public List<IngredienteViejoListDTO> toDTOIngredienteList(List<IngredienteDTOPersistencia> listaPersistencia);

}
