package control;

import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteNuevoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import DTOs.CRUDProductos.DetallesProductoDTO;
import DTOs.CRUDProductos.ProductoCreateDTO;
import DTOs.CRUDProductos.ProductoListDTO;
import DTOs.CRUDSalidas.DetalleSalidaDTO;
import DTOs.CRUDSalidas.SalidaListDTO;
import DTOs.CRUDSalidas.SalidaNuevaDTO;
import pantallas.cubiculos.PantallaCubiculosOPedidos;
import pantallas.cubiculos.PantallaReservar;
import pantallas.cubiculos.PantallaMenuCubiculos;
import DTOs.EfectivoDTO;
import DTOs.InicioSesionDTO;
import DTOs.PedidoDTO;
import DTOs.ProductoMostrarDTO;
import DTOs.ProductoPedidoDTO;
import DTOs.SaborMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.TarjetaDTO;
import DTOs.ToppingMostrarDTO;
import DTOs.cubiculos.EfectivoDTOCubiculo;
import DTOs.cubiculos.ReagendaDTO;
import DTOs.cubiculos.ReservacionDTOMostrar;
import DTOs.cubiculos.ReservacionDetalleDTO;
import DTOs.cubiculos.ReservacionNuevaDTO;
import Excepcion.GestorCRUDEntradasException;
import Gestion.GestorCRUDEntradas;
import Gestion.IGestorCRUDEntradas;
import excepciones.GestionCRUDIngredientesException;
import excepciones.GestionCRUDProductosException;
import excepciones.GestionCRUDSalidasException;
import excepciones.GestionCubiculosException;
import exception.GestionException;
import gestion.GestorCRUDProductos;
import gestion.GestorCubiculos;
import gestion.IGestionPedidos;
import gestion.IGestorCRUDProductos;
import gestion.IGestorCubiculos;
import gestion.ManejadorPedidos;
import gestionIngredientes.GestorCRUDIngredientes;
import gestionIngredientes.IGestorCRUDIngredientes;
import gestionSalidas.GestorCRUDSalidas;
import java.awt.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pantallas.*;
import pantallas.CRUDEntradas.PantallaTablaDetallesEntrada;
import pantallas.CRUDEntradas.PantallaTablaHistorialEntradas;
import pantallas.CRUDEntradas.PantallaTablaRegistroEntrada;
import pantallas.CRUDProductos.PantallaDetallesProducto;
import pantallas.CRUDProductos.PantallaRegistrarProducto;
import pantallas.CRUDProductos.PantallaTablaProductos;
import pantallas.CRUDSalidas.MenuSalidas;
import pantallas.CRUDSalidas.PantallaDetalleSalida;
import pantallas.CRUDSalidas.PantallaHistorialSalidas;
import pantallas.CRUDSalidas.PantallaRegistrarSalida;
import pantallas.cubiculos.PagoEfectivoCubiculos;
import pantallas.cubiculos.PagoTarjetaCubiculos;
import pantallas.cubiculos.PantallaReagendar;
import pantallas.cubiculos.PantallaVerReservaciones;
import pantallas.ingredientes.PantallaAgregarIngrediente;
import pantallas.ingredientes.PantallaBuscarIngrediente;
import pantallas.ingredientes.PantallaEditarDetallesIngrediente;
import pantallas.ingredientes.PantallaListaIngredientes;
import pantallas.ingredientes.PantallaVerDetallesIngrediente;

/**
 * Clase Control que maneja los métodos que se encuentran en gestionPedidos y
 * que administra el flujo de las pantallas del sistema.
 *
 * @author norma
 */
public class ControlNavegacion {

