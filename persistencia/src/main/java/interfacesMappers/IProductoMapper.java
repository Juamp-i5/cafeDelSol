/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.ProductoDTO;
import entidades.Producto;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jp
 */
public interface IProductoMapper {

    public ProductoDTO toDTO(Producto entidad);

    public Producto toMongo(ProductoDTO dto) throws PersistenciaException;

    public List<ProductoDTO> toDTOList(List<Producto> entidades);

    public List<Producto> toMongoList(List<ProductoDTO> dtos) throws PersistenciaException;
}
