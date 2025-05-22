/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOsMongo;

import DTOs.PersistenciaSaborDTO;
import IDAOs.ISaborDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongoPrueba;
import conexion.IConexionMongo;
import entidades.Sabor;
import java.util.List;
import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeAll;

/**
 * Pruebas unitarias para SaborDAO utilizando una base de datos de
 * prueba.
 * @author norma
 */
public class SaborDAOMongoTest {

    private static MongoCollection<Sabor> coleccion;

    private static IConexionMongo conexionPrueba;
    private static ISaborDAO saborDAO;
    private static MongoDatabase database;

    @BeforeAll
    public static void setUpClase() throws Exception {
        conexionPrueba = ConexionMongoPrueba.getInstance();
        database = conexionPrueba.getDatabase();
        saborDAO = SaborDAOMongo.getInstance(conexionPrueba);
        coleccion = conexionPrueba.getDatabase().getCollection("sabores", Sabor.class);
    }

    @BeforeEach
    public void setUp() {
        database.getCollection("sabores", Sabor.class).deleteMany(new Document());
    }

    @AfterAll
    public static void tearDownClase() {
        if (database != null) {
            database.getCollection("sabores", Sabor.class).deleteMany(new Document());
        }
        ConexionMongoPrueba.clearInstance();
    }

    /**
     * Test of buscarTodos method, of class SaborDAOMongo.
     */
    @Test
    public void testBuscarTodos() throws Exception {
        System.out.println("buscarTodos");

        PersistenciaSaborDTO sabor1 = new PersistenciaSaborDTO();
        sabor1.setNombre("Mango");

        PersistenciaSaborDTO sabor2 = new PersistenciaSaborDTO();
        sabor2.setNombre("Coco");

        saborDAO.guardar(sabor1);
        saborDAO.guardar(sabor2);

        List<PersistenciaSaborDTO> result = saborDAO.buscarTodos();

        assertNotNull( result, "La lista no debe ser null");
        assertEquals( 2, result.size(), "Debe haber 2 sabores guardados");
        assertTrue(result.stream().anyMatch(s -> s.getNombre().equals("Mango")));
        assertTrue(result.stream().anyMatch(s -> s.getNombre().equals("Coco")));
    }

    /**
     * Test of buscarPorNombre method, of class SaborDAOMongo.
     */
    @Test
    public void testBuscarPorNombre() throws Exception {
        System.out.println("buscarPorNombre");

        PersistenciaSaborDTO sabor = new PersistenciaSaborDTO();
        sabor.setNombre("Piña");
        saborDAO.guardar(sabor);

        PersistenciaSaborDTO result = saborDAO.buscarPorNombre("Piña");

        assertNotNull(result, "El sabor debe existir");
        assertEquals("Piña", result.getNombre(), "El nombre debe coincidir");
    }

    /**
     * Test of guardar method, of class SaborDAOMongo.
     */
    @Test
    public void testGuardar() throws Exception {
        System.out.println("guardar");

        PersistenciaSaborDTO sabor = new PersistenciaSaborDTO();
        sabor.setNombre("Melón");

        saborDAO.guardar(sabor);

        assertNotNull(sabor.getId(), "El sabor debe tener un ID asignado");

        PersistenciaSaborDTO resultado = saborDAO.buscarPorNombre("Melón");

        assertNotNull( resultado, "El sabor debe encontrarse después de guardarlo");
        assertEquals("Melón", resultado.getNombre(), "El nombre debe coincidir");
    }

}
