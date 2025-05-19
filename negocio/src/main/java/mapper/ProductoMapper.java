/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.CRUDProductos.CategoriaProducto;
import DTOs.CRUDProductos.EstadoProducto;
import DTOs.CRUDProductos.ProductoListDTO;
import DTOs.CRUDProductos.TamanioProducto;
import DTOs.PersistenciaProductoDTO;
import DTOs.PersistenciaProductoTamanioDTO;
import DTOs.PersistenciaTamanioDTO;
import DTOs.ProductoMostrarDTO;
import interfacesMapper.IProductoMapper;
import java.util.HashMap;
import java.util.Map;

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
    public ProductoMostrarDTO toProductoMostrarDTO(PersistenciaProductoDTO productoDTO) {
        if (productoDTO == null) {
            return null;
        }

        ProductoMostrarDTO dtoMostrar = new ProductoMostrarDTO();
        dtoMostrar.setNombre(productoDTO.getNombre());
        dtoMostrar.setPrecio(productoDTO.getPrecioBase());
        dtoMostrar.setImagenData(productoDTO.getImageData());

        return dtoMostrar;
    }

    @Override
    public ProductoListDTO toProductoListDTO(PersistenciaProductoDTO productoDTO) {
        if (productoDTO == null) {
            return null;
        }

        ProductoListDTO listDTO = new ProductoListDTO();

        listDTO.setId(productoDTO.getId());
        listDTO.setNombre(productoDTO.getNombre());
        listDTO.setCategoria(mapStringToCategoriaProducto(productoDTO.getCategoria()));
        listDTO.setEstadoProducto(mapStringToEstadoProducto(productoDTO.getEstado()));

        Map<TamanioProducto, Double> preciosMap = new HashMap<>();
        if (productoDTO.getTamanios() != null) {
            for (PersistenciaProductoTamanioDTO ptDTO : productoDTO.getTamanios()) {
                if (ptDTO != null && ptDTO.getTamanio() != null) {
                    PersistenciaTamanioDTO infoTamanio = ptDTO.getTamanio();
                    String nombreTamanioStr = infoTamanio.getNombre();

                    TamanioProducto tamanioEnum = mapStringToTamanioProducto(nombreTamanioStr);

                    if (tamanioEnum != null) {
                        double precioCalculado = productoDTO.getPrecioBase() + infoTamanio.getPrecioAdicional();
                        preciosMap.put(tamanioEnum, precioCalculado);
                    } else {
                        System.err.println("Advertencia: Nombre de tamaño no mapeable '" + nombreTamanioStr + "' para producto ID: " + productoDTO.getId());
                    }
                }
            }
        }
        listDTO.setPrecios(preciosMap);

        return listDTO;
    }

    //AUXILIARES (de enums)
    private CategoriaProducto mapStringToCategoriaProducto(String categoriaStr) {
        if (categoriaStr == null || categoriaStr.trim().isEmpty()) {
            return null;
        }

        try {
            return CategoriaProducto.valueOf(categoriaStr);
        } catch (IllegalArgumentException e) {
            System.err.println("Advertencia: No se encontró el enum CategoriaProducto para el string: '" + categoriaStr + "')");
            return null;
        }
    }

    private EstadoProducto mapStringToEstadoProducto(String estadoStr) {
        if (estadoStr == null || estadoStr.trim().isEmpty()) {
            return null;
        }
        try {
            return EstadoProducto.valueOf(estadoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Advertencia: No se encontró el enum EstadoProducto para el string: '" + estadoStr + "'");
            return null;
        }
    }

    private TamanioProducto mapStringToTamanioProducto(String tamanioStr) {
        if (tamanioStr == null || tamanioStr.trim().isEmpty()) {
            return null;
        }
        try {
            return TamanioProducto.valueOf(tamanioStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Advertencia: No se encontró el enum TamanioProducto para el string: '" + tamanioStr + "'");
            return null;
        }
    }
}
