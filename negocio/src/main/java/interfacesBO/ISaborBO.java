/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesBO;

import DTOs.SaboresMostrarDTO;
import exception.NegocioException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface ISaborBO {
    
    public List<SaboresMostrarDTO> cargarProductos() throws NegocioException;
    
}
