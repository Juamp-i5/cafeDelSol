package DAOsMongo.ingredientes;

import IDAOs.ingredientes.IProveedorDAOMongo;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.IConexionMongo;
import entidades.Proveedor;
import excepciones.PersistenciaIngredientesException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

/**
 *
 * @author norma
 */
public class ProveedorDAOMongo implements IProveedorDAOMongo {

    private static ProveedorDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private final MongoCollection<Proveedor> coleccion;
    private static final String NOMBRE_COLECCION = "proveedores";

    public ProveedorDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;

        // Registrar codec para POJOs
        CodecRegistry codecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        this.database = conexion.getDatabase().withCodecRegistry(codecRegistry);
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Proveedor.class);
    }

    public static ProveedorDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new ProveedorDAOMongo(conexion);
        }
        return instancia;
    }

    @Override
    public List<Proveedor> obtenerProveedores() throws PersistenciaIngredientesException {
        try {
            return coleccion.find().into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al obtener proveedores", e);
        }
    }

    @Override
    public void agregarProveedor(Proveedor proveedor) throws PersistenciaIngredientesException {
        try {
            MongoCollection<Document> coleccion = database.getCollection("proveedores");

            Document doc = new Document("nombre", proveedor.getNombre());
            coleccion.insertOne(doc);

            proveedor.setId(doc.getObjectId("_id"));
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al agregar el proveedor", e);
        }
    }

}
