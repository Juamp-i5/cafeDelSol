package DAOsMongo;

import DTOs.PersistenciaTamanioDTO;
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
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import utils.DependencyInjectors;

/**
 *
 * @author rodri
 */
public class TamanioDAOMongo implements ITamanioDAO {

    //Singleton
    private static TamanioDAOMongo instancia;
    private final MongoDatabase database;

    private final MongoCollection<Tamanio> coleccion;
    private final String NOMBRE_COLECCION = "tamanios";

    private final ITamanioMapper tamanioMapper = DependencyInjectors.getInstancia().getTamanioMapper();

    private TamanioDAOMongo(IConexionMongo conexion) {
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
    public List<PersistenciaTamanioDTO> buscarTodos() throws PersistenciaException {
        List<PersistenciaTamanioDTO> tamanios = new ArrayList<>();
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
    public PersistenciaTamanioDTO buscarPorNombre(String nombre) throws PersistenciaException {
        List<PersistenciaTamanioDTO> tamanios = new ArrayList<>();
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

    @Override
    public void guardar(PersistenciaTamanioDTO tamanioDTO) throws PersistenciaException {
        try {
            Tamanio tamanio = tamanioMapper.toEntity(tamanioDTO);
            if (tamanio.getId() == null) {
                tamanio.setId(new ObjectId());
            }
            coleccion.insertOne(tamanio);
            tamanioDTO.setId(tamanio.getId().toHexString());
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar tamanio en la base de datos", e);
        }
    }
}
