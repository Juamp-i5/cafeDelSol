/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.entradas;

import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import IDAOs.entradas.IEntradaDAO;
import acceso.AccesoDatos;
import entidades.Entrada;
import excepciones.NegocioException;
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
    public boolean registrarEntrada(EntradaNuevaDTO entrada) throws PersistenciaEntradasException, NegocioException {
        try {
            return entradaDAO.registrarEntrada(entradaMapper.toEntityNuevo(entrada));
        } catch (PersistenciaEntradasException e) {
            throw new NegocioException("Error al registrar la entrada", e);
        }
    }
    
    @Override
    public List<EntradaViejaDTO> obtenerEntradasPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaEntradasException, NegocioException{
        try {
            return entradaMapper.todtoViejoList(entradaDAO.obtenerEntradasPorFechas(fechaInicio, fechaFin));
        } catch (PersistenciaEntradasException e) {
            throw new NegocioException("Error al obtener entradas desde la fecha: " + fechaInicio, e);
        }
    }
}
