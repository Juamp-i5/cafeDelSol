/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAOs.ToppingDAOImp;
import DTOs.ToppingsMostrarDTO;
import entidades.Topping;
import exception.NegocioException;
import exception.persistenciaException;
import interfaces.ITopping;
import interfacesBO.IToppingBO;
import interfacesMapper.IToppingMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.ToppingMapper;

/**
 *
 * @author rodri
 */
public class ToppingBO implements IToppingBO{

    ITopping tamanioDAO = ToppingDAOImp.getInstance();
    IToppingMapper productoMapper = ToppingMapper.getInstance();  
    
    private static ToppingBO instanceBO;

    public ToppingBO() {
    }

    public static ToppingBO getInstance() {
        if (instanceBO == null) {
            instanceBO = new ToppingBO();
        }
        return instanceBO;
    }
    
    @Override
    public List<ToppingsMostrarDTO> cargarProductos() throws NegocioException {
        try {

            List<Topping> toppings = tamanioDAO.buscarTodos();
            List<ToppingsMostrarDTO> toppingsDTO = new ArrayList<>();

            for (Topping topping : toppings) {
                ToppingsMostrarDTO toppingDTO = productoMapper.aDTO(topping);
                toppingsDTO.add(toppingDTO);
            }

            return toppingsDTO;
        } catch (persistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al cargar los productos");
        }
    }
    
}
