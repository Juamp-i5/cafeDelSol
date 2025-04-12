/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import entidades.Producto;
import exception.persistenciaException;
import interfaces.IProducto;
import java.util.List;

/**
 *
 * @author rodri
 */
public class ProductoDAOImp implements IProducto{

    @Override
    public List<Producto> buscarTodos() throws persistenciaException {
        return List.of(
                new Producto(1L, "Affogato", 50, "../img/affogato.jpg"),
                new Producto(2L, "Café Americano", 40, "../img/cafeAmericano.jpg"),
                new Producto(3L, "Café Descafeinado", 30, "../img/cafeDescafeinado.jpg"),
                new Producto(4L, "Capuchino", 50, "../img/capuchino.jpg"),
                new Producto(5L, "Caramel Macchiato", 50, "../img/caramelMacchiato.jpg"),
                new Producto(6L, "Chocolate caliente", 50, "../img/chocolateCaliente.jpg"),
                new Producto(7L, "Espresso", 50, "../img/espresso.jpg"),
                new Producto(8L, "Flat White", 50, "../img/flatWhite.jpg"),
                new Producto(9L, "Frappe frío", 45, "../img/latteFrio.jpeg"),
                new Producto(10L, "Frappuccino", 45, "../img/frappuccino.jpg"),
                new Producto(11L, "Latte", 55, "../img/latte.jpg"),
                new Producto(12L, "Matcha Latte", 50, "../img/matchaLatte.jpg"),
                new Producto(13L, "Mocaccino", 30, "../img/mocaccino.jpg"),
                new Producto(14L, "Té Chai", 45, "../img/teChai.jpg"),
                new Producto(15L, "Té Negro", 20, "../img/teNegro.jpg")
        );
        
        
    }
    
}
