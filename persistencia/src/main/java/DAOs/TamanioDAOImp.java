/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import entidades.Tamanio;
import exception.persistenciaException;
import interfaces.ITamanio;
import java.util.List;

/**
 *
 * @author rodri
 */
public class TamanioDAOImp implements ITamanio{

    @Override
    public List<Tamanio> buscarTodos() throws persistenciaException {
        return List.of(
                new Tamanio(1L, "Pequenio", "../img/tamanioPequenio.jpg", 0),
                new Tamanio(2L, "Mediano", "../img/tamanioMediano.jpg", 5),
                new Tamanio(3L, "Grande", "../img/tamanioGrande.jpg", 10)
        );
    }
    
}
