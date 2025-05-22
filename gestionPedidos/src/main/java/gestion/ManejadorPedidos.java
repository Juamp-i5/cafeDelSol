/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion;

import BOs.PedidoBO;
import BOs.ProductoBO;
import BOs.SaborBO;
import BOs.TamanioBO;
import BOs.ToppingBO;
import BOs.UsuarioBO;
import DTOs.DetallesCobroTarjetaDTO;
import DTOs.EfectivoDTO;
import DTOs.InicioSesionDTO;
import DTOs.PagoDTO;
import DTOs.PedidoDTO;
import DTOs.ProductoMostrarDTO;
import DTOs.ProductoPedidoDTO;
import DTOs.SaborMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.ToppingMostrarDTO;
import DTOs.TarjetaDTO;
import DTOs.UsuarioDTO;
import excepciones.NegocioException;
import exception.GestionException;
import interfaces.IFachadaPago;
import interfacesBO.IPedidoBO;
import interfacesBO.IProductoBO;
import interfacesBO.ISaborBO;
import interfacesBO.ITamanioBO;
import interfacesBO.IToppingBO;
import interfacesBO.IUsuarioBO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pago.FachadaPago;
import pago.PagoTarjetaAPI;
import pago.ResultadoPago;
import pago.ValidarPago;

/**
 * Clase que implementa la interfaz IGestionPedidos para gestionar pedidos y
 * productos pedidos.
 *
 * @author Jp
 */
public class ManejadorPedidos implements IGestionPedidos {

//    private static final Logger LOG = Logger.getLogger(ManejadorPedidos.class.getName());
    private PedidoDTO pedido;
    private ProductoPedidoDTO productoPedidoActual;
    IPedidoBO pedidoBO = PedidoBO.getInstance();
    IProductoBO productoBO = ProductoBO.getInstance();
    ISaborBO saborBO = SaborBO.getInstance();
    ITamanioBO tamanioBO = TamanioBO.getInstance();
    IToppingBO toppingBO = ToppingBO.getInstance();
    IUsuarioBO usuarioBO = UsuarioBO.getInstancia();

    private IFachadaPago fachadaPago;

    public ManejadorPedidos() {
        this.fachadaPago = new FachadaPago(new PagoTarjetaAPI(), new ValidarPago());
        this.productoPedidoActual = new ProductoPedidoDTO();
        this.pedido = new PedidoDTO();
    }

    @Override
    public boolean validarTarjetaPresentacion(TarjetaDTO tarjetaDTO) throws GestionException {
        DetallesCobroTarjetaDTO detalles = new DetallesCobroTarjetaDTO();
        detalles.setNumeroTarjeta(tarjetaDTO.getNumTarjeta());
        detalles.setNombreBanco(tarjetaDTO.getNombreBanco());
        detalles.setCvv(tarjetaDTO.getCVV());
        detalles.setFechaExp(tarjetaDTO.getFechaExp());

        ResultadoPago resultado = fachadaPago.procesarPago(detalles);
        pedido.getPagoDTO().getResultadoPagoDTO().setIdTransaccion(resultado.getIdTransaccion());
        pedido.getPagoDTO().getResultadoPagoDTO().setBanco(resultado.getBanco());
        pedido.getPagoDTO().getResultadoPagoDTO().setMarca(resultado.getMarca());
        pedido.getPagoDTO().getResultadoPagoDTO().setTipoTarjeta(resultado.getTipoTarjeta());
        pedido.getPagoDTO().getResultadoPagoDTO().setTitular(resultado.getTitular());
        
        if (!resultado.isExito()) {
            throw new GestionException("Pago fallido: " + resultado.getMensajeError());
        }

        return true;
    }

    @Override
    public ProductoPedidoDTO getProductoPedidoActual() {
        return productoPedidoActual;
    }

    @Override
    public void setProductoPedidoActual(ProductoPedidoDTO productoPedidoActual) {
        this.productoPedidoActual = productoPedidoActual;
    }

