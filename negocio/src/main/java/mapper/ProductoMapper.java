/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.ProductoDTO;
import DTOs.ProductoMostrarDTO;
import interfacesMapper.IProductoMapper;

/**
 *
 * @author norma
 */
public class ProductoMapper implements IProductoMapper {

    private static ProductoMapper instanceMapper;

    private ProductoMapper() {
    }

    public static ProductoMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new ProductoMapper();
        }
        return instanceMapper;
    }

    @Override
    public ProductoMostrarDTO toProductoMostrarDTO(ProductoDTO productoDTO) {
        if (productoDTO == null) {
            return null;
        }

        ProductoMostrarDTO dtoMostrar = new ProductoMostrarDTO();
        dtoMostrar.setNombre(productoDTO.getNombre());
        dtoMostrar.setPrecio(productoDTO.getPrecioBase());
        dtoMostrar.setImagenData(productoDTO.getImageData());

        return dtoMostrar;
    }

}
