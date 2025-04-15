package DAOsMongo;

import entidades.Tamanio;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.ITamanioDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.IConexionMongo;

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
    public List<Tamanio> buscarTodos() throws PersistenciaException {
        List<Tamanio> tamanios = new ArrayList<>();

        if (conexion.getDatabase() == null) {
            return List.of(
                    new Tamanio(1L, "Pequenio", "../img/tamanioPequenio.jpg", 0),
                    new Tamanio(2L, "Mediano", "../img/tamanioMediano.jpg", 5),
                    new Tamanio(3L, "Grande", "../img/tamanioGrande.jpg", 10)
            );
        } else {
            // lógica de mongo
        }

        return tamanios;
    }

    @Override
    public Tamanio buscarPorNombre(String nombre) throws PersistenciaException {
        List<Tamanio> tamanios = buscarTodos(); // usa tu método existente

        for (Tamanio p : tamanios) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }

        return null; // si no lo encuentra
    }
}
