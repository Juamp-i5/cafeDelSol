/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import interfacesMappers.IProveedorMapper;
import DTOs.ProveedorDTO;
import entidades.Proveedor;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class ProveedorMapper implements IProveedorMapper {

    @Override
    public ProveedorDTO toDTO(Proveedor entidadMongo) {
        if (entidadMongo == null) {
            return null;
        }
        ProveedorDTO dto = new ProveedorDTO();
        if (entidadMongo.getId() != null) {
            dto.setId(entidadMongo.getId().toHexString());
        }
        dto.setNombre(entidadMongo.getNombre());
        return dto;
    }

    @Override
    public Proveedor toMongo(ProveedorDTO dto) throws PersistenciaException {
        if (dto == null) {
            return null;
        }
        Proveedor entidadMongo = new Proveedor();
        if (dto.getId() != null && ObjectId.isValid(dto.getId())) {
            entidadMongo.setId(new ObjectId(dto.getId()));
        } else if (dto.getId() != null && !dto.getId().isEmpty() && !ObjectId.isValid(dto.getId())) {
            throw new PersistenciaException("Error al mappear el id de Producto de tipo String a tipo ObjectId");
        }
        entidadMongo.setNombre(dto.getNombre());
        return entidadMongo;
    }

    @Override
    public List<ProveedorDTO> toDTOList(List<Proveedor> entidadesMongo) {
        if (entidadesMongo == null) {
            return null;
        }
        List<ProveedorDTO> proveedores = new ArrayList<>();
        for (Proveedor entidadMongo : entidadesMongo) {
            proveedores.add(toDTO(entidadMongo));
        }
        return proveedores;
    }

    @Override
    public List<Proveedor> toMongoList(List<ProveedorDTO> dtos) throws PersistenciaException {
        if (dtos == null) {
            return null;
        }
        List<Proveedor> proveedores = new ArrayList<>();
        for (ProveedorDTO dto : dtos) {
            proveedores.add(toMongo(dto));
        }
        return proveedores;
    }
}
