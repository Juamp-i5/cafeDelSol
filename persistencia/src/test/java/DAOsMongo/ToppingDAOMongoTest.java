/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOsMongo;

import DTOs.PersistenciaToppingDTO;
import IDAOs.IToppingDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongoPrueba;
import conexion.IConexionMongo;
import entidades.Topping;
import java.util.List;
import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

/**
 * Pruebas unitarias para ToppingDAO utilizando una base de datos de prueba.
 *
 * @author norma
 */
public class ToppingDAOMongoTest {

    private static MongoCollection<Topping> coleccion;

    private static IConexionMongo conexionPrueba;
    private static IToppingDAO toppingDAO;
    private static MongoDatabase database;

    @BeforeAll
    public static void setUpClase() throws Exception {
        conexionPrueba = ConexionMongoPrueba.getInstance();
        database = conexionPrueba.getDatabase();
        toppingDAO = ToppingDAOMongo.getInstance(conexionPrueba);
        coleccion = conexionPrueba.getDatabase().getCollection("toppings", Topping.class);
    }

    @BeforeEach
    public void setUp() {
        database.getCollection("toppings", Topping.class).deleteMany(new Document());
    }

    @AfterAll
    public static void tearDownClase() {
        if (database != null) {
            database.getCollection("toppings", Topping.class).deleteMany(new Document());
        }
        ConexionMongoPrueba.clearInstance();
    }

    /**
     * Test of buscarTodos method, of class ToppingDAOMongo.
     */
    @Test
    public void testBuscarTodos() throws Exception {
        Topping topping1 = new Topping();
        topping1.setNombre("Chispas");
        coleccion.insertOne(topping1);

        Topping topping2 = new Topping();
        topping2.setNombre("Caramelo");
        coleccion.insertOne(topping2);

        List<PersistenciaToppingDTO> resultado = toppingDAO.buscarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().anyMatch(t -> "Chispas".equals(t.getNombre())));
        assertTrue(resultado.stream().anyMatch(t -> "Caramelo".equals(t.getNombre())));
    }

    /**
     * Test of buscarPorNombre method, of class ToppingDAOMongo.
     */
    @Test
    public void testBuscarPorNombre() throws Exception {
        Topping topping = new Topping();
        topping.setNombre("Chispas");
        coleccion.insertOne(topping);

        PersistenciaToppingDTO resultado = toppingDAO.buscarPorNombre("Chispas");

        assertNotNull(resultado);
        assertEquals("Chispas", resultado.getNombre());

        PersistenciaToppingDTO noExiste = toppingDAO.buscarPorNombre("NoExiste");
        assertNull(noExiste);
    }

    /**
     * Test of guardar method, of class ToppingDAOMongo.
     */
    @Test
    public void testGuardar() throws Exception {
        PersistenciaToppingDTO dto = new PersistenciaToppingDTO();
        dto.setNombre("Nuevo Topping");

        toppingDAO.guardar(dto);

        assertNotNull(dto.getId());

        PersistenciaToppingDTO guardado = toppingDAO.buscarPorNombre("Nuevo Topping");
        assertNotNull(guardado);
        assertEquals("Nuevo Topping", guardado.getNombre());
        assertEquals(dto.getId(), guardado.getId());
    }

}
