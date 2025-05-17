/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs.cubiculos;

import entidades.Reagenda;
import excepciones.PersistenciaCubiculoEsception;

/**
 *
 * @author rodri
 */
public interface IReagendaDAO {
    
    public Reagenda agregarReagenda(Reagenda reagenda) throws PersistenciaCubiculoEsception;
    
}
