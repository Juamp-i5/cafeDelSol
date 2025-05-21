/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionSalidas;

import BOs.salidas.ISalidaBO;
import BOs.salidas.SalidaBO;
import DTOs.CRUDSalidas.DetalleSalidaDTO;
import DTOs.CRUDSalidas.SalidaListDTO;
import DTOs.CRUDSalidas.SalidaNuevaDTO;
import excepciones.GestionCRUDSalidasException;
import excepciones.NegocioException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import validadores.IValidadorGestorCRUDSalidas;
import validadores.ValidadorGestorCRUDSalidas;

/**
 *
 * @author katia
 */
public class GestorCRUDSalidas implements IGestorCRUDSalidas{
    private static GestorCRUDSalidas instance;
    private final ISalidaBO salidaBO = SalidaBO.getInstance();
    private final IValidadorGestorCRUDSalidas validador = new ValidadorGestorCRUDSalidas();

    public GestorCRUDSalidas() {
    }
    
    public static GestorCRUDSalidas getInstance() {
        if (instance == null) {
            instance = new GestorCRUDSalidas();
        }
        return instance;
    }
    
    @Override
    public boolean registrarSalida(SalidaNuevaDTO salidaNueva) throws GestionCRUDSalidasException {
        validador.validarSalida(salidaNueva);
        try {
            return salidaBO.registrarSalida(salidaNueva);
        } catch (NegocioException e) {
            Logger.getLogger(GestorCRUDSalidas.class.getName()).log(Level.SEVERE, null, e);
            throw new GestionCRUDSalidasException("Error al registrar la salida", e);
        }
    }

    @Override
    public List<SalidaListDTO> consultarTodas() throws GestionCRUDSalidasException {
        try {
            return salidaBO.consultarTodas();
        } catch (NegocioException e) {
            throw new GestionCRUDSalidasException("Error al consultar todas las salidas", e);
        }
    }

    @Override
    public List<SalidaListDTO> consultarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws GestionCRUDSalidasException {
        try {
            return salidaBO.consultarPorRangoFechas(fechaInicio, fechaFin);
        } catch (NegocioException e) {
            throw new GestionCRUDSalidasException("Error al consultar salidas por rango", e);
        }
    }

    @Override
    public DetalleSalidaDTO consultarPorId(String id) throws GestionCRUDSalidasException {
        try {
            return salidaBO.consultarPorId(id);
        } catch (NegocioException e) {
            throw new GestionCRUDSalidasException("Error al consultar salida por ID", e);
        }
    }
}
