/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;

/**
 * Representa la configuración específica de un producto para un tamaño
 * particular. Esta clase asocia un tamaño ({@link Tamanio}) con una lista de
 * ingredientes y sus cantidades ({@link ProductoTamanioIngrediente}) necesarios
 * para preparar el producto en dicho tamaño.
 * <p>
 * Es comúnmente utilizada como parte de la definición de un {@link Producto}
 * para detallar sus variantes de tamaño y las recetas correspondientes.
 * </p>
 *
 * @author Jp
 */
public class ProductoTamanio {

    /**
     * El tamaño específico al que se refiere esta configuración. Contiene la
     * información del tamaño, como su nombre o ID.
     *
     * @see Tamanio
     */
    private Tamanio tamanio;

    /**
     * La lista de ingredientes y sus respectivas cantidades necesarias para
     * preparar el producto en el tamaño especificado por {@link #tamanio}.
     *
     * @see ProductoTamanioIngrediente
     */
    private List<ProductoTamanioIngrediente> ingredientes;

    /**
     * Constructor por defecto. Crea una nueva instancia de
     * {@code ProductoTamanio} sin inicializar el tamaño ni la lista de
     * ingredientes.
     */
    public ProductoTamanio() {
    }

    /**
     * Obtiene el objeto {@link Tamanio} asociado con esta configuración de
     * producto.
     *
     * @return El objeto {@link Tamanio} que define el tamaño para esta
     * configuración. Puede ser {@code null} si no se ha establecido.
     */
    public Tamanio getTamanio() {
        return tamanio;
    }

    /**
     * Establece el objeto {@link Tamanio} para esta configuración de producto.
     *
     * @param tamanio El objeto {@link Tamanio} a asociar.
     */
    public void setTamanio(Tamanio tamanio) {
        this.tamanio = tamanio;
    }

    /**
     * Obtiene la lista de ingredientes y sus cantidades para esta configuración
     * de producto y tamaño. Cada elemento de la lista es un objeto
     * {@link ProductoTamanioIngrediente}.
     *
     * @return Una lista de {@link ProductoTamanioIngrediente} que detalla la
     * receta. Puede ser {@code null} si no se han establecido ingredientes.
     */
    public List<ProductoTamanioIngrediente> getIngredientes() {
        return ingredientes;
    }

    /**
     * Establece la lista de ingredientes y sus cantidades para esta
     * configuración de producto y tamaño.
     *
     * @param ingredientes Una lista de {@link ProductoTamanioIngrediente} que
     * define la nueva receta para esta configuración.
     */
    public void setIngredientes(List<ProductoTamanioIngrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

}
