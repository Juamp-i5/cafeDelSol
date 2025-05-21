/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOsMongo;

import DTOs.PersistenciaUsuarioDTO;
import IDAOs.IUsuarioDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.IConexionMongo;
import entidades.Usuario;
import interfacesMappers.IUsuarioMapper;
import java.util.Arrays;
import org.bson.conversions.Bson;
import org.mindrot.jbcrypt.BCrypt;
import utils.DependencyInjectors;

/**
 *
 * @author Jp
 */
public class UsuarioDAOMongo implements IUsuarioDAO {

    //Singleton
    private static UsuarioDAOMongo instancia;
    private final MongoDatabase database;

    private final MongoCollection<Usuario> coleccion;
    private final String NOMBRE_COLECCION = "usuarios";

    private final IUsuarioMapper usuarioMapper = DependencyInjectors.getInstancia().getUsuarioMapper();

    private UsuarioDAOMongo(IConexionMongo conexionMongo) {
        this.database = conexionMongo.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Usuario.class);
    }

    public static UsuarioDAOMongo getInstancia(IConexionMongo conexionMongo) {
        if (instancia == null) {
            instancia = new UsuarioDAOMongo(conexionMongo);
        }

        return instancia;
    }

    //Métodos de la colección
    @Override
    public PersistenciaUsuarioDTO buscarPorUsername(String username) {
        Bson filtro = Filters.eq("usuario", username);
        Usuario usuarioEntity = coleccion.find(filtro).first();
        if (usuarioEntity != null) {
            return usuarioMapper.toDTO(usuarioEntity);
        }
        return null;
    }

    @Override
    public boolean comprobarInicioSesion(String username, char[] contrasenia) {
        Bson filtro = Filters.eq("usuario", username);
        Usuario usuarioEntity = coleccion.find(filtro).first();

        if (usuarioEntity != null) {
            String contraseniaHasheadaAlmacenada = usuarioEntity.getContrasenia();
            String contraseniaCandidata = new String(contrasenia);
            boolean coinciden = false;
            if (contraseniaHasheadaAlmacenada != null) {
                coinciden = BCrypt.checkpw(contraseniaCandidata, contraseniaHasheadaAlmacenada);
            }
            Arrays.fill(contrasenia, ' ');
            return coinciden;
        }
        Arrays.fill(contrasenia, ' ');
        return false;
    }

    @Override
    public void agregarUsuario(PersistenciaUsuarioDTO usuarioDTO) {
        String contraseniaPlana = new String(usuarioDTO.getContrasenia());
        String contraseniaHasheada = BCrypt.hashpw(contraseniaPlana, BCrypt.gensalt());

        Usuario usuarioEntity = usuarioMapper.toEntity(usuarioDTO);
        usuarioEntity.setContrasenia(contraseniaHasheada);
        coleccion.insertOne(usuarioEntity);
    }

    @Override
    public boolean usernameDisponible(String username) {
        Bson filtro = Filters.eq("usuario", username);
        long count = coleccion.countDocuments(filtro);
        return count == 0;
    }
}
