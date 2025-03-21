/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import DTOs.ProductoMostrarDTO;
import DTOs.SaboresMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.ToppingsMostrarDTO;
import control.ControlNavegacion;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author katia
 */
public class EditarProducto extends javax.swing.JFrame {
    
    private JButton btnProducto, btnTamanio, btnSabor, btnToppings, btnRegresar;

    // Variables para almacenar las selecciones del usuario
    private ProductoMostrarDTO productoSeleccionado;
    private TamanioMostrarDTO tamanioSeleccionado;
    private SaboresMostrarDTO saborSeleccionado;
    private ToppingsMostrarDTO toppingSeleccionado;
    
    public EditarProducto(ProductoMostrarDTO producto) {
        this.productoSeleccionado = producto; // Guardamos el producto inicial
        
        setTitle("Editar Producto");
        setSize(1000, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear botones con las selecciones actuales
        btnProducto = new JButton("Producto: " + producto.getNombre());
        btnTamanio = new JButton("Tamaño: " + tamanioSeleccionado);
        btnSabor = new JButton("Sabor: " + saborSeleccionado);
        btnToppings = new JButton("Toppings: " + toppingSeleccionado);
        btnRegresar = new JButton("←");

        // Posicionar los botones
        btnProducto.setBounds(50, 50, 150, 40);
        btnTamanio.setBounds(220, 50, 150, 40);
        btnSabor.setBounds(50, 120, 150, 40);
        btnToppings.setBounds(220, 120, 150, 40);
        btnRegresar.setBounds(20, 200, 60, 40);

        // Agregar acción a cada botón
        btnProducto.addActionListener(e -> abrirProductos());
        btnTamanio.addActionListener(e -> abrirTamanios());
        btnSabor.addActionListener(e -> abrirSabores());
        btnToppings.addActionListener(e -> abrirToppings());
        btnRegresar.addActionListener(e -> regresar());

        // Agregar los botones al Frame
        add(btnProducto);
        add(btnTamanio);
        add(btnSabor);
        add(btnToppings);
        add(btnRegresar);

        setVisible(true);
    }

    private void abrirProductos() {
        List<ProductoMostrarDTO> productosD = ControlNavegacion.gestor.cargarProductos();
        Productos productosFrame = new Productos(productosD, this);
        productosFrame.setVisible(true);
        this.setVisible(false);
    }

    private void abrirTamanios() {
        List<TamanioMostrarDTO> tamaniosD = ControlNavegacion.gestor.cargarTamanios();
        Tamanios tamaniosFrame = new Tamanios(tamaniosD, this);
        tamaniosFrame.setVisible(true);
        this.setVisible(false);
    }

    private void abrirSabores() {
        List<SaboresMostrarDTO> saboresD = ControlNavegacion.gestor.cargarSabores();
        Sabores saboresFrame = new Sabores(saboresD, this);
        saboresFrame.setVisible(true);
        this.setVisible(false);
    }

    private void abrirToppings() {
        List<ToppingsMostrarDTO> toppingsD = ControlNavegacion.gestor.cargarToppings();
        Toppings toppingsFrame = new Toppings(toppingsD, this);
        toppingsFrame.setVisible(true);
        this.setVisible(false);
    }

    private void regresar() {
        this.dispose();
    }

    // Métodos para actualizar el texto de los botones según la selección del usuario
    public void actualizarProducto(ProductoMostrarDTO producto) {
        this.productoSeleccionado = producto;
        btnProducto.setText("Producto: " + producto.getNombre());
    }

    public void actualizarTamanio(TamanioMostrarDTO tamanio) {
        this.tamanioSeleccionado = tamanio;
        btnTamanio.setText("Tamaño: " + tamanio.getNombre());
    }

    public void actualizarSabor(SaboresMostrarDTO sabor) {
        this.saborSeleccionado = sabor;
        btnSabor.setText("Sabor: " + sabor.getNombre());
    }

    public void actualizarTopping(ToppingsMostrarDTO topping) {
        toppingSeleccionado = topping;
        btnToppings.setText("Toppings: " + topping);
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
