package DAOsMongo;

import DTOs.PersistenciaProductoDTO;
import DTOs.PersistenciaProductoTamanioDTO;
import DTOs.PersistenciaProductoTamanioIngredienteDTO;
import DTOs.PersistenciaTamanioDTO;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import conexion.ConexionMongoPrueba;
import conexion.IConexionMongo;
import entidades.Producto;
import excepciones.PersistenciaException;
import IDAOs.IProductoDAO;
import com.mongodb.client.MongoCollection;
import interfacesMappers.IProductoMapper;
import utils.DependencyInjectors;
import com.mongodb.client.MongoDatabase;
import entidades.Ingrediente;
import interfacesMappers.IIngredienteMapper;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;
import mappers.IngredienteMapper;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductoDAOMongoTest {

    private static IConexionMongo conexionPrueba;
    private static IProductoDAO productoDAO;
    private static MongoDatabase database;
    private static IProductoMapper productoMapper;

    @BeforeAll
    static void setUpClase() throws Exception {
        try {
            productoMapper = DependencyInjectors.getInstancia().getProductoMapper();
        } catch (Exception e) {
            System.err.println("Error inicializando DependencyInjectors/ProductoMapper: " + e.getMessage());
            throw e;
        }

        conexionPrueba = ConexionMongoPrueba.getInstance();
        database = conexionPrueba.getDatabase();
        productoDAO = ProductoDAOMongo.getInstance(conexionPrueba);
    }

    @BeforeEach
    void setUp() {
        database.getCollection("productos", Producto.class).deleteMany(new Document());
    }

    @AfterAll
    static void tearDownClase() {
        if (database != null) {
            database.getCollection("productos", Producto.class).deleteMany(new Document());
        }
        ConexionMongoPrueba.clearInstance();
    }

    private PersistenciaProductoDTO crearProductoDTOCompleto(String id, String nombre, String categoria, String estado, double precioBase) {
        PersistenciaProductoDTO dto = new PersistenciaProductoDTO();
        if (id != null) {
            dto.setId(id);
        }
        dto.setNombre(nombre);
        dto.setDescripcion("Descripción de " + nombre);
        dto.setCategoria(categoria);
        dto.setEstado(estado);
        dto.setPrecioBase(precioBase);
        dto.setImageData(new byte[]{1, 2, 3});
        dto.setTamanios(new ArrayList<>());

        return dto;
    }

    @Test
    void testGuardarProductoYBuscarPorId() throws PersistenciaException {
        String idGenerado = new ObjectId().toHexString();
        PersistenciaProductoDTO nuevoProductoDTO = crearProductoDTOCompleto(idGenerado, "Café Express", "Bebidas", "HABILITADO", 25.0);

        productoDAO.guardarProducto(nuevoProductoDTO);

        PersistenciaProductoDTO productoEncontradoDTO = productoDAO.buscarPorId(idGenerado);

        assertNotNull(productoEncontradoDTO, "El producto debería encontrarse en la BD.");
        assertEquals(idGenerado, productoEncontradoDTO.getId());
        assertEquals("Café Express", productoEncontradoDTO.getNombre());
        assertEquals("Bebidas", productoEncontradoDTO.getCategoria());
        assertEquals("HABILITADO", productoEncontradoDTO.getEstado());
    }

    @Test
    void testGuardarProductoNuloDebeLanzarExcepcion() {
        PersistenciaException ex = assertThrows(PersistenciaException.class, () -> {
            productoDAO.guardarProducto(null);
        });
        assertTrue(ex.getMessage().contains("el producto no puede ser nulo"));
    }

    @Test
    void testBuscarPorIdNoExistente() throws PersistenciaException {
        PersistenciaProductoDTO producto = productoDAO.buscarPorId(new ObjectId().toHexString());
        assertNull(producto, "No debería encontrar un producto con ID inexistente.");
    }

    @Test
    void testBuscarTodosCuandoEstaVacio() throws PersistenciaException {
        List<PersistenciaProductoDTO> productos = productoDAO.buscarTodos();
        assertNotNull(productos);
        assertTrue(productos.isEmpty(), "La lista de productos debería estar vacía.");
    }

    @Test
    void testBuscarTodosConDatos() throws PersistenciaException {
        PersistenciaProductoDTO p1 = crearProductoDTOCompleto(new ObjectId().toHexString(), "Jugo Naranja", "Bebidas", "HABILITADO", 30.0);
        PersistenciaProductoDTO p2 = crearProductoDTOCompleto(new ObjectId().toHexString(), "Pastel Chocolate", "Postres", "DESHABILITADO", 50.0);
        productoDAO.guardarProducto(p1);
        productoDAO.guardarProducto(p2);

        List<PersistenciaProductoDTO> productos = productoDAO.buscarTodos();
        assertEquals(2, productos.size(), "Debería haber dos productos.");
    }

    @Test
    void testBuscarTodosHabilitadosConStock() throws PersistenciaException {
        // Crear ingrediente con stock suficiente
        MongoCollection<Ingrediente> coleccionIngredientes = database.getCollection("ingredientes", Ingrediente.class);
        Ingrediente ingredienteConStock = new Ingrediente();
        ObjectId ingredienteId = new ObjectId();
        ingredienteConStock.setId(ingredienteId);
        ingredienteConStock.setNombre("Fresa");
        ingredienteConStock.setUnidadMedida("GRAMOS");
        ingredienteConStock.setCantidadDisponible(100.0); // Stock suficiente
        coleccionIngredientes.insertOne(ingredienteConStock);

        IIngredienteMapper ingredienteMapper = new IngredienteMapper();
        // Crear ingredientes con cantidades requeridas menores al stock
        PersistenciaProductoTamanioIngredienteDTO ingredienteDTO = new PersistenciaProductoTamanioIngredienteDTO();
        IngredienteDTOPersistencia ingredienteDTOPersistencia = ingredienteMapper.toDTO(ingredienteConStock);
        ingredienteDTO.setIngrediente(ingredienteDTOPersistencia);
        ingredienteDTO.setCantidad(20.0); // Se requiere solo 20.0

        // Crear tamaño con ese ingrediente
        PersistenciaProductoTamanioDTO tamanio = new PersistenciaProductoTamanioDTO();
        tamanio.setTamanio(new PersistenciaTamanioDTO(null, "Mediano", 2.0, null));
        tamanio.setIngredientes(List.of(ingredienteDTO));

        // Crear productos con ese tamaño
        PersistenciaProductoDTO pHabilitado = crearProductoDTOCompleto(new ObjectId().toHexString(), "Agua Fresca", "Bebidas", "HABILITADO", 20.0);
        PersistenciaProductoDTO pDeshabilitado = crearProductoDTOCompleto(new ObjectId().toHexString(), "Torta Especial", "Comida", "DESHABILITADO", 70.0);
        PersistenciaProductoDTO pHabilitado2 = crearProductoDTOCompleto(new ObjectId().toHexString(), "Licuado Fresa", "Bebidas", "HABILITADO", 40.0);

        pHabilitado.setTamanios(List.of(tamanio));
        pHabilitado2.setTamanios(List.of(tamanio));

        // Guardar productos
        productoDAO.guardarProducto(pHabilitado);
        productoDAO.guardarProducto(pDeshabilitado);
        productoDAO.guardarProducto(pHabilitado2);

        // Ejecutar método bajo prueba
        List<PersistenciaProductoDTO> productosHabilitados = productoDAO.buscarTodosHabilitadosConStock();

        // Verificaciones
        assertEquals(2, productosHabilitados.size(), "Debería haber dos productos habilitados con stock suficiente.");
        assertTrue(productosHabilitados.stream().allMatch(p -> p.getEstado().equals("HABILITADO")));
        assertTrue(productosHabilitados.stream().anyMatch(p -> p.getNombre().equals("Agua Fresca")));
        assertTrue(productosHabilitados.stream().anyMatch(p -> p.getNombre().equals("Licuado Fresa")));
    }

    @Test
    void testBuscarPorNombreYCategoria() throws PersistenciaException {
        PersistenciaProductoDTO p1 = crearProductoDTOCompleto(new ObjectId().toHexString(), "Sándwich de Pollo", "Comida", "HABILITADO", 60.0);
        PersistenciaProductoDTO p2 = crearProductoDTOCompleto(new ObjectId().toHexString(), "Sándwich Vegetariano", "Comida", "HABILITADO", 55.0);
        PersistenciaProductoDTO p3 = crearProductoDTOCompleto(new ObjectId().toHexString(), "Ensalada de Pollo", "Comida", "HABILITADO", 65.0);
        PersistenciaProductoDTO p4 = crearProductoDTOCompleto(new ObjectId().toHexString(), "Sándwich de Jamón", "Desayunos", "HABILITADO", 50.0);

        productoDAO.guardarProducto(p1);
        productoDAO.guardarProducto(p2);
        productoDAO.guardarProducto(p3);
        productoDAO.guardarProducto(p4);

        List<PersistenciaProductoDTO> encontrados = productoDAO.buscarPorNombreYCategoria("Sándwich", "Comida");
        assertEquals(2, encontrados.size(), "Debería encontrar dos sándwiches en Comida.");
        assertTrue(encontrados.stream().allMatch(p -> p.getNombre().toLowerCase().contains("sándwich") && p.getCategoria().equals("Comida")));

        List<PersistenciaProductoDTO> noEncontrados = productoDAO.buscarPorNombreYCategoria("Pizza", "Comida");
        assertTrue(noEncontrados.isEmpty(), "No debería encontrar Pizza en Comida.");
    }

    @Test
    void testBuscarPorNombreYCategoriaConNombreParcial() throws PersistenciaException {
        PersistenciaProductoDTO p1 = crearProductoDTOCompleto(new ObjectId().toHexString(), "Sándwich de Pollo Especial", "Comida", "HABILITADO", 60.0);
        productoDAO.guardarProducto(p1);

        List<PersistenciaProductoDTO> encontrados = productoDAO.buscarPorNombreYCategoria("Pollo Esp", "Comida");
        assertEquals(1, encontrados.size());
        assertEquals("Sándwich de Pollo Especial", encontrados.get(0).getNombre());
    }

    @Test
    void testBuscarPorNombre() throws PersistenciaException {
        String nombreExacto = "Galleta Gigante";
        PersistenciaProductoDTO p1 = crearProductoDTOCompleto(new ObjectId().toHexString(), nombreExacto, "Postres", "HABILITADO", 35.0);
        PersistenciaProductoDTO p2 = crearProductoDTOCompleto(new ObjectId().toHexString(), "Mini Galleta", "Postres", "HABILITADO", 15.0);
        productoDAO.guardarProducto(p1);
        productoDAO.guardarProducto(p2);

        PersistenciaProductoDTO encontrado = productoDAO.buscarPorNombre(nombreExacto);
        assertNotNull(encontrado);
        assertEquals(nombreExacto, encontrado.getNombre());

        PersistenciaProductoDTO noEncontrado = productoDAO.buscarPorNombre("Galleta Inexistente");
        assertNull(noEncontrado);
    }

    @Test
    void testActualizarProducto() throws PersistenciaException {
        String idOriginal = new ObjectId().toHexString();
        PersistenciaProductoDTO productoOriginalDTO = crearProductoDTOCompleto(idOriginal, "Té Verde", "Bebidas", "HABILITADO", 18.0);
        productoDAO.guardarProducto(productoOriginalDTO);

        PersistenciaProductoDTO productoActualizadoDTO = crearProductoDTOCompleto(idOriginal, "Té Verde Matcha", "Bebidas Premium", "HABILITADO", 22.0);
        productoActualizadoDTO.setId(idOriginal);

        productoDAO.actualizarProducto(productoActualizadoDTO);

        PersistenciaProductoDTO productoDesdeBD = productoDAO.buscarPorId(idOriginal);
        assertNotNull(productoDesdeBD);
        assertEquals("Té Verde Matcha", productoDesdeBD.getNombre());
        assertEquals("Bebidas Premium", productoDesdeBD.getCategoria());
        assertEquals(22.0, productoDesdeBD.getPrecioBase());
    }

    @Test
    void testActualizarProductoIdNoExistente() throws PersistenciaException {
        String idInexistente = new ObjectId().toHexString();
        PersistenciaProductoDTO dtoFantasma = crearProductoDTOCompleto(idInexistente, "Fantasma", "Etéreo", "DESHABILITADO", 0);

        assertDoesNotThrow(() -> productoDAO.actualizarProducto(dtoFantasma));

        PersistenciaProductoDTO noDeberiaExistir = productoDAO.buscarPorId(idInexistente);
        assertNull(noDeberiaExistir, "El producto fantasma no debería haber sido creado por actualizar.");
    }
}
