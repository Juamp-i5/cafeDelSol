/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import interfacesMappers.IIngredienteMapper;
import interfacesMappers.IProductoMapper;
import interfacesMappers.IProductoTamanioIngredienteMapper;
import interfacesMappers.IProductoTamanioMapper;
import interfacesMappers.ISaborMapper;
import interfacesMappers.ITamanioMapper;
import interfacesMappers.IToppingMapper;
import interfacesMappers.IUsuarioMapper;
import mappers.IngredienteMapper;
import mappers.ProductoMapper;
import mappers.ProductoTamanioIngredienteMapper;
import mappers.ProductoTamanioMapper;
import mappers.SaborMapper;
import mappers.TamanioMapper;
import mappers.ToppingMapper;
import mappers.UsuarioMapper;

/**
 *
 * @author Jp
 */
public class DependencyInjectors {

    private static DependencyInjectors instancia;

    private final ISaborMapper saborMapper = new SaborMapper();
    private final IToppingMapper toppingMapper = new ToppingMapper();
    private final IIngredienteMapper ingredienteMapper = new IngredienteMapper();
    private final ITamanioMapper tamanioMapper = new TamanioMapper();
    private final IProductoTamanioIngredienteMapper productoTamanioIngredienteMapper = new ProductoTamanioIngredienteMapper(ingredienteMapper);
    private final IProductoTamanioMapper productoTamanioMapper = new ProductoTamanioMapper(tamanioMapper, productoTamanioIngredienteMapper);
    private final IProductoMapper productoMapper = new ProductoMapper(productoTamanioMapper);
    private final IUsuarioMapper usuarioMapper = new UsuarioMapper();

    private DependencyInjectors() {
    }

    public static DependencyInjectors getInstancia() {
        if (instancia == null) {
            instancia = new DependencyInjectors();
        }
        return instancia;
    }

    //MAPPERS
    public IIngredienteMapper getIngredienteMapper() {
        return ingredienteMapper;
    }

    public ITamanioMapper getTamanioMapper() {
        return tamanioMapper;
    }

    public IProductoTamanioIngredienteMapper getProductoTamanioIngredienteMapper() {
        return productoTamanioIngredienteMapper;
    }

    public IProductoTamanioMapper getProductoTamanioMapper() {
        return productoTamanioMapper;
    }

    public IProductoMapper getProductoMapper() {
        return productoMapper;
    }

    public ISaborMapper getSaborMapper() {
        return saborMapper;
    }

    public IToppingMapper getToppingMapper() {
        return toppingMapper;
    }

    public IUsuarioMapper getUsuarioMapper() {
        return usuarioMapper;
    }

}
