package control;

import DTOs.EfectivoDTO;
import DTOs.PedidoDTO;
import DTOs.ProductoMostrarDTO;
import DTOs.ProductoPedidoDTO;
import DTOs.SaboresMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.TarjetaDTO;
import DTOs.ToppingsMostrarDTO;
import gestion.IGestionPedidos;
import gestion.ManejadorPedidos;
import java.awt.Component;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pantallas.*;

public class ControlNavegacion {

    private static IGestionPedidos gestor = new ManejadorPedidos();
    private static Stack framesVisitados = new Stack();

    //Encapsulacion gestor
    public static void agregarProducto(ProductoMostrarDTO producto) {
        gestor.agregarProducto(producto);
    }

    public static void iniciarPedidoNuevo() {
        gestor.iniciarPedido();
    }

    public static ProductoPedidoDTO getProductoPedidoActual() {
        return gestor.getProductoPedidoActual();
    }

    public static void agregarProductoPedidoAPedido() {
        gestor.agregarProductoPedidoAPedido();
    }

    public static void calcularCosto() {
        gestor.calcularCosto();
    }

    public static boolean terminarPedido() {
        return gestor.terminarPedido();
    }

    public static PedidoDTO getPedido() {
        return gestor.getPedido();
    }

    public static double calcularCambio(EfectivoDTO efectivo) {
        return gestor.calcularCambio(efectivo);
    }

    public static boolean validarTarjetaPresentacion(TarjetaDTO tarjetaIngresada) {
        return gestor.validarTarjetaPresentacion(tarjetaIngresada);
    }

    public static void setProductoPedidoActual(ProductoPedidoDTO productoPedido) {
        gestor.setProductoPedidoActual(productoPedido);
    }

    public static void crearProductoPedido() {
        gestor.crearProductoPedido();
    }

    public static void agregarSabor(SaboresMostrarDTO sabor) {
        gestor.agregarSabor(sabor);
    }

    public static void agregarTamanio(TamanioMostrarDTO tamanio) {
        gestor.agregarTamanio(tamanio);
    }

    public static void agregarTopping(ToppingsMostrarDTO topping) {
        gestor.agregarTopping(topping);
    }

    public static double calcularTotal() {
        return gestor.calcularTotal();
    }
    
    public static void cancelarProductoPedido(ProductoPedidoDTO productoPedido){
        gestor.cancelarProductoPedido(productoPedido);
    }
    
    public static boolean cancelarPedido(){
        return gestor.cancelarPedido(getPedido());
    }
    
    public static void actualizarTotal(){
        gestor.actualizarTotal();
    }

    // PANTALLAS
    public static void mostrarPantallaMenuPrincipal() {
        JFrame menuPrincipal = new PantallaMenuPrincipal();
        menuPrincipal.setLocationRelativeTo(null);
        menuPrincipal.setVisible(true);

        framesVisitados.add(menuPrincipal);
    }

    public static void mostrarPantallaProductos(Modo modo) {
        JFrame productos = new PantallaSeleccionarProducto(gestor.cargarProductos(), modo);
        productos.setLocationRelativeTo(null);
        productos.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(productos);
    }

    public static void mostrarPantallaTamanios(Modo modo) {
        JFrame tamanios = new PantallaTamanios(gestor.cargarTamanios(), modo);
        tamanios.setLocationRelativeTo(null);
        tamanios.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(tamanios);
    }

    public static void mostrarPantallaSabores(Modo modo) {
        JFrame sabores = new PantallaSabores(gestor.cargarSabores(), modo);
        sabores.setLocationRelativeTo(null);
        sabores.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(sabores);
    }

    public static void mostrarPantallaToppings(Modo modo) {
        JFrame toppings = new PantallaToppings(gestor.cargarToppings(), modo);
        toppings.setLocationRelativeTo(null);
        toppings.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(toppings);
    }

    public static void mostrarAgregarTerminarPedido() {
        JFrame agregarTerminarPedido = new AgregarOTerminarPedido();
        agregarTerminarPedido.setLocationRelativeTo(null);
        agregarTerminarPedido.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.empty();
    }

    public static void mostrarPantallaTotalDesglosado() {
        JFrame totalDesglosado = new TotalDesglosado();
        totalDesglosado.setLocationRelativeTo(null);
        totalDesglosado.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(totalDesglosado);
    }

    public static void mostrarPantallaEditarProducto(ProductoPedidoDTO productoPedido) {
        JFrame editarProducto = new PantallaEditarProducto(productoPedido);
        editarProducto.setLocationRelativeTo(null);
        editarProducto.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(editarProducto);
    }

    public static void mostrarPantallaPagoEfectivo() {
        JFrame pagoEfectivo = new PagoEfectivo();
        pagoEfectivo.setLocationRelativeTo(null);
        pagoEfectivo.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(pagoEfectivo);
    }

    public static void mostrarPantallaPagoTarjeta() {
        JFrame pagoTarjeta = new PagoTarjeta();
        pagoTarjeta.setLocationRelativeTo(null);
        pagoTarjeta.setVisible(true);
        gestor.imprimirPedidoConsola();

        framesVisitados.add(pagoTarjeta);
    }

    public static void volverPantallaAnterior() {
        if (!framesVisitados.isEmpty()) {
            JFrame ventanaActual = (JFrame) framesVisitados.pop();
            ventanaActual.dispose();

            if (!framesVisitados.isEmpty()) {
                JFrame frameAnterior = (JFrame) framesVisitados.peek();
                frameAnterior.setLocationRelativeTo(null);
                frameAnterior.setVisible(true);
            }
        }
    }

    //OPTION PANES
    public static void mostrarPantallaTarjetaRechazada() {
        JOptionPane.showMessageDialog(null, "Tarjeta rechazada");
    }

    public static void mostrarPantallaPedidoRealizado() {
        JOptionPane.showMessageDialog(null, "Pedido realizado");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ControlNavegacion.mostrarPantallaMenuPrincipal();
            }
        }, 1000);
    }

    public static void mostrarPantallaPedidoCancelado(Component frame) {
        JOptionPane.showMessageDialog(
                frame,
                "El pedido ha sido cancelado con éxito.",
                "Cancelación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
        );

    }

    public static void mostrarPantallaProductoPedidoCancelado(Component frame) {
        JOptionPane.showMessageDialog(
                frame,
                "El producto pedido ha sido cancelado con éxito.",
                "Cancelación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
        );

    }
}
