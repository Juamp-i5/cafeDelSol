/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricaDAOs;

import interfaces.IFabricaDAOs;
import DAOs.PedidoDAOImp;
import DAOs.ProductoDAOImp;
import DAOs.SaborDAOImp;
import DAOs.TamanioDAOImp;
import DAOs.ToppingDAOImp;
import conexion.Conexion;
import interfaces.IConexion;
import interfaces.IPedido;
import interfaces.IProducto;
import interfaces.IProductoPedido;
import interfaces.ISabor;
import interfaces.ITamanio;
import interfaces.ITopping;

/**
 *
 * @author norma
 */
public class FabricaDAOs implements IFabricaDAOs {

    private static final IConexion conexion = Conexion.getInstance(false);

    public static IProducto getProductoDAO() {
        return new ProductoDAOImp(conexion);
    }

    public static ISabor getSaborDAO() {
        return new SaborDAOImp(conexion);
    }

    public static ITamanio getTamanioDAO() {
        return new TamanioDAOImp(conexion);
    }

    public static ITopping getToppingDAO() {
        return new ToppingDAOImp(conexion);
    }

    public static IPedido getPedidoDAO() {
        return new PedidoDAOImp(conexion); 
    }
    
}
