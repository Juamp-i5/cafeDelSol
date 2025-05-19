/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.CubiculoCompletoDTO;
import DTOs.cubiculos.CubiculoCompletoDTOPersistencia;

/**
 *
 * @author rodri
 */
public interface ICubiculoMapper {
    
    public CubiculoCompletoDTO toDTOBO (CubiculoCompletoDTOPersistencia persistencia);
}
