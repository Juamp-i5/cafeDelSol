/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jp
 */
public class PedidoDTO {

    private List<ProductoPedidoDTO> productos = new ArrayList<>();
    private double costoTotal;
    private boolean terminado;
    private String idUsuario;
    private PagoDTO pagoDTO;

    public PedidoDTO() {
        pagoDTO = new PagoDTO();
        pagoDTO.setEfectivoDTO(new EfectivoDTO());
        pagoDTO.setCambioDTO(new CambioDTO());
    }

    public PedidoDTO(boolean terminado) {
        this.terminado = terminado;
    }

    public PedidoDTO(List<ProductoPedidoDTO> pedido) {
        this.productos = pedido;
    }

    public PedidoDTO(double costoTotal, boolean terminado, List<ProductoPedidoDTO> pedido) {
        this.costoTotal = costoTotal;
        this.terminado = terminado;
        this.productos = pedido;
    }

    public PedidoDTO(double costoTotal, boolean terminado) {
        this.costoTotal = costoTotal;
        this.terminado = terminado;
    }

    public List<ProductoPedidoDTO> getProductos() {
        return productos;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public void setProductos(List<ProductoPedidoDTO> productos) {
        this.productos = productos;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public PagoDTO getPagoDTO() {
        return pagoDTO;
    }

    public void setPagoDTO(PagoDTO pagoDTO) {
        this.pagoDTO = pagoDTO;
    }

    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder();
        for (ProductoPedidoDTO producto : productos) {
            salida.append(producto.toString());
        }
        return salida.toString();
    }
}
