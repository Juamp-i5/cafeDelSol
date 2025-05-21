/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import DAOsMongo.FabricaDAOsMongo;
import DTOs.cubiculos.CubiculoCompletoDTOPersistencia;
import DTOs.cubiculos.ReservacionDTOCompletaPersistencia;
import IDAOs.IFabricaDAOs;
import IDAOs.cubiculos.IContadorReservaciones;
import IDAOs.cubiculos.ICubiculoDAO;
import IDAOs.cubiculos.IReservacionDAO;
import entidades.Cubiculo;
import java.time.LocalDate;
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
        IContadorReservaciones contadorDAO = fabrica.getContadorReservaciones();
        IReservacionDAO reservacionDAO = fabrica.getReservacionDAO();

        try {
            // Obtener cubículos desde la base de datos
            List<String> cubiculos = cubiculoDAO.obtenerCubiculos();

            // Mostrar resultados
            System.out.println("Cubículos disponibles:");
            for (String cubiculo : cubiculos) {
                System.out.println(cubiculo);
            }

            System.out.println("-----");

            LocalDate fechaInicio = LocalDate.of(2025, 5, 1);  // 1 de mayo de 2023
            LocalDate fechaFin = LocalDate.of(2025, 5, 30);    // 31 de mayo de 2023

            List<ReservacionDTOCompletaPersistencia> dtoList = reservacionDAO.buscarPendientesPorRangoFechas(null, null);
            for (ReservacionDTOCompletaPersistencia reservacionDTOCompletaPersistencia : dtoList) {
                System.out.println(reservacionDTOCompletaPersistencia);
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error al obtener los cubículos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
