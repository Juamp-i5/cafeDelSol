/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOsMongo.cubiculos;

import IDAOs.cubiculos.IContadorReservaciones;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import conexion.IConexionMongo;
import entidades.ContadorReservaciones;
import excepciones.PersistenciaCubiculoEsception;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

/**
 *
 * @author rodri
 */
public class ContadorReservacionesDAOMongo implements IContadorReservaciones{

    private static ContadorReservacionesDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;
    private final MongoCollection<ContadorReservaciones> coleccion;
    private static final String NOMBRE_COLECCION = "contadorReservaciones";
    
    // Constructor con la conexion y codec para POJOs
    public ContadorReservacionesDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;

        // Registrar codec para POJOs
        CodecRegistry codecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        this.database = conexion.getDatabase().withCodecRegistry(codecRegistry);
        this.coleccion = database.getCollection(NOMBRE_COLECCION, ContadorReservaciones.class);
    }

    // Singleton getter
    public static ContadorReservacionesDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new ContadorReservacionesDAOMongo(conexion);
        }
        return instancia;
    }
    
    /**
     * Busca el contador en la base de datos y aumenta uno para guardarlo después
     * @return El número del contador  + 1
     * @throws PersistenciaCubiculoEsception 
     */
    @Override
    public Integer encontrarActualizar() throws PersistenciaCubiculoEsception {
         try {
        Bson filtro = Filters.exists("contador");
        Bson incremento = Updates.inc("contador", 1);
        FindOneAndUpdateOptions opciones = new FindOneAndUpdateOptions()
                .returnDocument(ReturnDocument.AFTER);

        Document documentoActualizado = database
                .getCollection("contadorReservaciones")
                .findOneAndUpdate(filtro, incremento, opciones);

        if (documentoActualizado == null || !documentoActualizado.containsKey("contador")) {
            throw new PersistenciaCubiculoEsception("No se pudo obtener el nuevo número de reservación.");
        }
        
        return documentoActualizado.getInteger("contador");
    } catch (Exception e) {
        throw new PersistenciaCubiculoEsception("Error al actualizar el contador de reservaciones.", e);
    }
    }
    
    
    
    
}
