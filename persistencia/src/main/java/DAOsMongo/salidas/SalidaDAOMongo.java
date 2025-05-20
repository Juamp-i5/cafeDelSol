/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOsMongo.salidas;

import IDAOs.salidas.ISalidaDAO;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.ConexionMongo;
import conexion.IConexionMongo;
import entidades.Salida;
import excepciones.PersistenciaSalidasException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author katia
 */
public class SalidaDAOMongo implements ISalidaDAO{
    private static SalidaDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;
    private final MongoCollection<Salida> coleccion;
    private static final String NOMBRE_COLECCION = "salidas";
    
    public SalidaDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;
        CodecRegistry codecRegistry = fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );
        this.database = conexion.getDatabase().withCodecRegistry(codecRegistry);
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Salida.class);
    }
    
    public static SalidaDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new SalidaDAOMongo(conexion);
        }
        return instancia;
    }
    
    @Override
    public boolean registrarSalida(Salida salida) throws PersistenciaSalidasException {
        try {
            coleccion.insertOne(salida);
            return true;
        } catch (Exception e) {
            throw new PersistenciaSalidasException("Error al registrar la salida: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Salida> consultarTodas() throws PersistenciaSalidasException {
        try {
            return coleccion.find().into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaSalidasException("Error al consultar todas las salidas: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Salida> consultarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaSalidasException {
        try {
            Bson filtro = Filters.and(
                Filters.gte("fecha", fechaInicio),
                Filters.lte("fecha", fechaFin)
            );
            return coleccion.find(filtro).into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaSalidasException("Error al consultar salidas por fechas: " + e.getMessage(), e);
        }
    }

    @Override
    public Salida consultarPorId(ObjectId id) throws PersistenciaSalidasException {
        try {
            return coleccion.find(Filters.eq("_id", id)).first();
        } catch (Exception e) {
            throw new PersistenciaSalidasException("Error al consultar la salida por ID: " + e.getMessage(), e);
        }
    }
    
}
