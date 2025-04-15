package IDAOs;

/**
 *
 * @author Jp
 */
public interface IFabricaDAOs {

    public IPedidoDAO getPedidoDAO();

    public IProductoDAO getProductoDAO();

    public IProductoPedidoDAO getProductoPedidoDAO();

    public ISaborDAO getSaborDAO();

    public ITamanioDAO getTamanioDAO();

    public IToppingDAO getToppingDAO();
}
