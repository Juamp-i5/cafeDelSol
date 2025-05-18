/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOsMongo;

import DTOs.ProductoDTO;
import DTOs.TamanioDTO;
import DTOs.ProveedorDTO;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import IDAOs.IProductoDAO;

import conexion.IConexionMongo;
import conexion.ConexionMongoPrueba;
import entidades.Producto;
import excepciones.PersistenciaException;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;

/**
 * Pruebas unitarias para ProductoDAOMongo utilizando una base de datos de
 * prueba.
 *
 * @author Jp y Asistente AI
 */
public class ProductoDAOMongoTest {

//    private static IConexionMongo conexionPruebaMongo;
//    private static IProductoDAO productoDAO;
//    private static MongoDatabase databaseTest;
//    private static final String NOMBRE_COLECCION_PRODUCTOS = "productos";
//
//    public ProductoDAOMongoTest() {
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//        System.out.println("--- INICIANDO PRUEBAS GLOBALES PARA ProductoDAOMongo ---");
//        conexionPruebaMongo = ConexionMongoPrueba.getInstance();
//        databaseTest = conexionPruebaMongo.getDatabase();
//        productoDAO = ProductoDAOMongo.getInstance(conexionPruebaMongo);
//        System.out.println("Conexión de prueba y DAO inicializados para ProductoDAOMongoTest.");
//        System.out.println("Usando base de datos de prueba: " + databaseTest.getName());
//    }
//
//    @AfterAll
//    public static void tearDownClass() {
//        System.out.println("--- FINALIZANDO PRUEBAS GLOBALES PARA ProductoDAOMongo ---");
//        if (databaseTest != null) {
//            try {
//                System.out.println("Limpiando la colección de productos de prueba: " + NOMBRE_COLECCION_PRODUCTOS);
//                databaseTest.getCollection(NOMBRE_COLECCION_PRODUCTOS).drop();
//                System.out.println("Colección de productos de prueba eliminada.");
//            } catch (Exception e) {
//                System.err.println("Error al eliminar la colección de prueba '" + NOMBRE_COLECCION_PRODUCTOS + "': " + e.getMessage());
//            }
//        }
//        ConexionMongoPrueba.clearInstance();
//        System.out.println("Instancia de conexión de prueba limpiada.");
//    }
//
//    @BeforeEach
//    public void setUp() {
//        System.out.println("===> INICIO PRUEBA: " + Thread.currentThread().getStackTrace()[2].getMethodName());
//        MongoCollection<Producto> coleccion = databaseTest.getCollection(NOMBRE_COLECCION_PRODUCTOS, Producto.class);
//        long countBeforeDelete = coleccion.countDocuments();
//        if (countBeforeDelete > 0) {
//            coleccion.deleteMany(Filters.empty());
//        }
//        System.out.println("Colección '" + NOMBRE_COLECCION_PRODUCTOS + "' limpia para la prueba.");
//    }
//
//    @AfterEach
//    public void tearDown() {
//        System.out.println("<=== FIN PRUEBA: " + Thread.currentThread().getStackTrace()[2].getMethodName());
//    }
//
//    private ProductoDTO crearProductoDTODePrueba(
//            String idProducto, String nombreProducto, String categoriaProducto, double precioBaseProducto, String estadoProducto,
//            String nombreTamanio, double precioAdicionalTamanio,
//            String nombreIngrediente, String unidadMedidaIng, double cantidadDispIng, double cantidadMinIng,
//            String nombreProveedor) {
//
//        ProductoDTO productoDTO = new ProductoDTO();
//        if (idProducto != null && ObjectId.isValid(idProducto)) {
//            productoDTO.setId(idProducto);
//        }
//        productoDTO.setNombre(nombreProducto);
//        productoDTO.setCategoria(categoriaProducto);
//        productoDTO.setPrecioBase(precioBaseProducto);
//        productoDTO.setEstado(estadoProducto);
//        productoDTO.setImageData(new byte[]{1, 2, 3});
//
//        // Crear ProveedorDTO
//        ProveedorDTO proveedorDTO = null;
//        if (nombreProveedor != null) {
//            proveedorDTO = new ProveedorDTO();
//            proveedorDTO.setNombre(nombreProveedor);
//        }
//
//        // Crear IngredienteDTO
//        IngredienteDTOPersistencia ingredienteDTO = null;
//        if (nombreIngrediente != null) {
//            ingredienteDTO = new IngredienteDTOPersistencia();
//            ingredienteDTO.setNombre(nombreIngrediente);
//            ingredienteDTO.setCantidadDisponible(cantidadDispIng);
//            ingredienteDTO.setCantidadMinima(cantidadMinIng);
//            ingredienteDTO.setUnidadMedida("");
//            ingredienteDTO.setNivelStock("");
//        }
//
//        List<IngredienteDTOPersistencia> ingredientesList = new ArrayList<>();
//        if (ingredienteDTO != null) {
//            ingredientesList.add(ingredienteDTO);
//        }
//
//        // Crear TamanioDTO
//        TamanioDTO tamanioDTO = null;
//        if (nombreTamanio != null) {
//            tamanioDTO = new TamanioDTO();
//            tamanioDTO.setNombre(nombreTamanio);
//            tamanioDTO.setPrecioAdicional(precioAdicionalTamanio);
//            tamanioDTO.setIngredientes(ingredientesList);
//        }
//
//        List<TamanioDTO> tamaniosList = new ArrayList<>();
//        if (tamanioDTO != null) {
//            tamaniosList.add(tamanioDTO);
//        }
//        productoDTO.setTamanios(tamaniosList);
//
//        return productoDTO;
//    }
//
//    @Test
//    @DisplayName("DAO001: Prueba de obtención de instancia (Singleton) del DAO")
//    public void testGetInstanceDAOSingleton() {
//        ProductoDAOMongo instancia1 = ProductoDAOMongo.getInstance(conexionPruebaMongo);
//        ProductoDAOMongo instancia2 = ProductoDAOMongo.getInstance(conexionPruebaMongo);
//        assertNotNull(instancia1);
//        assertSame(instancia1, instancia2);
//    }
//
//    @Test
//    @DisplayName("DAO002: Buscar todos los productos")
//    public void testBuscarTodos() throws PersistenciaException {
//        productoDAO.guardarProducto(crearProductoDTODePrueba(null, "Café Americano", "BEBIDAS", 30.0, "HABILITADO", "Mediano", 5.0, "Café Grano", "g", 1000, 200, "Cafetales La Sierra"));
//        productoDAO.guardarProducto(crearProductoDTODePrueba(null, "Pastel de Chocolate", "POSTRES", 50.0, "HABILITADO", "Rebanada", 0.0, "Chocolate Oscuro", "g", 500, 100, "Chocolates Finos"));
//
//        List<ProductoDTO> result = productoDAO.buscarTodos();
//        assertNotNull(result);
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    @DisplayName("DAO003: Buscar todos los productos cuando la colección está vacía")
//    public void testBuscarTodosColeccionVacia() throws PersistenciaException {
//        List<ProductoDTO> result = productoDAO.buscarTodos();
//        assertNotNull(result);
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    @DisplayName("DAO004: Buscar todos los productos habilitados")
//    public void testBuscarTodosHabilitados() throws PersistenciaException {
//        productoDAO.guardarProducto(crearProductoDTODePrueba(null, "Jugo de Naranja", "BEBIDAS", 25.0, "HABILITADO", "Grande", 0.0, "Naranja Fresca", "unidad", 50, 10, "Frutas del Valle"));
//        productoDAO.guardarProducto(crearProductoDTODePrueba(null, "Galleta de Avena", "PANADERIA", 15.0, "DESHABILITADO", "Pieza", 0.0, "Avena", "g", 300, 50, "Granos Selectos"));
//        productoDAO.guardarProducto(crearProductoDTODePrueba(null, "Sandwich de Pavo", "ALIMENTOS", 45.0, "HABILITADO", "Completo", 0.0, "Pechuga Pavo", "g", 200, 50, "Embutidos Premium"));
//
//        List<ProductoDTO> result = productoDAO.buscarTodosHabilitados();
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertTrue(result.stream().allMatch(p -> p.getEstado().equals("HABILITADO")));
//    }
//
//    @Test
//    @DisplayName("DAO005: Buscar productos por nombre y categoría")
//    public void testBuscarPorNombreYCategoria() throws PersistenciaException {
//        productoDAO.guardarProducto(crearProductoDTODePrueba(null, "Frappe Oreo", "BEBIDAS FRIAS", 55.0, "HABILITADO", "Grande", 5.0, "Galleta Oreo", "unidad", 20, 5, "Dulces Famosos"));
//        productoDAO.guardarProducto(crearProductoDTODePrueba(null, "Frappe Caramelo", "BEBIDAS FRIAS", 55.0, "HABILITADO", "Grande", 5.0, "Salsa Caramelo", "ml", 100, 20, "Salsas Dulces"));
//        productoDAO.guardarProducto(crearProductoDTODePrueba(null, "Torta de Oreo", "POSTRES", 60.0, "HABILITADO", "Rebanada", 0.0, "Galleta Oreo", "unidad", 30, 10, "Dulces Famosos"));
//
//        List<ProductoDTO> result = productoDAO.buscarPorNombreYCategoria("Frappe", "BEBIDAS FRIAS");
//        assertNotNull(result);
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    @DisplayName("DAO006: Buscar producto por ID existente con detalles anidados")
//    public void testBuscarPorIdExistenteConDetalles() throws PersistenciaException {
//        ProductoDTO productoOriginal = crearProductoDTODePrueba(null, "Té Chai", "BEBIDAS CALIENTES", 35.0, "HABILITADO", "Regular", 0.0, "Mezcla Chai", "g", 100, 20, "Especias Orientales");
//        productoDAO.guardarProducto(productoOriginal);
//
//        MongoCollection<Producto> coleccion = databaseTest.getCollection(NOMBRE_COLECCION_PRODUCTOS, Producto.class);
//        Producto productoEnDB = coleccion.find(Filters.eq("nombre", "Té Chai")).first();
//        assertNotNull(productoEnDB, "El producto no se guardó para la prueba.");
//        String idGenerado = productoEnDB.getId().toHexString();
//
//        ProductoDTO result = productoDAO.buscarPorId(idGenerado);
//        assertNotNull(result);
//        assertEquals(idGenerado, result.getId());
//        assertEquals("Té Chai", result.getNombre());
//        assertEquals(35.0, result.getPrecioBase());
//        assertNotNull(result.getTamanios());
//        assertFalse(result.getTamanios().isEmpty(), "La lista de tamaños no debería estar vacía.");
//        assertEquals(1, result.getTamanios().size());
//
//        TamanioDTO tamanioRecuperado = result.getTamanios().get(0);
//        assertEquals("Regular", tamanioRecuperado.getNombre());
//        assertEquals(0.0, tamanioRecuperado.getPrecioAdicional());
//        assertNotNull(tamanioRecuperado.getIngredientes());
//        assertFalse(tamanioRecuperado.getIngredientes().isEmpty(), "La lista de ingredientes del tamaño no debería estar vacía.");
//        assertEquals(1, tamanioRecuperado.getIngredientes().size());
//
//        IngredienteDTOPersistencia ingredienteRecuperado = tamanioRecuperado.getIngredientes().get(0);
//        assertEquals("Mezcla Chai", ingredienteRecuperado.getNombre());
////        assertNotNull(ingredienteRecuperado.getProveedor());
////        assertEquals("Especias Orientales", ingredienteRecuperado.getProveedor().getNombre());
//    }
//
//    @Test
//    @DisplayName("DAO007: Buscar producto por ID inexistente")
//    public void testBuscarPorIdInexistente() throws PersistenciaException {
//        String idFalso = new ObjectId().toHexString();
//        ProductoDTO result = productoDAO.buscarPorId(idFalso);
//        assertNull(result);
//    }
//
//    @Test
//    @DisplayName("DAO008: Buscar producto por ID con formato inválido")
//    public void testBuscarPorIdFormatoInvalido() {
//        String idInvalido = "formatoInvalido";
//        assertThrows(IllegalArgumentException.class, () -> productoDAO.buscarPorId(idInvalido));
//    }
//
//    @Test
//    @DisplayName("DAO009: Guardar un nuevo producto con detalles anidados")
//    public void testGuardarProductoConDetalles() throws PersistenciaException {
//        ProductoDTO nuevoProducto = crearProductoDTODePrueba(null, "Latte Vainilla", "BEBIDAS CALIENTES", 40.0, "HABILITADO", "Grande", 7.0, "Jarabe Vainilla", "ml", 50, 10, "Saborizantes Premium");
//
//        assertDoesNotThrow(() -> productoDAO.guardarProducto(nuevoProducto));
//
//        MongoCollection<Producto> coleccion = databaseTest.getCollection(NOMBRE_COLECCION_PRODUCTOS, Producto.class);
//        Producto productoGuardadoDB = coleccion.find(Filters.eq("nombre", "Latte Vainilla")).first();
//        assertNotNull(productoGuardadoDB);
//        assertEquals("Latte Vainilla", productoGuardadoDB.getNombre());
//        assertEquals(40.0, productoGuardadoDB.getPrecioBase());
//        ProductoDTO productoRecuperado = productoDAO.buscarPorId(productoGuardadoDB.getId().toHexString());
//        assertNotNull(productoRecuperado);
//        assertFalse(productoRecuperado.getTamanios().isEmpty());
//        assertEquals("Grande", productoRecuperado.getTamanios().get(0).getNombre());
//        assertFalse(productoRecuperado.getTamanios().get(0).getIngredientes().isEmpty());
//        assertEquals("Jarabe Vainilla", productoRecuperado.getTamanios().get(0).getIngredientes().get(0).getNombre());
////        assertEquals("Saborizantes Premium", productoRecuperado.getTamanios().get(0).getIngredientes().get(0).getProveedor().getNombre());
//
//    }
//
//    @Test
//    @DisplayName("DAO010: Guardar producto con DTO nulo")
//    public void testGuardarProductoNulo() {
//        ProductoDTO productoNulo = null;
//        assertThrows(PersistenciaException.class, () -> productoDAO.guardarProducto(productoNulo));
//    }
//
//    @Test
//    @DisplayName("DAO011: Actualizar un producto existente con detalles anidados")
//    public void testActualizarProductoConDetalles() throws PersistenciaException {
//        ProductoDTO original = crearProductoDTODePrueba(null, "Mocha Blanco", "BEBIDAS CALIENTES", 45.0, "HABILITADO", "Mediano", 5.0, "Chocolate Blanco", "g", 50, 10, "Chocolates Especiales");
//        productoDAO.guardarProducto(original);
//
//        MongoCollection<Producto> coleccion = databaseTest.getCollection(NOMBRE_COLECCION_PRODUCTOS, Producto.class);
//        Producto productoEnDB = coleccion.find(Filters.eq("nombre", "Mocha Blanco")).first();
//        assertNotNull(productoEnDB);
//        String idProducto = productoEnDB.getId().toHexString();
//
//        ProductoDTO actualizado = crearProductoDTODePrueba(idProducto, "Mocha Blanco Supremo", "ESPECIALES", 50.0, "DESHABILITADO",
//                "Grande", 8.0,
//                "Chocolate Blanco Premium", "g", 60, 15,
//                "Chocolates de Lujo");
//        actualizado.setId(idProducto); // Asegurar el ID
//
//        productoDAO.actualizarProducto(actualizado);
//
//        ProductoDTO productoVerificado = productoDAO.buscarPorId(idProducto);
//        assertNotNull(productoVerificado);
//        assertEquals(idProducto, productoVerificado.getId());
//        assertEquals("Mocha Blanco Supremo", productoVerificado.getNombre());
//        assertEquals("ESPECIALES", productoVerificado.getCategoria());
//        assertEquals(50.0, productoVerificado.getPrecioBase());
//        assertEquals("DESHABILITADO", productoVerificado.getEstado());
//
//        assertNotNull(productoVerificado.getTamanios());
//        assertFalse(productoVerificado.getTamanios().isEmpty());
//        TamanioDTO tamanioAct = productoVerificado.getTamanios().get(0);
//        assertEquals("Grande", tamanioAct.getNombre());
//        assertEquals(8.0, tamanioAct.getPrecioAdicional());
//
//        assertNotNull(tamanioAct.getIngredientes());
//        assertFalse(tamanioAct.getIngredientes().isEmpty());
//        IngredienteDTOPersistencia ingAct = tamanioAct.getIngredientes().get(0);
//        assertEquals("Chocolate Blanco Premium", ingAct.getNombre());
//
////        assertNotNull(ingAct.getProveedor());
////        assertEquals("Chocolates de Lujo", ingAct.getProveedor().getNombre());
//    }
//
//    @Test
//    @DisplayName("DAO012: Actualizar producto con ID inexistente")
//    public void testActualizarProductoIdInexistente() throws PersistenciaException {
//        String idFalso = new ObjectId().toHexString();
//        ProductoDTO productoFantasma = crearProductoDTODePrueba(idFalso, "Fantasma", "INEXISTENTE", 10.0, "HABILITADO", "Unico", 0.0, "Aire", "L", 1, 0, "Nadie");
//        productoFantasma.setId(idFalso);
//
//        assertDoesNotThrow(() -> productoDAO.actualizarProducto(productoFantasma));
//        assertNull(productoDAO.buscarPorId(idFalso));
//    }
//
//    @Test
//    @DisplayName("DAO013: Actualizar producto con ID de formato inválido")
//    public void testActualizarProductoIdFormatoInvalido() {
//        String idInvalido = "idNoValido";
//        ProductoDTO productoConIdInvalido = crearProductoDTODePrueba(idInvalido, "Test", "Test Cat", 10.0, "HABILITADO", "Test Tam", 0.0, "Test Ing", "u", 1, 0, "Test Prov");
//        productoConIdInvalido.setId(idInvalido);
//
//        assertThrows(IllegalArgumentException.class, () -> productoDAO.actualizarProducto(productoConIdInvalido));
//    }
}
