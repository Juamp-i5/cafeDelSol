/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMapper;

import DTOs.ProductoMostrarDTO;
import entidades.Producto;

/**
 *
 * @author norma
 */
public interface IProductoMapper {

    ProductoMostrarDTO aDTO(Producto producto);
}
