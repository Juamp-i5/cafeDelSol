/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaProductoTamanioDTO;
import entidades.ProductoTamanio;

/**
 *
 * @author Jp
 */
public interface IProductoTamanioMapper {

    public PersistenciaProductoTamanioDTO toDTO(ProductoTamanio productoTamanio);

    public ProductoTamanio toEntity(PersistenciaProductoTamanioDTO dto);

}
