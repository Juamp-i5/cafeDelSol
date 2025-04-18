package acceso;

import DAOsMongo.FabricaDAOsMongo;
import IDAOs.IFabricaDAOs;
import IDAOs.IPedidoDAO;
import IDAOs.IProductoDAO;
import IDAOs.IProductoPedidoDAO;
import IDAOs.ISaborDAO;
import IDAOs.ITamanioDAO;
import IDAOs.IToppingDAO;

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
     * Obtiene una instancia del DAO para sabores.
     *
     * @return una implementación de {@link ISaborDAO}
     */
    public static ISaborDAO getSaborDAO() {
        return fabricaDAOs.getSaborDAO();
    }

    /**
     * Obtiene una instancia del DAO para tamaños.
     *
     * @return una implementación de {@link ITamanioDAO}
     */
    public static ITamanioDAO getTamanioDAO() {
        return fabricaDAOs.getTamanioDAO();
    }

    /**
     * Obtiene una instancia del DAO para toppings.
     *
     * @return una implementación de {@link IToppingDAO}
     */
    public static IToppingDAO getToppingDAO() {
        return fabricaDAOs.getToppingDAO();
    }
}
