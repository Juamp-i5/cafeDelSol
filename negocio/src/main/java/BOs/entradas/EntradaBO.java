/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.entradas;

import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import IDAOs.entradas.IEntradaDAO;
import acceso.AccesoDatos;
import excepciones.NegocioExceptionNegocio;
import excepciones.PersistenciaEntradasException;
import interfacesBO.entradas.IEntradaBO;
import interfacesMapper.IEntradaMapper;
import java.time.LocalDateTime;
import java.util.List;
import mapper.EntradaMapper;

/**
 *
 * @author pablo
 */
public class EntradaBO implements IEntradaBO {

    private static EntradaBO instanciaBO;
    IEntradaMapper entradaMapper = EntradaMapper.getInstance();
    IEntradaDAO entradaDAO = AccesoDatos.getEntradaDAO();

    public static EntradaBO getInstance() {
        if (instanciaBO == null) {
            instanciaBO = new EntradaBO();
        }
        return instanciaBO;
    }

    @Override
    public boolean registrarEntrada(EntradaNuevaDTO entrada) throws PersistenciaEntradasException, NegocioExceptionNegocio {
        try {
            return entradaDAO.registrarEntrada(entradaMapper.toEntityNuevo(entrada));
        } catch (PersistenciaEntradasException e) {
            throw new NegocioExceptionNegocio("Error al registrar la entrada", e);
        }
    }

    @Override
    public List<EntradaViejaDTO> obtenerListaPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaEntradasException, NegocioExceptionNegocio {
        try {
            return entradaMapper.todtoViejoList(entradaDAO.obtenerListaEntradaPorRangoFecha(fechaInicio, fechaFin));
        } catch (PersistenciaEntradasException e) {
            throw new NegocioExceptionNegocio("Error al obtener la lista de entradas", e);
        }
    }

    @Override
    public List<EntradaViejaDTO> obtenerTodasLasEntradas() throws PersistenciaEntradasException, NegocioExceptionNegocio {
        try {
            return entradaMapper.todtoViejoList(entradaDAO.obtenerTodasLasEntradas());
        } catch (PersistenciaEntradasException e) {
            throw new NegocioExceptionNegocio("Error al obtener todas las entradas de la base de datos", e);
        }
    }

    @Override
    public List<EntradaViejaDTO> obtenerEntradasHastaFecha(LocalDateTime fechaFin) throws PersistenciaEntradasException, NegocioExceptionNegocio {
        try {
            return entradaMapper.todtoViejoList(entradaDAO.obtenerEntradasHastaFecha(fechaFin));
        } catch (PersistenciaEntradasException e) {
            throw new NegocioExceptionNegocio("Error al obtener entradas hasta la fecha: " + fechaFin, e);
        }
    }

    @Override
    public List<EntradaViejaDTO> obtenerEntradasDesdeFecha(LocalDateTime fechaInicio) throws PersistenciaEntradasException, NegocioExceptionNegocio {
        try {
            return entradaMapper.todtoViejoList(entradaDAO.obtenerEntradasDesdeFecha(fechaInicio));
        } catch (PersistenciaEntradasException e) {
            throw new NegocioExceptionNegocio("Error al obtener entradas desde la fecha: " + fechaInicio, e);
        }
    }
}
