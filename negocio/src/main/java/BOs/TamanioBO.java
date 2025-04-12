/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAOs.TamanioDAOImp;
import DTOs.TamanioMostrarDTO;
import entidades.Tamanio;
import exception.NegocioException;
import exception.persistenciaException;
import interfaces.ITamanio;
import interfacesBO.ITamanioBO;
import interfacesMapper.ITamanioMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.TamanioMapper;

/**
 *
 * @author rodri
 */
public class TamanioBO implements ITamanioBO{

    ITamanio tamanioDAO = TamanioDAOImp.getInstance();
    ITamanioMapper productoMapper = TamanioMapper.getInstance();

    private static TamanioBO instanceBO;

    public TamanioBO() {
    }

    public static TamanioBO getInstance() {
        if (instanceBO == null) {
            instanceBO = new TamanioBO();
        }
        return instanceBO;
    }
    
    @Override
    public List<TamanioMostrarDTO> cargarProductos() throws NegocioException {
        try {

            List<Tamanio> tamanios = tamanioDAO.buscarTodos();
            List<TamanioMostrarDTO> tamaniosDTO = new ArrayList<>();

            for (Tamanio tamanio : tamanios) {
                TamanioMostrarDTO tamanioDTO = productoMapper.aDTO(tamanio);
                tamaniosDTO.add(tamanioDTO);
            }

            return tamaniosDTO;
        } catch (persistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al cargar los productos");
        }

    }

}
