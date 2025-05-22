/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOsMongo;

import DTOs.PersistenciaUsuarioDTO;
import IDAOs.IUsuarioDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongoPrueba;
import conexion.IConexionMongo;
import entidades.Usuario;
import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

/**
 * Pruebas unitarias para UsuarioDAO utilizando una base de datos de prueba.
 *
 * @author norma
 */
public class UsuarioDAOMongoTest {

    private static MongoCollection<Usuario> coleccion;

    private static IConexionMongo conexionPrueba;
    private static IUsuarioDAO usuarioDAO;
    private static MongoDatabase database;

    @BeforeAll
    public static void setUpClase() throws Exception {
        conexionPrueba = ConexionMongoPrueba.getInstance();
        database = conexionPrueba.getDatabase();
        usuarioDAO = UsuarioDAOMongo.getInstancia(conexionPrueba);
        coleccion = conexionPrueba.getDatabase().getCollection("toppings", Usuario.class);
    }

    @BeforeEach
    public void setUp() {
        database.getCollection("usuarios", Usuario.class).deleteMany(new Document());
    }

    @AfterAll
    public static void tearDownClase() {
        if (database != null) {
            database.getCollection("usuarios", Usuario.class).deleteMany(new Document());
        }
        ConexionMongoPrueba.clearInstance();
    }

    /**
     * Test of buscarPorUsername method, of class UsuarioDAOMongo.
     */
    @Test
    public void testBuscarPorUsername() {
        PersistenciaUsuarioDTO usuarioDTO = new PersistenciaUsuarioDTO();
        usuarioDTO.setNombresPila("Norma");
        usuarioDTO.setApellidoPaterno("Beltran");
        usuarioDTO.setApellidoMaterno("M");
        usuarioDTO.setTipoEmpleado("Administrador");
        usuarioDTO.setUsuario("norma");
        usuarioDTO.setContrasenia("123".toCharArray());

        usuarioDAO.agregarUsuario(usuarioDTO);

        PersistenciaUsuarioDTO resultado = usuarioDAO.buscarPorUsername("norma");
        assertNotNull(resultado, "El usuario debería existir");
        assertEquals("norma", resultado.getUsuario(), "El username debe coincidir");
    }

    /**
     * Test of comprobarInicioSesion method, of class UsuarioDAOMongo.
     */
    @Test
    public void testComprobarInicioSesion() {
        PersistenciaUsuarioDTO usuarioDTO = new PersistenciaUsuarioDTO();
        usuarioDTO.setNombresPila("Norma");
        usuarioDTO.setApellidoPaterno("Beltran");
        usuarioDTO.setApellidoMaterno("M");
        usuarioDTO.setTipoEmpleado("Administrador");
        usuarioDTO.setUsuario("norma");
        usuarioDTO.setContrasenia("123".toCharArray());

        usuarioDAO.agregarUsuario(usuarioDTO);

        boolean sesionCorrecta = usuarioDAO.comprobarInicioSesion("norma", "123".toCharArray());
        assertTrue(sesionCorrecta, "La sesión debería ser correcta con la contraseña válida");

        boolean sesionIncorrecta = usuarioDAO.comprobarInicioSesion("norma", "noExiste".toCharArray());
        assertFalse(sesionIncorrecta, "La sesión debería fallar con contraseña incorrecta");

        boolean usuarioNoExiste = usuarioDAO.comprobarInicioSesion("noExiste", "123".toCharArray());
        assertFalse(usuarioNoExiste, "La sesión debería fallar si el usuario no existe");
    }

    /**
     * Test of agregarUsuario method, of class UsuarioDAOMongo.
     */
    @Test
    public void testAgregarUsuario() {
        PersistenciaUsuarioDTO usuarioDTO = new PersistenciaUsuarioDTO();
        usuarioDTO.setNombresPila("Norma");
        usuarioDTO.setApellidoPaterno("Beltran");
        usuarioDTO.setApellidoMaterno("M");
        usuarioDTO.setTipoEmpleado("Administrador");
        usuarioDTO.setUsuario("norma");
        usuarioDTO.setContrasenia("123".toCharArray());

        usuarioDAO.agregarUsuario(usuarioDTO);

        PersistenciaUsuarioDTO buscado = usuarioDAO.buscarPorUsername("norma");
        assertNotNull(buscado, "El usuario debe haberse agregado y encontrado");
        assertEquals("norma", buscado.getUsuario(), "El username debe coincidir");
    }

    /**
     * Test of usernameDisponible method, of class UsuarioDAOMongo.
     */
    @Test
    public void testUsernameDisponible() {
        assertTrue(usuarioDAO.usernameDisponible("norma"), "El username debería estar disponible inicialmente");

        PersistenciaUsuarioDTO usuarioDTO = new PersistenciaUsuarioDTO();
        usuarioDTO.setNombresPila("Norma");
        usuarioDTO.setApellidoPaterno("Beltran");
        usuarioDTO.setApellidoMaterno("M");
        usuarioDTO.setTipoEmpleado("Administrador");
        usuarioDTO.setUsuario("norma");
        usuarioDTO.setContrasenia("123".toCharArray());

        usuarioDAO.agregarUsuario(usuarioDTO);

        assertFalse(usuarioDAO.usernameDisponible("norma"), "El username ya no debería estar disponible después de agregarlo");
    }

}
