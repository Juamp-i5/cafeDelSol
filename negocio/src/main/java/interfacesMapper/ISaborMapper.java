/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMapper;

import DTOs.SaborDTO;
import DTOs.SaborMostrarDTO;

/**
 *
 * @author norma
 */
public interface ISaborMapper {

    public SaborMostrarDTO toSaboresMostrarDTO(SaborDTO sabor);
}
