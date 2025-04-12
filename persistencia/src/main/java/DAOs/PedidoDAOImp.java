/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import interfaces.IConexion;
import entidades.Pedido;
import exception.persistenciaException;
import interfaces.IPedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodri
 */
public class PedidoDAOImp implements IPedido {

    private IConexion conexion;

    public PedidoDAOImp(IConexion conexion) {
        this.conexion = conexion;
    }

    private static PedidoDAOImp instanceDAO;

    public PedidoDAOImp() {
    }

    public static PedidoDAOImp getInstance() {
        if (instanceDAO == null) {
            instanceDAO = new PedidoDAOImp();
        }
        return instanceDAO;
    }
    
    
    @Override
    public Pedido registrarPedido(Pedido pedido) throws persistenciaException {
        List<Pedido> pedidos = new ArrayList<>();

        if (conexion.getDatabase() == null) {
            pedidos.add(pedido);

            StringBuilder mensaje = new StringBuilder();
            for (Pedido pedidoMostrar : pedidos) {
                mensaje.append(pedidoMostrar.toString()).append("\n");
            }

            System.out.println(mensaje.toString());
        } else {
            // lógica de mongo

        }

        return pedido; 
    }
}
