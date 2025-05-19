/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.cubiculos;

import IDAOs.cubiculos.IContadorReservaciones;
import acceso.AccesoDatos;
import excepciones.NegocioCubiculoException;
import excepciones.PersistenciaCubiculoEsception;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodri
 */
public class ContadorBO implements IContadorBO{
    
    IContadorReservaciones contadorDAO = AccesoDatos.getContador();
    
    private static ContadorBO instanceBO;

    public ContadorBO() {
    }

    public static ContadorBO getInstance() {
        if (instanceBO == null) {
            instanceBO = new ContadorBO();
        }
        return instanceBO;
    }

    @Override
    public Integer obtenerContador() throws NegocioCubiculoException {
        try {
            return contadorDAO.encontrarActualizar();
        } catch (PersistenciaCubiculoEsception ex) {
            Logger.getLogger(ContadorBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioCubiculoException("Error al recuperar el contador");
        }
    }
    
    
    
}
