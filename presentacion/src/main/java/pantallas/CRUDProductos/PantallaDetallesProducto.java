/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas.CRUDProductos;

import DTOs.CRUDIngredientes.IngredienteListDTO;
import DTOs.CRUDProductos.CategoriaProducto;
import DTOs.CRUDProductos.DetallesProductoDTO;
import DTOs.CRUDProductos.EstadoProducto;
import DTOs.CRUDProductos.ProductoCreateDTO;
import DTOs.CRUDProductos.TamanioProducto;
import control.ControlNavegacion;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jp
 */
public class PantallaDetallesProducto extends javax.swing.JFrame {

    private DefaultTableModel modeloTablaProductos;
    private DetallesProductoDTO producto;
    private DetallesProductoDTO productoActualizado;

    public PantallaDetallesProducto(DetallesProductoDTO productoDTO) {
        initComponents();

        this.modeloTablaProductos = (DefaultTableModel) tablaProductosTable.getModel();
        this.producto = productoDTO;

        setInitialUI();
    }

    private void setInitialUI() {
        ponerDocumentosTextFields();
        cargarComboBoxCategoria();
        cargarComboBoxEstado();
        agregarRendererTablaProductos();
        agregarEventosTablaProductos();
        iniciarMapas();
    }

    private void iniciarMapas() {
        productoActualizado.setPrecios(new HashMap<>());
        productoActualizado.setIngredientes(new HashMap<>());
    }

    private void agregarIngrediente() {
        this.setVisible(false);
        ControlNavegacion.mostrarPantallaIngredienteSimulada(ingredienteSeleccionado -> {
            this.setVisible(true);

            if (ingredienteSeleccionado != null) {
                modeloTablaProductos.addRow(new Object[]{
                    ingredienteSeleccionado.getId(),
                    ingredienteSeleccionado.getNombre(),
                    0.0,
                    0.0,
                    0.0
                });
            }
        });
    }

    private void quitarIngrediente() {
        int row = tablaProductosTable.getSelectedRow();
        modeloTablaProductos.removeRow(row);
    }

    private void agregarRendererTablaProductos() {
        tablaProductosTable.getColumnModel().getColumn(2).setCellRenderer(new HipervinculoRenderer());
        tablaProductosTable.getColumnModel().getColumn(3).setCellRenderer(new HipervinculoRenderer());
        tablaProductosTable.getColumnModel().getColumn(4).setCellRenderer(new HipervinculoRenderer());
    }

