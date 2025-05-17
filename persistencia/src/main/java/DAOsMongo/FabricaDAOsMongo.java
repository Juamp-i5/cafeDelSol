package DAOsMongo;

import DAOsMongo.cubiculos.CancelacionDAOMongo;
import DAOsMongo.cubiculos.CubiculoDAOMongo;
import DAOsMongo.cubiculos.ReagendaDAOMongo;
import IDAOs.IFabricaDAOs;
import conexion.ConexionMongo;
import conexion.IConexionMongo;
import IDAOs.IPedidoDAO;
import IDAOs.IProductoDAO;
import IDAOs.IProductoPedidoDAO;
import IDAOs.ISaborDAO;
import IDAOs.ITamanioDAO;
import IDAOs.IToppingDAO;
import IDAOs.cubiculos.ICancelacionDAO;
import IDAOs.cubiculos.ICubiculoDAO;
import IDAOs.cubiculos.IReagendaDAO;

/**
 * Fábrica de DAOs estática que proporciona instancias de DAOs conectadas a
 * MongoDB mediante una implementación de IConexionMongo.
 *
 * Esta clase implementa el patrón de fábrica estática, permitiendo obtener
 * objetos DAO ya configurados sin necesidad de instanciar esta clase.
 *
 * @author Jp
 */
public class FabricaDAOsMongo implements IFabricaDAOs {

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
    @Override
    public IPedidoDAO getPedidoDAO() {
        return PedidoDAOMongo.getInstance(conexion);
    }

    /**
     * Retorna una instancia del DAO para la entidad Producto.
     *
     * @return Instancia de IProductoDAO conectada a MongoDB.
     */
    @Override
    public IProductoDAO getProductoDAO() {
        return ProductoDAOMongo.getInstance(conexion);
    }

    /**
     * Retorna una instancia del DAO para la entidad ProductoPedido.
     *
     * @return Instancia de IProductoPedidoDAO conectada a MongoDB.
     */
    @Override
    public IProductoPedidoDAO getProductoPedidoDAO() {
        return ProductoPedidoDAOMongo.getInstance(conexion);
    }

    /**
     * Retorna una instancia del DAO para la entidad Sabor.
     *
     * @return Instancia de ISaborDAO conectada a MongoDB.
     */
    @Override
    public ISaborDAO getSaborDAO() {
        return SaborDAOMongo.getInstance(conexion);
    }

    /**
     * Retorna una instancia del DAO para la entidad Tamaño.
     *
     * @return Instancia de ITamanioDAO conectada a MongoDB.
     */
    @Override
    public ITamanioDAO getTamanioDAO() {
        return TamanioDAOMongo.getInstance(conexion);
    }

    /**
     * Retorna una instancia del DAO para la entidad Topping.
     *
     * @return Instancia de IToppingDAO conectada a MongoDB.
     */
    @Override
    public IToppingDAO getToppingDAO() {
        return ToppingDAOMongo.getInstance(conexion);
    }

    /**
     * Retorna una instancia del DAO para la entidad Cubiculo
     * 
     * @return Instancia de ICubiculoDAO conectada a MongoDB
     */
    @Override
    public ICubiculoDAO getCubiculoDAO() {
        return CubiculoDAOMongo.getInstance(conexion);
    }

    @Override
    public ICancelacionDAO getCancelacionDAO() {
        return CancelacionDAOMongo.getInstance(conexion);
    }

    @Override
    public IReagendaDAO getReagendaDAO() {
        return ReagendaDAOMongo.getInstance(conexion);
    }
}
