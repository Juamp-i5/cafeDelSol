/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import DAOsMongo.UsuarioDAOMongo;
import DTOs.PersistenciaSaborDTO;
import DTOs.PersistenciaTamanioDTO;
import DTOs.PersistenciaToppingDTO;
import DTOs.PersistenciaUsuarioDTO;
import IDAOs.ISaborDAO;
import IDAOs.ITamanioDAO;
import IDAOs.IToppingDAO;
import IDAOs.IUsuarioDAO;
import conexion.ConexionMongo;
import excepciones.PersistenciaException;
import java.io.IOException;
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
        ConexionMongo conexionMongo = ConexionMongo.getInstance();

        insertarUsuarios(UsuarioDAOMongo.getInstancia(conexionMongo));
//        insertarTamanios(TamanioDAOMongo.getInstance(conexionMongo));
//        insertarSabores(SaborDAOMongo.getInstance(conexionMongo));
//        insertarToppings(ToppingDAOMongo.getInstance(conexionMongo));

    }

    private static void insertarUsuarios(IUsuarioDAO usuarioDAO) throws PersistenciaException {
        PersistenciaUsuarioDTO adminDTO = new PersistenciaUsuarioDTO();
        adminDTO.setNombresPila("Denise");
        adminDTO.setApellidoPaterno("Garcia");
        adminDTO.setApellidoMaterno("Acosta");
        adminDTO.setTipoEmpleado("Administrador");
        adminDTO.setUsuario("admin01");
        adminDTO.setContrasenia("12345678".toCharArray());

        PersistenciaUsuarioDTO baristaDTO = new PersistenciaUsuarioDTO();
        baristaDTO.setNombresPila("Laura");
        baristaDTO.setApellidoPaterno("Gomez");
        baristaDTO.setApellidoMaterno("Fernandez");
        baristaDTO.setTipoEmpleado("Barista");
        baristaDTO.setUsuario("barista01");
        baristaDTO.setContrasenia("12345678".toCharArray());

        usuarioDAO.agregarUsuario(adminDTO);
        usuarioDAO.agregarUsuario(baristaDTO);
    }

    private static void insertarTamanios(ITamanioDAO tamanioDAO) throws PersistenciaException {
        System.out.println("Iniciando inserción de Tamaños base (DTOs)...");
        List<PersistenciaTamanioDTO> tamanios = Arrays.asList(
                new PersistenciaTamanioDTO(new ObjectId().toHexString(), "CHICO", 0.0, cargarImagenDesdeRecursos("tamanioPequenio.jpg")),
                new PersistenciaTamanioDTO(new ObjectId().toHexString(), "MEDIANO", 20.0, cargarImagenDesdeRecursos("tamanioMediano.jpg")),
                new PersistenciaTamanioDTO(new ObjectId().toHexString(), "GRANDE", 40.0, cargarImagenDesdeRecursos("tamanioGrande.jpg"))
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
