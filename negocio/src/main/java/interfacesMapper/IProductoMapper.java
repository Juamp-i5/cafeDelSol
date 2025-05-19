/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMapper;

import DTOs.CRUDProductos.ProductoListDTO;
import DTOs.PersistenciaProductoDTO;
import DTOs.ProductoMostrarDTO;

/**
 *
 * @author norma
 */
public interface IProductoMapper {

    ProductoListDTO toProductoListDTO(PersistenciaProductoDTO productoDTO);

    ProductoMostrarDTO toProductoMostrarDTO(PersistenciaProductoDTO producto);
}
