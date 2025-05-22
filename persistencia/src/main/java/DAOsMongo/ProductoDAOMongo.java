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
import entidades.ProductoTamanio;
import entidades.ProductoTamanioIngrediente;
import interfacesMappers.IProductoMapper;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import utils.DependencyInjectors;

/**
 * Implementación de {@link IProductoDAO} para MongoDB. Esta clase maneja las
 * operaciones de acceso a datos para las entidades {@link Producto},
 * interactuando con una colección de MongoDB. Sigue un patrón Singleton para la
 * gestión de instancias.
 *
 * @author rodri
 */
public class ProductoDAOMongo implements IProductoDAO {

    /**
     * Instancia única de ProductoDAOMongo (Patrón Singleton).
     */
    private static ProductoDAOMongo instancia;
    /**
     * Conexión a la base de datos MongoDB.
     */
    private final MongoDatabase database;

    /**
     * Colección de MongoDB donde se almacenan los documentos de productos.
     */
    private final MongoCollection<Producto> coleccion;
    /**
     * Nombre de la colección de productos en MongoDB.
     */
    private final String NOMBRE_COLECCION = "productos";

    /**
     * Mapper para convertir entre entidades {@link Producto} y DTOs
     * {@link PersistenciaProductoDTO}. Obtenido a través de un inyector de
     * dependencias.
     */
    private final IProductoMapper productoMapper = DependencyInjectors.getInstancia().getProductoMapper();

