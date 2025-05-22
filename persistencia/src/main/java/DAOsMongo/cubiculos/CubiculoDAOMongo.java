/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOsMongo.cubiculos;

import DTOs.cubiculos.CubiculoCompletoDTOPersistencia;
import DTOs.cubiculos.CubiculoMapperPersistencia;
import DTOs.cubiculos.ICubiculoMapperPersistencia;
import IDAOs.cubiculos.ICubiculoDAO;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.IConexionMongo;
import entidades.Cubiculo;
import excepciones.PersistenciaCubiculoEsception;
import java.util.ArrayList;
import java.util.List;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

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
    
    ICubiculoMapperPersistencia cubiculoMapper = new CubiculoMapperPersistencia();
    
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

    /**
     * Método que busca todos los cubículos existentes
     * @return Lista de los nombres de los cubículos existentes
     * @throws PersistenciaCubiculoEsception 
     */
    @Override
    public List<String> obtenerCubiculos() throws PersistenciaCubiculoEsception {
        try {
            List<Cubiculo> entidades = coleccion.find().into(new ArrayList<>());
            List<String> lista = new ArrayList<>();
            
            for (Cubiculo entidad : entidades) {
                lista.add(entidad.getNombre());
            }
            return lista;
        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al cargar los cubículos: " + e.getMessage(), e);
        }
    }

    /**
     * Método para buscar un cubículo por nombre
     * @param nombre Nombre del cubículo a buscar
     * @return Cubículo encontrado
     * @throws PersistenciaCubiculoEsception 
     */
    @Override
    public CubiculoCompletoDTOPersistencia obtenerPorNombre(String nombre) throws PersistenciaCubiculoEsception {
        try {
            Bson filtro = Filters.eq("nombre", nombre);
            Cubiculo entidad = coleccion.find(filtro).first();
            CubiculoCompletoDTOPersistencia dto = cubiculoMapper.toDTO(entidad);
            return dto;

        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al buscar reservación por número: " + e.getMessage(), e);
        }
    }
}