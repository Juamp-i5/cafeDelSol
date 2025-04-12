/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import interfaces.IConexion;
import entidades.Tamanio;
import exception.persistenciaException;
import interfaces.ITamanio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodri
 */
public class TamanioDAOImp implements ITamanio {

    private IConexion conexion;

    public TamanioDAOImp(IConexion conexion) {
        this.conexion = conexion;
    }
    
    private static TamanioDAOImp instanceDAO;

    public TamanioDAOImp() {
    }

    public static TamanioDAOImp getInstance() {
        if (instanceDAO == null) {
            instanceDAO = new TamanioDAOImp();
        }
        return instanceDAO;
    }

    @Override
    public List<Tamanio> buscarTodos() throws persistenciaException {
        List<Tamanio> tamanios = new ArrayList<>();

        if (conexion.getDatabase() == null) {
            return List.of(
                    new Tamanio(1L, "Pequenio", "../img/tamanioPequenio.jpg", 0),
                    new Tamanio(2L, "Mediano", "../img/tamanioMediano.jpg", 5),
                    new Tamanio(3L, "Grande", "../img/tamanioGrande.jpg", 10)
            );
        } else {
            // lógica de mongo
        }

        return tamanios;
    }

    @Override
    public Tamanio buscarPorNombre(String nombre) throws persistenciaException {
        List<Tamanio> tamanios = buscarTodos(); // usa tu método existente

        for (Tamanio p : tamanios) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }

        return null; // si no lo encuentra
    }
}
