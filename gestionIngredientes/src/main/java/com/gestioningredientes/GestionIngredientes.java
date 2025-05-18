/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.gestioningredientes;

import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import excepciones.GestionCRUDIngredientesException;
import gestionIngredientes.GestorCRUDIngredientes;
import java.util.List;

/**
 *
 * @author norma
 */
public class GestionIngredientes {

    public static void main(String[] args) {
        GestorCRUDIngredientes gestor = GestorCRUDIngredientes.getInstance();

        // Definir filtros (puedes cambiar estos valores para probar diferentes escenarios)
        String filtroNombre = ""; // ejemplo: buscar por nombre
        String filtroNivelStock = ""; // ejemplo: nivel de stock bajo

        try {
            // Llamar al método y obtener resultados
            List<IngredienteViejoListDTO> ingredientes = gestor.buscarIngredientesPorFiltros(filtroNombre, filtroNivelStock);

            // Imprimir resultados
            if (ingredientes != null && !ingredientes.isEmpty()) {
                for (IngredienteViejoListDTO ingrediente : ingredientes) {
                    System.out.println(ingrediente); // Asegúrate de que IngredienteViejoListDTO tenga un buen toString()
                }
            } else {
                System.out.println("No se encontraron ingredientes con esos filtros.");
            }
        } catch (GestionCRUDIngredientesException e) {
            System.out.println("Error al buscar ingredientes: " + e.getMessage());
        }
    }
}

