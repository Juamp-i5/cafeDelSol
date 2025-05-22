/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOsMongo;

import DTOs.PersistenciaPedidoDTO;
import IDAOs.IPedidoDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.ConexionMongoPrueba;
import conexion.IConexionMongo;
import entidades.Pedido;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

/**
 * Pruebas unitarias para PedidoDAO utilizando una base de datos de prueba.
 *
 * @author norma
 */
public class PedidoDAOMongoTest {

    private static MongoCollection<Pedido> coleccion;

    private static IConexionMongo conexionPrueba;
    private static IPedidoDAO pedidoDAO;
    private static MongoDatabase database;

    @BeforeAll
    public static void setUpClase() throws Exception {
        conexionPrueba = ConexionMongoPrueba.getInstance();
        database = conexionPrueba.getDatabase();
        pedidoDAO = PedidoDAOMongo.getInstance(conexionPrueba);
        coleccion = conexionPrueba.getDatabase().getCollection("pedidos", Pedido.class);
    }

    @BeforeEach
    public void setUp() {
        database.getCollection("pedidos", Pedido.class).deleteMany(new Document());
    }

    @AfterAll
    public static void tearDownClase() {
        if (database != null) {
            database.getCollection("pedidos", Pedido.class).deleteMany(new Document());
        }
        ConexionMongoPrueba.clearInstance();
    }

    /**
     * Test of registrarPedido method, of class PedidoDAOMongo.
     */
    @Test
    public void testRegistrarPedido() throws Exception {
        PersistenciaPedidoDTO pedido = new PersistenciaPedidoDTO();
        pedido.setEstado("PENDIENTE");
        pedido.setFechaHora(LocalDateTime.now());
        pedido.setPrecioTotal(150.0);
        pedido.setProductos(new ArrayList<>());
        pedido.setBaristaId("barista1");
        pedido.setPago(null);

        PersistenciaPedidoDTO resultado = pedidoDAO.registrarPedido(pedido);

        assertNotNull(resultado);
        assertNotNull(resultado.getId());
        assertEquals("PENDIENTE", resultado.getEstado());

        Pedido encontrado = coleccion.find(Filters.eq("_id", new ObjectId(resultado.getId()))).first();
        assertNotNull(encontrado);
    }

    /**
     * Test of obtenerPedidosDelivery method, of class PedidoDAOMongo.
     */
    @Test
    public void testObtenerPedidosDelivery() throws Exception {
        PersistenciaPedidoDTO pedido1 = new PersistenciaPedidoDTO();
        pedido1.setEstado("PENDIENTE");
        pedido1.setFechaHora(LocalDateTime.now());
        pedido1.setPrecioTotal(100.0);
        pedido1.setProductos(new ArrayList<>());
        pedido1.setBaristaId("barista1");
        pedido1.setPago(null);
        pedidoDAO.registrarPedido(pedido1);

        PersistenciaPedidoDTO pedido2 = new PersistenciaPedidoDTO();
        pedido2.setEstado("PENDIENTE");
        pedido2.setFechaHora(LocalDateTime.now());
        pedido2.setPrecioTotal(200.0);
        pedido2.setProductos(new ArrayList<>());
        pedido2.setBaristaId("barista2");
        pedido2.setPago(null);
        pedidoDAO.registrarPedido(pedido2);

        PersistenciaPedidoDTO pedido3 = new PersistenciaPedidoDTO();
        pedido3.setEstado("TERMINADO");
        pedido3.setFechaHora(LocalDateTime.now());
        pedido3.setPrecioTotal(300.0);
        pedido3.setProductos(new ArrayList<>());
        pedido3.setBaristaId("barista3");
        pedido3.setPago(null);
        pedidoDAO.registrarPedido(pedido3);

        List<PersistenciaPedidoDTO> pedidos = pedidoDAO.obtenerPedidosDelivery();

        assertNotNull(pedidos);
        assertEquals(2, pedidos.size());
        for (PersistenciaPedidoDTO pedido : pedidos) {
            assertEquals("PENDIENTE", pedido.getEstado());
        }
    }

    /**
     * Test of actualizarEstado method, of class PedidoDAOMongo.
     */
    @Test
    public void testActualizarEstado() throws Exception {
        PersistenciaPedidoDTO pedido = new PersistenciaPedidoDTO();
        pedido.setEstado("PENDIENTE");
        pedido.setFechaHora(LocalDateTime.now());
        pedido.setPrecioTotal(120.0);
        pedido.setProductos(new ArrayList<>());
        pedido.setBaristaId("barista1");
        pedido.setPago(null);

        PersistenciaPedidoDTO registrado = pedidoDAO.registrarPedido(pedido);
        String idPedido = registrado.getId();

        pedidoDAO.actualizarEstado(idPedido);

        Pedido pedidoActualizado = coleccion.find(Filters.eq("_id", new ObjectId(idPedido))).first();
        assertNotNull(pedidoActualizado);
        assertEquals("TERMINADO", pedidoActualizado.getEstado());
    }

}
