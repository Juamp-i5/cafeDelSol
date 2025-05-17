/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOsMongo.cubiculos;

import IDAOs.cubiculos.ICancelacionDAO;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import conexion.IConexionMongo;
import entidades.Cancelacion;
import excepciones.PersistenciaCubiculoEsception;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author rodri
 */
public class CancelacionDAOMongo implements ICancelacionDAO{
    
    //Singleton
    private static CancelacionDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;
    private final MongoCollection<Cancelacion> coleccion;
    private static final String NOMBRE_COLECCION = "cancelaciones";

    public CancelacionDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;

        // Registrar codec para POJOs
        CodecRegistry codecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        this.database = conexion.getDatabase().withCodecRegistry(codecRegistry);
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Cancelacion.class);
    }
    
    // Singleton getter
    public static CancelacionDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new CancelacionDAOMongo(conexion);
        }
        return instancia;
    }
    

    @Override
    public Cancelacion agregarCancelacion(Cancelacion cancelacion) throws PersistenciaCubiculoEsception {
        try {
            InsertOneResult resultado = coleccion.insertOne(cancelacion);

            if (!resultado.wasAcknowledged()) {
                throw new PersistenciaCubiculoEsception("La inserción no fue reconocida por el servidor.");
            }

            return cancelacion;

        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al agregar una cancelación: " + e.getMessage());
        }
    }
    
}