    private void agregarEventosTablaProductos() {
        //Evento si hay una fila seleccionada
        ListSelectionModel selectionModel = tablaProductosTable.getSelectionModel();
        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            if (tablaProductosTable.getSelectedRow() != -1) {
                botonEliminarIngrediente.setEnabled(true);
                botonEliminarIngrediente.setBackground(new Color(255, 102, 102));
            } else {
                botonEliminarIngrediente.setEnabled(false);
                botonEliminarIngrediente.setBackground(new Color(204, 204, 204));
            }
        });

        //Evento si hace click en una de las columnas de cantidad (2, 3, 4)
        tablaProductosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int col = tablaProductosTable.columnAtPoint(evt.getPoint());
                int row = tablaProductosTable.rowAtPoint(evt.getPoint());
                if (row >= 0 && row < tablaProductosTable.getRowCount() && (col == 2 || col == 3 || col == 4)) {
                    Object value = tablaProductosTable.getValueAt(row, col);
                    Double valorActual = valorActual = (value instanceof Double) ? (Double) value : Double.valueOf(value.toString());

                    Double valorIngresado = JOptionPanePersonalizado.solicitarDoublePositivo(
                            "Dime la cantidad que deseas",
                            valorActual
                    );

                    Double nuevoValor = (valorIngresado != null) ? valorIngresado : valorActual;

                    modeloTablaProductos.setValueAt(nuevoValor, row, col);
                }
            }
        });

    }

    private void cargarComboBoxCategoria() {
        CategoriaProducto[] categorias = CategoriaProducto.values();
        for (CategoriaProducto categoria : categorias) {
            comboBoxCategoria.addItem(categoria.toString());
        }
    }

    private void cargarComboBoxEstado() {
        EstadoProducto[] estados = EstadoProducto.values();
        for (EstadoProducto estado : estados) {
            comboBoxEstado.addItem(estado.toString());
        }
    }

    private void ponerDocumentosTextFields() {
        textFieldPrecioChico.setDocument(new PositiveDoubleDocument());
        textFieldPrecioMediano.setDocument(new PositiveDoubleDocument());
        textFieldPrecioGrande.setDocument(new PositiveDoubleDocument());
    }

    private void desplegarDatosFormulario() {
        String nombre = producto.getNombre();
        String descripcion = producto.getDescipcion();
        CategoriaProducto categoria = producto.getCategoria();
        byte[] imagen = producto.getImagenData();
        EstadoProducto estado = producto.getEstadoProducto();
        Double precioChico = producto.getPrecios().get(TamanioProducto.CHICO);
        Double precioMediano = producto.getPrecios().get(TamanioProducto.MEDIANO);
        Double precioGrande = producto.getPrecios().get(TamanioProducto.GRANDE);

        textFieldNombre.setText(nombre);
        textFieldDescripcion.setText(descripcion);
        comboBoxCategoria.setSelectedItem(categoria);
        imagePanelProducto.setImageBytes(imagen);
        comboBoxEstado.setSelectedItem(estado);
        textFieldPrecioChico.setText(precioChico + "");
        textFieldPrecioMediano.setText(precioMediano + "");
        textFieldPrecioGrande.setText(precioGrande + "");

        desplegarDatosTabla();
    }

    private void desplegarDatosTabla() {
        Map<IngredienteListDTO, Map<TamanioProducto, Double>> ingredientes = new HashMap<>();

        for (Map.Entry<IngredienteListDTO, Map<TamanioProducto, Double>> entry : ingredientes.entrySet()) {
            IngredienteListDTO ingrediente = entry.getKey();
            Map<TamanioProducto, Double> cantidades = entry.getValue();

            String id = ingrediente.getId();
            String nombre = ingrediente.getNombre();
            Double cantidadChico = cantidades.get(TamanioProducto.CHICO);
            Double cantidadMediano = cantidades.get(TamanioProducto.MEDIANO);
            Double cantidadGrande = cantidades.get(TamanioProducto.GRANDE);

            modeloTablaProductos.addRow(new Object[]{
                id,
                nombre,
                cantidadChico,
                cantidadMediano,
                cantidadGrande
            });
        }
    }

    private void recolectarDatosFormulario() {
        String nombre = textFieldNombre.getText().trim();
        String descripcion = textFieldDescripcion.getText().trim();
        CategoriaProducto categoria = CategoriaProducto.valueOf(comboBoxCategoria.getSelectedItem().toString().trim());
        byte[] imagen = imagePanelProducto.getImageBytes();
        EstadoProducto estado = EstadoProducto.valueOf(comboBoxEstado.getSelectedItem().toString().trim());
        Double precioChico = Double.valueOf(textFieldPrecioChico.getText().trim());
        Double precioMediano = Double.valueOf(textFieldPrecioMediano.getText().trim());
        Double precioGrande = Double.valueOf(textFieldPrecioGrande.getText().trim());

        productoActualizado.setNombre(nombre);
        productoActualizado.setCategoria(categoria);
        productoActualizado.setDescipcion(descripcion);
        productoActualizado.setEstadoProducto(estado);
        productoActualizado.setImagenData(imagen);

        Map<TamanioProducto, Double> precios = new HashMap<>();
        precios.put(TamanioProducto.CHICO, precioChico);
        precios.put(TamanioProducto.MEDIANO, precioMediano);
        precios.put(TamanioProducto.GRANDE, precioGrande);
        productoActualizado.setPrecios(precios);

        recolectarDatosTabla();
    }

    private void recolectarDatosTabla() {
        Map<IngredienteListDTO, Map<TamanioProducto, Double>> ingredientes = new HashMap<>();
        for (int i = 0; i < tablaProductosTable.getRowCount(); i++) {
            String id = tablaProductosTable.getValueAt(i, 0).toString();
            Double cantidadChico = Double.valueOf(tablaProductosTable.getValueAt(i, 2).toString());
            Double cantidadMediano = Double.valueOf(tablaProductosTable.getValueAt(i, 2).toString());
            Double cantidadGrande = Double.valueOf(tablaProductosTable.getValueAt(i, 2).toString());

            Map<TamanioProducto, Double> cantidades = new HashMap<>();
            cantidades.put(TamanioProducto.CHICO, cantidadChico);
            cantidades.put(TamanioProducto.MEDIANO, cantidadMediano);
            cantidades.put(TamanioProducto.GRANDE, cantidadGrande);

            ingredientes.put(new IngredienteListDTO(id), cantidades);
        }
        productoActualizado.setIngredientes(ingredientes);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNorte = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        topSeparator = new javax.swing.JSeparator();
        botonAgregarIngrediente = new javax.swing.JButton();
        textFieldNombre = new javax.swing.JTextField();
        comboBoxCategoria = new javax.swing.JComboBox<>();
        labelNombre = new javax.swing.JLabel();
        labelCategoria = new javax.swing.JLabel();
        labelDescripcion = new javax.swing.JLabel();
        textFieldDescripcion = new javax.swing.JTextField();
        labelPrecioGrande = new javax.swing.JLabel();
        labelPrecioChico = new javax.swing.JLabel();
        LabelPrecioMediano = new javax.swing.JLabel();
        textFieldPrecioMediano = new javax.swing.JTextField();
        textFieldPrecioChico = new javax.swing.JTextField();
        textFieldPrecioGrande = new javax.swing.JTextField();
        botonEliminarIngrediente = new javax.swing.JButton();
        imagePanelProducto = new pantallas.CRUDProductos.ImagePanel();
        panelSur = new javax.swing.JPanel();
        botonVolver = new javax.swing.JButton();
        botonActualizarProducto = new javax.swing.JButton();
        bottomSeparator = new javax.swing.JSeparator();
        labelEstadoProducto = new javax.swing.JLabel();
        comboBoxEstado = new javax.swing.JComboBox<>();
        panelCentro = new javax.swing.JPanel();
        tablaProductosScrollPane = new javax.swing.JScrollPane();
        tablaProductosTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar Producto");
        setSize(new java.awt.Dimension(800, 600));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleLabel.setText("Detalles producto");

        botonAgregarIngrediente.setBackground(new java.awt.Color(0, 204, 51));
        botonAgregarIngrediente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonAgregarIngrediente.setText("Agregar Ingrediente");
        botonAgregarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarIngredienteActionPerformed(evt);
            }
        });

        textFieldNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        comboBoxCategoria.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        labelNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelNombre.setText("Nombre");

        labelCategoria.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelCategoria.setText("Categoria");

        labelDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelDescripcion.setText("Descripción");

        textFieldDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        labelPrecioGrande.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelPrecioGrande.setText("Precio grande");

        labelPrecioChico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelPrecioChico.setText("Precio chico");

        LabelPrecioMediano.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LabelPrecioMediano.setText("Precio mediano");

        textFieldPrecioMediano.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textFieldPrecioMediano.setMinimumSize(new java.awt.Dimension(80, 31));
        textFieldPrecioMediano.setPreferredSize(new java.awt.Dimension(80, 31));

        textFieldPrecioChico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textFieldPrecioChico.setMinimumSize(new java.awt.Dimension(80, 31));
        textFieldPrecioChico.setPreferredSize(new java.awt.Dimension(80, 31));

        textFieldPrecioGrande.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textFieldPrecioGrande.setMinimumSize(new java.awt.Dimension(80, 31));
        textFieldPrecioGrande.setPreferredSize(new java.awt.Dimension(80, 31));

        botonEliminarIngrediente.setBackground(new java.awt.Color(204, 204, 204));
        botonEliminarIngrediente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonEliminarIngrediente.setText("Quitar Ingrediente");
        botonEliminarIngrediente.setEnabled(false);
        botonEliminarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarIngredienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout imagePanelProductoLayout = new javax.swing.GroupLayout(imagePanelProducto);
        imagePanelProducto.setLayout(imagePanelProductoLayout);
        imagePanelProductoLayout.setHorizontalGroup(
            imagePanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );
        imagePanelProductoLayout.setVerticalGroup(
            imagePanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelNorteLayout = new javax.swing.GroupLayout(panelNorte);
        panelNorte.setLayout(panelNorteLayout);
        panelNorteLayout.setHorizontalGroup(
            panelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topSeparator)
            .addGroup(panelNorteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNorteLayout.createSequentialGroup()
                        .addGroup(panelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDescripcion)
                            .addComponent(labelNombre)
                            .addComponent(labelCategoria))
                        .addGap(18, 18, 18)
                        .addGroup(panelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboBoxCategoria, 0, 250, Short.MAX_VALUE)
                            .addComponent(textFieldDescripcion)
                            .addComponent(textFieldNombre))
                        .addGap(18, 18, 18)
                        .addGroup(panelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPrecioChico)
                            .addComponent(LabelPrecioMediano)
                            .addComponent(labelPrecioGrande))
                        .addGap(18, 18, 18)
                        .addGroup(panelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldPrecioGrande, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldPrecioMediano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldPrecioChico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imagePanelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelNorteLayout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                        .addComponent(botonEliminarIngrediente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonAgregarIngrediente)))
                .addContainerGap())
        );
        panelNorteLayout.setVerticalGroup(
            panelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNorteLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonAgregarIngrediente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonEliminarIngrediente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNorteLayout.createSequentialGroup()
                        .addGroup(panelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNombre)
                            .addGroup(panelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelPrecioChico)
                                .addComponent(textFieldPrecioChico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDescripcion)
                            .addComponent(textFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelPrecioMediano)
                            .addComponent(textFieldPrecioMediano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelCategoria)
                            .addComponent(comboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPrecioGrande)
                            .addComponent(textFieldPrecioGrande, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(imagePanelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(panelNorte, java.awt.BorderLayout.NORTH);

        botonVolver.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonVolver.setText("Volver");

        botonActualizarProducto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonActualizarProducto.setText("Actualizar Producto");

        labelEstadoProducto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelEstadoProducto.setText("Estado");

        comboBoxEstado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout panelSurLayout = new javax.swing.GroupLayout(panelSur);
        panelSur.setLayout(panelSurLayout);
        panelSurLayout.setHorizontalGroup(
            panelSurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSurLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonVolver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                .addComponent(labelEstadoProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonActualizarProducto)
                .addContainerGap())
            .addComponent(bottomSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelSurLayout.setVerticalGroup(
            panelSurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSurLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bottomSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSurLayout.createSequentialGroup()
                        .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(botonActualizarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelSurLayout.createSequentialGroup()
                        .addComponent(labelEstadoProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(comboBoxEstado, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        getContentPane().add(panelSur, java.awt.BorderLayout.SOUTH);

        tablaProductosScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tablaProductosScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tablaProductosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Cantidad chico", "Cantidad mediano", "Cantidad grande"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductosScrollPane.setViewportView(tablaProductosTable);

        javax.swing.GroupLayout panelCentroLayout = new javax.swing.GroupLayout(panelCentro);
        panelCentro.setLayout(panelCentroLayout);
        panelCentroLayout.setHorizontalGroup(
            panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCentroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tablaProductosScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelCentroLayout.setVerticalGroup(
            panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCentroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tablaProductosScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panelCentro, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAgregarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarIngredienteActionPerformed
        agregarIngrediente();
    }//GEN-LAST:event_botonAgregarIngredienteActionPerformed

    private void botonEliminarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarIngredienteActionPerformed
        quitarIngrediente();
    }//GEN-LAST:event_botonEliminarIngredienteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelPrecioMediano;
    private javax.swing.JButton botonActualizarProducto;
    private javax.swing.JButton botonAgregarIngrediente;
    private javax.swing.JButton botonEliminarIngrediente;
    private javax.swing.JButton botonVolver;
    private javax.swing.JSeparator bottomSeparator;
    private javax.swing.JComboBox<String> comboBoxCategoria;
    private javax.swing.JComboBox<String> comboBoxEstado;
    private pantallas.CRUDProductos.ImagePanel imagePanelProducto;
    private javax.swing.JLabel labelCategoria;
    private javax.swing.JLabel labelDescripcion;
    private javax.swing.JLabel labelEstadoProducto;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelPrecioChico;
    private javax.swing.JLabel labelPrecioGrande;
    private javax.swing.JPanel panelCentro;
    private javax.swing.JPanel panelNorte;
    private javax.swing.JPanel panelSur;
    private javax.swing.JScrollPane tablaProductosScrollPane;
    private javax.swing.JTable tablaProductosTable;
    private javax.swing.JTextField textFieldDescripcion;
    private javax.swing.JTextField textFieldNombre;
    private javax.swing.JTextField textFieldPrecioChico;
    private javax.swing.JTextField textFieldPrecioGrande;
    private javax.swing.JTextField textFieldPrecioMediano;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JSeparator topSeparator;
    // End of variables declaration//GEN-END:variables

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PantallaDetallesProducto(null).setVisible(true);
        });
    }
}
