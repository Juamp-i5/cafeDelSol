/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.SaborDTO;
import DTOs.SaborMostrarDTO;
import entidades.Sabor;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfacesBO.ISaborBO;
import interfacesMapper.ISaborMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.SaborMapper;
import IDAOs.ISaborDAO;
import acceso.AccesoDatos;

/**
 *
 * @author rodri
 */
public class SaborBO implements ISaborBO {

    ISaborDAO saborDAO = AccesoDatos.getSaborDAO();
    ISaborMapper saborMapper = new SaborMapper();

    private static SaborBO instanceBO;

    public SaborBO() {
    }

    public static SaborBO getInstance() {
        if (instanceBO == null) {
            instanceBO = new SaborBO();
        }
        return instanceBO;
    }

    @Override
    public List<SaborMostrarDTO> cargarSabores() throws NegocioException {
        try {

            List<SaborDTO> sabores = saborDAO.buscarTodos();
            List<SaborMostrarDTO> saboresDTO = new ArrayList<>();

            for (SaborDTO sabor : sabores) {
                SaborMostrarDTO saborDTO = saborMapper.toSaboresMostrarDTO(sabor);
                saboresDTO.add(saborDTO);
            }

            return saboresDTO;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al cargar los productos");
        }
    }

}
