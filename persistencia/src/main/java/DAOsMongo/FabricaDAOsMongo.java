package DAOsMongo;

import DAOsMongo.cubiculos.ContadorReservacionesDAOMongo;
import DAOsMongo.cubiculos.CubiculoDAOMongo;
import DAOsMongo.cubiculos.ReservacionDAOMongo;
import DAOsMongo.entradas.EntradaDAOMongo;
import DAOsMongo.ingredientes.IngredienteDAOMongo;
import DAOsMongo.ingredientes.ProveedorDAOMongo;
import DAOsMongo.salidas.SalidaDAOMongo;
import IDAOs.IFabricaDAOs;
import conexion.ConexionMongo;
import conexion.IConexionMongo;
import IDAOs.IPedidoDAO;
import IDAOs.IProductoDAO;
import IDAOs.IProductoPedidoDAO;
import IDAOs.ISaborDAO;
import IDAOs.ITamanioDAO;
import IDAOs.IToppingDAO;
import IDAOs.IUsuarioDAO;
import IDAOs.cubiculos.IContadorReservaciones;
import IDAOs.cubiculos.ICubiculoDAO;
import IDAOs.cubiculos.IReservacionDAO;
import IDAOs.entradas.IEntradaDAO;
import IDAOs.ingredientes.IIngredienteDAOMongo;
import IDAOs.ingredientes.IProveedorDAOMongo;
import IDAOs.salidas.ISalidaDAO;

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

    /**
     * Obtiene una instancia del DAO para gestionar el contador de
     * reservaciones. Este método utiliza un patrón Singleton o similar para
     * devolver una instancia compartida o una nueva basada en la conexión
     * proporcionada.
     *
     * @return Una instancia de {@link IContadorReservaciones} para interactuar
     * con los datos del contador de reservaciones en MongoDB.
     */
    @Override
    public IContadorReservaciones getContadorReservaciones() {
        return ContadorReservacionesDAOMongo.getInstance(conexion);
    }

    /**
     * Obtiene una instancia del DAO para gestionar las entidades de tipo
     * "Entrada". "Entrada" podría referirse a registros de entrada de
     * inventario, accesos, etc. Este método utiliza un patrón Singleton o
     * similar.
     *
     * @return Una instancia de {@link IEntradaDAO} para operar con los datos de
     * entradas en MongoDB.
     */
    @Override
    public IEntradaDAO getEntradaDAO() {
        return EntradaDAOMongo.getInstance(conexion);
    }

    /**
     * Obtiene una instancia del DAO para gestionar las entidades de tipo
     * "Ingrediente". Este DAO está configurado para interactuar con una base de
     * datos MongoDB. Este método utiliza un patrón Singleton o similar.
     *
     * @return Una instancia de {@link IIngredienteDAOMongo} para las
     * operaciones CRUD y otras consultas sobre ingredientes en MongoDB.
     */
    @Override
    public IIngredienteDAOMongo getIngredienteDAO() {
        return IngredienteDAOMongo.getInstance(conexion);
    }

    /**
     * Obtiene una instancia del DAO para gestionar las entidades de tipo
     * "Proveedor". Este DAO está configurado para interactuar con una base de
     * datos MongoDB. Este método utiliza un patrón Singleton o similar.
     *
     * @return Una instancia de {@link IProveedorDAOMongo} para realizar
     * operaciones sobre los datos de proveedores en MongoDB.
     */
    @Override
    public IProveedorDAOMongo getProveedorDAO() {
        return ProveedorDAOMongo.getInstance(conexion);
    }

    /**
     * Obtiene una instancia del DAO para gestionar las entidades de tipo
     * "Reservacion". Este método utiliza un patrón Singleton o similar.
     *
     * @return Una instancia de {@link IReservacionDAO} para administrar los
     * datos de reservaciones en MongoDB.
     */
    @Override
    public IReservacionDAO getReservacionDAO() {
        return ReservacionDAOMongo.getInstance(conexion);
    }

    /**
     * Obtiene una instancia del DAO para gestionar las entidades de tipo
     * "Salida". "Salida" podría referirse a registros de salida de inventario,
     * egresos, etc. Este método utiliza un patrón Singleton o similar.
     *
     * @return Una instancia de {@link ISalidaDAO} para operar con los datos de
     * salidas en MongoDB.
     */
    @Override
    public ISalidaDAO getSalidaDAO() {
        return SalidaDAOMongo.getInstance(conexion);
    }

    /**
     * Obtiene una instancia del DAO para gestionar las entidades de tipo
     * "Usuario". Este método utiliza un patrón Singleton o similar (nótese que
     * usa {@code getInstancia} en lugar de {@code getInstance}).
     *
     * @return Una instancia de {@link IUsuarioDAO} para administrar los datos
     * de usuarios en MongoDB.
     */
    @Override
    public IUsuarioDAO getUsuarioDAO() {
        return UsuarioDAOMongo.getInstancia(conexion);
    }

}
