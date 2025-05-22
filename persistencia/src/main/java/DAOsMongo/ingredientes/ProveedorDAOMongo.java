package DAOsMongo.ingredientes;

import DTOs.PersistenciaProveedorDTO;
import DTOs.ingredientes.IProveedorMapperPersistencia;
import DTOs.ingredientes.ProveedorDTOPersistencia;
import DTOs.ingredientes.ProveedorMapperPersistencia;
import IDAOs.ingredientes.IProveedorDAOMongo;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.IConexionMongo;
import entidades.Proveedor;
import excepciones.PersistenciaException;
import excepciones.PersistenciaIngredientesException;
import java.util.ArrayList;
import java.util.List;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

/**
 * Clase DAO que logra la gestión de proveedores en MongoDB. Sigue el patrón
 * Singleton para garantizar una única instancia.
 *
 * @author norma
 */
public class ProveedorDAOMongo implements IProveedorDAOMongo {

    private static ProveedorDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private final MongoCollection<Proveedor> coleccion;
    private static final String NOMBRE_COLECCION = "proveedores";

    IProveedorMapperPersistencia proveedorMapper = new ProveedorMapperPersistencia();

    /**
     * Constructor para el patrón Singleton. Configura el CodecRegistry para
     * permitir el uso de POJOs.
     *
     * @param conexion Conexión a la base de datos.
     */
    public ProveedorDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;

        CodecRegistry codecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        this.database = conexion.getDatabase().withCodecRegistry(codecRegistry);
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Proveedor.class);
    }

    /**
     * Obtiene la instancia única de ProveedorDAOMongo.
     *
     * @param conexion Conexión a la base de datos.
     * @return Instancia única del DAO.
     */
    public static ProveedorDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new ProveedorDAOMongo(conexion);
        }
        return instancia;
    }

    /**
     * Obtiene todos los proveedores exisitentes.
     *
     * @return Lista de proveedores.
     * @throws PersistenciaIngredientesException Si ocurre un error durante la
     * operación.
     */
    @Override
    public List<ProveedorDTOPersistencia> obtenerProveedores() throws PersistenciaIngredientesException {
        try {
            return proveedorMapper.toDTOList(coleccion.find().into(new ArrayList<>()));
        } catch (Exception e) {
            throw new PersistenciaIngredientesException("Error al obtener proveedores", e);
        }
    }

    /**
     * Guarda un nuevo proveedor a la colección (No se usa en el sistema, pero
     * sirve para insertar manualmente proveedores ya que no se puede hacer eso
     * en el sistema ejecutado).
     *
     * @param proveedorDTO proveedor a guardar.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public void guardarProveedor(ProveedorDTOPersistencia proveedorDTO) throws PersistenciaException {
        if (proveedorDTO == null) {
            throw new PersistenciaException("Error al guardar el proveedor en la base de datos, el proveedor no puede ser nulo");
        }
        try {
            Proveedor proveedor = proveedorMapper.toEntity(proveedorDTO);
            if (proveedor.getId() == null) {
                proveedor.setId(new ObjectId());
                proveedorDTO.setId(proveedor.getId().toHexString());
            }
            coleccion.insertOne(proveedor);
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar el proveedor en la base de datos", e);
        }
    }

}
