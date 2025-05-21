/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMapper;

import DTOs.CRUDProductos.DetallesProductoDTO;
import DTOs.CRUDProductos.ProductoCreateDTO;
import DTOs.CRUDProductos.ProductoListDTO;
import DTOs.PersistenciaProductoDTO;
import DTOs.ProductoMostrarDTO;

/**
 *
 * @author norma
 */
public interface IProductoMapper {

    public ProductoListDTO toProductoListDTO(PersistenciaProductoDTO productoDTO);

    public ProductoMostrarDTO toProductoMostrarDTO(PersistenciaProductoDTO producto);

    public DetallesProductoDTO toDetallesProductoDTO(PersistenciaProductoDTO pDTO);

    public PersistenciaProductoDTO toPersistenciaProductoDTO(DetallesProductoDTO detallesDTO);

    public PersistenciaProductoDTO toPersistenciaProductoDTO(ProductoCreateDTO createDTO);
}
