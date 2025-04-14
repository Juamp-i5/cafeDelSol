package conexionMongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Implementación de la interfaz {@link IConexionMongo} para establecer y manejar una
 * conexión con una base de datos MongoDB utilizando el patrón Singleton.
 *
 * @author norma
 */
public class ConexionMongo implements IConexionMongo {

    private static final String URL = "mongodb://localhost:27017";
    private static final String DB_NAME = "cafedelsol";

    private static ConexionMongo instancia;
    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;

    /**
     * Constructor privado para implementar el patrón Singleton. Establece la
     * conexión con la base de datos MongoDB y prueba la conexión. Lanza una
     * excepción en caso de error de conexión.
     */
    private ConexionMongo() {
        try {
            this.mongoClient = MongoClients.create(URL);
            this.mongoDatabase = mongoClient.getDatabase(DB_NAME);

            mongoDatabase.listCollectionNames().first();
            System.out.println("Conexión exitosa a MongoDB: " + DB_NAME);
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar a MongoDB: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene la única instancia de la clase {@code ConexionMongo}.
     * <p>
     * Si no existe una instancia previa, se crea una nueva.
     * </p>
     *
     * @return la instancia Singleton de {@code ConexionMongo}
     */
    public static ConexionMongo getInstance() {
        if (instancia == null) {
            instancia = new ConexionMongo();
        }
        return instancia;
    }

    /**
     * Retorna la instancia de la base de datos conectada.
     *
     * @return objeto {@link MongoDatabase} correspondiente a la conexión actual
     * @throws IllegalStateException si la conexión no ha sido inicializada
     */
    @Override
    public MongoDatabase getDatabase() {
        if (mongoDatabase == null) {
            throw new IllegalStateException("La conexión a MongoDB no está inicializada");
        }
        return mongoDatabase;
    }

    /**
     * Cierra la conexión actual con MongoDB si existe.
     * <p>
     * También limpia las referencias a la instancia, base de datos y cliente.
     * </p>
     */
    @Override
    public void close() {
        if (mongoClient != null) {
            try {
                mongoClient.close();
                System.out.println("Conexión a MongoDB cerrada correctamente");
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            } finally {
                mongoClient = null;
                mongoDatabase = null;
                instancia = null;
            }
        }
    }

    /**
     * Limpia la instancia Singleton existente y cierra la conexión si está
     * activa.
     */
    public static void clearInstance() {
        if (instancia != null) {
            instancia.close();
            instancia = null;
        }
    }
}
