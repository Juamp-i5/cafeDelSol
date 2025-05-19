/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMapper;

import DTOs.PersistenciaSaborDTO;
import DTOs.SaborMostrarDTO;

/**
 *
 * @author norma
 */
public interface ISaborMapper {

    public SaborMostrarDTO toSaboresMostrarDTO(PersistenciaSaborDTO sabor);
}