    /**
     * Constructor privado para implementar el patrón Singleton. Inicializa la
     * conexión a la base de datos y obtiene la colección de productos.
     *
     * @param conexion Implementación de {@link IConexionMongo} para obtener la
     * conexión a la base de datos.
     */
    private ProductoDAOMongo(IConexionMongo conexion) {
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Producto.class);
    }

    /**
     * Obtiene la instancia única de {@link ProductoDAOMongo} (Patrón
     * Singleton). Si la instancia no existe, la crea utilizando la conexión
     * proporcionada.
     *
     * @param conexion Implementación de {@link IConexionMongo} para la conexión
     * a la base de datos.
     * @return La instancia única de {@link ProductoDAOMongo}.
     */
    public static ProductoDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new ProductoDAOMongo(conexion);
        }
        return instancia;
    }

    /**
     * Busca y devuelve todos los productos almacenados en la base de datos.
     *
     * @return Una lista de {@link PersistenciaProductoDTO} que representan
     * todos los productos. La lista estará vacía si no hay productos.
     * @throws PersistenciaException Si ocurre un error durante la consulta a la
     * base de datos.
     */
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

    /**
     * Busca y devuelve todos los productos que están en estado "HABILITADO" y
     * para los cuales existe al menos una configuración de tamaño
     * ({@link ProductoTamanio}) con stock suficiente para todos sus
     * ingredientes requeridos ({@link ProductoTamanioIngrediente}).
     * <p>
     * Para cada producto habilitado, itera sobre sus tamaños. Para cada tamaño,
     * verifica si todos los ingredientes de su receta tienen
     * {@code cantidadDisponible} en la colección "ingredientes" mayor o igual a
     * la {@code cantidadNecesaria}. Si al menos un tamaño cumple esta
     * condición, el producto se incluye en los resultados.
     *
     * @return Una lista de {@link PersistenciaProductoDTO} que representan los
     * productos habilitados con stock suficiente. La lista estará vacía si no
     * se encuentran productos.
     * @throws PersistenciaException Si ocurre un error durante la consulta a la
     * base de datos o al verificar el stock de los ingredientes.
     */
    @Override
    public List<PersistenciaProductoDTO> buscarTodosHabilitadosConStock() throws PersistenciaException {
        List<PersistenciaProductoDTO> productos = new ArrayList<>();
        Bson filtro = Filters.eq("estado", "HABILITADO");

        try (MongoCursor<Producto> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Producto producto = cursor.next();
                boolean alMenosUnTamanioConStock = false;

                if (producto.getTamanios() == null) {
                    continue; // Saltar si no hay tamaños definidos
                }
                for (ProductoTamanio tamanio : producto.getTamanios()) {
                    if (tamanio.getIngredientes() == null || tamanio.getIngredientes().isEmpty()) {
                        // Si un tamaño no tiene ingredientes, se considera con stock (ej. Agua Embotellada)
                        alMenosUnTamanioConStock = true;
                        break;
                    }
                    boolean stockSuficienteParaEsteTamanio = true;
                    for (ProductoTamanioIngrediente ic : tamanio.getIngredientes()) {
                        if (ic.getIngrediente() == null || ic.getIngrediente().getId() == null || ic.getCantidad() == null) {
                            stockSuficienteParaEsteTamanio = false; // Dato de receta inválido
                            break;
                        }
                        ObjectId idIngrediente = ic.getIngrediente().getId();
                        Double cantidadNecesaria = ic.getCantidad();

                        Document ingredienteDoc = database.getCollection("ingredientes") // Asume nombre de colección "ingredientes"
                                .find(Filters.eq("_id", idIngrediente))
                                .first();

                        if (ingredienteDoc == null) {
                            stockSuficienteParaEsteTamanio = false; // Ingrediente no encontrado en stock
                            break;
                        }

                        Object cantidadDisponibleObj = ingredienteDoc.get("cantidadDisponible");
                        Double cantidadDisponible;

                        if (cantidadDisponibleObj instanceof Number) {
                            cantidadDisponible = ((Number) cantidadDisponibleObj).doubleValue();
                        } else {
                            stockSuficienteParaEsteTamanio = false; // Tipo de dato incorrecto o campo faltante
                            break;
                        }

                        if (cantidadDisponible < cantidadNecesaria) {
                            stockSuficienteParaEsteTamanio = false; // Stock insuficiente
                            break;
                        }
                    }

                    if (stockSuficienteParaEsteTamanio) {
                        alMenosUnTamanioConStock = true;
                        break; // Encontrado un tamaño con stock, el producto es válido
                    }
                }

                if (alMenosUnTamanioConStock) {
                    productos.add(productoMapper.toDTO(producto));
                }
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar productos habilitados con stock suficiente", e);
        }
        return productos;
    }

    /**
     * Busca productos utilizando un pipeline de agregación de MongoDB. Los
     * productos se filtran por un patrón de nombre (case-insensitive) y,
     * opcionalmente, por una categoría exacta. La agregación también realiza un
     * {@code $lookup} a la colección "tamanios" para enriquecer cada
     * {@link ProductoTamanio} dentro del producto con el objeto
     * {@link entidades.Tamanio} completo.
     *
     * @param filtroNombre Parte del nombre del producto a buscar. Si es
     * {@code null} o vacío, no se filtra por nombre de manera restrictiva
     * (coincidirá con cualquier nombre).
     * @param filtroCategoria La categoría exacta del producto a buscar. Si es
     * {@code null} o vacía, no se filtra por categoría.
     * @return Una lista de {@link PersistenciaProductoDTO} que coinciden con
     * los criterios de búsqueda. La lista estará vacía si no se encuentran
     * productos.
     * @throws PersistenciaException Si ocurre un error durante la ejecución de
     * la agregación.
     */
    @Override
    public List<PersistenciaProductoDTO> buscarPorNombreYCategoria(String filtroNombre, String filtroCategoria) throws PersistenciaException {
        List<PersistenciaProductoDTO> dtosResultantes = new ArrayList<>();
        List<Bson> pipeline = new ArrayList<>();

        // Stage 1: $match inicial
        String nombrePattern = (filtroNombre == null || filtroNombre.trim().isEmpty()) ? ".*" : filtroNombre; // ".*" para cualquier cosa si es vacío
        Bson filtroMatch;
        if (filtroCategoria == null || filtroCategoria.trim().isEmpty()) {
            filtroMatch = Filters.regex("nombre", nombrePattern, "i"); // 'i' para case-insensitive
        } else {
            filtroMatch = Filters.and(
                    Filters.regex("nombre", nombrePattern, "i"),
                    Filters.eq("categoria", filtroCategoria)
            );
        }
        pipeline.add(Aggregates.match(filtroMatch));

        // Stage 2: $unwind el array 'tamanios' (lista de ProductoTamanio)
        pipeline.add(Aggregates.unwind("$tamanios", new UnwindOptions().preserveNullAndEmptyArrays(true)));

        // Stage 3: $lookup para obtener los detalles del Tamanio desde la colección "tamanios"
        pipeline.add(Aggregates.lookup(
                "tamanios",
                "tamanios.tamanio._id",
                "_id",
                "tamanioCompletoArr"
        ));
        pipeline.add(Aggregates.unwind("$tamanioCompletoArr", new UnwindOptions().preserveNullAndEmptyArrays(true))); // Desanidar el resultado del lookup

        // Stage 4: $group para reconstruir el producto con su lista de ProductoTamanio
        pipeline.add(Aggregates.group("$_id",
                Accumulators.first("nombre", "$nombre"),
                Accumulators.first("descripcion", "$descripcion"),
                Accumulators.first("categoria", "$categoria"),
                Accumulators.first("estado", "$estado"),
                Accumulators.first("precioBase", "$precioBase"),
                Accumulators.first("imageData", "$imageData"),
                Accumulators.push("tamaniosReconstruidos",
                        new Document("tamanio", "$tamanioCompletoArr") // Objeto Tamanio completo
                                .append("ingredientes", "$tamanios.ingredientes") // Lista original de ingredientes de ProductoTamanio
                )
        ));

        // Stage 5: $project final para que coincida con la entidad Producto
        pipeline.add(Aggregates.project(
                Projections.fields(
                        Projections.computed("id", "$_id"), // Renombrar _id a id si la entidad usa 'id'
                        Projections.include("nombre", "descripcion", "categoria", "estado", "precioBase", "imageData"),
                        Projections.computed("tamanios", "$tamaniosReconstruidos")
                )
        ));

        try (MongoCursor<Producto> cursor = coleccion.aggregate(pipeline, Producto.class).iterator()) {
            while (cursor.hasNext()) {
                Producto productoEntity = cursor.next();
                dtosResultantes.add(productoMapper.toDTO(productoEntity));
            }
        } catch (Exception e) {
            // Log detallado del pipeline para depuración
            System.err.println("Error durante la agregación en buscarPorNombreYCategoria. Pipeline:");
            for (Bson stage : pipeline) {
                try {
                    System.err.println(stage.toBsonDocument(Document.class, com.mongodb.MongoClientSettings.getDefaultCodecRegistry()).toJson());
                } catch (Exception exJson) {
                    System.err.println("Error al convertir stage a JSON: " + stage.toString());
                }
            }
            throw new PersistenciaException("Error al consultar productos por nombre/categoría con agregación: " + e.getMessage(), e);
        }
        return dtosResultantes;
    }

    /**
     * Busca un producto específico por su ID utilizando un pipeline de
     * agregación de MongoDB. Este método está diseñado para cargar
     * completamente el producto, incluyendo los detalles completos de sus
     * tamaños ({@link entidades.Tamanio}) y los detalles completos de los
     * ingredientes ({@link entidades.Ingrediente}) para cada receta de tamaño.
     * <p>
     * El pipeline realiza múltiples {@code $unwind} y {@code $lookup} para
     * ensamblar el objeto {@link Producto} con todas sus referencias resueltas.
     *
     * @param id El ID del producto a buscar (en formato String).
     * @return Un {@link PersistenciaProductoDTO} que representa el producto
     * encontrado con todos sus detalles, o {@code null} si no se encuentra
     * ningún producto con ese ID.
     * @throws PersistenciaException Si el ID proporcionado es inválido, o si
     * ocurre un error durante la ejecución de la agregación en la base de
     * datos.
     */
    @Override
    public PersistenciaProductoDTO buscarPorId(String id) throws PersistenciaException {
        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("ID de producto inválido: " + id, e);
        }

        List<Bson> pipeline = new ArrayList<>();

        // Stage 1: $match - Encontrar el producto por _id
        pipeline.add(Aggregates.match(Filters.eq("_id", objectId)));

        // Stage 2 & 3: $unwind y $lookup para Tamanio
        pipeline.add(Aggregates.unwind("$tamanios", new UnwindOptions().preserveNullAndEmptyArrays(true)));
        pipeline.add(Aggregates.lookup("tamanios", "tamanios.tamanio._id", "_id", "tamanioLookupArr"));
        pipeline.add(Aggregates.unwind("$tamanioLookupArr", new UnwindOptions().preserveNullAndEmptyArrays(true)));

        // Stage 4 & 5: $unwind y $lookup para Ingrediente (dentro de cada tamanio.ingredientes)
        pipeline.add(Aggregates.unwind("$tamanios.ingredientes", new UnwindOptions().preserveNullAndEmptyArrays(true)));
        pipeline.add(Aggregates.lookup("ingredientes", "tamanios.ingredientes.ingrediente._id", "_id", "ingredienteLookupArr"));
        pipeline.add(Aggregates.unwind("$ingredienteLookupArr", new UnwindOptions().preserveNullAndEmptyArrays(true)));

        // Stage 6: $group para reconstruir cada ProductoTamanioIngrediente (con Ingrediente completo)
        Bson groupIngredientesId = new Document("originalProductId", "$_id")
                .append("tamanioObjetoDelLookup", "$tamanioLookupArr"); // Agrupa por el objeto Tamanio completo
        pipeline.add(Aggregates.group(groupIngredientesId,
                Accumulators.first("camposOriginalesDelProducto", // Guardar campos del producto para usarlos después
                        new Document("id", "$_id").append("nombre", "$nombre").append("descripcion", "$descripcion")
                                .append("categoria", "$categoria").append("estado", "$estado")
                                .append("precioBase", "$precioBase").append("imageData", "$imageData")
                ),
                Accumulators.push("listaDeIngredientesParaEsteTamanio", // Lista de ProductoTamanioIngrediente
                        new Document("ingrediente", "$ingredienteLookupArr") // Objeto Ingrediente completo
                                .append("cantidad", "$tamanios.ingredientes.cantidad")
                )
        ));

        // Stage 7: $group de nuevo para reconstruir el Producto con su List<ProductoTamanio>
        pipeline.add(Aggregates.group("$_id.originalProductId",
                Accumulators.first("nombre", "$camposOriginalesDelProducto.nombre"), // Recuperar campos del producto
                Accumulators.first("descripcion", "$camposOriginalesDelProducto.descripcion"),
                Accumulators.first("categoria", "$camposOriginalesDelProducto.categoria"),
                Accumulators.first("estado", "$camposOriginalesDelProducto.estado"),
                Accumulators.first("precioBase", "$camposOriginalesDelProducto.precioBase"),
                Accumulators.first("imageData", "$camposOriginalesDelProducto.imageData"),
                Accumulators.push("tamaniosConSusIngredientes", // Lista de ProductoTamanio
                        new Document("tamanio", "$_id.tamanioObjetoDelLookup") // Objeto Tamanio completo
                                .append("ingredientes", "$listaDeIngredientesParaEsteTamanio") // Lista de ProductoTamanioIngrediente
                )
        ));

        // Stage 8: $project final para que coincida con entidad.Producto
        pipeline.add(Aggregates.project(
                Projections.fields(
                        Projections.computed("id", "$_id"),
                        Projections.include("nombre", "descripcion", "categoria", "estado", "precioBase", "imageData"),
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

    /**
     * Busca un producto por su nombre exacto en la base de datos.
     *
     * @param nombre El nombre exacto del producto a buscar.
     * @return Un {@link PersistenciaProductoDTO} que representa el producto
     * encontrado, o {@code null} si no se encuentra ningún producto con ese
     * nombre.
     * @throws PersistenciaException Si ocurre un error durante la consulta a la
     * base de datos.
     */
    @Override
    public PersistenciaProductoDTO buscarPorNombre(String nombre) throws PersistenciaException {
        List<PersistenciaProductoDTO> productos = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        try (MongoCursor<Producto> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                productos.add(productoMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar el producto por nombre en la base de datos", e);
        }
        if (productos.isEmpty()) {
            return null;
        }
        return productos.get(0);
    }

    /**
     * Guarda un nuevo producto en la base de datos. Si el
     * {@link PersistenciaProductoDTO} proporcionado no tiene un ID, se genera
     * uno nuevo.
     *
     * @param producto El DTO del producto a guardar.
     * @throws PersistenciaException Si el producto es {@code null} o si ocurre
     * un error durante la inserción en la base de datos.
     */
    @Override
    public void guardarProducto(PersistenciaProductoDTO producto) throws PersistenciaException {
        if (producto == null) {
            throw new PersistenciaException("Error al guardar el producto: el producto no puede ser nulo.");
        }
        Producto entidad = productoMapper.toEntity(producto);

        if (entidad.getId() == null) { // Asumiendo que toEntity deja el ID null si el DTO no lo tiene
            entidad.setId(new ObjectId());
        }

        try {
            coleccion.insertOne(entidad);
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar el producto en la base de datos", e);
        }
    }

    /**
     * Actualiza un producto existente en la base de datos. El producto a
     * actualizar se identifica por el ID contenido en el DTO.
     *
     * @param producto El DTO del producto con la información actualizada. Debe
     * contener un ID válido.
     * @throws PersistenciaException Si el producto es {@code null}, si su ID es
     * inválido, o si ocurre un error durante la actualización en la base de
     * datos.
     */
    @Override
    public void actualizarProducto(PersistenciaProductoDTO producto) throws PersistenciaException {
        if (producto == null || producto.getId() == null || producto.getId().trim().isEmpty()) {
            throw new PersistenciaException("Error al actualizar: el producto o su ID no pueden ser nulos/vacíos.");
        }
        ObjectId objectId;
        try {
            objectId = new ObjectId(producto.getId());
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("ID de producto inválido para actualizar: " + producto.getId(), e);
        }

        Bson filtro = Filters.eq("_id", objectId);
        Producto entidad = productoMapper.toEntity(producto);
        entidad.setId(objectId);

        try {
            com.mongodb.client.result.UpdateResult result = coleccion.replaceOne(filtro, entidad);
            if (result.getMatchedCount() == 0) {
                throw new PersistenciaException("No se encontró ningún producto con ID " + producto.getId() + " para actualizar.");
            }
            if (result.getModifiedCount() == 0) {
                System.out.println("Advertencia: El producto con ID " + producto.getId() + " no fue modificado (podría ser idéntico).");
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar el producto en la base de datos", e);
        }
    }

}
