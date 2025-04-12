/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import entidades.Topping;
import exception.persistenciaException;
import interfaces.ITopping;
import java.util.List;

/**
 *
 * @author rodri
 */
public class ToppingDAOImp implements ITopping{

    @Override
    public List<Topping> buscarTodos() throws persistenciaException {
        return List.of(
                new Topping(1L, "Azúcar", "../img/azucar.jpeg"),
                new Topping(2L, "Canela", "../img/canela.jpg"),
                new Topping(3L, "Nutella", "../img/nutella.jpg"),
                new Topping(4L, "Cajeta", "../img/cajeta.jpg")
        ); 
    }
    
}
