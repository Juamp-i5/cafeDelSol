/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaSaborDTO;
import entidades.Sabor;
import excepciones.PersistenciaException;
import interfacesMappers.ISaborMapper;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 * Esta clase, `SaborMapper`, implementa la interfaz `ISaborMapper` y es un
 * **mapeador (mapper)** fundamental en la **capa de persistencia** de la aplicación.
 * Su propósito es gestionar la conversión bidireccional entre la entidad `Sabor`
 * (que es la representación de los datos para la base de datos, incluyendo `ObjectId`)
 * y el `PersistenciaSaborDTO` (el objeto de transferencia de datos utilizado para
 * comunicarse con la capa de negocio, donde el ID es un `String`). Este mapeo es
 * crucial para mantener la separación y la independencia entre las capas de la aplicación.
 */
public class SaborMapper implements ISaborMapper {

    /**
     * Convierte una entidad `Sabor` (el modelo de persistencia/dominio) a un
     * `PersistenciaSaborDTO` (el DTO de la capa de persistencia).
     * Este método se usa cuando se recuperan datos de la base de datos y se necesitan
     * transformar a un formato que la capa de negocio pueda utilizar, sin exponer
     * los detalles específicos de MongoDB (como el tipo `ObjectId`).
     *
     * @param sabor El objeto `Sabor` (entidad) a convertir.
     * @return Un `PersistenciaSaborDTO` con los datos mapeados, o `null` si la entidad de entrada es `null`.
     */
    @Override
    public PersistenciaSaborDTO toDTO(Sabor sabor) {
        if (sabor == null) {
            return null;
        }
        PersistenciaSaborDTO dto = new PersistenciaSaborDTO();
        // Convierte el ObjectId a String para el DTO de persistencia, si el ID existe.
        if (sabor.getId() != null) {
            dto.setId(sabor.getId().toHexString());
        }
        dto.setNombre(sabor.getNombre());
        dto.setImagenData(sabor.getImagenData());
        return dto;
    }

    /**
     * Convierte un `PersistenciaSaborDTO` (el DTO de la capa de persistencia) a una
     * entidad `Sabor` (el modelo de persistencia/dominio).
     * Este método se utiliza cuando la capa de negocio envía datos de `Sabor`
     * para ser guardados o actualizados en la base de datos. Se encarga de convertir
     * el ID de `String` a `ObjectId` y de validar si el `String` es un `ObjectId` válido
     * antes de la conversión para evitar errores.
     *
     * @param saborDTO El `PersistenciaSaborDTO` a convertir.
     * @return Un objeto `Sabor` (entidad) con los datos mapeados, o `null` si el DTO de entrada es `null`.
     */
    @Override
    public Sabor toEntity(PersistenciaSaborDTO saborDTO) {
        if (saborDTO == null) {
            return null;
        }
        Sabor sabor = new Sabor();
        // Convierte el String del ID a ObjectId, solo si el String es un ObjectId válido.
        if (saborDTO.getId() != null && ObjectId.isValid(saborDTO.getId())) {
            sabor.setId(new ObjectId(saborDTO.getId()));
        }
        sabor.setNombre(saborDTO.getNombre());
        sabor.setImagenData(saborDTO.getImagenData());
        return sabor;
    }
}
