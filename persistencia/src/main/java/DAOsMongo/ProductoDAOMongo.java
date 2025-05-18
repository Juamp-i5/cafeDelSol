package DAOsMongo;

import DTOs.ProductoDTO;
import entidades.Producto;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.IProductoDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.IConexionMongo;
import interfacesMappers.IProductoMapper;
import mappers.IngredienteMapper;
import mappers.ProductoMapper;
import mappers.ProveedorMapper;
import mappers.TamanioMapper;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author rodri
 */
public class ProductoDAOMongo implements IProductoDAO {

    //Singleton
    private static ProductoDAOMongo instancia;
    private final MongoDatabase database;

    private final MongoCollection<Producto> coleccion;
    private final String NOMBRE_COLECCION = "productos";

    private final IProductoMapper productoMapper = new ProductoMapper(new TamanioMapper(new IngredienteMapper()));

    private ProductoDAOMongo(IConexionMongo conexion) {
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Producto.class);
    }

    public static ProductoDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new ProductoDAOMongo(conexion);
        }
        return instancia;
    }

    //Métodos de la coleccion
    @Override
    public List<ProductoDTO> buscarTodos() throws PersistenciaException {
        List<ProductoDTO> productos = new ArrayList<>();
        try (MongoCursor<Producto> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                productos.add(productoMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar todos los productos en la base de datos", e);
        }
        return productos;
    }

    @Override
    public List<ProductoDTO> buscarTodosHabilitados() throws PersistenciaException {
        List<ProductoDTO> productos = new ArrayList<>();
        Bson filtro = Filters.eq("estado", "HABILITADO");
        try (MongoCursor<Producto> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                productos.add(productoMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar todos los productos habilitados en la base de datos", e);
        }
        return productos;
    }

    @Override
    public List<ProductoDTO> buscarPorNombreYCategoria(String filtroNombre, String filtroCategoria) throws PersistenciaException {
        List<ProductoDTO> productos = new ArrayList<>();
        Bson filtro = Filters.and(
                Filters.regex("nombre", filtroNombre, "i"),
                Filters.eq("categoria", filtroCategoria)
        );
        try (MongoCursor<Producto> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                productos.add(productoMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar todos los productos filtrados por nombre y categoria en la base de datos", e);
        }
        return productos;
    }

    @Override
    public ProductoDTO buscarPorId(String id) throws PersistenciaException {
        List<ProductoDTO> productos = new ArrayList<>();
        ObjectId objectId = new ObjectId(id);
        Bson filtro = Filters.eq("_id", objectId);
        try (MongoCursor<Producto> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                productos.add(productoMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar el producto por id en la base de datos");
        }
        if (productos.isEmpty()) {
            return null;
        }
        return productos.get(0);
    }

    @Override
    public ProductoDTO buscarPorNombre(String nombre) throws PersistenciaException {
        List<ProductoDTO> productos = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        try (MongoCursor<Producto> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                productos.add(productoMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar el producto por nombre en la base de datos");
        }
        if (productos.isEmpty()) {
            return null;
        }
        return productos.get(0);
    }

    @Override
    public void guardarProducto(ProductoDTO producto) throws PersistenciaException {
        if (producto == null) {
            throw new PersistenciaException("Error al guardar el producto en la base de datos, el producto no puede ser nulo");
        }

        try {
            coleccion.insertOne(productoMapper.toMongo(producto));
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al guardar el producto en la base de datos", e);
        }
    }

    @Override
    public void actualizarProducto(ProductoDTO producto) throws PersistenciaException {
        ObjectId objectId = new ObjectId(producto.getId());
        Bson filtro = Filters.eq("_id", objectId);
        try {
            coleccion.replaceOne(filtro, productoMapper.toMongo(producto));
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al actualizar el producto en la base de datos", e);
        }
    }
}
