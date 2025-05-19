/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaProductoTamanioIngredienteDTO;
import entidades.ProductoTamanioIngrediente;

/**
 *
 * @author Jp
 */
public interface IProductoTamanioIngredienteMapper {

    public PersistenciaProductoTamanioIngredienteDTO toDTO(ProductoTamanioIngrediente productoTamanioIngrediente);

    public ProductoTamanioIngrediente toEntity(PersistenciaProductoTamanioIngredienteDTO dto);

}
