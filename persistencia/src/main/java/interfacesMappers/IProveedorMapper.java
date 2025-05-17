/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacesMappers;

import DTOs.ProveedorDTO;
import entidades.Proveedor;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jp
 */
public interface IProveedorMapper {

    public ProveedorDTO toDTO(Proveedor entidadMongo);

    public Proveedor toMongo(ProveedorDTO dto) throws PersistenciaException;

    public List<ProveedorDTO> toDTOList(List<Proveedor> entidadesMongo);

    public List<Proveedor> toMongoList(List<ProveedorDTO> dtos) throws PersistenciaException;
}
