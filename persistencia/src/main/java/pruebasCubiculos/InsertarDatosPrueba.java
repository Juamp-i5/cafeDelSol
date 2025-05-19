/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebasCubiculos;

import DAOsMongo.SaborDAOMongo;
import DAOsMongo.TamanioDAOMongo;
import DAOsMongo.ToppingDAOMongo;
import DTOs.PersistenciaSaborDTO;
import DTOs.PersistenciaTamanioDTO;
import DTOs.PersistenciaToppingDTO;
import IDAOs.ISaborDAO;
import IDAOs.ITamanioDAO;
import IDAOs.IToppingDAO;
import conexion.ConexionMongo;
import excepciones.PersistenciaException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jp
 */
public class InsertarDatosPrueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistenciaException {
        insertarTamanios(TamanioDAOMongo.getInstance(ConexionMongo.getInstance()));
        insertarSabores(SaborDAOMongo.getInstance(ConexionMongo.getInstance()));
        insertarToppings(ToppingDAOMongo.getInstance(ConexionMongo.getInstance()));
    }

    private static void insertarTamanios(ITamanioDAO tamanioDAO) throws PersistenciaException {
        System.out.println("Iniciando inserción de Tamaños base (DTOs)...");
        List<PersistenciaTamanioDTO> tamanios = Arrays.asList(
                new PersistenciaTamanioDTO(new ObjectId().toHexString(), "PEQUEÑO", 0, cargarImagenDesdeRecursos("tamanioPequenio.jpg")),
                new PersistenciaTamanioDTO(new ObjectId().toHexString(), "MEDIANO", 20, cargarImagenDesdeRecursos("tamanioMediano.jpg")),
                new PersistenciaTamanioDTO(new ObjectId().toHexString(), "GRANDE", 40, cargarImagenDesdeRecursos("tamanioGrande.jpg"))
        );

        for (PersistenciaTamanioDTO tamanio : tamanios) {
            tamanioDAO.guardar(tamanio);
            System.out.println("Tamaño DTO guardado: " + tamanio.getNombre());
        }
        System.out.println("Inserción de Tamaños base (DTOs) finalizada.");
    }

    private static void insertarSabores(ISaborDAO saborDAO) throws PersistenciaException {
        System.out.println("Iniciando inserción de Sabores base (DTOs)...");
        List<PersistenciaSaborDTO> sabores = Arrays.asList(
                new PersistenciaSaborDTO(new ObjectId().toHexString(), "Vainilla", cargarImagenDesdeRecursos("saborVainilla.jpg")),
                new PersistenciaSaborDTO(new ObjectId().toHexString(), "Chocolate", cargarImagenDesdeRecursos("saborChocolate.jpeg")),
                new PersistenciaSaborDTO(new ObjectId().toHexString(), "Moka", cargarImagenDesdeRecursos("saborMoka.jpg")),
                new PersistenciaSaborDTO(new ObjectId().toHexString(), "Fresa", cargarImagenDesdeRecursos("saborFresa.jpg")),
                new PersistenciaSaborDTO(new ObjectId().toHexString(), "Oreo", cargarImagenDesdeRecursos("saborOreo.jpg")),
                new PersistenciaSaborDTO(new ObjectId().toHexString(), "Caramelo", cargarImagenDesdeRecursos("saborCaramelo.jpg"))
        );

        for (PersistenciaSaborDTO sabor : sabores) {
            saborDAO.guardar(sabor);
            System.out.println("Sabor DTO guardado: " + sabor.getNombre());
        }
        System.out.println("Inserción de Sabores base (DTOs) finalizada.");
    }

    private static void insertarToppings(IToppingDAO toppingDAO) throws PersistenciaException {
        System.out.println("Iniciando inserción de Toppings base (DTOs)...");
        List<PersistenciaToppingDTO> toppings = Arrays.asList(
                new PersistenciaToppingDTO(new ObjectId().toHexString(), "Azúcar", cargarImagenDesdeRecursos("azucar.jpeg")),
                new PersistenciaToppingDTO(new ObjectId().toHexString(), "Canela", cargarImagenDesdeRecursos("canela.jpg")),
                new PersistenciaToppingDTO(new ObjectId().toHexString(), "Nutella", cargarImagenDesdeRecursos("nutella.jpg")),
                new PersistenciaToppingDTO(new ObjectId().toHexString(), "Cajeta", cargarImagenDesdeRecursos("cajeta.jpg"))
        );

        for (PersistenciaToppingDTO topping : toppings) {
            toppingDAO.guardar(topping);
            System.out.println("Topping DTO guardado: " + topping.getNombre());
        }
        System.out.println("Inserción de Toppings base (DTOs) finalizada.");
    }

    private static byte[] cargarImagenDesdeRecursos(String nombreArchivoImagen) {
        if (nombreArchivoImagen == null || nombreArchivoImagen.trim().isEmpty()) {
            System.err.println("Error: Nombre de archivo de imagen es nulo o vacío.");
            return null;
        }

        String rutaRelativa = "../img/" + nombreArchivoImagen;

        try {
            Path imagePath = Paths.get(rutaRelativa).toAbsolutePath();

            if (Files.exists(imagePath) && Files.isReadable(imagePath)) {
                byte[] data = Files.readAllBytes(imagePath);
                return data;
            } else {
                System.err.println("Error: El archivo de imagen NO EXISTE o NO SE PUEDE LEER en la ruta: " + imagePath);
                return null;
            }
        } catch (IOException e) {
            System.err.println("IOException al leer el archivo de imagen '" + nombreArchivoImagen + "' desde '" + rutaRelativa + "': " + e.getMessage());
            return null;
        } catch (InvalidPathException e) {
            System.err.println("InvalidPathException para la ruta: '" + rutaRelativa + "': " + e.getMessage());
            return null;
        }
    }
}
