/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

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

    List<Pedido> pedidos = new ArrayList<>();

    @Override
    public Pedido registrarPedido(Pedido pedido) throws persistenciaException {
        pedidos.add(pedido);
        
        StringBuilder mensaje = new StringBuilder();
        for (Pedido pedidoMostrar : pedidos) {
            mensaje.append(pedidoMostrar.toString()).append("\n");
        }
        return pedido;
    }
}
