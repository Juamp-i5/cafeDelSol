/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author rodri
 */
public class Pedido {

    private ObjectId id;
    private List<ProductoPedido> pedido = new ArrayList<>();
    private double costoTotal;
    private boolean terminado;

    public Pedido() {
    }

    public Pedido(boolean terminado) {
        this.terminado = terminado;
    }

    public Pedido(List<ProductoPedido> pedido) {
        this.pedido = pedido;
    }

    public Pedido(double costoTotal, boolean terminado) {
        this.costoTotal = costoTotal;
        this.terminado = terminado;
    }

    public Pedido(double costoTotal, boolean terminado, List<ProductoPedido> pedido) {
        this.costoTotal = costoTotal;
        this.terminado = terminado;
        this.pedido = pedido;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<ProductoPedido> getPedido() {
        return pedido;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public void setPedido(List<ProductoPedido> pedido) {
        this.pedido = pedido;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder();
        for (ProductoPedido producto : pedido) {
            salida.append(producto.toString());
        }
        return salida.toString();
    }

}
