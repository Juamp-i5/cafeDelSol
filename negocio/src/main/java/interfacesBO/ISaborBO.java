/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesBO;

import DTOs.SaborMostrarDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface ISaborBO {

    public List<SaborMostrarDTO> cargarSabores() throws NegocioException;

}
