/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesBO;

import DTOs.ToppingMostrarDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface IToppingBO {

    public List<ToppingMostrarDTO> cargarProductos() throws NegocioException;

}
