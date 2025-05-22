/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.PersistenciaToppingDTO;
import entidades.Topping;
import interfacesMappers.IToppingMapper;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 * Su propósito es gestionar la conversión bidireccional entre la entidad `Topping`
 * (que es la representación de los datos para la base de datos, incluyendo `ObjectId`)
 * y el `PersistenciaToppingDTO` (el objeto de transferencia de datos utilizado para
 * comunicarse con la capa de negocio, donde el ID es un `String`). Este mapeo es
 * crucial para mantener la separación y la independencia entre las capas de la aplicación.
 */
public class ToppingMapper implements IToppingMapper {

    /**
     * Convierte un objeto de la entidad `Topping` (modelo de la base de datos)
     * a un `PersistenciaToppingDTO` (DTO para la capa de persistencia).
     * Este método se usa cuando se recuperan datos de la base de datos y se necesitan
     * transformar a un formato que la capa de negocio pueda utilizar, sin exponer
     * los detalles específicos de MongoDB (como el tipo `ObjectId`).
     *
     * @param topping El objeto `Topping` (entidad) a convertir.
     * @return Un `PersistenciaToppingDTO` con los datos mapeados, o `null` si la entidad de entrada es `null`.
     */
    @Override
    public PersistenciaToppingDTO toDTO(Topping topping) {
        if (topping == null) {
            return null;
        }
        PersistenciaToppingDTO dto = new PersistenciaToppingDTO();
        // Convierte el ObjectId a String para el DTO de persistencia, si el ID existe.
        if (topping.getId() != null) {
            dto.setId(topping.getId().toHexString());
        }
        dto.setNombre(topping.getNombre());
        dto.setImagenData(topping.getImagenData());
        return dto;
    }

    /**
     * Convierte un `PersistenciaToppingDTO` (DTO de la capa de persistencia)
     * a un objeto de la entidad `Topping` (modelo de la base de datos).
     * Este método se utiliza principalmente cuando la capa de negocio envía
     * datos de un topping para ser guardados o actualizados en la base de datos.
     * Se encarga de cualquier transformación necesaria para que el DTO sea compatible
     * con la estructura de la entidad de la base de datos. Se incluye una validación
     * para asegurar que el ID de tipo String sea un `ObjectId` válido antes de la conversión,
     * previniendo errores.
     *
     * @param toppingDTO El `PersistenciaToppingDTO` a convertir.
     * @return Un objeto `Topping` (entidad) con los datos mapeados, o `null` si el DTO de entrada es `null`.
     */
    @Override
    public Topping toEntity(PersistenciaToppingDTO toppingDTO) {
        if (toppingDTO == null) {
            return null;
        }
        Topping topping = new Topping();
        // Convierte el String del ID a ObjectId, solo si el String es un ObjectId válido.
        if (toppingDTO.getId() != null && ObjectId.isValid(toppingDTO.getId())) {
            topping.setId(new ObjectId(toppingDTO.getId()));
        }
        topping.setNombre(toppingDTO.getNombre());
        topping.setImagenData(toppingDTO.getImagenData());
        return topping;
    }
}
