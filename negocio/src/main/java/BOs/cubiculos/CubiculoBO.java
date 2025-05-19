/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.CubiculoCompletoDTO;
import DTOs.cubiculos.CubiculoCompletoDTOPersistencia;
import IDAOs.cubiculos.ICubiculoDAO;
import acceso.AccesoDatos;
import excepciones.NegocioCubiculoException;
import excepciones.PersistenciaCubiculoEsception;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodri
 */
public class CubiculoBO implements ICubiculoBO {

    ICubiculoDAO cubiculoDAO = AccesoDatos.getCubiculoDAO();
    ICubiculoMapper cubiculoMapper = new CubiculoMapper();

    private static CubiculoBO instanceBO;

    public CubiculoBO() {
    }

    public static CubiculoBO getInstance() {
        if (instanceBO == null) {
            instanceBO = new CubiculoBO();
        }
        return instanceBO;
    }

    @Override
    public List<String> obtenerCubiculos() throws NegocioCubiculoException {

        List<String> lista;
        try {
            lista = cubiculoDAO.obtenerCubiculos();
            return lista;
        } catch (PersistenciaCubiculoEsception ex) {
            Logger.getLogger(CubiculoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioCubiculoException("Error al cargar los cubículos");
        }
    }

    @Override
    public CubiculoCompletoDTO obtenerPorNombre(String nombre) throws NegocioCubiculoException {
        try {
            CubiculoCompletoDTOPersistencia dtoPersistencia = cubiculoDAO.obtenerPorNombre(nombre);
            return cubiculoMapper.toDTOBO(dtoPersistencia);
            
        } catch (PersistenciaCubiculoEsception ex) {
            Logger.getLogger(CubiculoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioCubiculoException("Error al obtener cubiculo por nombre");
        }
    }
    
    
    
    
    

}
