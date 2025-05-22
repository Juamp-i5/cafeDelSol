/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacesMappers.salidas;

import DTOs.salidas.DetalleSalidaDTOPersistencia;
import DTOs.salidas.SalidaListDTOPersistencia;
import DTOs.salidas.SalidaNuevaDTOPersistencia;
import entidades.Salida;
import java.util.List;

/**
 *
 * @author katia
 */
public interface ISalidaMapperPersistencia {

    Salida toEntity(SalidaNuevaDTOPersistencia dto);

    SalidaListDTOPersistencia toSalidaListDTO(Salida salida, String nombreIngrediente);

    DetalleSalidaDTOPersistencia toDetalleSalidaDTO(Salida salida, String nombreIngrediente);

    List<SalidaListDTOPersistencia> toSalidaListDTOList(List<Salida> salidas, List<String> nombresIngredientes);

    List<DetalleSalidaDTOPersistencia> toDetalleSalidaDTOList(List<Salida> salidas, List<String> nombresIngredientes);
}
