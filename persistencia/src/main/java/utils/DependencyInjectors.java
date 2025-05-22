/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import interfacesMappers.IDetallesEfectivoMapper;
import interfacesMappers.IIngredienteMapper;
import interfacesMappers.IPagoMapper;
import interfacesMappers.IPedidoMapper;
import interfacesMappers.IProductoMapper;
import interfacesMappers.IProductoPedidoMapper;
import interfacesMappers.IProductoTamanioIngredienteMapper;
import interfacesMappers.IProductoTamanioMapper;
import interfacesMappers.ISaborMapper;
import interfacesMappers.ITamanioMapper;
import interfacesMappers.IToppingMapper;
import interfacesMappers.IUsuarioMapper;
import mappers.DetallesEfectivoMapper;
import mappers.DetallesTarjetaMapper;
import mappers.IDetallesTarjetaMapper;
import mappers.IngredienteMapper;
import mappers.PagoMapper;
import mappers.PedidoMapper;
import mappers.ProductoMapper;
import mappers.ProductoPedidoMapper;
import mappers.ProductoTamanioIngredienteMapper;
import mappers.ProductoTamanioMapper;
import mappers.SaborMapper;
import mappers.TamanioMapper;
import mappers.ToppingMapper;
import mappers.UsuarioMapper;

/**
 * Proveedor centralizado de instancias de mappers para la aplicación,
 * implementado como un Singleton.
 * <p>
 * Esta clase se encarga de la creación e inyección de dependencias entre los
 * diferentes mappers utilizados en el sistema. Proporciona un punto único de
 * acceso para obtener las implementaciones concretas de las interfaces de los
 * mappers, facilitando la gestión de estas dependencias y promoviendo un bajo
 * acoplamiento entre los componentes que los utilizan.
 * </p>
 * <p>
 * Todas las instancias de mappers son creadas y mantenidas como singletons
 * dentro de esta clase.
 * </p>
 *
 * @author Jp
 */
public class DependencyInjectors {

    /**
     * Instancia única de {@code DependencyInjectors} (Patrón Singleton).
     */
    private static DependencyInjectors instancia;

    // Instanciación de todos los mappers y resolución de sus dependencias.
    // Estos mappers son efectivamente singletons dentro del contexto de esta clase.
    private final ISaborMapper saborMapper = new SaborMapper();
    private final IToppingMapper toppingMapper = new ToppingMapper();
    private final IIngredienteMapper ingredienteMapper = new IngredienteMapper();
    private final ITamanioMapper tamanioMapper = new TamanioMapper();
    private final IProductoTamanioIngredienteMapper productoTamanioIngredienteMapper = new ProductoTamanioIngredienteMapper(ingredienteMapper);
    private final IProductoTamanioMapper productoTamanioMapper = new ProductoTamanioMapper(tamanioMapper, productoTamanioIngredienteMapper);
    private final IProductoMapper productoMapper = new ProductoMapper(productoTamanioMapper);
    private final IUsuarioMapper usuarioMapper = new UsuarioMapper();
    private final IProductoPedidoMapper productoPedidoMapper = new ProductoPedidoMapper(productoMapper, saborMapper, tamanioMapper, toppingMapper);
    private final IDetallesEfectivoMapper detallesEfectivoMapper = new DetallesEfectivoMapper();
    private final IDetallesTarjetaMapper detallesTarjetaMapper = new DetallesTarjetaMapper();
    private final IPagoMapper pagoMapper = new PagoMapper(detallesEfectivoMapper, detallesTarjetaMapper);
    private final IPedidoMapper pedidoMapper = new PedidoMapper(productoPedidoMapper, pagoMapper);

    /**
     * Constructor privado para prevenir la instanciación directa y forzar el
     * uso del patrón Singleton a través de {@link #getInstancia()}.
     */
    private DependencyInjectors() {
    }

    /**
     * Obtiene la instancia única (Singleton) de {@code DependencyInjectors}. Si
     * la instancia no ha sido creada aún, este método la inicializa.
     *
     * @return La instancia única de {@code DependencyInjectors}.
     */
    public static DependencyInjectors getInstancia() {
        if (instancia == null) {
            instancia = new DependencyInjectors();
        }
        return instancia;
    }

