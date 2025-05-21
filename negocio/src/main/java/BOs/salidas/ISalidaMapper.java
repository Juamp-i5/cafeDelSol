/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs.salidas;

import DTOs.CRUDSalidas.DetalleSalidaDTO;
import DTOs.CRUDSalidas.SalidaListDTO;
import DTOs.CRUDSalidas.SalidaNuevaDTO;
import DTOs.salidas.DetalleSalidaDTOPersistencia;
import DTOs.salidas.SalidaListDTOPersistencia;
import DTOs.salidas.SalidaNuevaDTOPersistencia;
import entidades.Salida;

/**
 *
 * @author katia
 */
public interface ISalidaMapper {
    SalidaNuevaDTOPersistencia toSalidaNuevaDTOPersistencia(SalidaNuevaDTO dto);

    SalidaListDTO toSalidaListDTO(SalidaListDTOPersistencia dto);
    DetalleSalidaDTO toDetalleSalidaDTO(DetalleSalidaDTOPersistencia dto);

    Salida toEntity(SalidaNuevaDTOPersistencia dto);
    SalidaListDTOPersistencia toSalidaListDTO(Salida salida, String nombreIngrediente);
    DetalleSalidaDTOPersistencia toDetalleSalidaDTO(Salida salida, String nombreIngrediente);

}
