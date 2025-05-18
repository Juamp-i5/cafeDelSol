package Gestion;

import BOs.entradas.EntradaBO;
import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import Excepcion.GestorCRUDEntradasException;
import excepciones.NegocioException;
import excepciones.NegocioExceptionNegocio;
import excepciones.PersistenciaEntradasException;
import interfacesBO.entradas.IEntradaBO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import validaciones.IValidadorGestorEntradas;
import validaciones.ValidadorGestorCRUDEntradas;

/**
 *
 * @author pablo
 */
public class GestorCRUDEntradas implements IGestorCRUDEntradas{
    private static GestorCRUDEntradas instance;
    private final IEntradaBO entradaBO = EntradaBO.getInstance();
    IValidadorGestorEntradas validador = new ValidadorGestorCRUDEntradas();

    public GestorCRUDEntradas() {
    }
    
    public static GestorCRUDEntradas getInstance() {
        if (instance == null) {
            instance = new GestorCRUDEntradas();
        }

        return instance;
    }

    @Override
    public boolean registrarEntrada(EntradaNuevaDTO entrada) throws GestorCRUDEntradasException{
        validador.validarEntrada(entrada);
        try {
            return entradaBO.registrarEntrada(entrada);
        } catch (PersistenciaEntradasException | NegocioException ex) {
            Logger.getLogger(GestorCRUDEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public List<EntradaViejaDTO> obtenerEntradasPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws GestorCRUDEntradasException{
        try {
            return entradaBO.obtenerEntradasPorFechas(fechaInicio, fechaFin);
        } catch (PersistenciaEntradasException | NegocioException ex) {
            Logger.getLogger(GestorCRUDEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
