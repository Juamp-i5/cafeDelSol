/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.cubiculos;

import entidades.Cubiculo;

/**
 *
 * @author rodri
 */
public class CubiculoMapperPersistencia implements ICubiculoMapperPersistencia{
    
    public CubiculoMapperPersistencia() {
    }

    /**
     * Convierte una entidad Cubículo a CubiculoCompletoDTOPersistencia
     * @param entidad cubículo a transformar
     * @return Entidad transformada a DTO
     */
    @Override
    public CubiculoCompletoDTOPersistencia toDTO(Cubiculo entidad) {
        if (entidad == null) {
            return null;
        }
        CubiculoCompletoDTOPersistencia dto = new CubiculoCompletoDTOPersistencia();
        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toHexString());
        }
        
        dto.setNombre(entidad.getNombre());
        dto.setPrecioHora(entidad.getPrecioHora());
        return dto;
    }
}
