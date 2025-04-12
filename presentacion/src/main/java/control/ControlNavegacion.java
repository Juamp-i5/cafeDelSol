package control;

import DTOs.EfectivoDTO;
import DTOs.PedidoDTO;
import DTOs.ProductoMostrarDTO;
import DTOs.ProductoPedidoDTO;
import DTOs.SaboresMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.TarjetaDTO;
import DTOs.ToppingsMostrarDTO;
import exception.GestionException;
import gestion.IGestionPedidos;
import gestion.ManejadorPedidos;
import java.awt.Component;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pantallas.*;

/**
 * Clase Control que maneja los métodos que se encuentran en gestionPedidos y
 * que administra el flujo de las pantallas del sistema.
 *
 * @author norma
 */
public class ControlNavegacion {

    private static IGestionPedidos gestor = new ManejadorPedidos();
    private static Stack framesVisitados = new Stack();

    /**
     * Agrega un producto al producto pedido actual. Establece el producto del
     * parámetro como producto del producto pedido.
     *
     * @param producto El producto que se quiere agregar.
     */
    public static void agregarProducto(ProductoMostrarDTO producto) {
        gestor.agregarProducto(producto);
    }

    /**
     * Inicia un nuevo pedido, y a su vez inicia un producto pedido.
     */
    public static void iniciarPedidoNuevo() {
        gestor.iniciarPedido();
    }

    /**
     * Obtiene el pedido actual.
     *
     * @return El pedido actual.
     */
    public static ProductoPedidoDTO getProductoPedidoActual() {
        return gestor.getProductoPedidoActual();
    }

