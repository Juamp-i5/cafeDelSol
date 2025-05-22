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
 *Interfaz que define los métodos de mapeo entre la entidad Salida
 * y sus respectivos DTOs utilizados en la capa de persistencia.
 * @author katia
 */
public interface ISalidaMapperPersistencia {
    
    /**
     * Convierte un DTO de nueva salida a una entidad Salida.
     *
     * @param dto DTO con los datos necesarios para registrar una nueva salida.
     * @return Entidad {@link Salida} lista para ser almacenada.
     */
    Salida toEntity(SalidaNuevaDTOPersistencia dto);

    /**
     * Convierte una salida a un DTO resumido para lista.
     *
     * @param salida Entidad Salida.
     * @param nombreIngrediente Nombre del ingrediente correspondiente.
     * @return DTO de salida con información resumida.
     */
    SalidaListDTOPersistencia toSalidaListDTO(Salida salida, String nombreIngrediente);

    /**
     * Convierte una salida a un DTO detallado para visualización por fecha.
     *
     * @param salida Entidad Salida.
     * @param nombreIngrediente Nombre del ingrediente correspondiente.
     * @return DTO con información detallada de la salida.
     */
    DetalleSalidaDTOPersistencia toDetalleSalidaDTO(Salida salida, String nombreIngrediente);

    /**
     * Convierte una lista de salidas a una lista de DTOs resumidos para tabla.
     *
     * @param salidas Lista de entidades Salida.
     * @param nombresIngredientes Lista de nombres de ingredientes (en el mismo orden que las salidas).
     * @return Lista de DTOs de salidas con información resumida.
     */
    List<SalidaListDTOPersistencia> toSalidaListDTOList(List<Salida> salidas, List<String> nombresIngredientes);

    /**
     * Convierte una lista de salidas a una lista de DTOs detallados para detalle por fecha.
     *
     * @param salidas Lista de entidades Salida.
     * @param nombresIngredientes Lista de nombres de ingredientes (en el mismo orden que las salidas).
     * @return Lista de DTOs con información detallada de cada salida.
     */
    List<DetalleSalidaDTOPersistencia> toDetalleSalidaDTOList(List<Salida> salidas, List<String> nombresIngredientes);
}
