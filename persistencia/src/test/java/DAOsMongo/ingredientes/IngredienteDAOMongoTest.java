/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOsMongo.ingredientes;

import DTOs.ingredientes.DetallesIngredienteViejoDTOPersistencia;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import IDAOs.ingredientes.IIngredienteDAOMongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongoPrueba;
import conexion.IConexionMongo;
import entidades.Ingrediente;
import java.util.List;
import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para IngredienteDAOMongo utilizando una base de datos de
 * prueba.
 *
 * @author norma
 */
public class IngredienteDAOMongoTest {

    private static MongoCollection<Ingrediente> coleccion;

    private static IConexionMongo conexionPrueba;
    private static IIngredienteDAOMongo ingredienteDAO;
    private static MongoDatabase database;

    @BeforeAll
    public static void setUpClase() throws Exception {
        conexionPrueba = ConexionMongoPrueba.getInstance();
        database = conexionPrueba.getDatabase();
        ingredienteDAO = IngredienteDAOMongo.getInstance(conexionPrueba);
        coleccion = conexionPrueba.getDatabase().getCollection("ingredientes", Ingrediente.class);
    }

    @BeforeEach
    public void setUp() {
        database.getCollection("ingredientes", Ingrediente.class).deleteMany(new Document());
    }

    @AfterAll
    public static void tearDownClase() {
        if (database != null) {
            database.getCollection("ingredientes", Ingrediente.class).deleteMany(new Document());
        }
        ConexionMongoPrueba.clearInstance();
    }

    /**
     * Test of agregarIngrediente method, of class IngredienteDAOMongo.
     */
    @Test
    public void testAgregarIngrediente() throws Exception {
        IngredienteDTOPersistencia ingrediente = new IngredienteDTOPersistencia();
        ingrediente.setNombre("Azúcar");
        ingrediente.setCantidadDisponible(100.0);
        ingrediente.setCantidadMinima(15.0);
        ingrediente.setUnidadMedida("GRAMOS");
        ingrediente.setNivelStock("ENSTOCK");
        ingrediente.setIdProveedor("682adc6e276fe18581fca4be");

        boolean resultado = ingredienteDAO.agregarIngrediente(ingrediente);
        assertTrue(resultado, "Debe agregar ingrediente correctamente");

        Ingrediente encontrado = coleccion.find(new Document("nombre", "Azúcar")).first();
        assertNotNull(encontrado, "Ingrediente debe existir en la colección");
        assertEquals("Azúcar", encontrado.getNombre());
    }

    /**
     * Test of obtenerDetallesIngrediente method, of class IngredienteDAOMongo.
     */
    @Test
    public void testObtenerDetallesIngrediente() throws Exception {
        IngredienteDTOPersistencia ingrediente = new IngredienteDTOPersistencia();
        ingrediente.setNombre("Sal");
        ingrediente.setCantidadDisponible(20.0);
        ingrediente.setCantidadMinima(10.0);
        ingrediente.setUnidadMedida("UNIDADES");
        ingrediente.setNivelStock("ENSTOCK");
        ingrediente.setIdProveedor("682adc6e276fe18581fca4be");

        ingredienteDAO.agregarIngrediente(ingrediente);

        Ingrediente encontrado = coleccion.find(new Document("nombre", "Sal")).first();
        assertNotNull(encontrado);

        DetallesIngredienteViejoDTOPersistencia detalles = ingredienteDAO.obtenerDetallesIngrediente(encontrado.getId().toHexString());
        assertNotNull(detalles);
        assertEquals("Sal", detalles.getNombre());
    }

    /**
     * Test of editarIngrediente method, of class IngredienteDAOMongo.
     */
    @Test
    public void testEditarIngrediente() throws Exception {
        IngredienteDTOPersistencia ingrediente = new IngredienteDTOPersistencia();
        ingrediente.setNombre("Harina");
        ingrediente.setCantidadDisponible(15.0);
        ingrediente.setCantidadMinima(7.0);
        ingrediente.setUnidadMedida("GRAMOS");
        ingrediente.setNivelStock("ENSTOCK");
        ingrediente.setIdProveedor("682adc6e276fe18581fca4be");

        ingredienteDAO.agregarIngrediente(ingrediente);

        Ingrediente encontrado = coleccion.find(new Document("nombre", "Harina")).first();
        assertNotNull(encontrado);

        String nuevoNombre = "Harina Integral";

        DetallesIngredienteViejoDTOPersistencia resultado = ingredienteDAO.editarIngrediente(encontrado.getId().toHexString(), nuevoNombre);
        assertNotNull(resultado);
        assertEquals(nuevoNombre, resultado.getNombre());

        Ingrediente actualizado = coleccion.find(new Document("_id", encontrado.getId())).first();
        assertEquals(nuevoNombre, actualizado.getNombre());
    }

