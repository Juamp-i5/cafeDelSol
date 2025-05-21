/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.cubiculos;

import DTOs.cubiculos.ReservacionCompletaDTO;
import DTOs.cubiculos.ReservacionDTOCompletaPersistencia;
import DTOs.cubiculos.ReservacionDTOMostrar;
import DTOs.cubiculos.ReservacionDetalleDTO;
import DTOs.cubiculos.ReservacionDetalleDTOPersistencia;
import IDAOs.cubiculos.IReservacionDAO;
import acceso.AccesoDatos;
import enumCubiculos.Estado;
import excepciones.NegocioCubiculoException;
import excepciones.PersistenciaCubiculoEsception;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodri
 */
public class ReservacionBO implements IReservacionBO {

    IReservacionDAO reservacionDAO = AccesoDatos.getReservacionDAO();
    IReservacionMapper mapperReservacion = new ReservacionMapper();
    IReservacionMostrarMapper mapperMostrar = new ReservacionMostrarMapper();
    IReservacionDetalleMapper detalleMapper = new ReservacionDetalleMapper();

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
    public boolean modificarReservacion(Integer numReservacion, Integer numReservacionNueva, String motivo, LocalDateTime fechaHora) throws NegocioCubiculoException {
        try {
            reservacionDAO.modificarReservacion(numReservacion, numReservacionNueva, motivo, fechaHora);
            return true;
        } catch (PersistenciaCubiculoEsception ex) {
            Logger.getLogger(ReservacionBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioCubiculoException("Error al modificar la reservación original");
        }
    }

    @Override
    public List<ReservacionDTOMostrar> obtenerReservacionesPendientes(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioCubiculoException {
        try {
            List<ReservacionDTOCompletaPersistencia> listaPers = reservacionDAO.buscarPendientesPorRangoFechas(fechaInicio, fechaFin);
            List<ReservacionDTOMostrar> listaDTO = new ArrayList<>();

            for (ReservacionDTOCompletaPersistencia pers : listaPers) {
                ReservacionDTOMostrar dto = mapperMostrar.toDTO(pers);
                listaDTO.add(dto);
            }
            return listaDTO;

        } catch (PersistenciaCubiculoEsception ex) {
            Logger.getLogger(ReservacionBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioCubiculoException("Error al obtener reservaciones inconclusas por rango de fechas");
        }
    }

    @Override
    public List<ReservacionDTOMostrar> obtenerReservacionesHistorial(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioCubiculoException {
        try {
            List<ReservacionDTOCompletaPersistencia> listaPers = reservacionDAO.buscarPorRangoFechas(fechaInicio, fechaFin);
            List<ReservacionDTOMostrar> listaDTO = new ArrayList<>();

            for (ReservacionDTOCompletaPersistencia pers : listaPers) {
                ReservacionDTOMostrar dto = mapperMostrar.toDTO(pers);
                listaDTO.add(dto);
            }
            return listaDTO;

        } catch (PersistenciaCubiculoEsception ex) {
            Logger.getLogger(ReservacionBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioCubiculoException("Error al obtener reservaciones inconclusas por rango de fechas");
        }
    }

    @Override
    public Integer actualizarEstado(Integer numReservacion, String estado) throws NegocioCubiculoException {
        try {
            boolean resultado = false;
            
            if (estado == "ACTIVA") {
                resultado = reservacionDAO.actualizarEstadoReservacion(numReservacion, Estado.ACTIVA);
            } else if(estado == "CONCLUIDA"){
                resultado = reservacionDAO.actualizarEstadoReservacion(numReservacion, Estado.CONCLUIDA);
            }
            if(resultado){
                return numReservacion;
            } else{
                throw new NegocioCubiculoException("Error al modificar el estado de la reservacion");
            }
        } catch (Exception ex) {
            Logger.getLogger(ReservacionBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioCubiculoException("Error al modificar el estado de la reservacion");
        }
    }

    @Override
    public ReservacionDetalleDTO getDetalleReservacion(Integer numReservacion) throws NegocioCubiculoException {
        try {
            ReservacionDetalleDTOPersistencia dtoPers = reservacionDAO.getDetalleReservacion(numReservacion);
            ReservacionDetalleDTO dto = detalleMapper.toDTOBO(dtoPers);
            return dto;
        } catch (PersistenciaCubiculoEsception ex) {
            Logger.getLogger(ReservacionBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioCubiculoException("Error al obtener detalle de la reservacion");
        }
    }
}
