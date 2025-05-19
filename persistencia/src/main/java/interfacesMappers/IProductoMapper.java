/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaProductoDTO;
import entidades.Producto;

/**
 *
 * @author Jp
 */
public interface IProductoMapper {

    public PersistenciaProductoDTO toDTO(Producto entidad);

    public Producto toEntity(PersistenciaProductoDTO dto);

}
