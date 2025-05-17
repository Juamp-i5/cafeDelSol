package DAOsMongo.entradas;

import IDAOs.entradas.IEntradaDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.IConexionMongo;
import entidades.Entrada;
import excepciones.PersistenciaEntradasException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author pablo
 */
public class EntradaDAOMongo implements IEntradaDAO  {
    
    private static EntradaDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;

    private final MongoCollection<Entrada> coleccion;
    private final String NOMBRE_COLECCION = "entrada";

    private EntradaDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;
        this.database = conexion.getDatabase();
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Entrada.class);
    }

    public static EntradaDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new EntradaDAOMongo(conexion);
        }
        return instancia;
    }

    @Override
    public boolean registrarEntrada(Entrada entrada) throws PersistenciaEntradasException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Entrada> obtenerListaEntradaPorRangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaEntradasException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Entrada> obtenerTodasLasEntradas() throws PersistenciaEntradasException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Entrada> obtenerEntradasHastaFecha(LocalDateTime fechaFin) throws PersistenciaEntradasException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Entrada> obtenerEntradasDesdeFecha(LocalDateTime fechaInicio) throws PersistenciaEntradasException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