    /**
     * Agrega un producto pedido al pedido actual.
     */
    public static void agregarProductoPedidoAPedido() {
        try {
            gestor.agregarProductoPedidoAPedido();
        } catch (GestionException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Calcula el costo del producto pedido en relación al costo del producto,
     * el costo del tamaño seleccionado y la cantidad del producto pedido que se
     * desea.
     */
    public static void calcularCosto() {
        gestor.calcularCosto();
    }

    /**
     * Termina un pedido. Da como terminado un pedido.
     *
     * @return true si el pedido se termino con éxito, false en caso contrario.
     */
    public static boolean terminarPedido() {
        try {
            return gestor.terminarPedido();
        } catch (GestionException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Obtiene el pedido actual.
     *
     * @return El pedido actual.
     */
    public static PedidoDTO getPedido() {
        return gestor.getPedido();
    }

    /**
     * Calcula el cambio que debe entregarse basado en la cantidad de efectivo
     * proporcionado.
     *
     * @param efectivo El efectivo que representa el pago realizado.
     * @return El monto del cambio a regresar.
     */
    public static double calcularCambio(EfectivoDTO efectivo) {
        return gestor.calcularCambio(efectivo);
    }

    /**
     * Valida la información de una tarjeta de presentación.
     *
     * @param tarjetaIngresada La tarjeta que representa la tarjeta a validar
     * (con la que se esta pagando).
     * @return true si la tarjeta es valida, false en caso contrario.
     * @throws GestionException Excepción que lanzará si un campo de la tarjeta
     * es inválido.
     */
    public static boolean validarTarjetaPresentacion(TarjetaDTO tarjetaIngresada) throws GestionException {
        return gestor.validarTarjetaPresentacion(tarjetaIngresada);
    }

    /**
     * Establece el producto pedido actual.
     *
     * @param productoPedido El producto pedido actual.
     */
    public static void setProductoPedidoActual(ProductoPedidoDTO productoPedido) {
        gestor.setProductoPedidoActual(productoPedido);
    }

    /**
     * Crea un nuevo producto pedido.
     */
    public static void crearProductoPedido() {
        gestor.crearProductoPedido();
    }

    /**
     * Agrega un sabor al producto pedido actual. Establece el sabor del
     * parámetro como sabor del producto pedido.
     *
     * @param sabor El sabor que se quiere agregar.
     */
    public static void agregarSabor(SaboresMostrarDTO sabor) {
        gestor.agregarSabor(sabor);
    }

    /**
     * Agrega un tamaño al producto pedido actual. Establece el tamaño del
     * parámetro como tamaño del producto pedido.
     *
     * @param tamanio El tamaño que se quiere agregar.
     */
    public static void agregarTamanio(TamanioMostrarDTO tamanio) {
        gestor.agregarTamanio(tamanio);
    }

    /**
     * Agrega un topping al producto pedido actual.
     *
     * @param topping Establece el topping del parámetro como topping del
     * producto pedido.
     */
    public static void agregarTopping(ToppingsMostrarDTO topping) {
        gestor.agregarTopping(topping);
    }

    /**
     * Calcula el costo total del pedido en relación a los costos de los
     * productos pedidos que tiene.
     *
     * @return El costo total del producto pedido.
     */
    public static double calcularTotal() {
        return gestor.calcularTotal();
    }

    /**
     * Cancela un producto pedido dentro del pedido. Lo elimina del pedido.
     *
     * @param productoPedido El producto pedido que se quiere eliminar.
     */
    public static void cancelarProductoPedido(ProductoPedidoDTO productoPedido) {
        gestor.cancelarProductoPedido(productoPedido);
    }

    /**
     * Cancela el pedido actual.
     *
     * @return true si el pedido se cancelo con éxito, false en caso contrario.
     */
    public static boolean cancelarPedido() {
        try {
            return gestor.cancelarPedido(getPedido());
        } catch (GestionException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Actualiza el total del pedido en relación a los costos de los productos
     * pedidos que tiene el pedido.
     */
    public static void actualizarTotal() {
        gestor.actualizarTotal();
    }

    /**
     * Muestra la pantalla del menú principal. Se establece en el centro de la
     * pantalla y se marca como visible.
     */
    public static void mostrarPantallaMenuPrincipal() {
        JFrame menuPrincipal = new PantallaMenuPrincipal();
        menuPrincipal.setLocationRelativeTo(null);
        menuPrincipal.setVisible(true);

        framesVisitados.add(menuPrincipal);
    }

    /**
     * Muestra la pantalla de selección de productos. Se pasan los productos
     * cargados y el modo de operación como parámetros. Se imprime el pedido en
     * consola y se agrega la pantalla a la pila de pantallas visitadas.
     *
     * @param modo El modo de operación (indica si es modo creación o edición).
     */
    public static void mostrarPantallaProductos(Modo modo) {
        JFrame productos = new PantallaSeleccionarProducto(gestor.cargarProductos(), modo);
        productos.setLocationRelativeTo(null);
        productos.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(productos);
    }

    /**
     * Muestra la pantalla de selección de tamaños. Se pasan los tamaños
     * cargados y el modo de operación como parámetros. Se imprime el pedido en
     * consola y se agrega la pantalla a la pila de pantallas visitadas.
     *
     * @param modo El modo de operación (indica si es modo creación o edición).
     */
    public static void mostrarPantallaTamanios(Modo modo) {
        JFrame tamanios = new PantallaTamanios(gestor.cargarTamanios(), modo);
        tamanios.setLocationRelativeTo(null);
        tamanios.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(tamanios);
    }

    /**
     * Muestra la pantalla de selección de sabores. Se pasan los sabores
     * cargados y el modo de operación como parámetros. Se imprime el pedido en
     * consola y se agrega la pantalla a la pila de pantallas visitadas.
     *
     * @param modo El modo de operación (indica si es modo creación o edición).
     */
    public static void mostrarPantallaSabores(Modo modo) {
        JFrame sabores = new PantallaSabores(gestor.cargarSabores(), modo);
        sabores.setLocationRelativeTo(null);
        sabores.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(sabores);
    }

    /**
     * Muestra la pantalla de selección de toppings. Se pasan los toppings
     * cargados y el modo de operación como parámetros. Se imprime el pedido en
     * consola y se agrega la pantalla a la pila de pantallas visitadas.
     *
     * @param modo El modo de operación (indica si es modo creación o edición).
     */
    public static void mostrarPantallaToppings(Modo modo) {
        JFrame toppings = new PantallaToppings(gestor.cargarToppings(), modo);
        toppings.setLocationRelativeTo(null);
        toppings.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(toppings);
    }

    /**
     * Muestra la pantalla para agregar o terminar el pedido. Se imprime el
     * pedido en consola y quitan todas las pantallas visitadas.
     */
    public static void mostrarAgregarTerminarPedido() {
        JFrame agregarTerminarPedido = new AgregarOTerminarPedido();
        agregarTerminarPedido.setLocationRelativeTo(null);
        agregarTerminarPedido.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.empty();
    }

    /**
     * Muestra la pantalla de total desglosado del pedido. Se imprime el pedido
     * en consola y se agrega la pantalla a la pila de pantallas visitadas.
     */
    public static void mostrarPantallaTotalDesglosado() {
        JFrame totalDesglosado = new TotalDesglosado();
        totalDesglosado.setLocationRelativeTo(null);
        totalDesglosado.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(totalDesglosado);
    }

    /**
     * Muestra la pantalla para editar un producto dentro del pedido. Se pasa el
     * producto que se desea editar como parámetro. Se imprime el pedido en
     * consola y se agrega la pantalla a la pila de pantallas visitadas.
     *
     * @param productoPedido El producto que se desea editar.
     */
    public static void mostrarPantallaEditarProducto(ProductoPedidoDTO productoPedido) {
        JFrame editarProducto = new PantallaEditarProducto(productoPedido);
        editarProducto.setLocationRelativeTo(null);
        editarProducto.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(editarProducto);
    }

    /**
     * Muestra la pantalla de pago en efectivo. Se imprime el pedido en consola
     * y se agrega la pantalla de pago a la pila de pantallas visitadas.
     */
    public static void mostrarPantallaPagoEfectivo() {
        JFrame pagoEfectivo = new PagoEfectivo();
        pagoEfectivo.setLocationRelativeTo(null);
        pagoEfectivo.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(pagoEfectivo);
    }

    /**
     * Muestra la pantalla de pago con tarjeta. Se imprime el pedido en consola
     * y se agrega la pantalla de pago a la pila de pantallas visitadas.
     */
    public static void mostrarPantallaPagoTarjeta() {
        JFrame pagoTarjeta = new PagoTarjeta();
        pagoTarjeta.setLocationRelativeTo(null);
        pagoTarjeta.setVisible(true);
        gestor.imprimirPedidoConsola();

        framesVisitados.add(pagoTarjeta);
    }

    /**
     * Vuelve a la pantalla anterior en la pila de pantallas visitadas. Si hay
     * una pantalla previa en la pila, se la muestra nuevamente.
     */
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

    /**
     * Muestra un mensaje de tarjeta rechazada.
     */
    public static void mostrarPantallaTarjetaRechazada() {
        JOptionPane.showMessageDialog(null, "Tarjeta rechazada");
    }

    /**
     * Muestra un mensaje de éxito cuando el pedido ha sido realizado. Después
     * de un segundo, regresa a la pantalla principal.
     */
    public static void mostrarPantallaPedidoRealizado() {
        JOptionPane.showMessageDialog(null, "Pedido realizado");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ControlNavegacion.mostrarPantallaMenuPrincipal();
            }
        }, 1000);
    }

    /**
     * Muestra un mensaje de éxito cuando el pedido ha sido cancelado.
     *
     * @param frame El componente desde el cual se muestra el mensaje.
     */
    public static void mostrarPantallaPedidoCancelado(Component frame) {
        JOptionPane.showMessageDialog(
                frame,
                "El pedido ha sido cancelado con éxito.",
                "Cancelación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
        );

    }

    /**
     * Muestra un mensaje de éxito cuando un producto pedido ha sido cancelado.
     *
     * @param frame El componente desde el cual se muestra el mensaje.
     */
    public static void mostrarPantallaProductoPedidoCancelado(Component frame) {
        JOptionPane.showMessageDialog(
                frame,
                "El producto pedido ha sido cancelado con éxito.",
                "Cancelación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
        );

    }
    
    public static void registrarPedido() {
//        gestor.
    }
}