    private static IGestionPedidos gestor = new ManejadorPedidos();
    private static IGestorCRUDProductos gestorCRUDProductos = GestorCRUDProductos.getInstance();
    private static IGestorCRUDEntradas gestorCRUDEntradas = GestorCRUDEntradas.getInstance();
    private static IGestorCRUDIngredientes gestorCRUDIngredientes = GestorCRUDIngredientes.getInstance();
    private static IGestorCubiculos gestorCubiculos = GestorCubiculos.getInstance();
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
     */
    public static boolean validarTarjetaPresentacion(TarjetaDTO tarjetaIngresada) {
        try {
            return gestor.validarTarjetaPresentacion(tarjetaIngresada);
        } catch (GestionException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
    public static void agregarSabor(SaborMostrarDTO sabor) {
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
    public static void agregarTopping(ToppingMostrarDTO topping) {
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
        try {
            List<ProductoMostrarDTO> productosMostrar = gestor.cargarProductos();
            JFrame productos = new PantallaSeleccionarProducto(productosMostrar, modo);
            productos.setLocationRelativeTo(null);
            productos.setVisible(true);

            gestor.imprimirPedidoConsola();

            framesVisitados.add(productos);
        } catch (GestionException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    /**
     * Muestra la pantalla de selección de tamaños. Se pasan los tamaños
     * cargados y el modo de operación como parámetros. Se imprime el pedido en
     * consola y se agrega la pantalla a la pila de pantallas visitadas.
     *
     * @param modo El modo de operación (indica si es modo creación o edición).
     */
    public static void mostrarPantallaTamanios(Modo modo) {
        List<TamanioMostrarDTO> tamanios = getTamanios();
        if (tamanios == null) {
            return;
        }
        JFrame pantalla = new PantallaTamanios(tamanios, modo);
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);

        gestor.imprimirPedidoConsola();

        framesVisitados.add(pantalla);
    }

    public static List<TamanioMostrarDTO> getTamanios() {
        try {
            return gestor.cargarTamanios();
        } catch (GestionException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al obtener tamanios");
            return null;
        }
    }

    /**
     * Muestra la pantalla de selección de sabores. Se pasan los sabores
     * cargados y el modo de operación como parámetros. Se imprime el pedido en
     * consola y se agrega la pantalla a la pila de pantallas visitadas.
     *
     * @param modo El modo de operación (indica si es modo creación o edición).
     */
    public static void mostrarPantallaSabores(Modo modo) {
        try {
            JFrame sabores = new PantallaSabores(gestor.cargarSabores(), modo);
            sabores.setLocationRelativeTo(null);
            sabores.setVisible(true);

            gestor.imprimirPedidoConsola();

            framesVisitados.add(sabores);
        } catch (GestionException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muestra la pantalla de selección de toppings. Se pasan los toppings
     * cargados y el modo de operación como parámetros. Se imprime el pedido en
     * consola y se agrega la pantalla a la pila de pantallas visitadas.
     *
     * @param modo El modo de operación (indica si es modo creación o edición).
     */
    public static void mostrarPantallaToppings(Modo modo) {
        try {
            JFrame toppings = new PantallaToppings(gestor.cargarToppings(), modo);
            toppings.setLocationRelativeTo(null);
            toppings.setVisible(true);

            gestor.imprimirPedidoConsola();

            framesVisitados.add(toppings);
        } catch (GestionException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                ControlNavegacion.mostrarPantallaMenuIniciado();
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
        try {
            gestor.agregarUsuarioAlPedido(Sesion.getCurrentUsuario().getId());
            gestor.registrarPedido();
        } catch (GestionException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muestra la pantalla del menú de cubiculos. Se establece en el centro de
     * la pantalla y se marca como visible.
     */
    public static void mostrarPantallaMenuCubiculos() {
        JFrame menuCubiculos = new PantallaMenuCubiculos();
        menuCubiculos.setLocationRelativeTo(null);
        menuCubiculos.setVisible(true);

        framesVisitados.add(menuCubiculos);
    }

    /**
     * Muestra la pantalla para elegir si ir al menu de cubiculos o de pedidos.
     * Se establece en el centro de la pantalla y se marca como visible.
     */
    public static void mostrarPantallaCubiculosOPedidos() {
        JFrame menu = new PantallaCubiculosOPedidos();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);

        framesVisitados.add(menu);
    }

    /**
     * Muestra la pantalla para realizar una reservación. Se establece en el
     * centro de la pantalla y se marca como visible.
     */
    public static void mostrarPantallaReservar() {
        JFrame pantallaReservar = new PantallaReservar();
        pantallaReservar.setLocationRelativeTo(null);
        pantallaReservar.setVisible(true);

        framesVisitados.add(pantallaReservar);
    }

    public static void comprobarUsuario(InicioSesionDTO inicioSesionDTO) throws GestionException {
        Sesion.setCurrentUsuario(gestor.comprobarInicioSesion(inicioSesionDTO));
        if (Sesion.getCurrentUsuario().getTipoEmpleado().equals("Barista")) {
            mostrarPantallaMenuBarista();
        } else if (Sesion.getCurrentUsuario().getTipoEmpleado().equals("Administrador")) {
            mostrarPantallaMenuAdministrador();
        }
    }

    public static void mostrarPantallaMenuAdministrador() {
        JFrame pantalla = new PantallaMenuAdmin();
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);

        framesVisitados.add(pantalla);
    }

    public static void mostrarPantallaInicioSesion() {
        Sesion.setCurrentUsuario(null);

        JFrame pantalla = new PantallaInicioSesion();
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);
    }

    public static void mostrarPantallaMenuBarista() {
        JFrame pantalla = new PantallaMenuBarista();
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);

        framesVisitados.add(pantalla);
    }

    public static void mostrarPantallaMenuIniciado() {
        if (Sesion.getCurrentUsuario().getTipoEmpleado().equals("Barista")) {
            mostrarPantallaMenuBarista();
        } else {
            mostrarPantallaMenuAdministrador();
        }
    }

    //=====================================================
    //================== CRUD PRODUCTOS ===================
    //=====================================================
    //METODOS DE FLUJO DE PANTALLAS
    public static void mostrarPantallaTablaProductos() {
        JFrame frame = new PantallaTablaProductos();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void mostrarPantallaRegistrarProducto() {
        JFrame frame = new PantallaRegistrarProducto();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void mostrarPantallaDetallesProducto(String idProducto) {
        DetallesProductoDTO productoDTO = obtenerDetallesProducto(idProducto);

        JFrame frame = new PantallaDetallesProducto(productoDTO);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void mostrarPantallaIngrediente(Consumer<IngredienteViejoListDTO> regreso) {
        JFrame frame = new PantallaBuscarIngrediente(regreso);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    // METODOS DE COMUNICACION CON CAPA INFERIOR
    public static List<ProductoListDTO> obtenerProductosFiltrados(String filtroNombre, String filtroCategoria) {
        try {
            return gestorCRUDProductos.obtenerProductosFiltrados(filtroNombre, filtroCategoria);
        } catch (GestionCRUDProductosException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static DetallesProductoDTO obtenerDetallesProducto(String idProducto) {
        try {
            return gestorCRUDProductos.obtenerDetallesProducto(idProducto);
        } catch (GestionCRUDProductosException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void guardarProducto(ProductoCreateDTO productoDTO) throws GestionCRUDProductosException {
        gestorCRUDProductos.guardarProducto(productoDTO);
        JOptionPane.showMessageDialog(null, "Producto guardado exitosamente");
        ControlNavegacion.mostrarPantallaTablaProductos();

    }

    public static void actualizarProducto(DetallesProductoDTO productoDTO) throws GestionCRUDProductosException {
        gestorCRUDProductos.actualizarProducto(productoDTO);
        JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente");

    }

    //ingredientes pantallas
    public static void mostrarPantallaListaIngredientes() {
        JFrame frame = new PantallaListaIngredientes();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void mostrarPantallaVerDetallesIngrediente(String idIngrediente) {
        DetallesIngredienteViejoDTO ingredienteDTO = obtenerDetallesIngrediente(idIngrediente);

        JFrame frame = new PantallaVerDetallesIngrediente(ingredienteDTO);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void mostrarPantallaEditarDetallesIngrediente(DetallesIngredienteViejoDTO ingrediente) {
        JFrame frame = new PantallaEditarDetallesIngrediente(ingrediente);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void mostrarPantallaAgregarIngrediente() {
        JFrame frame = new PantallaAgregarIngrediente();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void mostrarBuscarIngrediente() {
        JFrame frame = new PantallaBuscarIngrediente();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //métodos
//    public static List<IngredienteViejoListDTO> obtenerIngredientesFiltrados(String filtroNombre, String filtroNivelStock) {
//        return new ArrayList();
//    }
//    private static DetallesIngredienteViejoDTO obtenerDetallesIngrediente(String idIngrediente) {
//        return new DetallesIngredienteViejoDTO();
//    }
    //Pantallas Entradas  
    public static void mostrarPantallaDetallesEntrada(EntradaViejaDTO entrada) {
        JFrame frame = new PantallaTablaDetallesEntrada(entrada);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void mostrarPantallaHistorialEntradas() {
        JFrame frame = new PantallaTablaHistorialEntradas();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void mostrarPantallaRegistroEntrada() {
        JFrame frame = new PantallaTablaRegistroEntrada();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // entradas metodos
    public static boolean registrarEntrada(EntradaNuevaDTO entrada) {
        try {
            gestorCRUDEntradas.registrarEntrada(entrada);
            JOptionPane.showMessageDialog(null, "Entrada registrada exitosamente");
            return true;
        } catch (GestorCRUDEntradasException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static List<EntradaViejaDTO> obtenerListaEntradasPorRangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        try {
            return gestorCRUDEntradas.obtenerEntradasPorFechas(fechaInicio, fechaFin);
        } catch (GestorCRUDEntradasException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static EntradaViejaDTO obtenerDetallesConIngredientes(String entradaId) {
        try {
            return gestorCRUDEntradas.obtenerDetallesConIngredientes(entradaId);
        } catch (GestorCRUDEntradasException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //ingredientes métodos
    public static boolean agregarIngrediente(IngredienteNuevoDTO ingrediente) {
        try {
            gestorCRUDIngredientes.agregarIngrediente(ingrediente);
            JOptionPane.showMessageDialog(null, "Ingrediente agregado exitosamente");
            return true;
        } catch (GestionCRUDIngredientesException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static List<ProveedorViejoDTO> obtenerProveedores() {
        try {
            return gestorCRUDIngredientes.obtenerProveedores();
        } catch (GestionCRUDIngredientesException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<IngredienteViejoListDTO> buscarIngredientesPorFiltros(String filtroNombre, String filtroNivelStock) {
        try {
            return gestorCRUDIngredientes.buscarIngredientesPorFiltros(filtroNombre, filtroNivelStock);
        } catch (GestionCRUDIngredientesException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static DetallesIngredienteViejoDTO obtenerDetallesIngrediente(String idIngrediente) {
        try {
            return gestorCRUDIngredientes.obtenerDetallesIngrediente(idIngrediente);
        } catch (GestionCRUDIngredientesException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static DetallesIngredienteViejoDTO editarIngrediente(String idIngrediente, String nuevoNombre) {
        try {
            return gestorCRUDIngredientes.editarIngrediente(idIngrediente, nuevoNombre);
        } catch (GestionCRUDIngredientesException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean aumentarStock(String idIngrediente, Double cantidad) {
        try {
            gestorCRUDIngredientes.aumentarStock(idIngrediente, cantidad);
            return true;
        } catch (GestionCRUDIngredientesException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static void actualizarNivelStock(String idIngrediente) {
        try {
            gestorCRUDIngredientes.actualizarNivelStock(idIngrediente);
            JOptionPane.showMessageDialog(null, "Se actualizó el stock exitosamente");
        } catch (GestionCRUDIngredientesException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String obtenerIdIngredientePorNombre(String nombre) {
        try {
            return gestorCRUDIngredientes.obtenerIdIngredientePorNombre(nombre);
        } catch (GestionCRUDIngredientesException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //=====================================================
    //==================== CUBICULOS ======================
    //=====================================================
    
    /**
     * Método que obtiene los cubiculos para poderlos mostrar en los combobox
     * 
     * @return Lista tipo String con los nombres de los cubículos
     *
     */
    public static List<String> obtenerCubiculos() {
        try {
            return gestorCubiculos.obtenerCubiculos();
        } catch (GestionCubiculosException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Método que obtiene el precio del cubículo que se usará en la reservación
     * 
     * @param nombre para buscar el cubículo por nombre, el cuál es único.
     * 
     * @return El precio por hora que tiene el cubículo
     *
     */
    public static Double obtenerPrecioCubiculo(String nombre) {
        try {
            return gestorCubiculos.obtenerPorNombre(nombre).getPrecioHora();
        } catch (GestionCubiculosException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Método que calcula el precio de la reservación segun la duración y el precio del cubículo
     * 
     * @param inicio Fecha inicio de la reservación
     * @param fin Fecha fin de la reservación
     * @param precioPorHora Precio del cubículo por hora
     * 
     * @return El precio total de la reservación
     *
     */
    public static Double calcularPrecioReservacion(LocalTime inicio, LocalTime fin, Double precioPorHora) {
        long minutos = ChronoUnit.MINUTES.between(inicio, fin);
        double horas = minutos / 60.0;

        return horas * precioPorHora;
    }

    /**
     * Método que obtiene el precio del cubículo que se usará en la reservación
     * 
     * @return Método get para la variable de Gestor reservacionNueva
     *
     */
    public static ReservacionNuevaDTO getReservacionNueva() {
        return gestorCubiculos.getReservacionNueva();
    }

    /**
     * Método para settear la reservación nueva en la variable de Gestor
     * 
     * @param reservacionNueva dto con los datos para crear una ReservacionNuevaDTO en Gestor
     * 
     * @return True is se logró realizar el set, facilita la validación después
     *
     */
    public static boolean setReservacionNueva(ReservacionNuevaDTO reservacionNueva) {
        try {
            return gestorCubiculos.setReservacionNueva(reservacionNueva);
        } catch (GestionCubiculosException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Método para settear la reagenda nueva en la variable de Gestor
     * 
     * @param reagenda DTO para enviar a gestor
     * 
     * @return Retorna la hora fin de la reagenda
     *
     */
    public static LocalTime setReagendaNueva(ReagendaDTO reagenda) {
        try {
            return gestorCubiculos.setReagendaNueva(reagenda);
        } catch (GestionCubiculosException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Método para mostrar la pantalla de pago en efectivo del CU cubículos
     */
    public static void mostrarPantallaPagoEfCubiculo() {
        JFrame pagoEfectivo = new PagoEfectivoCubiculos();
        pagoEfectivo.setLocationRelativeTo(null);
        pagoEfectivo.setVisible(true);

        framesVisitados.add(pagoEfectivo);
    }

    /**
     * Método para mostrar la pantalla de pago con tarjeta del CU cubículos
     */
    public static void mostrarPantallaPagoTarjCubiculo() {
        JFrame pagoTarjeta = new PagoTarjetaCubiculos();
        pagoTarjeta.setLocationRelativeTo(null);
        pagoTarjeta.setVisible(true);

        framesVisitados.add(pagoTarjeta);
    }

    /**
     * Método que llama a gestor para calcula el cambio respecto a la cantidad ingresada para pagar la reservación
     * 
     * @param efectivo la cantidad recibida, se pasa como parámetro al gestor
     * 
     * @return El cambio a dar al cliente
     */
    public static double calcularCambioCubiculo(EfectivoDTOCubiculo efectivo) {
        return gestorCubiculos.calcularCambio(efectivo);
    }

    /**
     * Método que muestra que la reservación se ha realizado con éxito
     * 
     * @param numReservacion se le da como parámetro para mostrarlo en el optionPane
     */
    public static void mostrarPantallaReservacionExitosa(Integer numReservacion) {
        JOptionPane.showMessageDialog(null, "Reservación exitosa"+"\n"+"Numero de Reservación: " + numReservacion);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ControlNavegacion.mostrarPantallaMenuCubiculos();
            }
        }, 1000);
    }

    /**
     * Método que manda llamar a gestor para guardar la reservación en la base de datos
     * 
     * @return El número de reservación asignado
     */
    public static Integer realizarReservacion() {
        try {
            return gestorCubiculos.realizarReservacion();
        } catch (GestionCubiculosException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Muestra la pantalla para reagendar una reservación
     * 
     */
    public static void mostrarPantallaReagendar() {
        JFrame pantallaReagendar = new PantallaReagendar();
        pantallaReagendar.setLocationRelativeTo(null);
        pantallaReagendar.setVisible(true);

        framesVisitados.add(pantallaReagendar);
    }
    
    /**
     * Método que engloba los métodos de gestor para guardar una reservación en la base de datos y ajustar la vieja
     * @param reagenda Contiene datos necesarios para la reagenda
     * @param horaFinNueva La hora fin de la reservación nueva
     * @return El número de reservación nuevo ya que está reagendada.
     */
    public static Integer realizarReagenda(ReagendaDTO reagenda, LocalTime horaFinNueva) {
        try {
            Integer numReservacionNuevo;
            numReservacionNuevo = gestorCubiculos.realizarReagenda(horaFinNueva);
            Integer numReservacionViejo = Integer.valueOf(reagenda.getNumReservacion());
            gestorCubiculos.modificarReservacion(numReservacionViejo, numReservacionNuevo, reagenda.getMotivo());

            return numReservacionNuevo;
        } catch (GestionCubiculosException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Método que llama a gestor para modificar el estado de la reservación a CANCELADO y agregar los datos extra
     * @param numReservacion El número de reservación a cancelar
     * @param motivo El motivo de cancelación
     * @return El número de reservación a cancelar
     */
    public static Integer cancelarReservacion(Integer numReservacion, String motivo) {
        try {
            gestorCubiculos.modificarReservacion(numReservacion, null, motivo);

            return numReservacion;
        } catch (GestionCubiculosException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Método que manda llamar a gestor para cambiar la reservación a ACTIVA o CONCLUIDA según sea el caso
     * @param numReservacion Número de la reservación a modificar el estado
     * @param estado Estado nuevo de la reservación
     * @return True si se logró completar la acción
     */
    public static boolean actualizarEstadoReservacion(Integer numReservacion, String estado) {
        try {
            gestorCubiculos.actualizarEstado(numReservacion, estado);
            return true;
        } catch (GestionCubiculosException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Llama a Gestor para obtener las reservaciones con estado PENDIENTE o ACTIVA por filtro de fechas
     * @param fechaInicio Filtro para buscar por fecha "Desde", se da null para ignorar el filtro
     * @param fechaFin Filtro para buscar por fecha "Hasta", se da null para ignorar el filtro
     * @return Lista de reservaciones pendientes y activas.
     */
    public static List<ReservacionDTOMostrar> cargarReservacionesPendientes(LocalDate fechaInicio, LocalDate fechaFin) {
        try {
            return gestorCubiculos.obtenerReservacionesPendientes(fechaInicio, fechaFin);
        } catch (GestionCubiculosException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Llama a Gestor para obtener todas las reservaciones por filtro de fechas
     * @param fechaInicio Filtro para buscar por fecha "Desde", se da null para ignorar el filtro
     * @param fechaFin Filtro para buscar por fecha "Hasta", se da null para ignorar el filtro
     * @return Lista de todas las reservaciones.
     */
    public static List<ReservacionDTOMostrar> cargarReservacionesHistorial(LocalDate fechaInicio, LocalDate fechaFin) {
        try {
            return gestorCubiculos.obtenerReservacionesHistorial(fechaInicio, fechaFin);
        } catch (GestionCubiculosException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Llama a Gestor para obtener una reservación con todos sus posibles campos de información.
     * @param numReservacion La reservación a buscar
     * @return Los detalles completos de una reservación
     */
    public static ReservacionDetalleDTO getDetalleReservacion(Integer numReservacion) {
        try {
            return gestorCubiculos.getDetalleReservacion(numReservacion);
        } catch (GestionCubiculosException ex) {
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Muestra la pantalla en la que se cargan todas las reservaciones con páneles
     * @param modo VER o HISTORIAL segun sea el caso
     */
    public static void mostrarPantallaVerReservaciones(ModoCubiculos modo) {
        JFrame pantallaVerReservaciones = new PantallaVerReservaciones(modo);
        pantallaVerReservaciones.setLocationRelativeTo(null);
        pantallaVerReservaciones.setVisible(true);

        framesVisitados.add(pantallaVerReservaciones);
    }

    public static void mostrarPantallaEstadoPedidos() {
        JFrame frame = new PantallaEstadoPedidos();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void actualizarEstado(String idPedido) {
        try {
            gestor.actualizarEstado(idPedido);
            JOptionPane.showMessageDialog(null, "Se actualizó el estado exitosamente");
        } catch (GestionException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<PedidoDTO> obtenerPedidosDelivery() {
        try {
            return gestor.obtenerPedidosDelivery();
        } catch (GestionException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void mostrarPantallaMenuSalidas() {
        JFrame pantallaMenuSalidas = new MenuSalidas();
        pantallaMenuSalidas.setLocationRelativeTo(null);
        pantallaMenuSalidas.setVisible(true);

        framesVisitados.add(pantallaMenuSalidas);
    }

    public static void mostrarPantallaRegistrarSalida() {
        JFrame pantallaRegistrarSalida = new PantallaRegistrarSalida();
        pantallaRegistrarSalida.setLocationRelativeTo(null);
        pantallaRegistrarSalida.setVisible(true);

        framesVisitados.add(pantallaRegistrarSalida);
    }

    public static void mostrarPantallaHistorialSalidas() {
        JFrame pantallaHistorialSalidas = new PantallaHistorialSalidas();
        pantallaHistorialSalidas.setLocationRelativeTo(null);
        pantallaHistorialSalidas.setVisible(true);

        framesVisitados.add(pantallaHistorialSalidas);
    }

    public static void mostrarPantallaDetalleSalida(List<DetalleSalidaDTO> detalles) {
        PantallaDetalleSalida.setDetalles(detalles);
        JFrame pantallaDetalleSalida = new PantallaDetalleSalida();
        pantallaDetalleSalida.setLocationRelativeTo(null);
        pantallaDetalleSalida.setVisible(true);

        framesVisitados.add(pantallaDetalleSalida);
    }

    public static boolean registrarSalida(SalidaNuevaDTO salida) {
        try {
            return GestorCRUDSalidas.getInstance().registrarSalida(salida);
        } catch (GestionCRUDSalidasException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    public static List<SalidaListDTO> obtenerSalidasPorRango(LocalDate inicio, LocalDate fin) {
        try {
            return GestorCRUDSalidas.getInstance().consultarPorRangoFechas(inicio, fin);
        } catch (GestionCRUDSalidasException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.emptyList();
        }
    }

    public static List<SalidaListDTO> obtenerTodasLasSalidas() {
        try {
            return GestorCRUDSalidas.getInstance().consultarTodas();
        } catch (GestionCRUDSalidasException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.emptyList();
        }
    }

    public static List<DetalleSalidaDTO> obtenerDetallesPorFecha(LocalDate fecha) {
        try {
            List<SalidaListDTO> salidas = GestorCRUDSalidas.getInstance().consultarPorRangoFechas(fecha, fecha);
            List<DetalleSalidaDTO> detalles = new ArrayList<>();
            for (SalidaListDTO salida : salidas) {
                detalles.add(GestorCRUDSalidas.getInstance().consultarPorId(salida.getId()));
            }
            return detalles;
        } catch (GestionCRUDSalidasException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(ControlNavegacion.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.emptyList();
        }
    }

}