    @Override
    public List<ProductoMostrarDTO> cargarProductos() throws GestionException {
        try {
            return productoBO.cargarProductos();
        } catch (NegocioException ex) {
            Logger.getLogger(ManejadorPedidos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionException("Error al cargar Productos");
        }
    }

    @Override
    public PedidoDTO getPedido() {
        return pedido;
    }

    @Override
    public void agregarProducto(ProductoMostrarDTO producto) {
        productoPedidoActual.setProducto(producto);
    }

    @Override
    public void iniciarPedido() {
        this.pedido = new PedidoDTO();
        this.productoPedidoActual = new ProductoPedidoDTO();
    }

    @Override
    public void crearProductoPedido() {
        this.productoPedidoActual = new ProductoPedidoDTO();
    }

    @Override
    public List<TamanioMostrarDTO> cargarTamanios() throws GestionException {
        try {
            return tamanioBO.cargarTamanios();
        } catch (NegocioException ex) {
            Logger.getLogger(ManejadorPedidos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionException("Error al cargar Tamanios");
        }
    }

    @Override
    public void agregarTamanio(TamanioMostrarDTO tamanio) {
        productoPedidoActual.setTamanio(tamanio);

    }

    @Override
    public List<SaborMostrarDTO> cargarSabores() throws GestionException {
        try {
            return saborBO.cargarSabores();
        } catch (NegocioException ex) {
            Logger.getLogger(ManejadorPedidos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionException("Error al cargar Sabores");
        }
    }

    @Override
    public void agregarSabor(SaborMostrarDTO sabor) {
        productoPedidoActual.setSabor(sabor);
    }

    @Override
    public List<ToppingMostrarDTO> cargarToppings() throws GestionException {
        try {
            return toppingBO.cargarProductos();
        } catch (NegocioException ex) {
            Logger.getLogger(ManejadorPedidos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionException("Error al cargar Sabores");
        }
    }

    @Override
    public void agregarTopping(ToppingMostrarDTO topping) {
        productoPedidoActual.setTopping(topping);
    }

    @Override
    public boolean cancelarPedido(PedidoDTO pedido) throws GestionException {

        if (productoPedidoActual == null) {
            throw new GestionException("No hay un producto activo para cancelar.");
        }
        productoPedidoActual = null;
        return true;
    }

    @Override
    public boolean agregarProductoPedidoAPedido() throws GestionException {
        boolean agregado = pedido.getProductos().add(productoPedidoActual);
        if (!agregado) {
            throw new GestionException("No se pudo agregar al pedido");
        }
        crearProductoPedido();
        return agregado;
    }

    @Override
    public boolean terminarPedido() throws GestionException {
        if (pedido == null) {
            throw new GestionException("Error al terminar el pedido");
        }
        pedido.setTerminado(true);
        System.out.println("Pedido terminado con éxito");
        return true;
    }

    @Override
    public double calcularCosto() {
        double costo = (productoPedidoActual.getProducto().getPrecio() + productoPedidoActual.getTamanio().getPrecioAdicional()) * productoPedidoActual.getCantidad();
        productoPedidoActual.setCosto(costo);
        return productoPedidoActual.getCosto();
    }

    @Override
    public double calcularTotal() {
        double total = 0;

        for (ProductoPedidoDTO productoPedido : pedido.getProductos()) {
            total += productoPedido.getCosto();
        }
        pedido.setCostoTotal(total);
        return total;
    }

    @Override
    public double calcularCambio(EfectivoDTO efectivo) {
        if (efectivo.getCantidadIngresada() >= pedido.getCostoTotal()) {
            double cambio = efectivo.getCantidadIngresada() - pedido.getCostoTotal();
            this.pedido.getPagoDTO().getEfectivoDTO().setCantidadIngresada(efectivo.getCantidadIngresada());
            this.pedido.getPagoDTO().getCambioDTO().setCambio(cambio);
            return cambio;
        } else {
            return -1;
        }
    }

    @Override
    public void cancelarProductoPedido(ProductoPedidoDTO productoPedido) {
        pedido.getProductos().remove(productoPedido);
    }

    @Override
    public void imprimirPedidoConsola() {
        System.out.println("########################");
        System.out.println("pedido");
        System.out.println(pedido.toString());
        System.out.println("----------------------");
        System.out.println("producto pedido actual");
        System.out.println(productoPedidoActual);
    }

    @Override
    public double actualizarTotal() {
        double total = 0;

        for (ProductoPedidoDTO productoPedido : pedido.getProductos()) {
            double costoProducto = (productoPedido.getProducto().getPrecio() + productoPedido.getTamanio().getPrecioAdicional()) * productoPedido.getCantidad();
            productoPedido.setCosto(costoProducto);
            total += costoProducto;
        }

        pedido.setCostoTotal(total);
        return total;
    }

    @Override
    public PedidoDTO registrarPedido() throws GestionException {
        try {
            pedidoBO.registrarPedido(pedido);
            return pedido;
        } catch (NegocioException ex) {
            Logger.getLogger(ManejadorPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedido;
    }

    @Override
    public UsuarioDTO comprobarInicioSesion(InicioSesionDTO inicioSesionDTO) throws GestionException {
        try {
            return usuarioBO.comprobarInicioSesion(inicioSesionDTO);
        } catch (NegocioException ex) {
            throw new GestionException("Los datos de inicio de sesion son incorrectos", ex);
        }
    }

    @Override
    public void agregarUsuarioAlPedido(String idUsuario) {
        this.pedido.setIdUsuario(idUsuario);
    }

    @Override
    public List<PedidoDTO> obtenerPedidosDelivery() throws GestionException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarEstado(String idPedido) throws GestionException {
        try {
            pedidoBO.actualizarEstado(idPedido);
        } catch (NegocioException ex) {
            throw new GestionException("Error al actualizar el estado del pedido", ex);
        }
    }
}
