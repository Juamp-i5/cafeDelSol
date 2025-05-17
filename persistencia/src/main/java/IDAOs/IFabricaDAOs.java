package IDAOs;

import IDAOs.cubiculos.ICancelacionDAO;
import IDAOs.cubiculos.ICubiculoDAO;
import IDAOs.cubiculos.IReagendaDAO;
import IDAOs.entradas.IEntradaDAO;

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
    
    public ICubiculoDAO getCubiculoDAO();
    
    public ICancelacionDAO getCancelacionDAO();
    
    public IReagendaDAO getReagendaDAO();
    
    public IEntradaDAO getEntradaDAO();
}
