/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion;

import DTOs.PedidoDTO;
import DTOs.ProductoMostrarDTO;
import DTOs.ProductoPedidoDTO;
import java.util.List;

/**
 *
 * @author Jp
 */
public class ManejadorPedidos implements IGestionPedidos {

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

    public void agregarProducto(ProductoMostrarDTO producto){
        productoPedido.setProducto(producto);
    }

}
