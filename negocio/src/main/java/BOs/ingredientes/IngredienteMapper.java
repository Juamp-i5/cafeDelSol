/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.ingredientes;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDIngredientes.NivelStock;
import DTOs.CRUDIngredientes.UnidadMedida;
import DTOs.ingredientes.DetallesIngredienteViejoDTOPersistencia;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import entidades.Ingrediente;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author norma
 */
public class IngredienteMapper implements IIngredienteMapper {

    private static IngredienteMapper instanceMapper;

    public IngredienteMapper() {
    }

    public static IngredienteMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new IngredienteMapper();
        }
        return instanceMapper;
    }

    @Override
    public IngredienteDTOPersistencia toDTOPersistencia(IngredienteNuevoDTO dtoNuevo) {
        if (dtoNuevo == null) {
            return null;
        }

        IngredienteDTOPersistencia dtoPersistencia = new IngredienteDTOPersistencia();

        dtoPersistencia.setNombre(dtoNuevo.getNombre());
        dtoPersistencia.setCantidadDisponible(dtoNuevo.getCantidadDisponible());
        dtoPersistencia.setCantidadMinima(dtoNuevo.getCantidadMinima());

        dtoPersistencia.setUnidadMedida(dtoNuevo.getUnidadMedida() != null ? dtoNuevo.getUnidadMedida().name() : null);
        dtoPersistencia.setNivelStock(dtoNuevo.getNivelStock() != null ? dtoNuevo.getNivelStock().name() : null);

        dtoPersistencia.setIdProveedor(dtoNuevo.getIdProveedor());

        return dtoPersistencia;
    }

    @Override
    public DetallesIngredienteViejoDTO toDTOIngredienteDetalles(DetallesIngredienteViejoDTOPersistencia dtoPersistencia) {
        if (dtoPersistencia == null) {
            return null;
        }

        DetallesIngredienteViejoDTO dto = new DetallesIngredienteViejoDTO();

        dto.setId(dtoPersistencia.getId());
        dto.setNombre(dtoPersistencia.getNombre());
        dto.setCantidadDisponible(dtoPersistencia.getCantidadDisponible());
        dto.setCantidadMinima(dtoPersistencia.getCantidadMinima());

        dto.setUnidadMedida(dtoPersistencia.getUnidadMedida() != null
                ? UnidadMedida.valueOf(dtoPersistencia.getUnidadMedida())
                : null);

        dto.setNivelStock(dtoPersistencia.getNivelStock() != null
                ? NivelStock.valueOf(dtoPersistencia.getNivelStock())
                : null);

        dto.setNombreProveedor(dtoPersistencia.getNombreProveedor());

        return dto;
    }

    @Override
    public List<IngredienteViejoListDTO> toDTOIngredienteList(List<IngredienteDTOPersistencia> listaPersistencia) {
        if (listaPersistencia == null) {
            return null;
        }

        List<IngredienteViejoListDTO> listaDTO = new ArrayList<>();
        for (IngredienteDTOPersistencia dtoPersistencia : listaPersistencia) {
            IngredienteViejoListDTO dto = new IngredienteViejoListDTO();

            dto.setId(dtoPersistencia.getId());
            dto.setNombre(dtoPersistencia.getNombre());
            dto.setCantidadDisponible(dtoPersistencia.getCantidadDisponible());

            dto.setUnidadMedida(dtoPersistencia.getUnidadMedida() != null
                    ? UnidadMedida.valueOf(dtoPersistencia.getUnidadMedida())
                    : null);

            dto.setNivelStock(dtoPersistencia.getNivelStock() != null
                    ? NivelStock.valueOf(dtoPersistencia.getNivelStock())
                    : null);

            listaDTO.add(dto);
        }

        return listaDTO;
    }

}
