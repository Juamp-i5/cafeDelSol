/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.ingredientes;

import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import DTOs.ingredientes.ProveedorDTOPersistencia;
import IDAOs.ingredientes.IProveedorDAOMongo;
import acceso.AccesoDatos;
import excepciones.NegocioException;
import excepciones.PersistenciaIngredientesException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author norma
 */
public class ProveedorBO implements IProveedorBO {

    IProveedorDAOMongo proveedorDAO = AccesoDatos.getProveedorDAO();
    IProveedorMapper proveedorMapper = ProveedorMapper.getInstance();
    
    private static ProveedorBO instanceBO;

    public ProveedorBO() {
    }

    public static ProveedorBO getInstance() {
        if (instanceBO == null) {
            instanceBO = new ProveedorBO();
        }
        return instanceBO;
    }

    @Override
    public List<ProveedorViejoDTO> obtenerProveedores() throws NegocioException {
        try {
            List<ProveedorDTOPersistencia>  proveedores = proveedorDAO.obtenerProveedores();
            return proveedorMapper.toDTOList(proveedores);
        } catch (PersistenciaIngredientesException ex) {
            throw new NegocioException("Error al obtener los proveedores.");
        }
    }

}
