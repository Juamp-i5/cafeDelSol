/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOsMongo.salidas;

import IDAOs.salidas.ISalidaDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongo;
import conexion.IConexionMongo;
import entidades.Salida;
import enums.MotivoEnum;
import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import excepciones.PersistenciaSalidasException;
import org.junit.jupiter.api.TestInstance;


/**
 *
 * @author katia
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SalidaDAOMongoTest {
    
    private ISalidaDAO salidaDAO;
    private IConexionMongo conexion;
    private MongoDatabase database;
    
    @BeforeAll
    void setUp() {
        conexion = ConexionMongo.getInstance();
        salidaDAO = SalidaDAOMongo.getInstance(conexion);
        database = conexion.getDatabase();
    }

    @BeforeEach
    void limpiarColeccion() {
        MongoCollection<Salida> coleccion = database.getCollection("salidas", Salida.class);
        coleccion.deleteMany(new org.bson.Document());
    }

    @Test
    void testRegistrarSalida() throws PersistenciaSalidasException {
        Salida salida = new Salida(
                LocalDate.now(),
                new ObjectId("682c56a8c37ab59fb4d178c7"),
                5.0,
                MotivoEnum.ROBO
        );

        boolean resultado = salidaDAO.registrarSalida(salida);
        assertTrue(resultado);
    }

    @Test
    void testConsultarTodas() throws PersistenciaSalidasException {
        salidaDAO.registrarSalida(new Salida(
                LocalDate.of(2025, 5, 20),
                new ObjectId("682c56a8c37ab59fb4d178c7"),
                2.0,
                MotivoEnum.CADUCIDAD
        ));

        List<Salida> salidas = salidaDAO.consultarTodas();
        assertNotNull(salidas);
        assertEquals(1, salidas.size());
    }

    @Test
    void testConsultarPorId() throws PersistenciaSalidasException {
        Salida salida = new Salida(
                LocalDate.of(2025, 5, 19),
                new ObjectId("682c56a8c37ab59fb4d178c7"),
                1.5,
                MotivoEnum.DETERIORO
        );

        salidaDAO.registrarSalida(salida);
        ObjectId id = salida.getId();

        Salida salidaRecuperada = salidaDAO.consultarPorId(id);
        assertNotNull(salidaRecuperada);
        assertEquals(id, salidaRecuperada.getId());
    }

    @Test
    void testConsultarPorRangoFechas() throws PersistenciaSalidasException {
        salidaDAO.registrarSalida(new Salida(
                LocalDate.of(2025, 5, 18),
                new ObjectId("682c56a8c37ab59fb4d178c7"),
                3.0,
                MotivoEnum.ROBO
        ));

        salidaDAO.registrarSalida(new Salida(
                LocalDate.of(2025, 5, 20),
                new ObjectId("682c56a8c37ab59fb4d178c7"),
                4.0,
                MotivoEnum.CADUCIDAD
        ));

        List<Salida> salidas = salidaDAO.consultarPorRangoFechas(
                LocalDate.of(2025, 5, 18),
                LocalDate.of(2025, 5, 20)
        );

        assertEquals(2, salidas.size());
    }
    
    
}
