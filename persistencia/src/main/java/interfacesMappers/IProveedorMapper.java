/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaProveedorDTO;
import entidades.Proveedor;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jp
 */
public interface IProveedorMapper {

    public PersistenciaProveedorDTO toDTO(Proveedor entidadMongo);

    public Proveedor toMongo(PersistenciaProveedorDTO dto) throws PersistenciaException;

    public List<PersistenciaProveedorDTO> toDTOList(List<Proveedor> entidadesMongo);

    public List<Proveedor> toMongoList(List<PersistenciaProveedorDTO> dtos) throws PersistenciaException;
}
