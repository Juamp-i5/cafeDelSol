/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 * Representa un ingrediente específico y la cantidad requerida del mismo como
 * parte de la receta para una configuración de producto y tamaño particular
 * (definida en {@link ProductoTamanio}).
 * <p>
 * Esta clase enlaza un {@link Ingrediente} (del stock) con la cantidad
 * necesaria para elaborar una unidad de un producto en un tamaño determinado.
 * </p>
 *
 * @author Jp
 */
public class ProductoTamanioIngrediente {

    /**
     * El ingrediente de stock que forma parte de la receta. Contiene la
     * información del ingrediente, como su ID y nombre.
     *
     * @see Ingrediente
     */
    private Ingrediente ingrediente;

    /**
     * La cantidad de este {@link #ingrediente} necesaria para la receta. La
     * unidad de medida está implícita en la definición del {@link Ingrediente}.
     */
    private Double cantidad;

    /**
     * Constructor por defecto. Crea una nueva instancia de
     * {@code ProductoTamanioIngrediente} sin inicializar el ingrediente ni la
     * cantidad.
     */
    public ProductoTamanioIngrediente() {
    }

    /**
     * Obtiene el objeto {@link Ingrediente} asociado con este ítem de la
     * receta.
     *
     * @return El objeto {@link Ingrediente} que se requiere. Puede ser
     * {@code null} si no se ha establecido.
     */
    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    /**
     * Establece el objeto {@link Ingrediente} para este ítem de la receta.
     *
     * @param ingrediente El objeto {@link Ingrediente} a asociar.
     */
    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    /**
     * Obtiene la cantidad necesaria de este ingrediente para la receta.
     *
     * @return La cantidad del ingrediente. Puede ser {@code null} si no se ha
     * establecido.
     */
    public Double getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad necesaria de este ingrediente para la receta.
     *
     * @param cantidad La nueva cantidad del ingrediente.
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

}
