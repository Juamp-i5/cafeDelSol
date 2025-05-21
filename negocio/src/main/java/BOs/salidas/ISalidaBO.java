/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs.salidas;

import DTOs.CRUDSalidas.DetalleSalidaDTO;
import DTOs.CRUDSalidas.SalidaListDTO;
import DTOs.CRUDSalidas.SalidaNuevaDTO;
import excepciones.NegocioException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author katia
 */
public interface ISalidaBO {
    public boolean registrarSalida(SalidaNuevaDTO salidaDTO) throws NegocioException;
    public List<SalidaListDTO> consultarTodas() throws NegocioException;
    public List<SalidaListDTO> consultarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException;
    public DetalleSalidaDTO consultarPorId(String id) throws NegocioException;
}
