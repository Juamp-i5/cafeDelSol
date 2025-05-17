package DAOsMongo;

import DTOs.SaborDTO;
import entidades.Sabor;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.ISaborDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.IConexionMongo;
import interfacesMappers.ISaborMapper;
import mappers.SaborMapper;
import org.bson.conversions.Bson;

/**
 *
 * @author rodri
 */
public class SaborDAOMongo implements ISaborDAO {

    //Singleton
    private static SaborDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private MongoCollection<Sabor> coleccion;
    private final String NOMBRE_COLECCION = "sabores";

    private final ISaborMapper saborMapper = new SaborMapper();

    private SaborDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Sabor.class);
    }

    public static SaborDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new SaborDAOMongo(conexion);
        }
        return instancia;
    }

    //Métodos de la colección
    @Override
    public List<SaborDTO> buscarTodos() throws PersistenciaException {
        List<SaborDTO> tamanios = new ArrayList<>();
        try (MongoCursor<Sabor> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                tamanios.add(saborMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar todos los sabores en la base de datos", e);
        }
        return tamanios;
    }

    @Override
    public SaborDTO buscarPorNombre(String nombre) throws PersistenciaException {
        List<SaborDTO> sabores = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        try (MongoCursor<Sabor> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                sabores.add(saborMapper.toDTO(cursor.next()));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar el sabor por nombre en la base de datos");
        }
        if (sabores.isEmpty()) {
            return null;
        }
        return sabores.get(0);
    }
}
