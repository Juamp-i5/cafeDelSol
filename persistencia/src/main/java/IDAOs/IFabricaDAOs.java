package IDAOs;

import IDAOs.cubiculos.IContadorReservaciones;
import IDAOs.cubiculos.ICubiculoDAO;
import IDAOs.cubiculos.IReservacionDAO;
import IDAOs.entradas.IEntradaDAO;
import IDAOs.ingredientes.IIngredienteDAOMongo;
import IDAOs.ingredientes.IProveedorDAOMongo;
import IDAOs.salidas.ISalidaDAO;

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
    
    public IContadorReservaciones getContadorReservaciones();
    
    public IEntradaDAO getEntradaDAO();
    
    public IIngredienteDAOMongo getIngredienteDAO();

    public IProveedorDAOMongo getProveedorDAO();
    
    public IReservacionDAO getReservacionDAO();
    
    public ISalidaDAO getSalidaDAO();
}
