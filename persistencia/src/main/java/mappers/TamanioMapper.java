/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import interfacesMappers.ITamanioMapper;
import DTOs.PersistenciaTamanioDTO;
import entidades.Tamanio;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 * Esta clase, `TamanioMapper`, implementa la interfaz `ITamanioMapper` y es un
 * mapeador. Su función principal es realizar la conversión bidireccional entre 
 * la entidad `Tamanio` (tal como se almacena en la base de datos, con su `ObjectId`) 
 * y el `PersistenciaTamanioDTO` Este mapeo es fundamental para desacoplar las capas y asegurar que cada una
 * trabaje con la representación de datos que le es más conveniente.
 */
public class TamanioMapper implements ITamanioMapper {

    /**
     * Convierte una entidad `Tamanio` (el modelo de persistencia/dominio) a un
     * `PersistenciaTamanioDTO` (el DTO de la capa de persistencia).
     * Este método se utiliza cuando se recuperan datos de la base de datos
     * y se necesitan transformar a un formato que la capa de negocio pueda entender
     * sin conocer los detalles de MongoDB (como `ObjectId`).
     *
     * @param entidad El objeto `Tamanio` (entidad) a convertir.
     * @return Un `PersistenciaTamanioDTO` con los datos mapeados, o `null` si la entidad de entrada es `null`.
     */
    @Override
    public PersistenciaTamanioDTO toDTO(Tamanio entidad) {
        if (entidad == null) {
            return null;
        }
        PersistenciaTamanioDTO dto = new PersistenciaTamanioDTO();
        // Convierte el ObjectId a String para el DTO de persistencia.
        dto.setId(entidad.getId() != null ? entidad.getId().toHexString() : null);
        dto.setNombre(entidad.getNombre());
        dto.setPrecioAdicional(entidad.getPrecioAdicional());
        dto.setImagenData(entidad.getImagenData());
        return dto;
    }

    /**
     * Convierte un `PersistenciaTamanioDTO` (el DTO de la capa de persistencia) a una
     * entidad `Tamanio` (el modelo de persistencia/dominio).
     * Este método se utiliza cuando la capa de negocio envía datos de `Tamanio`
     * para ser guardados o actualizados en la base de datos. Se encarga de convertir
     * el ID de `String` a `ObjectId` y de validar si el `String` es un `ObjectId` válido.
     *
     * @param dto El `PersistenciaTamanioDTO` a convertir.
     * @return Un objeto `Tamanio` (entidad) con los datos mapeados, o `null` si el DTO de entrada es `null`.
     */
    @Override
    public Tamanio toEntity(PersistenciaTamanioDTO dto) {
        if (dto == null) {
            return null;
        }
        Tamanio tamanio = new Tamanio();
        // Convierte el String del ID a ObjectId, solo si es un ObjectId válido.
        if (dto.getId() != null && ObjectId.isValid(dto.getId())) {
            tamanio.setId(new ObjectId(dto.getId()));
        }
        tamanio.setNombre(dto.getNombre());
        tamanio.setPrecioAdicional(dto.getPrecioAdicional());
        tamanio.setImagenData(dto.getImagenData());
        return tamanio;
    }
}
