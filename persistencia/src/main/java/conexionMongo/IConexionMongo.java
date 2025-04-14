package conexionMongo;

import com.mongodb.client.MongoDatabase;

/**
 * Interfaz que define los métodos necesarios para manejar una conexión con una
 * base de datos MongoDB.
 *
 * @author norma
 */
public interface IConexionMongo {

    /**
     * Obtiene la instancia de la base de datos MongoDB activa.
     *
     * @return una instancia de {@link MongoDatabase} que representa la base de
     * datos conectada.
     */
    public MongoDatabase getDatabase();

    /**
     * Cierra la conexión activa con la base de datos MongoDB.
     */
    public void close();
}
