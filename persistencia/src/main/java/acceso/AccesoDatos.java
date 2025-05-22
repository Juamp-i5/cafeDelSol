package acceso;

import DAOsMongo.FabricaDAOsMongo;
import IDAOs.IFabricaDAOs;
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
 * Clase que proporciona acceso centralizado a las implementaciones de los DAOs
 * utilizados en el sistema. Esta clase actúa como una fachada para ocultar los
 * detalles de la creación de objetos DAO, delegando esta responsabilidad a una
 * fábrica concreta (en este caso, {@code FabricaDAOsMongo}).
 *
 * <p>
 * Permite a otras capas de la aplicación obtener instancias de DAOs sin
 * acoplarse directamente a la lógica de persistencia o al tipo de base de
 * datos.
 * </p>
 *
 * @author Jp
 */
public class AccesoDatos {

    /**
     * Fábrica concreta utilizada para obtener las implementaciones de los DAOs.
     */
    private static final IFabricaDAOs fabricaDAOs = new FabricaDAOsMongo();

    /**
     * Obtiene una instancia del DAO para pedidos.
     *
     * @return una implementación de {@link IPedidoDAO}
     */
    public static IPedidoDAO getPedidoDAO() {
        return fabricaDAOs.getPedidoDAO();
    }

    /**
     * Obtiene una instancia del DAO para productos.
     *
     * @return una implementación de {@link IProductoDAO}
     */
    public static IProductoDAO getProductoDAO() {
        return fabricaDAOs.getProductoDAO();
    }

    /**
     * Obtiene una instancia del DAO para la relación producto-pedido.
     *
     * @return una implementación de {@link IProductoPedidoDAO}
     */
    public static IProductoPedidoDAO getProductoPedidoDAO() {
        return fabricaDAOs.getProductoPedidoDAO();
    }

    /**
     * Obtiene una instancia del DAO para gestionar sabores de productos.
     *
     * @return una implementación de {@link ISaborDAO} proporcionada por la
     * fábrica.
     */
    public static ISaborDAO getSaborDAO() {
        return fabricaDAOs.getSaborDAO();
    }

    /**
     * Obtiene una instancia del DAO para gestionar tamaños de productos.
     *
     * @return una implementación de {@link ITamanioDAO} proporcionada por la
     * fábrica.
     */
    public static ITamanioDAO getTamanioDAO() {
        return fabricaDAOs.getTamanioDAO();
    }

    /**
     * Obtiene una instancia del DAO para gestionar toppings de productos.
     *
     * @return una implementación de {@link IToppingDAO} proporcionada por la
     * fábrica.
     */
    public static IToppingDAO getToppingDAO() {
        return fabricaDAOs.getToppingDAO();
    }

    /**
     * Obtiene una instancia del DAO para gestionar registros de entradas (por
     * ejemplo, entradas de inventario o accesos).
     *
     * @return una implementación de {@link IEntradaDAO} proporcionada por la
     * fábrica.
     */
    public static IEntradaDAO getEntradaDAO() {
        return fabricaDAOs.getEntradaDAO();
    }

    /**
     * Obtiene una instancia del DAO para gestionar ingredientes, utilizando una
     * implementación específica para MongoDB.
     *
     * @return una implementación de {@link IIngredienteDAOMongo} proporcionada
     * por la fábrica.
     */
    public static IIngredienteDAOMongo getIngredienteDAO() {
        return fabricaDAOs.getIngredienteDAO();
    }

    /**
     * Obtiene una instancia del DAO para gestionar proveedores, utilizando una
     * implementación específica para MongoDB.
     *
     * @return una implementación de {@link IProveedorDAOMongo} proporcionada
     * por la fábrica.
     */
    public static IProveedorDAOMongo getProveedorDAO() {
        return fabricaDAOs.getProveedorDAO();
    }

    /**
     * Obtiene una instancia del DAO para gestionar reservaciones
     *
     * @return una implementación de {@link IDAOs.cubiculos.IReservacionDAO}
     * proporcionada por la fábrica.
     */
    public static IDAOs.cubiculos.IReservacionDAO getReservacionDAO() {
        return fabricaDAOs.getReservacionDAO();
    }

    /**
     * Obtiene una instancia del DAO para gestionar cubículos.
     *
     * @return una implementación de {@link ICubiculoDAO} proporcionada por la
     * fábrica.
     */
    public static ICubiculoDAO getCubiculoDAO() {
        return fabricaDAOs.getCubiculoDAO();
    }

    /**
     * Obtiene una instancia del DAO o servicio para gestionar el contador de
     * reservaciones de cubículos.
     *
     * @return una implementación de {@link IContadorReservaciones}
     * proporcionada por la fábrica.
     */
    public static IContadorReservaciones getContador() {
        return fabricaDAOs.getContadorReservaciones();
    }

    /**
     * Obtiene una instancia del DAO para gestionar registros de salidas (por
     * ejemplo, salidas de inventario o egresos).
     *
     * @return una implementación de {@link ISalidaDAO} proporcionada por la
     * fábrica.
     */
    public static ISalidaDAO getSalidaDAO() {
        return fabricaDAOs.getSalidaDAO();
    }

    /**
     * Obtiene una instancia del DAO para gestionar usuarios del sistema.
     *
     * @return una implementación de {@link IUsuarioDAO} proporcionada por la
     * fábrica.
     */
    public static IUsuarioDAO getUsuarioDAO() {
        return fabricaDAOs.getUsuarioDAO();
    }

}
