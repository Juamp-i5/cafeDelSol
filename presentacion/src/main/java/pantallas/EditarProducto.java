/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import DTOs.ProductoMostrarDTO;
import DTOs.ProductoPedidoDTO;
import DTOs.SaboresMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.ToppingsMostrarDTO;
import control.ControlNavegacion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author katia
 */
public class EditarProducto extends javax.swing.JFrame {
    
    private JButton btnProducto, btnTamanio, btnSabor, btnToppings, btnRegresar;

    private ProductoPedidoDTO productoPedido;
    
    public EditarProducto(ProductoPedidoDTO productoPedido) {
        this.productoPedido = productoPedido; // Guardamos el producto inicial
        
        setTitle("Editar Producto");
        setSize(1000, 800);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 20, 20));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Crear botones con las selecciones actuales
        btnProducto = new JButton("Producto: " + productoPedido.getProducto().getNombre());
        btnTamanio = new JButton("Tamaño: " + productoPedido.getTamanio().getNombre());
        btnSabor = new JButton("Sabor: " + productoPedido.getSabor().getNombre());
        btnToppings = new JButton("Toppings: " + productoPedido.getTopping().getNombre());
        
        JButton[] botones = {btnProducto, btnTamanio, btnSabor, btnToppings};

        for (JButton boton : botones) {
            boton.setPreferredSize(new Dimension(250, 50));
            boton.setFont(new Font("Arial", Font.BOLD, 16));
        }
        
        btnRegresar = new JButton("←");
        btnRegresar.setPreferredSize(new Dimension(80, 40));

        panelBotones.add(btnProducto);
        panelBotones.add(btnTamanio);
        panelBotones.add(btnSabor);
        panelBotones.add(btnToppings);
        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInferior.add(btnRegresar);
        
        // Agregar acción a cada botón
        btnProducto.addActionListener(e -> abrirProductos());
        btnTamanio.addActionListener(e -> abrirTamanios());
        btnSabor.addActionListener(e -> abrirSabores());
        btnToppings.addActionListener(e -> abrirToppings());
        btnRegresar.addActionListener(e -> regresar());
        
        // Agregar los paneles al Frame
        add(panelBotones, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

//    private void abrirProductos() {
//        List<ProductoMostrarDTO> productosD = ControlNavegacion.gestor.cargarProductos();
//        Productos productosFrame = new Productos(productosD, this);
//        productosFrame.setVisible(true);
//        this.setVisible(false);
//    }
    
    private void abrirProductos() {
        dispose(); // Cierra la pantalla actual antes de abrir la nueva
        ControlNavegacion.mostrarPantallaProductos(this);
    }

//    private void abrirTamanios() {
//        List<TamanioMostrarDTO> tamaniosD = ControlNavegacion.gestor.cargarTamanios();
//        Tamanios tamaniosFrame = new Tamanios(tamaniosD, this);
//        tamaniosFrame.setVisible(true);
//        this.setVisible(false);
//    }
    
    private void abrirTamanios() {
        dispose();
        ControlNavegacion.mostrarPantallaTamanios(this);
    }

//    private void abrirSabores() {
//        List<SaboresMostrarDTO> saboresD = ControlNavegacion.gestor.cargarSabores();
//        Sabores saboresFrame = new Sabores(saboresD, this);
//        saboresFrame.setVisible(true);
//        this.setVisible(false);
//    }
    
    private void abrirSabores() {
        dispose();
        ControlNavegacion.mostrarPantallaSabores(this);
    }

//    private void abrirToppings() {
//        List<ToppingsMostrarDTO> toppingsD = ControlNavegacion.gestor.cargarToppings();
//        Toppings toppingsFrame = new Toppings(toppingsD, this);
//        toppingsFrame.setVisible(true);
//        this.setVisible(false);
//    }

    private void abrirToppings() {
        dispose();
        ControlNavegacion.mostrarPantallaToppings(this);
    }
    
    private void regresar() {
        ControlNavegacion.gestor.calcularCosto();
        this.dispose();
        ControlNavegacion.mostrarPantallaTotalDesglosado();
    }

    // Métodos para actualizar el texto de los botones según la selección del usuario
    public void actualizarProducto(ProductoMostrarDTO producto) {
        this.productoPedido.setProducto(producto);
        btnProducto.setText("Producto: " + producto.getNombre());
    }

    public void actualizarTamanio(TamanioMostrarDTO tamanio) {
        this.productoPedido.setTamanio(tamanio);
        btnTamanio.setText("Tamaño: " + tamanio.getNombre());
    }

    public void actualizarSabor(SaboresMostrarDTO sabor) {
        this.productoPedido.setSabor(sabor);
        btnSabor.setText("Sabor: " + sabor.getNombre());
    }

    public void actualizarTopping(ToppingsMostrarDTO topping) {
        this.productoPedido.setTopping(topping);
        btnToppings.setText("Toppings: " + topping.getNombre());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
