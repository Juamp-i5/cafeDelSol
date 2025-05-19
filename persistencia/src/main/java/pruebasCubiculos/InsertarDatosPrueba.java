/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebasCubiculos;

import DTOs.PersistenciaTamanioDTO;
import IDAOs.ITamanioDAO;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jp
 */
public class InsertarDatosPrueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    private static void insertarTamanios(ITamanioDAO tamanioDAO){
        byte[] imgPequenio = cargarImagenDesdeRecursos("tamanioPequenio.jpg");
        byte[] imgMediano = cargarImagenDesdeRecursos("tamanioPequenio.jpg");
        byte[] imgGrande = cargarImagenDesdeRecursos("tamanioPequenio.jpg");
        
        List<PersistenciaTamanioDTO> tamanios = Arrays.asList(
                new PersistenciaTamanioDTO(null, "PEQUEÑO", 0, imgPequenio)
        );
    }

    private static byte[] cargarImagenDesdeRecursos(String nombreArchivoImagen) {
        try {
            String rutaEnRecursos = "../img/" + nombreArchivoImagen;
            URL resourceUrl = InsertarDatosPrueba.class.getClassLoader().getResource(rutaEnRecursos);
            if (resourceUrl == null) {
                System.err.println("Error: No se pudo encontrar el archivo de imagen en recursos: " + rutaEnRecursos);
                return null;
            }
            Path imagePath = Paths.get(resourceUrl.toURI());
            if (Files.exists(imagePath) && Files.isReadable(imagePath)) {
                return Files.readAllBytes(imagePath);
            } else {
                System.err.println("Error: El archivo de imagen no existe o no se puede leer en la ruta: " + imagePath);
                return null;
            }
        } catch (IOException | URISyntaxException e) {
            System.err.println("Error al leer el archivo de imagen '" + nombreArchivoImagen + "': " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
