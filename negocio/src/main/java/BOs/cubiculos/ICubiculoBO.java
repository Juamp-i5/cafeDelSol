/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.CubiculoCompletoDTO;
import DTOs.cubiculos.CubiculoCompletoDTOPersistencia;
import excepciones.NegocioCubiculoException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface ICubiculoBO {
    
    public List<String> obtenerCubiculos() throws NegocioCubiculoException;
    
    public CubiculoCompletoDTO obtenerPorNombre(String nombre) throws NegocioCubiculoException;
    
}
