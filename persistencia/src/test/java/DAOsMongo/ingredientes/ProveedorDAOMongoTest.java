/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOsMongo.ingredientes;

import DTOs.ingredientes.ProveedorDTOPersistencia;
import com.mongodb.client.MongoCollection;
import conexion.ConexionMongoPrueba;
import conexion.IConexionMongo;
import entidades.Proveedor;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para ProveedorDAOMongo utilizando una base de datos de
 * prueba.
 * @author norma
 */
public class ProveedorDAOMongoTest {

    private ProveedorDAOMongo dao;
    private MongoCollection<Proveedor> coleccion;

    @BeforeEach
    public void setUp() {
        ConexionMongoPrueba.clearInstance(); 

        IConexionMongo conexion = ConexionMongoPrueba.getInstance();
        dao = new ProveedorDAOMongo(conexion);  

        coleccion = conexion.getDatabase().getCollection("proveedores", Proveedor.class);
        coleccion.deleteMany(new org.bson.Document());
    }

    @AfterEach
    public void tearDown() {
        coleccion.deleteMany(new org.bson.Document());
        ConexionMongoPrueba.clearInstance();
    }

    /**
     * Test of obtenerProveedores method, of class ProveedorDAOMongo.
     */
    @Test
    public void testObtenerProveedores() throws Exception {
        Proveedor proveedor1 = new Proveedor(new ObjectId(), "Proveedor Uno");
        Proveedor proveedor2 = new Proveedor(new ObjectId(), "Proveedor Dos");
        coleccion.insertOne(proveedor1);
        coleccion.insertOne(proveedor2);

        List<ProveedorDTOPersistencia> resultado = dao.obtenerProveedores();
        for(ProveedorDTOPersistencia p : resultado){
            System.out.println(p.getNombre());
        }

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().anyMatch(p -> p.getNombre().equals("Proveedor Uno")));
        assertTrue(resultado.stream().anyMatch(p -> p.getNombre().equals("Proveedor Dos")));
    }

    @Test
    public void testObtenerProveedores_SinResultados() throws Exception {
        List<ProveedorDTOPersistencia> resultado = dao.obtenerProveedores();
        assertTrue(resultado.isEmpty(), "La lista debe estar vacía si no hay proveedores.");
    }

}
