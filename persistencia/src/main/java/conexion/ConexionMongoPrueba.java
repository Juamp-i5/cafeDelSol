package conexion;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class ConexionMongoPrueba implements IConexionMongo {

    private static final String URL = "mongodb://localhost:27017";
    private static final String DB_NAME = "cafedelsol_pruebas";

    private static ConexionMongoPrueba instancia;
    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;

    private ConexionMongoPrueba() {
        try {
            CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
            CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(URL))
                    .codecRegistry(codecRegistry)
                    .build();

            this.mongoClient = MongoClients.create(settings);
            this.mongoDatabase = mongoClient.getDatabase(DB_NAME);

            mongoDatabase.listCollectionNames().first();
            System.out.println("Conexión exitosa a MongoDB: " + DB_NAME);
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar a MongoDB: " + e.getMessage(), e);
        }
    }

    public static ConexionMongoPrueba getInstance() {
        if (instancia == null) {
            instancia = new ConexionMongoPrueba();
        }
        return instancia;
    }

    @Override
    public MongoDatabase getDatabase() {
        if (mongoDatabase == null) {
            throw new IllegalStateException("La conexión a MongoDB no está inicializada");
        }
        return mongoDatabase;
    }

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

    public static void clearInstance() {
        if (instancia != null) {
            instancia.close();
            instancia = null;
        }
    }
}
