/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import interfaces.IConexion;
import entidades.Producto;
import exception.persistenciaException;
import interfaces.IProducto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodri
 */
public class ProductoDAOImp implements IProducto {

    IConexion conexion;

    public ProductoDAOImp(IConexion conexion) {
        this.conexion = conexion;
    }

    private static ProductoDAOImp instanceDAO;

    public ProductoDAOImp() {
    }

    public static ProductoDAOImp getInstance() {
        if (instanceDAO == null) {
            instanceDAO = new ProductoDAOImp();
        }
        return instanceDAO;
    }

    @Override
    public List<Producto> buscarTodos() throws persistenciaException {
        List<Producto> productos = new ArrayList<>();

        if (conexion.getDatabase() == null) {
            return List.of(
                    new Producto(1L, "Affogato", 50, "../img/affogato.jpg"),
                    new Producto(2L, "Café Americano", 40, "../img/cafeAmericano.jpg"),
                    new Producto(3L, "Café Descafeinado", 30, "../img/cafeDescafeinado.jpg"),
                    new Producto(4L, "Capuchino", 50, "../img/capuchino.jpg"),
                    new Producto(5L, "Caramel Macchiato", 50, "../img/caramelMacchiato.jpg")
            );
        } else {
            //lógica de mongo
        }

        return productos;
    }

    @Override
    public Producto buscarPorNombre(String nombre) throws persistenciaException {
        List<Producto> productos = buscarTodos(); // usa tu método existente

        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }

        return null; // si no lo encuentra
    }

}
