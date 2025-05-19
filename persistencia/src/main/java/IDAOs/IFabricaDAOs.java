package IDAOs;

import IDAOs.cubiculos.ICancelacionDAO;
import IDAOs.cubiculos.IContadorReservaciones;
import IDAOs.cubiculos.ICubiculoDAO;
import IDAOs.cubiculos.IReagendaDAO;
import IDAOs.cubiculos.IReservacionDAO;
import IDAOs.entradas.IEntradaDAO;
import IDAOs.ingredientes.IIngredienteDAOMongo;
import IDAOs.ingredientes.IProveedorDAOMongo;

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
    
    public IContadorReservaciones getContadorReservaciones();
    
    public IEntradaDAO getEntradaDAO();
    
    public IIngredienteDAOMongo getIngredienteDAO();

    public IProveedorDAOMongo getProveedorDAO();
    
    public IReservacionDAO getReservacionDAO();
}
