/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionSalidas;

import DTOs.CRUDSalidas.DetalleSalidaDTO;
import DTOs.CRUDSalidas.SalidaListDTO;
import DTOs.CRUDSalidas.SalidaNuevaDTO;
import excepciones.GestionCRUDSalidasException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author katia
 */
public interface IGestorCRUDSalidas {
    public boolean registrarSalida(SalidaNuevaDTO salidaNueva) throws GestionCRUDSalidasException;
    public List<SalidaListDTO> consultarTodas() throws GestionCRUDSalidasException;
    public List<SalidaListDTO> consultarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws GestionCRUDSalidasException;
    public DetalleSalidaDTO consultarPorId(String id) throws GestionCRUDSalidasException;
}
