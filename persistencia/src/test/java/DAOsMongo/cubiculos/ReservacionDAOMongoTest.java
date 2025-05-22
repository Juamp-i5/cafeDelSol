/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOsMongo.cubiculos;

import DTOs.cubiculos.ReservacionDTOCompletaPersistencia;
import DTOs.cubiculos.ReservacionDetalleDTOPersistencia;
import IDAOs.cubiculos.ICubiculoDAO;
import IDAOs.cubiculos.IReservacionDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongoPrueba;
import conexion.IConexionMongo;
import entidades.Cubiculo;
import entidades.Reservacion;
import enumCubiculos.Estado;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.bson.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author rodri
 */
public class ReservacionDAOMongoTest {

    private static MongoCollection<Reservacion> coleccion;

    private static IConexionMongo conexionPrueba;
    private static IReservacionDAO dao;
    private static MongoDatabase database;

    @BeforeAll
    public static void setUpClass() throws Exception {
        conexionPrueba = ConexionMongoPrueba.getInstance();
        database = conexionPrueba.getDatabase();
        dao = ReservacionDAOMongo.getInstance(conexionPrueba);
        coleccion = conexionPrueba.getDatabase().getCollection("reservaciones", Reservacion.class);
    }

    @AfterAll
    public static void tearDownClase() {
        if (database != null) {
            database.getCollection("reservaciones", Reservacion.class).deleteMany(new Document());
        }
        ConexionMongoPrueba.clearInstance();
    }

    /**
     * Test of agregarReservacion method, of class ReservacionDAOMongo.
     */
    @Test
    void agregarReservacion() throws Exception {
        ReservacionDTOCompletaPersistencia reservacion = new ReservacionDTOCompletaPersistencia(1, "Rodri", "1234567890", LocalDate.now(), LocalTime.now(), LocalTime.now(), Estado.ACTIVA, "682eeda539682aeb2e0aa821", "Cubículo A", 50.0, 0.0);
        ReservacionDTOCompletaPersistencia resultado = dao.agregarReservacion(reservacion);

        assertNotNull(resultado);
        assertEquals(reservacion.getNumReservacion(), resultado.getNumReservacion());

        // Confirmar que se guardó realmente
        ReservacionDTOCompletaPersistencia guardada = dao.buscarPorId(reservacion.getNumReservacion());
        assertEquals("Rodri", guardada.getNombre());
    }

    /**
     * Test of actualizarEstadoReservacion method, of class ReservacionDAOMongo.
     */
    @Test
    void actualizarEstadoReservacion() throws Exception {
        ReservacionDTOCompletaPersistencia reservacion = new ReservacionDTOCompletaPersistencia(1, "Rodri", "1234567890", LocalDate.now(), LocalTime.now(), LocalTime.now(), Estado.ACTIVA, "682eeda539682aeb2e0aa821", "Cubículo A", 50.0, 0.0);
        dao.agregarReservacion(reservacion);

        boolean actualizado = dao.actualizarEstadoReservacion(reservacion.getNumReservacion(), Estado.CANCELADO);
        assertTrue(actualizado);

        ReservacionDTOCompletaPersistencia actualizada = dao.buscarPorId(reservacion.getNumReservacion());
        assertEquals(Estado.CANCELADO, actualizada.getEstado());
    }

    /**
     * Test of buscarPorId method, of class ReservacionDAOMongo.
     */
    @Test
    void buscarPorId() throws Exception {
        ReservacionDTOCompletaPersistencia reservacion = new ReservacionDTOCompletaPersistencia(1, "Rodri", "1234567890", LocalDate.now(), LocalTime.now(), LocalTime.now(), Estado.ACTIVA, "682eeda539682aeb2e0aa821", "Cubículo A", 50.0, 0.0);
        dao.agregarReservacion(reservacion);

        ReservacionDTOCompletaPersistencia encontrada = dao.buscarPorId(reservacion.getNumReservacion());
        assertNotNull(encontrada);
        assertEquals(reservacion.getNombre(), encontrada.getNombre());
    }

    /**
     * Test of buscarPorRangoFechas method, of class ReservacionDAOMongo.
     */
    @Test
    void buscarPorRangoFechas() throws Exception {
        ReservacionDTOCompletaPersistencia r = new ReservacionDTOCompletaPersistencia(1, "Rodri", "1234567890", LocalDate.now(), LocalTime.now(), LocalTime.now(), Estado.ACTIVA, "682eeda539682aeb2e0aa821", "Cubículo A", 50.0, 0.0);
        dao.agregarReservacion(r);

        List<ReservacionDTOCompletaPersistencia> lista = dao.buscarPorRangoFechas(null, null);
        assertFalse(lista.isEmpty());
    }

    /**
     * Test of modificarReservacion method, of class ReservacionDAOMongo.
     */
    @Test
    void modificarReservacion() throws Exception {
        ReservacionDTOCompletaPersistencia r = new ReservacionDTOCompletaPersistencia(1, "Rodri", "1234567890", LocalDate.now(), LocalTime.now(), LocalTime.now(), Estado.ACTIVA, "682eeda539682aeb2e0aa821", "Cubículo A", 50.0, 0.0);
        dao.agregarReservacion(r);

        boolean resultado = dao.modificarReservacion(r.getNumReservacion(), null, "Cambio por cancelación", LocalDateTime.now());
        assertTrue(resultado);

        ReservacionDTOCompletaPersistencia actualizada = dao.buscarPorId(r.getNumReservacion());
        assertEquals(Estado.CANCELADO, actualizada.getEstado());
    }

    /**
     * Test of getDetalleReservacion method, of class ReservacionDAOMongo.
     */
    @Test
    void getDetalleReservacion_deberiaRetornarDetalleCorrecto() throws Exception {
        ReservacionDTOCompletaPersistencia r =new ReservacionDTOCompletaPersistencia(1, "Rodri", "1234567890", LocalDate.now(), LocalTime.now(), LocalTime.now(), Estado.ACTIVA, "682eeda539682aeb2e0aa821", "Cubículo A", 50.0, 0.0);
        dao.agregarReservacion(r);

        ReservacionDetalleDTOPersistencia detalle = dao.getDetalleReservacion(r.getNumReservacion());
        assertNotNull(detalle);
        assertEquals(r.getNombreCubiculo(), detalle.getNombreCubiculo());
    }

}
