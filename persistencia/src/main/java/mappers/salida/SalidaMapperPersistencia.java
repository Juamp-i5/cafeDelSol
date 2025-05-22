/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers.salida;

import DTOs.salidas.DetalleSalidaDTOPersistencia;
import DTOs.salidas.SalidaListDTOPersistencia;
import DTOs.salidas.SalidaNuevaDTOPersistencia;
import entidades.Salida;
import interfacesMappers.salidas.ISalidaMapperPersistencia;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Implementación de la interfaz ISalidaMapperPersistencia que se encarga de
 * convertir entre la entidad Salida y sus distintos DTOs utilizados en la capa de persistencia.
 * @author katia
 */
public class SalidaMapperPersistencia implements ISalidaMapperPersistencia{
    
    /**
     * Convierte un SalidaNuevaDTOPersistencia a una entidad Salida,
     * asignando el ID del ingrediente como un ObjectId.
     *
     * @param dto DTO con los datos de una nueva salida.
     * @return Entidad lista para ser insertada en la base de datos.
     */
    @Override
    public Salida toEntity(SalidaNuevaDTOPersistencia dto) {
        return new Salida(
                dto.getFecha(),
                new ObjectId(dto.getIdIngrediente()),
                dto.getCantidad(),
                dto.getMotivo()
        );
    }

    /**
     * Convierte una entidad Salida en un DTO resumido SalidaListDTOPersistencia
     * que incluye el nombre del ingrediente.
     *
     * @param salida Entidad a convertir.
     * @param nombreIngrediente Nombre del ingrediente asociado a la salida.
     * @return DTO con información resumida de la salida.
     */
    @Override
    public SalidaListDTOPersistencia toSalidaListDTO(Salida salida, String nombreIngrediente) {
        return new SalidaListDTOPersistencia(
                salida.getId().toHexString(),
                nombreIngrediente,
                salida.getCantidad(),
                salida.getMotivo(),
                salida.getFecha()
        );
    }

    /**
     * Convierte una entidad Salida en un DTO detallado DetalleSalidaDTOPersistencia
     * para su visualización agrupada por fecha.
     *
     * @param salida Entidad a convertir.
     * @param nombreIngrediente Nombre del ingrediente relacionado.
     * @return DTO con detalle completo de la salida.
     */
    @Override
    public DetalleSalidaDTOPersistencia toDetalleSalidaDTO(Salida salida, String nombreIngrediente) {
        return new DetalleSalidaDTOPersistencia(
                nombreIngrediente,
                salida.getCantidad(),
                salida.getMotivo(),
                salida.getFecha()
        );
    }
    
    /**
     * Convierte una lista de entidades Salida a una lista de DTOs resumidos,
     * manteniendo la correspondencia con los nombres de ingredientes.
     *
     * @param salidas Lista de entidades a convertir.
     * @param nombresIngredientes Lista de nombres asociados, en el mismo orden que las salidas.
     * @return Lista de DTOs resumidos para vista general.
     */
    @Override
    public List<SalidaListDTOPersistencia> toSalidaListDTOList(List<Salida> salidas, List<String> nombresIngredientes) {
        List<SalidaListDTOPersistencia> lista = new ArrayList<>();
        for (int i = 0; i < salidas.size(); i++) {
            lista.add(toSalidaListDTO(salidas.get(i), nombresIngredientes.get(i)));
        }
        return lista;
    }

    /**
     * Convierte una lista de entidades Salida a una lista de DTOs detallados,
     * con correspondencia entre cada salida y su ingrediente.
     *
     * @param salidas Lista de entidades a convertir.
     * @param nombresIngredientes Lista de nombres de ingredientes (ordenado).
     * @return Lista de DTOs detallados de salidas.
     */
    @Override
    public List<DetalleSalidaDTOPersistencia> toDetalleSalidaDTOList(List<Salida> salidas, List<String> nombresIngredientes) {
        List<DetalleSalidaDTOPersistencia> lista = new ArrayList<>();
        for (int i = 0; i < salidas.size(); i++) {
            lista.add(toDetalleSalidaDTO(salidas.get(i), nombresIngredientes.get(i)));
        }
        return lista;
    }
}
