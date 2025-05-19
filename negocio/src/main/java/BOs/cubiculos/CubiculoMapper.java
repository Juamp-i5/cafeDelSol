/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.CubiculoCompletoDTO;
import DTOs.cubiculos.CubiculoCompletoDTOPersistencia;

/**
 *
 * @author rodri
 */
public class CubiculoMapper implements ICubiculoMapper{
    
    private static CubiculoMapper instanceMapper;

    public CubiculoMapper() {
    }

    public static CubiculoMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new CubiculoMapper();
        }
        return instanceMapper;
    }

    @Override
    public CubiculoCompletoDTO toDTOBO(CubiculoCompletoDTOPersistencia persistencia) {
        if (persistencia == null) {
            return null;
        }
        
        CubiculoCompletoDTO dto = new CubiculoCompletoDTO();
        dto.setId(persistencia.getId());
        dto.setNombre(persistencia.getNombre());
        dto.setPrecioHora(persistencia.getPrecioHora());
        return dto;
    }
    
}
