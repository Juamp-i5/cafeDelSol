/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simuladorPedidosDelivery;

import DTOs.PedidoDeliveryDTO;
import DTOs.ProductoPedidoDeliveryDTO;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Jp
 */
public class SimuladorPedidosDelivery {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final Random random = new Random();

    // Listas de ejemplo para valores posibles
    private static final List<String> nombresProductos = List.of("Café Americano", "Latte", "Capuchino", "Té Chai", "Chocolate Caliente");
    private static final List<String> tamanios = List.of("Chico", "Mediano", "Grande");
    private static final List<String> sabores = List.of("Vainilla", "Caramelo", "Chocolate", "Sin sabor");
    private static final List<String> toppings = List.of("Crema batida", "Canela", "Malvaviscos", "Ninguno");

    public SimuladorPedidosDelivery() {
    }

    public void iniciarSimulacion() {
        Runnable tarea = () -> {
            try {
                PedidoDeliveryDTO pedidoSimulado = generarPedidoAleatorio();
//                manejadorPedidos.registrarPedido(pedidoSimulado);
                System.out.println("Pedido simulado registrado: " + pedidoSimulado);
            } catch (Exception e) {
                System.err.println("Error al simular pedido: " + e.getMessage());
            }
        };

        scheduler.scheduleAtFixedRate(tarea, 60, 600, TimeUnit.SECONDS);
    }

    private PedidoDeliveryDTO generarPedidoAleatorio() {
        PedidoDeliveryDTO pedido = new PedidoDeliveryDTO();

        int numProductos = random.nextInt(3) + 1;

        double costoTotal = 0.0;

        for (int i = 0; i < numProductos; i++) {
            String producto = obtenerAleatorio(nombresProductos);
            String tamanio = obtenerAleatorio(tamanios);
            String sabor = obtenerAleatorio(sabores);
            String topping = obtenerAleatorio(toppings);
            int cantidad = random.nextInt(3) + 1;
            double costoUnitario = calcularCosto(producto, tamanio);
            double costo = costoUnitario * cantidad;

            ProductoPedidoDeliveryDTO detalle = new ProductoPedidoDeliveryDTO(
                    producto, tamanio, sabor, topping, cantidad, costo
            );

            pedido.getProductos().add(detalle);
            costoTotal += costo;
        }

        pedido.setCostoTotal(costoTotal);
        pedido.setTerminado(false);

        return pedido;
    }

    private String obtenerAleatorio(List<String> opciones) {
        return opciones.get(random.nextInt(opciones.size()));
    }

    private double calcularCosto(String producto, String tamanio) {
        double base = switch (producto) {
            case "Café Americano" ->
                30.0;
            case "Latte" ->
                35.0;
            case "Capuchino" ->
                40.0;
            case "Té Chai" ->
                33.0;
            case "Chocolate Caliente" ->
                38.0;
            default ->
                30.0;
        };

        double extra = switch (tamanio) {
            case "Mediano" ->
                20.0;
            case "Grande" ->
                40.0;
            default ->
                0.0;
        };

        return base + extra;
    }
}