    /**
     * Test of buscarIngredientesPorFiltros method, of class
     * IngredienteDAOMongo.
     */
    @Test
    public void testBuscarIngredientesPorFiltros() throws Exception {
        IngredienteDTOPersistencia ingrediente1 = new IngredienteDTOPersistencia();
        ingrediente1.setNombre("Agua");
        ingrediente1.setCantidadDisponible(15.0);
        ingrediente1.setCantidadMinima(7.0);
        ingrediente1.setUnidadMedida("MILILITROS");
        ingrediente1.setNivelStock("BAJOSTOCK");
        ingrediente1.setIdProveedor("682adc6e276fe18581fca4be");

        IngredienteDTOPersistencia ingrediente2 = new IngredienteDTOPersistencia();
        ingrediente2.setNombre("Matcha");
        ingrediente2.setCantidadDisponible(150.0);
        ingrediente2.setCantidadMinima(70.0);
        ingrediente2.setUnidadMedida("GRAMOS");
        ingrediente2.setNivelStock("ENSTOCK");
        ingrediente2.setIdProveedor("682adc6e276fe18581fca4be");

        ingredienteDAO.agregarIngrediente(ingrediente1);
        ingredienteDAO.agregarIngrediente(ingrediente2);

        List<IngredienteDTOPersistencia> resultado = ingredienteDAO.buscarIngredientesPorFiltros("Matcha", "");
        assertNotNull(resultado);
        assertTrue(resultado.size() == 1);

        resultado = ingredienteDAO.buscarIngredientesPorFiltros("", "BAJOSTOCK");
        assertTrue(resultado.stream().anyMatch(i -> "Agua".equals(i.getNombre())));
    }

    /**
     * Test of aumentarStock method, of class IngredienteDAOMongo.
     */
    @Test
    public void testAumentarStock() throws Exception {
        IngredienteDTOPersistencia ingrediente = new IngredienteDTOPersistencia();
        ingrediente.setNombre("Café");
        ingrediente.setCantidadDisponible(50.0);
        ingrediente.setCantidadMinima(20.0);
        ingrediente.setUnidadMedida("kg");
        ingrediente.setNivelStock("ENSTOCK");
        ingrediente.setIdProveedor("682adc6e276fe18581fca4be");

        ingredienteDAO.agregarIngrediente(ingrediente);

        Ingrediente encontrado = coleccion.find(new Document("nombre", "Café")).first();
        assertNotNull(encontrado);

        boolean res = ingredienteDAO.aumentarStock(encontrado.getId().toHexString(), 10.0);
        assertTrue(res);

        Ingrediente actualizado = coleccion.find(new Document("_id", encontrado.getId())).first();
        assertEquals(60.0, actualizado.getCantidadDisponible());
    }

    /**
     * Test of reducirStock method, of class IngredienteDAOMongo.
     */
    @Test
    public void testReducirStock() throws Exception {
        IngredienteDTOPersistencia ingrediente = new IngredienteDTOPersistencia();
        ingrediente.setNombre("Leche");
        ingrediente.setCantidadDisponible(10.0);
        ingrediente.setCantidadMinima(5.0);
        ingrediente.setUnidadMedida("MILILITROS");
        ingrediente.setNivelStock("ENSTOCK");
        ingrediente.setIdProveedor("682adc6e276fe18581fca4be");

        ingredienteDAO.agregarIngrediente(ingrediente);

        Ingrediente encontrado = coleccion.find(new Document("nombre", "Leche")).first();
        assertNotNull(encontrado);

        boolean res = ingredienteDAO.reducirStock(encontrado.getId().toHexString(), 4.0);
        assertTrue(res);

        Ingrediente actualizado = coleccion.find(new Document("_id", encontrado.getId())).first();
        assertEquals(6.0, actualizado.getCantidadDisponible());
    }

    /**
     * Test of actualizarNivelStock method, of class IngredienteDAOMongo.
     */
    @Test
    public void testActualizarNivelStock() throws Exception {
        IngredienteDTOPersistencia ingrediente = new IngredienteDTOPersistencia();
        ingrediente.setNombre("Miel");
        ingrediente.setCantidadDisponible(3.0);
        ingrediente.setCantidadMinima(5.0);
        ingrediente.setUnidadMedida("MILILITROS");
        ingrediente.setNivelStock("ENSTOCK");
        ingrediente.setIdProveedor("682adc6e276fe18581fca4be");

        ingredienteDAO.agregarIngrediente(ingrediente);

        Ingrediente encontrado = coleccion.find(new Document("nombre", "Miel")).first();
        assertNotNull(encontrado);

        ingredienteDAO.actualizarNivelStock(encontrado.getId().toHexString());

        Ingrediente actualizado = coleccion.find(new Document("_id", encontrado.getId())).first();
        assertEquals("BAJOSTOCK", actualizado.getNivelStock());
    }

    /**
     * Test of obtenerIngredientePorNombre method, of class IngredienteDAOMongo.
     */
    @Test
    public void testObtenerIngredientePorNombre() throws Exception {
        IngredienteDTOPersistencia ingrediente = new IngredienteDTOPersistencia();
        ingrediente.setNombre("Chai");
        ingrediente.setCantidadDisponible(8.0);
        ingrediente.setCantidadMinima(4.0);
        ingrediente.setUnidadMedida("GRAMOS");
        ingrediente.setNivelStock("ENSTOCK");
        ingrediente.setIdProveedor("682adc6e276fe18581fca4be");

        ingredienteDAO.agregarIngrediente(ingrediente);

        boolean existe = ingredienteDAO.obtenerIngredientePorNombre("Chai");
        assertTrue(existe);

        boolean noExiste = ingredienteDAO.obtenerIngredientePorNombre("No Existe");
        assertFalse(noExiste);
    }

}
