/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import interfaces.IConexion;
import entidades.Sabor;
import exception.persistenciaException;
import interfaces.ISabor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodri
 */
public class SaborDAOImp implements ISabor {

    private IConexion conexion;

    public SaborDAOImp(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Sabor> buscarTodos() throws persistenciaException {
        List<Sabor> sabores = new ArrayList<>();

        if (conexion.getDatabase() == null) {
            return List.of(
                    new Sabor(1L, "Vainilla", "../img/saborVainilla.jpg"),
                    new Sabor(2L, "Chocolate", "../img/saborChocolate.jpeg"),
                    new Sabor(3L, "Moka", "../img/saborMoka.jpg"),
                    new Sabor(4L, "Fresa", "../img/saborFresa.jpg"),
                    new Sabor(5L, "Oreo", "../img/saborOreo.jpg"),
                    new Sabor(6L, "Caramelo", "../img/saborCaramelo.jpg")
            );
        } else {
            // lógica de mongo
        }

        return sabores;
    }
}
