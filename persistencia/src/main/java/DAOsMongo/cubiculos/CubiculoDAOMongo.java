/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOsMongo.cubiculos;

import IDAOs.cubiculos.ICubiculoDAO;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.IConexionMongo;
import entidades.Cubiculo;
import excepciones.PersistenciaCubiculoEsception;
import java.util.ArrayList;
import java.util.List;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author rodri
 */
public class CubiculoDAOMongo implements ICubiculoDAO {

    private static CubiculoDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;
    private final MongoCollection<Cubiculo> coleccion;
    private static final String NOMBRE_COLECCION = "cubiculos";

    
    // Constructor con la conexion y codec para POJOs
    public CubiculoDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;

        // Registrar codec para POJOs
        CodecRegistry codecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        this.database = conexion.getDatabase().withCodecRegistry(codecRegistry);
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Cubiculo.class);
    }
    
    // Singleton getter
    public static CubiculoDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new CubiculoDAOMongo(conexion);
        }
        return instancia;
    }

    @Override
    public List<Cubiculo> obtenerCubiculos() throws PersistenciaCubiculoEsception {
        try {
            return coleccion.find().into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al cargar los cubículos: " + e.getMessage(), e);
        }
    }
}