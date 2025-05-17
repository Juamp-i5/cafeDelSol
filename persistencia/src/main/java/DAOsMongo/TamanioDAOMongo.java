package DAOsMongo;

import DTOs.TamanioDTO;
import entidades.Tamanio;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.ITamanioDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.IConexionMongo;
import interfacesMappers.ITamanioMapper;
import mappers.IngredienteMapper;
import mappers.ProveedorMapper;
import mappers.TamanioMapper;
import org.bson.conversions.Bson;

/**
 *
 * @author rodri
 */
public class TamanioDAOMongo implements ITamanioDAO {

    //Singleton
    private static TamanioDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private MongoCollection<Tamanio> coleccion;
    private final String NOMBRE_COLECCION = "tamanios";

    private final ITamanioMapper tamanioMapper = new TamanioMapper(new IngredienteMapper(new ProveedorMapper()));

    private TamanioDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Tamanio.class);
    }

    public static TamanioDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new TamanioDAOMongo(conexion);
        }
        return instancia;
    }

    //Métodos de la colección
    @Override
    public List<TamanioDTO> buscarTodos() throws PersistenciaException {
        List<TamanioDTO> tamanios = new ArrayList<>();
        try (MongoCursor<Tamanio> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                tamanios.add(tamanioMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar todos los productos en la base de datos", e);
        }
        return tamanios;
    }

    @Override
    public TamanioDTO buscarPorNombre(String nombre) throws PersistenciaException {
        List<TamanioDTO> tamanios = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        try (MongoCursor<Tamanio> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                tamanios.add(tamanioMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar el tamanio por nombre en la base de datos");
        }
        if (tamanios.isEmpty()) {
            return null;
        }
        return tamanios.get(0);
    }
}
