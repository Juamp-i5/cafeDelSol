package DAOsMongo;

import DTOs.PersistenciaProductoDTO;
import entidades.Producto;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.IProductoDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UnwindOptions;
import conexion.IConexionMongo;
import interfacesMappers.IProductoMapper;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import utils.DependencyInjectors;

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

    private final IProductoMapper productoMapper = DependencyInjectors.getInstancia().getProductoMapper();

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
    public List<PersistenciaProductoDTO> buscarTodos() throws PersistenciaException {
        List<PersistenciaProductoDTO> productos = new ArrayList<>();
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
    public List<PersistenciaProductoDTO> buscarTodosHabilitados() throws PersistenciaException {
        List<PersistenciaProductoDTO> productos = new ArrayList<>();
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
    public List<PersistenciaProductoDTO> buscarPorNombreYCategoria(String filtroNombre, String filtroCategoria) throws PersistenciaException {
        List<PersistenciaProductoDTO> dtosResultantes = new ArrayList<>();
        List<Bson> pipeline = new ArrayList<>();

        // Stage 1: $match inicial en la colección de productos
        Bson filtroMatch;
        String nombrePattern = filtroNombre == null ? "" : filtroNombre;
        if (filtroCategoria == null || filtroCategoria.trim().isEmpty()) {
            filtroMatch = Filters.regex("nombre", nombrePattern, "i");
        } else {
            filtroMatch = Filters.and(
                    Filters.regex("nombre", nombrePattern, "i"),
                    Filters.eq("categoria", filtroCategoria)
            );
        }
        pipeline.add(Aggregates.match(filtroMatch));

        // Stage 2: $unwind el array 'tamanios' del producto (el que tiene tamanioId e ingredientes[])
        pipeline.add(Aggregates.unwind("$tamanios", new UnwindOptions().preserveNullAndEmptyArrays(true)));

        // Stage 3: $lookup para traer los detalles del Tamanio desde la colección global "tamanios"
        pipeline.add(Aggregates.lookup(
                "tamanios",
                "tamanios.tamanio._id",
                "_id",
                "tamanioCompletoArr"
        ));

        // Stage 3.1: $unwind el resultado del lookup de tamaño.
        pipeline.add(Aggregates.unwind("$tamanioCompletoArr", new UnwindOptions().preserveNullAndEmptyArrays(true)));

        // Stage 4: $group para reconstruir el producto con su lista de ProductoTamanio.
        pipeline.add(Aggregates.group("$_id", // Agrupar por el _id original del producto
                Accumulators.first("nombre", "$nombre"),
                Accumulators.first("descripcion", "$descripcion"),
                Accumulators.first("categoria", "$categoria"),
                Accumulators.first("estado", "$estado"),
                Accumulators.first("precioBase", "$precioBase"),
                Accumulators.first("imageData", "$imageData"),
                Accumulators.push("tamaniosReconstruidos", // Este array debe contener objetos que mapeen a ProductoTamanio
                        // Construimos un documento que se asemeja a 'entidades.ProductoTamanio'
                        new Document("tamanio", "$tamanioCompletoArr") // El objeto Tamanio completo del lookup
                                .append("ingredientes", "$tamanios.ingredientes") // La lista original de {ingredienteId, cantidad}
                )
        ));

        // Stage 5: $project final para que los nombres de campo coincidan con 'entidades.Producto'
        pipeline.add(Aggregates.project(
                Projections.fields(
                        Projections.computed("id", "$_id"), // Mapea _id del grupo al campo 'id' de tu entidad Producto
                        Projections.include(
                                "nombre", "descripcion", "categoria", "estado",
                                "precioBase", "imageData"),
                        Projections.computed("tamanios", "$tamaniosReconstruidos") // El array de ProductoTamanio
                )
        ));

        try (MongoCursor<Producto> cursor
                = coleccion.aggregate(pipeline, Producto.class).iterator()) {
            while (cursor.hasNext()) {
                Producto productoEntity = cursor.next();
                dtosResultantes.add(productoMapper.toDTO(productoEntity));
            }
        } catch (Exception e) {
            System.err.println("Error durante la agregación en buscarPorNombreYCategoria (solo lookup tamaños). Pipeline:");
            for (Bson stage : pipeline) {
                try {
                    System.err.println(stage.toBsonDocument(Document.class, com.mongodb.MongoClientSettings.getDefaultCodecRegistry()).toJson());
                } catch (Exception exJson) {
                    System.err.println("Error al convertir stage a JSON: " + stage.toString());
                }
            }
            throw new PersistenciaException("Error al consultar productos (nombre/cat) con agregación (solo lookup tamaños): " + e.getMessage(), e);
        }
        return dtosResultantes;
    }

    @Override
    public PersistenciaProductoDTO buscarPorId(String id) throws PersistenciaException {
        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("ID de producto inválido: " + id, e);
        }

        List<Bson> pipeline = new ArrayList<>();

        // Stage 1: $match - Encontrar el producto específico por su _id
        pipeline.add(Aggregates.match(Filters.eq("_id", objectId)));

        // Stage 2: $unwind el array 'tamanios' del producto
        pipeline.add(Aggregates.unwind("$tamanios", new UnwindOptions().preserveNullAndEmptyArrays(true)));

        // Stage 3: $lookup para traer los detalles del Tamanio desde la colección global "tamanios"
        pipeline.add(Aggregates.lookup(
                "tamanios",
                "tamanios.tamanio._id",
                "_id",
                "tamanioLookupArr"
        ));
        pipeline.add(Aggregates.unwind("$tamanioLookupArr", new UnwindOptions().preserveNullAndEmptyArrays(true)));

        // Stage 4: $unwind el array 'ingredientes' dentro de cada 'tamanios' del producto
        pipeline.add(Aggregates.unwind("$tamanios.ingredientes", new UnwindOptions().preserveNullAndEmptyArrays(true)));

        // Stage 5: $lookup para traer los detalles del Ingrediente desde la colección "Ingredientes"
        pipeline.add(Aggregates.lookup(
                "ingredientes",
                "tamanios.ingredientes.ingrediente._id",
                "_id",
                "ingredienteLookupArr"
        ));
        pipeline.add(Aggregates.unwind("$ingredienteLookupArr", new UnwindOptions().preserveNullAndEmptyArrays(true)));

        // Stage 6: $group para reconstruir la lista de ingredientes detallados para CADA TAMAÑO ESPECÍFICO DEL PRODUCTO
        Bson groupIngredientesId = new Document("originalProductId", "$_id")
                .append("tamanioObjetoDelLookup", "$tamanioLookupArr"); // El OBJETO Tamanio completo

        pipeline.add(Aggregates.group(groupIngredientesId,
                Accumulators.first("camposOriginalesDelProducto",
                        new Document("id", "$_id")
                                .append("nombre", "$nombre")
                                .append("descripcion", "$descripcion")
                                .append("categoria", "$categoria")
                                .append("estado", "$estado")
                                .append("precioBase", "$precioBase")
                                .append("imageData", "$imageData")
                ),
                Accumulators.push("listaDeIngredientesParaEsteTamanio",
                        new Document("ingrediente", "$ingredienteLookupArr") // Objeto Ingrediente completo del lookup
                                .append("cantidad", "$tamanios.ingredientes.cantidad")
                )
        ));

        // Stage 7: $group de nuevo para reconstruir el Producto con su List<ProductoTamanio>
        pipeline.add(Aggregates.group("$_id.originalProductId", // Agrupamos por el ID original del producto
                Accumulators.first("nombre", "$camposOriginalesDelProducto.nombre"),
                Accumulators.first("descripcion", "$camposOriginalesDelProducto.descripcion"),
                Accumulators.first("categoria", "$camposOriginalesDelProducto.categoria"),
                Accumulators.first("estado", "$camposOriginalesDelProducto.estado"),
                Accumulators.first("precioBase", "$camposOriginalesDelProducto.precioBase"),
                Accumulators.first("imageData", "$camposOriginalesDelProducto.imageData"),
                Accumulators.push("tamaniosConSusIngredientes",
                        new Document("tamanio", "$_id.tamanioObjetoDelLookup") // Objeto Tamanio completo
                                .append("ingredientes", "$listaDeIngredientesParaEsteTamanio") // Lista de ingredientes detallados
                )
        ));

        // Stage 8: $project final para que los nombres de campo coincidan con entidad.Producto
        pipeline.add(Aggregates.project(
                Projections.fields(
                        Projections.computed("id", "$_id"),
                        Projections.include(
                                "nombre", "descripcion", "categoria", "estado",
                                "precioBase", "imageData"),
                        Projections.computed("tamanios", "$tamaniosConSusIngredientes")
                )
        ));

        try {
            entidades.Producto productoEntity = coleccion.aggregate(pipeline, entidades.Producto.class).first();

            if (productoEntity == null) {
                return null;
            }
            return productoMapper.toDTO(productoEntity);

        } catch (Exception e) {
            System.err.println("Error durante la agregación en buscarPorId. Pipeline:");
            for (Bson stage : pipeline) {
                try {
                    System.err.println(stage.toBsonDocument(Document.class, com.mongodb.MongoClientSettings.getDefaultCodecRegistry()).toJson());
                } catch (Exception exJson) {
                    System.err.println("Error al convertir stage a JSON: " + stage.toString());
                }
            }
            throw new PersistenciaException("Error al buscar producto por ID con agregación completa: " + e.getMessage(), e);
        }
    }

    @Override
    public PersistenciaProductoDTO buscarPorNombre(String nombre) throws PersistenciaException {
        List<PersistenciaProductoDTO> productos = new ArrayList<>();
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
    public void guardarProducto(PersistenciaProductoDTO producto) throws PersistenciaException {
        if (producto == null) {
            throw new PersistenciaException("Error al guardar el producto en la base de datos, el producto no puede ser nulo");
        }
        Producto entidad = productoMapper.toEntity(producto);
        if (producto.getId() == null || producto.getId().trim().equals("")) {
            entidad.setId(new ObjectId());
            producto.setId(entidad.getId().toHexString());
        }
        try {
            coleccion.insertOne(entidad);
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar el producto en la base de datos", e);
        }
    }

    @Override
    public void actualizarProducto(PersistenciaProductoDTO producto) throws PersistenciaException {
        if (producto == null) {
            throw new PersistenciaException("Error al actualizar el producto en la base de datos, el producto no puede ser nulo");
        }
        ObjectId objectId = new ObjectId(producto.getId());
        Bson filtro = Filters.eq("_id", objectId);
        try {
            coleccion.replaceOne(filtro, productoMapper.toEntity(producto));
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar el producto en la base de datos", e);
        }
    }

    @Override
    public void restarIngredientes(String idProducto, String idTamanio) throws PersistenciaException {
        
    }
}
