/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion;

import DTOs.PedidoDTO;
import DTOs.ProductoMostrarDTO;
import DTOs.ProductoPedidoDTO;
import DTOs.SaboresMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.ToppingsMostrarDTO;
import DTOs.TarjetaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jp
 */
public class ManejadorPedidos implements IGestionPedidos {
    
    List<ProductoPedidoDTO> pedidos = new ArrayList<>();
    private PedidoDTO pedido;
    private ProductoPedidoDTO productoPedido;

    @Override
    public List<ProductoMostrarDTO> cargarProductos() {
        return List.of(
                new ProductoMostrarDTO(1L, "Frappe frío", "../img/latteFrio.jpeg"),
                new ProductoMostrarDTO(2L, "Chocolate caliente", "../img/chocolateCaliente.jpeg"),
                new ProductoMostrarDTO(3L, "Café Americano", "../img/cafeAmericano.jpeg"),
                new ProductoMostrarDTO(4L, "Latte", "../img/latte.jpeg"),
                new ProductoMostrarDTO(5L, "Capuchino", "../img/capuchino.jpeg"),
                new ProductoMostrarDTO(6L, "Espresso", "../img/espresso.jpeg"),
                new ProductoMostrarDTO(7L, "Té Chai", "../img/teChai.jpeg"),
                new ProductoMostrarDTO(8L, "Matcha Latte", "../img/matchaLatte.jpeg"),
                new ProductoMostrarDTO(9L, "Mocaccino", "../img/mocaccino.jpeg"),
                new ProductoMostrarDTO(10L, "Té Negro", "../img/teNegro.jpeg"),
                new ProductoMostrarDTO(11L, "Café Descafeinado", "../img/cafeDescafeinado.jpeg"),
                new ProductoMostrarDTO(12L, "Affogato", "../img/affogato.jpeg"),
                new ProductoMostrarDTO(13L, "Flat White", "../img/flatWhite.jpeg"),
                new ProductoMostrarDTO(14L, "Caramel Macchiato", "../img/caramelMacchiato.jpeg"),
                new ProductoMostrarDTO(15L, "Frappuccino", "../img/frappuccino.jpeg")
        );
    }

    @Override
    public PedidoDTO getPedido() {
        return pedido;
    }

    @Override
    public ProductoPedidoDTO getProductoPedido() {
        return productoPedido;
    }

    @Override 
    public void agregarProducto(ProductoMostrarDTO producto) {
        productoPedido.setProducto(producto);
    }
    
    @Override
    public void crearPedido(){
        this.pedido = new PedidoDTO();
    }
    
    @Override
    public void crearProductoPedido(){
        if (pedido == null) {
            crearPedido();
        }
        if (productoPedido != null) {
            pedido.getPedido().add(productoPedido);
        }
        
        this.productoPedido = new ProductoPedidoDTO();
    }

    @Override
    public List<TamanioMostrarDTO> cargarTamanios() {
        return List.of(
                new TamanioMostrarDTO(1L, "Pequenio", "../img/tamanioPequenio.jpg", 0),
                new TamanioMostrarDTO(2L, "Mediano", "../img/tamanioMediano.jpg", 5),
                new TamanioMostrarDTO(3L, "Grande", "../img/tamanioGrande.jpg",10)
        );
    }
    
    @Override
    public void agregarTamanio(TamanioMostrarDTO tamanio) {
        productoPedido.setTamanio(tamanio);
    }

    @Override
    public List<SaboresMostrarDTO> cargarSabores() {
        return List.of(
                new SaboresMostrarDTO(1L, "Vainilla",  "../img/saborVainilla.jpg"),
                new SaboresMostrarDTO(2L, "Chocolate",  "../img/saborChocolate.jpeg"),
                new SaboresMostrarDTO(3L, "Moka",  "../img/saborMoka.jpg"),
                new SaboresMostrarDTO(4L, "Fresa",  "../img/saborFresa.jpg"),
                new SaboresMostrarDTO(5L, "Oreo",  "../img/saborOreo.jpg"),
                new SaboresMostrarDTO(6L, "Caramelo",  "../img/saborCaramelo.jpg")
        
        );
    }
    
    @Override
    public void agregarSabor(SaboresMostrarDTO sabor) {
        productoPedido.setSabor(sabor);
    }

    @Override
    public List<ToppingsMostrarDTO> cargarToppings() {
        return List.of(
                new ToppingsMostrarDTO(1L, "Azúcar",  "../img/azucar.jpeg"),
                new ToppingsMostrarDTO(2L, "Canela",  "../img/canela.jpg"),
                new ToppingsMostrarDTO(3L, "Nutella",  "../img/nutella.jpg"),
                new ToppingsMostrarDTO(4L, "Cajeta",  "../img/cajeta.jpg")     
        );
    }
    
    @Override
    public void agregarTopping(ToppingsMostrarDTO topping) {
        productoPedido.setTopping(topping);
    }

    @Override
    public boolean validarTarjetaPresentacion(TarjetaDTO tarjeta) {
        if (tarjeta == null) {
            return false; 
        }
        String numeroTarjeta = tarjeta.getNumTarjeta();
        if (numeroTarjeta == null || !numeroTarjeta.matches("\\d{16}")) {
            return false;
        }
        String banco = tarjeta.getNombreBanco();
        if (banco == null || banco.trim().isEmpty()) {
            return false;
        }
        String cvv = tarjeta.getCVV();
        if (cvv == null || !cvv.matches("\\d{3,4}")) {
            return false;
        }
        return true;       
    }
    
    @Override
    public boolean agregarProductoPedidoAPedido(ProductoPedidoDTO productoPedido) {
//        if (productoPedido == null) {
//            System.out.println("Error: No se puede agregar un pedido que es nulo");
//            return false;
//        }        
        return pedidos.add(productoPedido);   
    }
    
    @Override
    public boolean terminarPedido() {
//        if (pedidos==null) {
//            System.out.println("Error: No se puede terminar un pedido vacío");
//            return false;
//        }
        pedido.setTerminado(true);        
        System.out.println("Pedido terminado con éxito");
        pedidos.add(productoPedido);
        return true;
    }
}
