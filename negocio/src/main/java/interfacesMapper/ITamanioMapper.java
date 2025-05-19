/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMapper;

import DTOs.PersistenciaTamanioDTO;
import DTOs.TamanioMostrarDTO;

/**
 *
 * @author norma
 */
public interface ITamanioMapper {

    TamanioMostrarDTO toTamanioMostrarDTO(PersistenciaTamanioDTO tamanioDTO);
}
