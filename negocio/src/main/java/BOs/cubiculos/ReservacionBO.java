/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.cubiculos;

import BOs.ingredientes.IIngredienteMapper;
import BOs.ingredientes.IngredienteBO;
import BOs.ingredientes.IngredienteMapper;
import DTOs.cubiculos.ReservacionCompletaDTO;
import DTOs.cubiculos.ReservacionDTOCompletaPersistencia;
import DTOs.cubiculos.ReservacionNuevaDTO;
import IDAOs.cubiculos.ICubiculoDAO;
import IDAOs.cubiculos.IReservacionDAO;
import IDAOs.ingredientes.IIngredienteDAOMongo;
import acceso.AccesoDatos;
import enumCubiculos.Estado;
import excepciones.NegocioCubiculoException;
import excepciones.PersistenciaCubiculoEsception;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodri
 */
public class ReservacionBO implements IReservacionBO{

    IReservacionDAO reservacionDAO = AccesoDatos.getReservacionDAO();
    IReservacionMapper mapperReservacion = new ReservacionMapper();
    
    private static ReservacionBO instanceBO;

    public ReservacionBO() {
    }

    public static ReservacionBO getInstance() {
        if (instanceBO == null) {
            instanceBO = new ReservacionBO();
        }
        return instanceBO;
    }

    @Override
    public Integer agregarReservacion(ReservacionCompletaDTO reservacion) throws NegocioCubiculoException {
        ReservacionDTOCompletaPersistencia dtoPers = mapperReservacion.toDTOPersistencia(reservacion);
        dtoPers.setEstado(Estado.PENDIENTE);
        
        try {
            reservacionDAO.agregarReservacion(dtoPers);
            return dtoPers.getNumReservacion();
        } catch (PersistenciaCubiculoEsception ex) {
            Logger.getLogger(ReservacionBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioCubiculoException("Error al insertar una reservación nueva");
        }
        
    }

    @Override
    public ReservacionCompletaDTO buscarPorId(Integer id) throws NegocioCubiculoException {
        try {
            ReservacionDTOCompletaPersistencia dtoPers = reservacionDAO.buscarPorId(id);
            ReservacionCompletaDTO dto = mapperReservacion.toDTO(dtoPers);
            return dto;
        } catch (PersistenciaCubiculoEsception ex) {
            Logger.getLogger(ReservacionBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioCubiculoException("Error al buscar reservacion por id");
        }
    }

    @Override
    public boolean modificarReservacion(Integer numReservacion, Integer numReservacionNueva, String motivo, LocalDateTime fechaHora)  throws NegocioCubiculoException{
        try {
            reservacionDAO.modificarReservacion(numReservacion, numReservacionNueva, motivo, fechaHora);
            return true;
        } catch (PersistenciaCubiculoEsception ex) {
            Logger.getLogger(ReservacionBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioCubiculoException("Error al modificar la reservación original");
        }
    }
    
    
    
    

}
