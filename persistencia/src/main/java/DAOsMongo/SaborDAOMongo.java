package DAOsMongo;

import entidades.Sabor;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.ISaborDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.IConexionMongo;

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
    public List<Sabor> buscarTodos() throws PersistenciaException {
        List<Sabor> sabores = new ArrayList<>();

        if (conexion.getDatabase() == null) {
            return List.of(
                    new Sabor(1L, "Vainilla", "../img/saborVainilla.jpg"),
                    new Sabor(2L, "Chocolate", "../img/saborChocolate.jpeg"),
                    new Sabor(3L, "Moka", "../img/saborMoka.jpg"),
                    new Sabor(4L, "Fresa", "../img/saborFresa.jpg"),
                    new Sabor(5L, "Oreo", "../img/saborOreo.jpg"),
                    new Sabor(6L, "Caramelo", "../img/saborCaramelo.jpg")
            );
        } else {
            // lógica de mongo
        }

        return sabores;
    }

    @Override
    public Sabor buscarPorNombre(String nombre) throws PersistenciaException {
        List<Sabor> sabores = buscarTodos(); // usa tu método existente

        for (Sabor p : sabores) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }

        return null; // si no lo encuentra
    }
}
