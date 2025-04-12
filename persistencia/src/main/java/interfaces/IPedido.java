/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Pedido;
import exception.persistenciaException;

/**
 *
 * @author rodri
 */
public interface IPedido {
    
    public Pedido registrarPedido (Pedido pedido) throws persistenciaException;
    
}
