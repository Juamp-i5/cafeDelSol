/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs.cubiculos;

import entidades.Cubiculo;
import excepciones.PersistenciaCubiculoEsception;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface ICubiculoDAO {
    
    public List<Cubiculo> obtenerCubiculos() throws PersistenciaCubiculoEsception;
}
