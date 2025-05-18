/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.ingredientes;

import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import DTOs.ingredientes.ProveedorDTOPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author norma
 */
public class ProveedorMapper implements IProveedorMapper {

    private static ProveedorMapper instanceMapper;

    public ProveedorMapper() {
    }

    public static ProveedorMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new ProveedorMapper();
        }
        return instanceMapper;
    }

    @Override
    public List<ProveedorViejoDTO> toDTOList(List<ProveedorDTOPersistencia> listaPersistencia) {
        if (listaPersistencia == null) {
            return null;
        }

        List<ProveedorViejoDTO> listaViejoDTO = new ArrayList<>();
        for (ProveedorDTOPersistencia persistencia : listaPersistencia) {
            ProveedorViejoDTO viejoDTO = new ProveedorViejoDTO();
            viejoDTO.setId(persistencia.getId());
            viejoDTO.setNombre(persistencia.getNombre());
            listaViejoDTO.add(viejoDTO);
        }

        return listaViejoDTO;
    }

}
