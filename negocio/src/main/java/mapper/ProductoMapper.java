/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.ProductoMostrarDTO;
import entidades.Producto;
import interfacesMapper.IProductoMapper;

/**
 *
 * @author norma
 */
public class ProductoMapper implements IProductoMapper {

    private static ProductoMapper instanceMapper;

    public ProductoMapper() {
    }

    public static ProductoMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new ProductoMapper();
        }
        return instanceMapper;
    }

    @Override
    public ProductoMostrarDTO aDTO(Producto producto) {
        if (producto == null) {
            return null;
        }

        ProductoMostrarDTO dto = new ProductoMostrarDTO();
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setUrlImagen(producto.getUrlImagen());
        return dto;
    }

}
