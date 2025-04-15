/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.SaboresMostrarDTO;
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

    ISaborDAO tamanioDAO = AccesoDatos.getSaborDAO();
    ISaborMapper productoMapper = SaborMapper.getInstance();

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
    public List<SaboresMostrarDTO> cargarProductos() throws NegocioException {
        try {

            List<Sabor> sabores = tamanioDAO.buscarTodos();
            List<SaboresMostrarDTO> saboresDTO = new ArrayList<>();

            for (Sabor sabor : sabores) {
                SaboresMostrarDTO saborDTO = productoMapper.aDTO(sabor);
                saboresDTO.add(saborDTO);
            }

            return saboresDTO;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al cargar los productos");
        }
    }

}
