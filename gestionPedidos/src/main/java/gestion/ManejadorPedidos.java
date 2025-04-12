/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion;

import DTOs.DetallesCobroTarjetaDTO;
import DTOs.EfectivoDTO;
import DTOs.PedidoDTO;
import DTOs.ProductoMostrarDTO;
import DTOs.ProductoPedidoDTO;
import DTOs.SaboresMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.ToppingsMostrarDTO;
import DTOs.TarjetaDTO;
import exception.GestionException;
import interfaces.IFachadaPago;
import java.util.List;
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

    private static final Logger LOG = Logger.getLogger(ManejadorPedidos.class.getName());
    private PedidoDTO pedido;
    private ProductoPedidoDTO productoPedidoActual;
    private IFachadaPago fachadaPago;

    public ManejadorPedidos() {
        this.fachadaPago = new FachadaPago(new PagoTarjetaAPI(), new ValidarPago());
    }
    
     @Override
    public boolean validarTarjetaPresentacion(TarjetaDTO tarjetaDTO) throws GestionException {
        DetallesCobroTarjetaDTO detalles = new DetallesCobroTarjetaDTO();
        detalles.setNumeroTarjeta(tarjetaDTO.getNumTarjeta());
        detalles.setNombreBanco(tarjetaDTO.getNombreBanco());
        detalles.setCvv(tarjetaDTO.getCVV());
        detalles.setFechaExp(tarjetaDTO.getFechaExp());

        ResultadoPago resultado = fachadaPago.procesarPago(detalles);

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
    public List<ProductoMostrarDTO> cargarProductos() {
        return List.of(
                new ProductoMostrarDTO("Affogato", 50, "../img/affogato.jpg"),
                new ProductoMostrarDTO("Café Americano", 40, "../img/cafeAmericano.jpg"),
                new ProductoMostrarDTO("Café Descafeinado", 30, "../img/cafeDescafeinado.jpg"),
                new ProductoMostrarDTO("Capuchino", 50, "../img/capuchino.jpg"),
                new ProductoMostrarDTO("Caramel Macchiato", 50, "../img/caramelMacchiato.jpg"),
                new ProductoMostrarDTO("Chocolate caliente", 50, "../img/chocolateCaliente.jpg"),
                new ProductoMostrarDTO("Espresso", 50, "../img/espresso.jpg"),
                new ProductoMostrarDTO("Flat White", 50, "../img/flatWhite.jpg"),
                new ProductoMostrarDTO("Frappe frío", 45, "../img/latteFrio.jpeg"),
                new ProductoMostrarDTO("Frappuccino", 45, "../img/frappuccino.jpg"),
                new ProductoMostrarDTO("Latte", 55, "../img/latte.jpg"),
                new ProductoMostrarDTO("Matcha Latte", 50, "../img/matchaLatte.jpg"),
                new ProductoMostrarDTO("Mocaccino", 30, "../img/mocaccino.jpg"),
                new ProductoMostrarDTO("Té Chai", 45, "../img/teChai.jpg"),
                new ProductoMostrarDTO("Té Negro", 20, "../img/teNegro.jpg")
        );
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
    public List<TamanioMostrarDTO> cargarTamanios() {
        return List.of(
                new TamanioMostrarDTO("Pequenio", "../img/tamanioPequenio.jpg", 0),
                new TamanioMostrarDTO("Mediano", "../img/tamanioMediano.jpg", 5),
                new TamanioMostrarDTO("Grande", "../img/tamanioGrande.jpg", 10)
        );
    }

    @Override
    public void agregarTamanio(TamanioMostrarDTO tamanio) {
        productoPedidoActual.setTamanio(tamanio);

    }

    @Override
    public List<SaboresMostrarDTO> cargarSabores() {
        return List.of(
                new SaboresMostrarDTO("Vainilla", "../img/saborVainilla.jpg"),
                new SaboresMostrarDTO("Chocolate", "../img/saborChocolate.jpeg"),
                new SaboresMostrarDTO("Moka", "../img/saborMoka.jpg"),
                new SaboresMostrarDTO("Fresa", "../img/saborFresa.jpg"),
                new SaboresMostrarDTO("Oreo", "../img/saborOreo.jpg"),
                new SaboresMostrarDTO("Caramelo", "../img/saborCaramelo.jpg")
        );
    }

    @Override
    public void agregarSabor(SaboresMostrarDTO sabor) {
        productoPedidoActual.setSabor(sabor);
    }

    @Override
    public List<ToppingsMostrarDTO> cargarToppings() {
        return List.of(
                new ToppingsMostrarDTO("Azúcar", "../img/azucar.jpeg"),
                new ToppingsMostrarDTO("Canela", "../img/canela.jpg"),
                new ToppingsMostrarDTO("Nutella", "../img/nutella.jpg"),
                new ToppingsMostrarDTO("Cajeta", "../img/cajeta.jpg")
        );
    }

    @Override
    public void agregarTopping(ToppingsMostrarDTO topping) {
        productoPedidoActual.setTopping(topping);
    }

//    @Override
//    public boolean validarTarjetaPresentacion(TarjetaDTO tarjeta) throws GestionException {
//        String numeroTarjeta = tarjeta.getNumTarjeta();
//        String banco = tarjeta.getNombreBanco();
//        String cvv = tarjeta.getCVV();
//        String fechaExp = tarjeta.getFechaExp();
//
//        if (banco == null || banco.trim().isEmpty()
//                || numeroTarjeta == null || numeroTarjeta.trim().isEmpty()
//                || cvv == null || cvv.trim().isEmpty()
//                || fechaExp == null) {
//            throw new GestionException("Se tiene que llenar todos los campos.");
//        }
//
//        if (tarjeta == null) {
//            throw new GestionException("La tarjeta no puede ser nula.");
//        }
//
//        if (!numeroTarjeta.matches("\\d{16}")) {
//            throw new GestionException("Número de tarjeta inválido. Tiene que tener 16 dígitos.");
//        }
//
//        if (!cvv.matches("\\d{3,4}")) {
//            throw new GestionException("CVV inválido. Tiene que tener 3 o 4 dígitos.");
//        }
//        if (!fechaExp.matches("\\d{2}/\\d{2}")) {
//            throw new GestionException("Fecha de expiración inválida. Tiene que tener formato MM/YY.");
//        }
//        return true;
//    }

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
        boolean agregado = pedido.getPedido().add(productoPedidoActual);
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
        double costo = (productoPedidoActual.getProducto().getPrecio() + productoPedidoActual.getTamanio().getPrecio()) * productoPedidoActual.getCantidad();
        productoPedidoActual.setCosto(costo);
        return productoPedidoActual.getCosto();
    }

    @Override
    public double calcularTotal() {
        double total = 0;

        for (ProductoPedidoDTO productoPedido : pedido.getPedido()) {
            total += productoPedido.getCosto();
        }
        pedido.setCostoTotal(total);
        return total;
    }

    @Override
    public double calcularCambio(EfectivoDTO efectivo) {
        if (efectivo.getCantidadIngresada() >= pedido.getCostoTotal()) {
            return efectivo.getCantidadIngresada() - pedido.getCostoTotal();
        } else {
            return -1;
        }
    }

    @Override
    public void cancelarProductoPedido(ProductoPedidoDTO productoPedido) {
        pedido.getPedido().remove(productoPedido);
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

        for (ProductoPedidoDTO productoPedido : pedido.getPedido()) {
            double costoProducto = (productoPedido.getProducto().getPrecio() + productoPedido.getTamanio().getPrecio()) * productoPedido.getCantidad();
            productoPedido.setCosto(costoProducto);
            total += costoProducto;
        }

        pedido.setCostoTotal(total);
        return total;
    }

    //Ahoria lo hago, tiene que conectarse con su BO de Pedido
    @Override
    public PedidoDTO registrarPedido() {
        return pedido;
    }
}
