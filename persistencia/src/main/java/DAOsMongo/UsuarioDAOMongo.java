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
 * Implementación de IUsuarioDAO que utiliza MongoDB para manejar
 * operaciones relacionadas con los usuarios del sistema.
 * @author Jp
 * Esta clase sigue el patrón Singleton para asegurar una única instancia durante la ejecución.
 * Se encarga de registrar, autenticar y consultar usuarios, utilizando hashing con BCrypt para
 * almacenar contraseñas de forma segura.
 */
public class UsuarioDAOMongo implements IUsuarioDAO {

    //Singleton
    private static UsuarioDAOMongo instancia;
    private final MongoDatabase database;

    private final MongoCollection<Usuario> coleccion;
    private final String NOMBRE_COLECCION = "usuarios";

    private final IUsuarioMapper usuarioMapper = DependencyInjectors.getInstancia().getUsuarioMapper();

    /**
     * Constructor privado. 
     * Inicializa la conexión con la base de datos y la colección.
     * @param conexionMongo Objeto de conexión a MongoDB.
     */
    private UsuarioDAOMongo(IConexionMongo conexionMongo) {
        this.database = conexionMongo.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Usuario.class);
    }

    /**
     * Devuelve la instancia única de UsuarioDAOMongo.
     * @param conexionMongo Objeto de conexión a MongoDB.
     * @return Instancia única de UsuarioDAOMongo.
     */
    public static UsuarioDAOMongo getInstancia(IConexionMongo conexionMongo) {
        if (instancia == null) {
            instancia = new UsuarioDAOMongo(conexionMongo);
        }

        return instancia;
    }

    /**
     * Busca un usuario en la base de datos por su nombre de usuario.
     * @param username Nombre de usuario a buscar.
     * @return DTO con los datos del usuario si se encuentra, o null si no existe.
     */
    @Override
    public PersistenciaUsuarioDTO buscarPorUsername(String username) {
        Bson filtro = Filters.eq("usuario", username);
        Usuario usuarioEntity = coleccion.find(filtro).first();
        if (usuarioEntity != null) {
            return usuarioMapper.toDTO(usuarioEntity);
        }
        return null;
    }

    /**
     * Verifica si las credenciales proporcionadas coinciden con un usuario existente.
     * La contraseña ingresada se compara con el hash almacenado usando BCrypt.
     * @param username Nombre de usuario.
     * @param contrasenia Arreglo de caracteres que representa la contraseña ingresada.
     * @return true si las credenciales son correctas, false en caso contrario.
     */
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

    /**
     * Agrega un nuevo usuario a la base de datos.
     * La contraseña es hasheada con BCrypt antes de almacenarse.
     * @param usuarioDTO DTO con los datos del nuevo usuario.
     */
    @Override
    public void agregarUsuario(PersistenciaUsuarioDTO usuarioDTO) {
        String contraseniaPlana = new String(usuarioDTO.getContrasenia());
        String contraseniaHasheada = BCrypt.hashpw(contraseniaPlana, BCrypt.gensalt());

        Usuario usuarioEntity = usuarioMapper.toEntity(usuarioDTO);
        usuarioEntity.setContrasenia(contraseniaHasheada);
        coleccion.insertOne(usuarioEntity);
    }

    /**
     * Verifica si un nombre de usuario ya está registrado en la base de datos.
     * @param username Nombre de usuario a verificar.
     * @return true si el nombre está disponible, false si ya está en uso.
     */
    @Override
    public boolean usernameDisponible(String username) {
        Bson filtro = Filters.eq("usuario", username);
        long count = coleccion.countDocuments(filtro);
        return count == 0;
    }
}
