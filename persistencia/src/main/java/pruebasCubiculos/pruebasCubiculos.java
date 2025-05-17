/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebasCubiculos;

import DAOsMongo.FabricaDAOsMongo;
import IDAOs.IFabricaDAOs;
import IDAOs.cubiculos.ICubiculoDAO;
import entidades.Cubiculo;
import java.util.List;

/**
 *
 * @author rodri
 */
public class pruebasCubiculos {
    public static void main(String[] args) {
        // Obtener fábrica y DAO
        IFabricaDAOs fabrica = new FabricaDAOsMongo();
        ICubiculoDAO cubiculoDAO = fabrica.getCubiculoDAO();

        try {
            // Obtener cubículos desde la base de datos
            List<Cubiculo> cubiculos = cubiculoDAO.obtenerCubiculos();

            // Mostrar resultados
            System.out.println("Cubículos disponibles:");
            for (Cubiculo cubiculo : cubiculos) {
                System.out.println("- " + cubiculo.getNombre() + " | $" + cubiculo.getPrecioHora() + " por hora");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al obtener los cubículos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
