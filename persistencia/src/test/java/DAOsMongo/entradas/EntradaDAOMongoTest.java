package DAOsMongo.entradas;

import DAOsMongo.ingredientes.IngredienteDAOMongo;
import DTOs.entradas.DetalleEntradaDTOPersistencia;
import DTOs.entradas.EntradaNuevaDTOPersistencia;
import DTOs.entradas.EntradaViejaDTOPersistencia;
import com.mongodb.client.MongoCollection;
import conexion.ConexionMongoPrueba;
import conexion.IConexionMongo;
import entidades.DetalleEntrada;
import entidades.Entrada;
import entidades.Ingrediente;
import entidades.Proveedor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author pablo
 */
public class EntradaDAOMongoTest {
    
    private EntradaDAOMongo dao;
    private MongoCollection<Entrada> coleccion;
    private final String NOMBRE_COLECCION_ENTRADAS = "RegistroEntradas"; 
    
    @BeforeEach
    public void setUp() {
        ConexionMongoPrueba.clearInstance();

        IConexionMongo conexion = ConexionMongoPrueba.getInstance();
        dao = new EntradaDAOMongo(conexion);

        coleccion = conexion.getDatabase().getCollection(NOMBRE_COLECCION_ENTRADAS, Entrada.class);
        coleccion.deleteMany(new Document());
    }

    @AfterEach
    public void tearDown() {
        coleccion.deleteMany(new Document());
        ConexionMongoPrueba.clearInstance();
    }

    /**
     * Test del metodo registrarEntrada, de la clase EntradaDAOMongo.
     */
    @Test
    public void testRegistrarEntrada() throws Exception {
        System.out.println("testRegistrarEntradaExitoso");

        EntradaNuevaDTOPersistencia entradaDTO = new EntradaNuevaDTOPersistencia();
        entradaDTO.setProveedor("Proveedor Test");
        entradaDTO.setFechaHora(LocalDateTime.now().withNano(0)); 
        entradaDTO.setPrecioTotal(100.0);
        
        DetalleEntradaDTOPersistencia detalleDTO = new DetalleEntradaDTOPersistencia();
        detalleDTO.setIdIngrediente(new ObjectId().toHexString()); 
        detalleDTO.setNombreIngrediente("Ingrediente X");
        detalleDTO.setCantidadIngrediente(10.0);
        detalleDTO.setPrecioUnitario(5.0);
        detalleDTO.setPrecioTotal(50.0);
        
        List<DetalleEntradaDTOPersistencia> detalles = new ArrayList<>();
        detalles.add(detalleDTO);
        entradaDTO.setDetallesEntrada(detalles);

        boolean resultado = dao.registrarEntrada(entradaDTO);
        assertTrue(resultado, "El registro de la entrada debería ser exitoso");
        long count = coleccion.countDocuments();
        assertEquals(1, count, "Debería haber exactamente una entrada en la colección");

        Entrada entradaGuardada = coleccion.find(new Document("proveedor", "Proveedor Test")).first();
        assertNotNull(entradaGuardada, "La entrada debería haber sido encontrada en la base de datos");
        assertEquals(entradaDTO.getProveedor(), entradaGuardada.getProveedor());
        assertEquals(entradaDTO.getPrecioTotal(), entradaGuardada.getPrecioTotal(), 0.001);
        assertEquals(entradaDTO.getFechaHora(), entradaGuardada.getFechaHora()); 
        assertNotNull(entradaGuardada.getDetallesEntrada());
        assertEquals(1, entradaGuardada.getDetallesEntrada().size());
        assertEquals(detalleDTO.getNombreIngrediente(), entradaGuardada.getDetallesEntrada().get(0).getNombreIngrediente());
    }

    /**
     * Test del metodo obtenerEntradasPorFechas, de la clase EntradaDAOMongo.
     */
    @Test
    public void testObtenerEntradasPorFechas() throws Exception {
       System.out.println("testObtenerEntradasPorFechasRangoCompleto");

        Entrada e1 = new Entrada();
        e1.setIdEntrada(new ObjectId()); 
        e1.setProveedor("P1");
        e1.setFechaHora(LocalDateTime.of(2024, 1, 10, 10, 0, 0));
        e1.setPrecioTotal(50.0);
        e1.setDetallesEntrada(Arrays.asList(
            new DetalleEntrada("Ing1", 10.0, 50.0, 5.0, "ALTO", new ObjectId())
        ));

        Entrada e2 = new Entrada();
        e2.setIdEntrada(new ObjectId());
        e2.setProveedor("P2");
        e2.setFechaHora(LocalDateTime.of(2024, 1, 15, 12, 0, 0));
        e2.setPrecioTotal(75.0);
        e2.setDetallesEntrada(new ArrayList<>()); 

        Entrada e3 = new Entrada();
        e3.setIdEntrada(new ObjectId());
        e3.setProveedor("P3");
        e3.setFechaHora(LocalDateTime.of(2024, 1, 20, 14, 0, 0));
        e3.setPrecioTotal(120.0);
        e3.setDetallesEntrada(new ArrayList<>()); 
        
        coleccion.insertMany(Arrays.asList(e1, e2, e3));

        LocalDateTime fechaInicio = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2024, 1, 31, 23, 59, 59);

        List<EntradaViejaDTOPersistencia> resultados = dao.obtenerEntradasPorFechas(fechaInicio, fechaFin);

        assertNotNull(resultados);
        assertEquals(3, resultados.size(), "Se esperaban 3 entradas dentro del rango de fechas");
        assertTrue(resultados.stream().anyMatch(e -> "P1".equals(e.getProveedor())));
        assertTrue(resultados.stream().anyMatch(e -> "P2".equals(e.getProveedor())));
        assertTrue(resultados.stream().anyMatch(e -> "P3".equals(e.getProveedor())));
    }
}
