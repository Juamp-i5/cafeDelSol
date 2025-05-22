/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOsMongo.cubiculos;

import DTOs.cubiculos.CubiculoCompletoDTOPersistencia;
import IDAOs.cubiculos.ICubiculoDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongoPrueba;
import conexion.IConexionMongo;
import entidades.Cubiculo;
import java.util.List;
import org.bson.Document;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author rodri
 */
public class CubiculoDAOMongoTest {

    private static MongoCollection<Cubiculo> coleccion;

    private static IConexionMongo conexionPrueba;
    private static ICubiculoDAO cubiculoDAO;
    private static MongoDatabase database;

    @BeforeAll
    public static void setUpClass() throws Exception {
        conexionPrueba = ConexionMongoPrueba.getInstance();
        database = conexionPrueba.getDatabase();
        cubiculoDAO = CubiculoDAOMongo.getInstance(conexionPrueba);
        coleccion = conexionPrueba.getDatabase().getCollection("cubiculos", Cubiculo.class);
        database.getCollection("cubiculos", Cubiculo.class).insertOne(new Cubiculo("Cubículo A", 50.0));
    }

    @AfterAll
    public static void tearDownClase() {
        if (database != null) {
            database.getCollection("cubiculos", Cubiculo.class).deleteMany(new Document());
        }
        ConexionMongoPrueba.clearInstance();
    }

    /**
     * Test of obtenerCubiculos method, of class CubiculoDAOMongo.
     */
    @Test
    public void testObtenerCubiculos() throws Exception {
        System.out.println("Obtener todos");

        List<String> lista = List.of("Cubículo A");

        List<String> result = cubiculoDAO.obtenerCubiculos();
        assertNotNull(result);
        assertEquals(lista, result);
    }

    /**
     * Test of obtenerPorNombre method, of class CubiculoDAOMongo.
     */
    @Test
    public void testObtenerPorNombre() throws Exception {
        System.out.println("obtenerPorNombre");
        String nombre = "Cubiculo A";

        Cubiculo encontrado = coleccion.find(new Document("nombre", "Cubículo A")).first();
        assertNotNull(encontrado);

        CubiculoCompletoDTOPersistencia result = cubiculoDAO.obtenerPorNombre(encontrado.getNombre());
        assertNotNull(result);
        assertEquals("Cubículo A", result.getNombre());
    }

}