    /**
     * Obtiene la instancia del mapper para entidades {@code Ingrediente}.
     *
     * @return Una instancia de {@link IIngredienteMapper}.
     */
    public IIngredienteMapper getIngredienteMapper() {
        return ingredienteMapper;
    }

    /**
     * Obtiene la instancia del mapper para entidades {@code Tamanio}.
     *
     * @return Una instancia de {@link ITamanioMapper}.
     */
    public ITamanioMapper getTamanioMapper() {
        return tamanioMapper;
    }

    /**
     * Obtiene la instancia del mapper para entidades
     * {@code ProductoTamanioIngrediente}. Este mapper depende de
     * {@link IIngredienteMapper}.
     *
     * @return Una instancia de {@link IProductoTamanioIngredienteMapper}.
     */
    public IProductoTamanioIngredienteMapper getProductoTamanioIngredienteMapper() {
        return productoTamanioIngredienteMapper;
    }

    /**
     * Obtiene la instancia del mapper para entidades {@code ProductoTamanio}.
     * Este mapper depende de {@link ITamanioMapper} y
     * {@link IProductoTamanioIngredienteMapper}.
     *
     * @return Una instancia de {@link IProductoTamanioMapper}.
     */
    public IProductoTamanioMapper getProductoTamanioMapper() {
        return productoTamanioMapper;
    }

    /**
     * Obtiene la instancia del mapper para entidades {@code Producto}. Este
     * mapper depende de {@link IProductoTamanioMapper}.
     *
     * @return Una instancia de {@link IProductoMapper}.
     */
    public IProductoMapper getProductoMapper() {
        return productoMapper;
    }

    /**
     * Obtiene la instancia del mapper para entidades {@code Sabor}.
     *
     * @return Una instancia de {@link ISaborMapper}.
     */
    public ISaborMapper getSaborMapper() {
        return saborMapper;
    }

    /**
     * Obtiene la instancia del mapper para entidades {@code Topping}.
     *
     * @return Una instancia de {@link IToppingMapper}.
     */
    public IToppingMapper getToppingMapper() {
        return toppingMapper;
    }

    /**
     * Obtiene la instancia del mapper para entidades {@code Usuario}.
     *
     * @return Una instancia de {@link IUsuarioMapper}.
     */
    public IUsuarioMapper getUsuarioMapper() {
        return usuarioMapper;
    }

    /**
     * Obtiene la instancia del mapper para entidades {@code ProductoPedido}.
     * Este mapper depende de {@link IProductoMapper}, {@link ISaborMapper},
     * {@link ITamanioMapper} y {@link IToppingMapper}.
     *
     * @return Una instancia de {@link IProductoPedidoMapper}.
     */
    public IProductoPedidoMapper getProductoPedidoMapper() {
        return productoPedidoMapper;
    }

    /**
     * Obtiene la instancia del mapper para DTOs {@code DetallesEfectivo}.
     *
     * @return Una instancia de {@link IDetallesEfectivoMapper}.
     */
    public IDetallesEfectivoMapper getDetallesEfectivoMapper() {
        return detallesEfectivoMapper;
    }

    /**
     * Obtiene la instancia del mapper para DTOs {@code DetallesTarjeta}.
     *
     * @return Una instancia de {@link IDetallesTarjetaMapper}.
     */
    public IDetallesTarjetaMapper getDetallesTarjetaMapper() {
        return detallesTarjetaMapper;
    }

    /**
     * Obtiene la instancia del mapper para entidades {@code Pago}. Este mapper
     * depende de {@link IDetallesEfectivoMapper} y
     * {@link IDetallesTarjetaMapper}.
     *
     * @return Una instancia de {@link IPagoMapper}.
     */
    public IPagoMapper getPagoMapper() {
        return pagoMapper;
    }

    /**
     * Obtiene la instancia del mapper para entidades {@code Pedido}. Este
     * mapper depende de {@link IProductoPedidoMapper} y {@link IPagoMapper}.
     *
     * @return Una instancia de {@link IPedidoMapper}.
     */
    public IPedidoMapper getPedidoMapper() {
        return pedidoMapper;
    }

}
