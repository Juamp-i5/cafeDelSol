/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.salidas;

import BOs.ingredientes.IIngredienteBO;
import BOs.ingredientes.IngredienteBO;
import DTOs.CRUDSalidas.DetalleSalidaDTO;
import DTOs.CRUDSalidas.SalidaListDTO;
import DTOs.CRUDSalidas.SalidaNuevaDTO;
import DTOs.salidas.DetalleSalidaDTOPersistencia;
import DTOs.salidas.SalidaListDTOPersistencia;
import DTOs.salidas.SalidaNuevaDTOPersistencia;
import IDAOs.ingredientes.IIngredienteDAOMongo;
import IDAOs.salidas.ISalidaDAO;
import acceso.AccesoDatos;
import entidades.Salida;
import excepciones.NegocioException;
import excepciones.PersistenciaIngredientesException;
import excepciones.PersistenciaSalidasException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author katia
 */
public class SalidaBO implements ISalidaBO{
    
    private static SalidaBO instanciaBO;
    
    private final ISalidaDAO salidaDAO = AccesoDatos.getSalidaDAO();
    private final IIngredienteDAOMongo ingredienteDAO = AccesoDatos.getIngredienteDAO();
    private final ISalidaMapper mapper = SalidaMapper.getInstance();

    public SalidaBO() {
    }
    
    public static SalidaBO getInstance(){
        if (instanciaBO == null){
            instanciaBO = new SalidaBO();
        }
        return instanciaBO;
    }
    
    @Override
    public boolean registrarSalida(SalidaNuevaDTO salidaNueva) throws NegocioException {
        try {
            SalidaNuevaDTOPersistencia dtoPersistencia = mapper.toSalidaNuevaDTOPersistencia(salidaNueva);
            Salida salida = mapper.toEntity(dtoPersistencia);
            boolean registrada = salidaDAO.registrarSalida(salida);

            ingredienteDAO.reducirStock(salidaNueva.getIdIngrediente(), salidaNueva.getCantidad());

            return registrada;
        } catch (PersistenciaSalidasException | PersistenciaIngredientesException e) {
            throw new NegocioException("No se pudo registrar la salida.", e);
        }
    }

    @Override
    public List<SalidaListDTO> consultarTodas() throws NegocioException {
        try {
            List<Salida> salidas = salidaDAO.consultarTodas();
            List<SalidaListDTO> resultado = new ArrayList<>();

            for (Salida salida : salidas) {
                String nombreIngrediente = ingredienteDAO.obtenerDetallesIngrediente(salida.getIdIngrediente().toHexString()).getNombre();
                SalidaListDTOPersistencia dtoPersistencia = mapper.toSalidaListDTO(salida, nombreIngrediente);
                resultado.add(mapper.toSalidaListDTO(dtoPersistencia));
            }

            return resultado;
        } catch (Exception e) {
            throw new NegocioException("Error al consultar las salidas.", e);
        }
    }

    @Override
    public List<SalidaListDTO> consultarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {
        try {
            List<Salida> salidas = salidaDAO.consultarPorRangoFechas(fechaInicio, fechaFin);
            List<SalidaListDTO> resultado = new ArrayList<>();

            for (Salida salida : salidas) {
                String nombreIngrediente = ingredienteDAO.obtenerDetallesIngrediente(salida.getIdIngrediente().toHexString()).getNombre();
                SalidaListDTOPersistencia dtoPersistencia = mapper.toSalidaListDTO(salida, nombreIngrediente);
                resultado.add(mapper.toSalidaListDTO(dtoPersistencia));
            }

            return resultado;
        } catch (Exception e) {
            throw new NegocioException("Error al consultar las salidas por rango de fechas.", e);
        }
    }

    @Override
    public DetalleSalidaDTO consultarPorId(String id) throws NegocioException {
        try {
            Salida salida = salidaDAO.consultarPorId(new ObjectId(id));
            String nombreIngrediente = ingredienteDAO.obtenerDetallesIngrediente(salida.getIdIngrediente().toHexString()).getNombre();
            DetalleSalidaDTOPersistencia dtoPersistencia = mapper.toDetalleSalidaDTO(salida, nombreIngrediente);
            return mapper.toDetalleSalidaDTO(dtoPersistencia);
        } catch (Exception e) {
            throw new NegocioException("No se pudo obtener la salida con ID " + id, e);
        }
    }
}
