package DAOsMongo;

import conexionMongo.ConexionMongo;
import conexionMongo.IConexionMongo;
import IDAOs.IPedidoDAO;
import IDAOs.IProductoDAO;
import IDAOs.ISaborDAO;
import IDAOs.ITamanioDAO;
import IDAOs.IToppingDAO;

/**
 * Fábrica de DAOs estática que proporciona instancias de DAOs conectadas a
 * MongoDB mediante una implementación de IConexionMongo.
 *
 * Esta clase implementa el patrón de fábrica estática, permitiendo obtener
 * objetos DAO ya configurados sin necesidad de instanciar esta clase.
 *
 * @author Jp
 */
public class FabricaDAOsMongo {

    /**
     * Conexión compartida a la base de datos MongoDB. Se obtiene una sola vez a
     * través del patrón Singleton.
     */
    private static final IConexionMongo conexion = ConexionMongo.getInstance();

    /**
     * Retorna una instancia del DAO para la entidad Pedido.
     *
     * @return Instancia de IPedidoDAO conectada a MongoDB.
     */
    public static IPedidoDAO getPedidoDAO() {
        return PedidoDAOMongo.getInstance(conexion);
    }

    /**
     * Retorna una instancia del DAO para la entidad Producto.
     *
     * @return Instancia de IProductoDAO conectada a MongoDB.
     */
    public static IProductoDAO getProductoDAO() {
        return ProductoDAOMongo.getInstance(conexion);
    }

    /**
     * Retorna una instancia del DAO para la entidad Sabor.
     *
     * @return Instancia de ISaborDAO conectada a MongoDB.
     */
    public static ISaborDAO getSaborDAO() {
        return SaborDAOMongo.getInstance(conexion);
    }

    /**
     * Retorna una instancia del DAO para la entidad Tamaño.
     *
     * @return Instancia de ITamanioDAO conectada a MongoDB.
     */
    public static ITamanioDAO getTamanioDAO() {
        return TamanioDAOMongo.getInstance(conexion);
    }

    /**
     * Retorna una instancia del DAO para la entidad Topping.
     *
     * @return Instancia de IToppingDAO conectada a MongoDB.
     */
    public static IToppingDAO getToppingDAO() {
        return ToppingDAOMongo.getInstance(conexion);
    }
}
