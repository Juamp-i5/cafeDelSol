/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.PedidoDTO;
import interfacesBO.IPedidoBO;
import java.util.ArrayList;
import java.util.List;
import observers.interfaces.NuevaVentaObserver;

/**
 *
 * @author rodri
 */
public class PedidoBO implements IPedidoBO {

    private List<NuevaVentaObserver> observers = new ArrayList<>();

    public void agregarObserver(NuevaVentaObserver observer) {
        observers.add(observer);
    }

    private void notificarObservers() {
        for (NuevaVentaObserver observer : observers) {
            observer.update();
        }
    }

    @Override
    public PedidoDTO registrarPedido(PedidoDTO pedidoDTO) {
        notificarObservers();
    }

}
