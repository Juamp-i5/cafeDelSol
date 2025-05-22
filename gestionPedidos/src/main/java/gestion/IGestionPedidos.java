/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestion;

import DTOs.CambioDTO;
import DTOs.EfectivoDTO;
import DTOs.InicioSesionDTO;
import DTOs.PedidoDTO;
import DTOs.ProductoMostrarDTO;
import DTOs.ProductoPedidoDTO;
import DTOs.SaborMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.ToppingMostrarDTO;
import DTOs.TarjetaDTO;
import DTOs.UsuarioDTO;
import exception.GestionException;
import java.util.List;

/**
 * Interfaz que define los métodos para gestionar pedidos y productos pedido así
 * como las operaciones relacionadas con la validación, cálculo de costos y
 * manejo de pagos.
 *
 * @author Jp
 */
public interface IGestionPedidos {

    /**
     * Obtiene el pedido actual.
     *
     * @return El pedido actual.
     */
    public PedidoDTO getPedido();

    /**
     * Carga la lista de productos disponibles.
     *
     * @return La lista de productos disponibles.
     * @throws exception.GestionException
     */
    public List<ProductoMostrarDTO> cargarProductos() throws GestionException;

    /**
     * Agrega un producto al producto pedido actual. Establece el producto del
     * parámetro como producto del producto pedido.
     *
     * @param producto El producto que se quiere agregar.
     */
    public void agregarProducto(ProductoMostrarDTO producto);

    /**
     * Inicia un nuevo pedido, y a su vez inicia un producto pedido.
     */
    public void iniciarPedido();

    /**
     * Crea un nuevo producto pedido.
     */
    public void crearProductoPedido();

    /**
     * Carga la lista de tamaños disponibles.
     *
     * @return La lista de tamaños disponibles.
     * @throws exception.GestionException
     */
    public List<TamanioMostrarDTO> cargarTamanios() throws GestionException;

    /**
     * Agrega un tamaño al producto pedido actual. Establece el tamaño del
     * parámetro como tamaño del producto pedido.
     *
     * @param tamanio El tamaño que se quiere agregar.
     */
    public void agregarTamanio(TamanioMostrarDTO tamanio);

    /**
     * Carga la lista de sabores disponibles.
     *
     * @return La lista de sabores disponibles.
     * @throws exception.GestionException
     */
    public List<SaborMostrarDTO> cargarSabores() throws GestionException;

    /**
     * Agrega un sabor al producto pedido actual. Establece el sabor del
     * parámetro como sabor del producto pedido.
     *
     * @param sabor El sabor que se quiere agregar.
     */
    public void agregarSabor(SaborMostrarDTO sabor);

    /**
     * Carga la lista de toppings disponibles.
     *
     * @return La lista de toppings disponibles.
     * @throws exception.GestionException
     */
    public List<ToppingMostrarDTO> cargarToppings() throws GestionException;

    /**
     * Agrega un topping al producto pedido actual.
     *
     * @param topping Establece el topping del parámetro como topping del
     * producto pedido.
     */
    public void agregarTopping(ToppingMostrarDTO topping);

    /**
     * Valida la información de una tarjeta de presentación.
     *
     * @param tarjeta La tarjeta que representa la tarjeta a validar (con la que
     * se esta pagando).
     * @return true si la tarjeta es valida, false en caso contrario.
     * @throws GestionException Excepción que lanzará si un campo de la tarjeta
     * es inválido.
     */
    public boolean validarTarjetaPresentacion(TarjetaDTO tarjeta) throws GestionException;

    /**
     * Cancela el pedido actual.
     *
     * @param pedido Pedido que se quiere cancelar.
     * @return true si el pedido se cancelo con éxito, false en caso contrario.
     * @throws GestionException Excepción que lanzará si ocurre un error al
     * cancelar el pedido.
     */
    public boolean cancelarPedido(PedidoDTO pedido) throws GestionException;

    /**
     * Agrega un producto pedido al pedido actual.
     *
     * @return true si el producto pedido se agrga con éxito, false en caso
     * contrario.
     * @throws GestionException Excepción que lanzará si ocurre un error al
     * agregar el producto pedido al pedido.
     */
    public boolean agregarProductoPedidoAPedido() throws GestionException;

    /**
     * Termina un pedido. Da como terminado un pedido.
     *
     * @return true si el pedido se termino con éxito, false en caso contrario.
     * @throws GestionException Excepción que lanzará si ocurre un error al dar
     * como terminado un pedido.
     */
    public boolean terminarPedido() throws GestionException;

    /**
     * Calcula el costo del producto pedido en relación al costo del producto,
     * el costo del tamaño seleccionado y la cantidad del producto pedido que se
     * desea.
     *
     * @return El costo del producto pedido.
     */
    public double calcularCosto();

    /**
     * Calcula el costo total del pedido en relación a los costos de los
     * productos pedidos que tiene.
     *
     * @return El costo total del producto pedido.
     */
    public double calcularTotal();

    /**
     * Calcula el cambio que debe entregarse basado en la cantidad de efectivo
     * proporcionado.
     *
     * @param efectivo El efectivo que representa el pago realizado.
     * @return El monto del cambio a regresar.
     */
    public double calcularCambio(EfectivoDTO efectivo);

    /**
     * Cancela un producto pedido dentro del pedido. Lo elimina del pedido.
     *
     * @param productoPedido El producto pedido que se quiere eliminar.
     */
    public void cancelarProductoPedido(ProductoPedidoDTO productoPedido);

    /**
     * Imprime la información del pedido y del producto pedido actual en
     * consola.
     */
    public void imprimirPedidoConsola();

    /**
     * Establece el producto pedido actual.
     *
     * @param productoPedidoActual El producto pedido actual.
     */
    public void setProductoPedidoActual(ProductoPedidoDTO productoPedidoActual);

    /**
     * Obtiene el producto pedido actual.
     *
     * @return El producto pedido actual.
     */
    public ProductoPedidoDTO getProductoPedidoActual();

    /**
     * Actualiza el total del pedido en relación a los costos de los productos
     * pedidos que tiene el pedido.
     *
     * @return El costo total actualizado del pedido actual.
     */
    public double actualizarTotal();

    /**
     *
     * Registar el pedido en la base de datos
     *
     * @return
     * @throws exception.GestionException
     */
    public PedidoDTO registrarPedido() throws GestionException;

    public UsuarioDTO comprobarInicioSesion(InicioSesionDTO inicioSesionDTO) throws GestionException;

    public void agregarUsuarioAlPedido(String idUsuario);
    
    public List<PedidoDTO> obtenerPedidosDelivery() throws GestionException;
    
    public void actualizarEstado(String idPedido) throws GestionException;
}
