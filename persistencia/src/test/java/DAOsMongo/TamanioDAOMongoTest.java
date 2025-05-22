/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOsMongo;

import DTOs.PersistenciaTamanioDTO;
import IDAOs.ITamanioDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongoPrueba;
import conexion.IConexionMongo;
import entidades.Tamanio;
import java.util.List;
import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeAll;

/**
 * Pruebas unitarias para TamanioDAO utilizando una base de datos de
 * prueba.
 * @author norma
 */
public class TamanioDAOMongoTest {

    private static MongoCollection<Tamanio> coleccion;

    private static IConexionMongo conexionPrueba;
    private static ITamanioDAO tamanioDAO;
    private static MongoDatabase database;

    @BeforeAll
    public static void setUpClase() throws Exception {
        conexionPrueba = ConexionMongoPrueba.getInstance();
        database = conexionPrueba.getDatabase();
        tamanioDAO = TamanioDAOMongo.getInstance(conexionPrueba);
        coleccion = conexionPrueba.getDatabase().getCollection("tamanios", Tamanio.class);
    }

    @BeforeEach
    public void setUp() {
        database.getCollection("tamanios", Tamanio.class).deleteMany(new Document());
    }

    @AfterAll
    public static void tearDownClase() {
        if (database != null) {
            database.getCollection("tamanios", Tamanio.class).deleteMany(new Document());
        }
        ConexionMongoPrueba.clearInstance();
    }

    /**
     * Test of buscarTodos method, of class TamanioDAOMongo.
     */
    @Test
    public void testBuscarTodos() throws Exception {
        System.out.println("buscarTodos");

        PersistenciaTamanioDTO t1 = new PersistenciaTamanioDTO();
        t1.setNombre("Chico");
        t1.setPrecioAdicional(0.0);

        PersistenciaTamanioDTO t2 = new PersistenciaTamanioDTO();
        t2.setNombre("Extra Grande");
        t2.setPrecioAdicional(50.0);

        tamanioDAO.guardar(t1);
        tamanioDAO.guardar(t2);

        List<PersistenciaTamanioDTO> result = tamanioDAO.buscarTodos();

        assertNotNull( result, "La lista no debe ser null");
        assertEquals( 2, result.size(), "Debe haber 2 tamanios guardados");
        assertTrue(result.stream().anyMatch(t -> t.getNombre().equals("Chico")));
        assertTrue(result.stream().anyMatch(t -> t.getNombre().equals("Extra Grande")));
    }

    /**
     * Test of buscarPorNombre method, of class TamanioDAOMongo.
     */
    @Test
    public void testBuscarPorNombre() throws Exception {
        System.out.println("buscarPorNombre");

        PersistenciaTamanioDTO tamanioDTO = new PersistenciaTamanioDTO();
        tamanioDTO.setNombre("Mini");
        tamanioDTO.setPrecioAdicional(5.0);
        tamanioDAO.guardar(tamanioDTO);

        PersistenciaTamanioDTO resultado = tamanioDAO.buscarPorNombre("Mini");
        assertEquals("Mini", resultado.getNombre());
    }

    /**
     * Test of guardar method, of class TamanioDAOMongo.
     */
    @Test
    public void testGuardar() throws Exception {
        System.out.println("guardar");

        PersistenciaTamanioDTO tamanioDTO = new PersistenciaTamanioDTO();
        tamanioDTO.setNombre("Grande");
        tamanioDTO.setPrecioAdicional(30.0);

        tamanioDAO.guardar(tamanioDTO);

        assertNotNull(tamanioDTO.getId(), "El ID debe establecerse después de guardar");

        PersistenciaTamanioDTO recuperado = tamanioDAO.buscarPorNombre("Grande");
        assertNotNull(recuperado);
        assertEquals("Grande", recuperado.getNombre());
    }

}
