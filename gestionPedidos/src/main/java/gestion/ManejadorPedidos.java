/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion;

import DTOs.EfectivoDTO;
import DTOs.PedidoDTO;
import DTOs.ProductoMostrarDTO;
import DTOs.ProductoPedidoDTO;
import DTOs.SaboresMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.ToppingsMostrarDTO;
import DTOs.TarjetaDTO;
import java.util.List;

/**
 *
 * @author Jp
 */
public class ManejadorPedidos implements IGestionPedidos {

    private PedidoDTO pedido;
    private ProductoPedidoDTO productoPedidoActual;

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
                new ProductoMostrarDTO(1L, "Affogato", 50, "../img/affogato.jpg"),
                new ProductoMostrarDTO(2L, "Café Americano", 40, "../img/cafeAmericano.jpg"),
                new ProductoMostrarDTO(3L, "Café Descafeinado", 30, "../img/cafeDescafeinado.jpg"),
                new ProductoMostrarDTO(4L, "Capuchino", 50, "../img/capuchino.jpg"),
                new ProductoMostrarDTO(5L, "Caramel Macchiato", 50, "../img/caramelMacchiato.jpg"),
                new ProductoMostrarDTO(6L, "Chocolate caliente", 50, "../img/chocolateCaliente.jpg"),
                new ProductoMostrarDTO(7L, "Espresso", 50, "../img/espresso.jpg"),
                new ProductoMostrarDTO(8L, "Flat White", 50, "../img/flatWhite.jpg"),
                new ProductoMostrarDTO(9L, "Frappe frío", 45, "../img/latteFrio.jpeg"),
                new ProductoMostrarDTO(10L, "Frappuccino", 45, "../img/frappuccino.jpg"),
                new ProductoMostrarDTO(11L, "Latte", 55, "../img/latte.jpg"),
                new ProductoMostrarDTO(12L, "Matcha Latte", 50, "../img/matchaLatte.jpg"),
                new ProductoMostrarDTO(13L, "Mocaccino", 30, "../img/mocaccino.jpg"),
                new ProductoMostrarDTO(14L, "Té Chai", 45, "../img/teChai.jpg"),
                new ProductoMostrarDTO(15L, "Té Negro", 20, "../img/teNegro.jpg")
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
                new TamanioMostrarDTO(1L, "Pequenio", "../img/tamanioPequenio.jpg", 0),
                new TamanioMostrarDTO(2L, "Mediano", "../img/tamanioMediano.jpg", 5),
                new TamanioMostrarDTO(3L, "Grande", "../img/tamanioGrande.jpg", 10)
        );
    }

    @Override
    public void agregarTamanio(TamanioMostrarDTO tamanio) {
        productoPedidoActual.setTamanio(tamanio);
    }

    @Override
    public List<SaboresMostrarDTO> cargarSabores() {
        return List.of(
                new SaboresMostrarDTO(1L, "Vainilla", "../img/saborVainilla.jpg"),
                new SaboresMostrarDTO(2L, "Chocolate", "../img/saborChocolate.jpeg"),
                new SaboresMostrarDTO(3L, "Moka", "../img/saborMoka.jpg"),
                new SaboresMostrarDTO(4L, "Fresa", "../img/saborFresa.jpg"),
                new SaboresMostrarDTO(5L, "Oreo", "../img/saborOreo.jpg"),
                new SaboresMostrarDTO(6L, "Caramelo", "../img/saborCaramelo.jpg")
        );
    }

    @Override
    public void agregarSabor(SaboresMostrarDTO sabor) {
        productoPedidoActual.setSabor(sabor);
    }

    @Override
    public List<ToppingsMostrarDTO> cargarToppings() {
        return List.of(
                new ToppingsMostrarDTO(1L, "Azúcar", "../img/azucar.jpeg"),
                new ToppingsMostrarDTO(2L, "Canela", "../img/canela.jpg"),
                new ToppingsMostrarDTO(3L, "Nutella", "../img/nutella.jpg"),
                new ToppingsMostrarDTO(4L, "Cajeta", "../img/cajeta.jpg")
        );
    }

    @Override
    public void agregarTopping(ToppingsMostrarDTO topping) {
        productoPedidoActual.setTopping(topping);
    }

    @Override
    public boolean validarTarjetaPresentacion(TarjetaDTO tarjeta) {
        String numeroTarjeta = tarjeta.getNumTarjeta();
        String banco = tarjeta.getNombreBanco();
        String cvv = tarjeta.getCVV();
        String fechaExp = tarjeta.getFechaExp();

        if (banco == null || banco.trim().isEmpty()
                || numeroTarjeta == null || numeroTarjeta.trim().isEmpty()
                || cvv == null || cvv.trim().isEmpty()
                || fechaExp == null) {
            throw new IllegalArgumentException("Se tiene que llenar todos los campos.");
        }

        if (tarjeta == null) {
            throw new IllegalArgumentException("La tarjeta no puede ser nula.");
        }

        if (!numeroTarjeta.matches("\\d{16}")) {
            throw new IllegalArgumentException("Número de tarjeta inválido. Tiene que tener 16 dígitos.");
        }

        if (!cvv.matches("\\d{3,4}")) {
            throw new IllegalArgumentException("CVV inválido. Tiene que tener 3 o 4 dígitos.");
        }
        if (!fechaExp.matches("\\d{2}/\\d{2}")) {
            throw new IllegalArgumentException("Fecha de expiración inválida. Tiene que tener formato MM/YY.");
        }
        return true;
    }

    @Override
    public boolean cancelarPedido(PedidoDTO pedido) {

        if (productoPedidoActual != null) {
            productoPedidoActual = null;
        }

        return true;
    }

    @Override
    public boolean agregarProductoPedidoAPedido() {
        boolean agregado = pedido.getPedido().add(productoPedidoActual);
        if (agregado) {
            crearProductoPedido();
        }
        return agregado;
    }

    @Override
    public boolean terminarPedido() {
        if (pedido != null) {
            pedido.setTerminado(true);
            System.out.println("Pedido terminado con éxito");
            return true;
        }
        return false;
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
}
