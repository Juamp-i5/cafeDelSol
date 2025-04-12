package conexion;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author norma
 */
public class Conexion implements IConexion {

    private static Conexion instancia;
    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;
    private String URL;
    private String DB_NAME;
    private boolean esMongoDB;

    private Conexion(boolean esMongoDB) {
        this.esMongoDB = esMongoDB;

        if (esMongoDB) {
            this.URL = "mongodb://localhost:27017";
            this.DB_NAME = "cafeteria";
            this.mongoClient = new MongoClient(URL);
            this.mongoDatabase = mongoClient.getDatabase(DB_NAME);
            System.out.println("Conexión exitosa a MongoDB.");
        } else {
            System.out.println("Conexión simulada a la consola de pedidos.");
        }
    }

    public static Conexion getInstance(boolean esMongoDB) {
        if (instancia == null) {
            instancia = new Conexion(esMongoDB);
        }
        return instancia;
    }

    @Override
    public MongoDatabase getDatabase() {
        if (esMongoDB) {
            return mongoDatabase;
        } else {
            System.out.println("Conexión simulada. No hay base de datos real.");
            return null;
        }
    }

    @Override
    public void close() {
        if (esMongoDB) {
            mongoClient.close();
            System.out.println("Cerrando conexión a MongoDB.");
        } else {
            System.out.println("Cerrando conexión simulada a la consola.");
        }
    }

}
