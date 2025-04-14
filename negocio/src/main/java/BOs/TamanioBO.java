/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAOs.TamanioDAOMongo;
import DTOs.TamanioMostrarDTO;
import entidades.Tamanio;
import exception.NegocioException;
import excepciones.PersistenciaException;
import interfacesBO.ITamanioBO;
import interfacesMapper.ITamanioMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.TamanioMapper;
import IDAOs.ITamanioDAO;

/**
 *
 * @author rodri
 */
public class TamanioBO implements ITamanioBO{

    ITamanioDAO tamanioDAO = TamanioDAOMongo.getInstance();
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
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al cargar los productos");
        }

    }

}
