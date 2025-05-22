package entidades;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo Clase que representa un "Detalle de Entrada". Cada instancia de
 * esta clase describe un ingrediente específico que forma parte de una
 * 'Entrada' más grande. Por ejemplo, si una Entrada es una factura de compra,
 * cada DetalleEntrada sería una línea de esa factura.
 */
public class DetalleEntrada {

    private String nombreIngrediente; // El nombre del ingrediente que se está detallando.
    private Double precioUnitario;    // El precio de una unidad de este ingrediente.
    private Double precioTotal;       // El precio total de este ingrediente en esta entrada (precioUnitario * cantidad).
    private Double cantidad;          // La cantidad de este ingrediente que se ha registrado en la entrada.
    private String nivelStock;        // Una descripción o estado del nivel de stock actual de este ingrediente (e.g., "Alto", "Bajo", "Crítico").
    private ObjectId idIngrediente;   // El identificador único del ingrediente al que hace referencia este detalle.
    // Se usa para vincular este detalle con la información completa del ingrediente.
    private List<Ingrediente> ingredienteInfo; // Una lista (posiblemente con un solo elemento) que contiene la información completa del ingrediente.
    // Esto podría ser una "vista" o un "lookup" del ingrediente al que se refiere idIngrediente.

    /**
     * Constructor por defecto de la clase DetalleEntrada. Necesario para
     * frameworks de serialización/deserialización (como los de MongoDB).
     */
    public DetalleEntrada() {
    }

    /**
     * Constructor completo para inicializar una nueva instancia de
     * DetalleEntrada con todos sus atributos.
     *
     * @param nombreIngrediente El nombre del ingrediente.
     * @param precioUnitario El precio por unidad del ingrediente.
     * @param precioTotal El precio total de la cantidad de este ingrediente.
     * @param cantidad La cantidad del ingrediente.
     * @param nivelStock El nivel de stock actual del ingrediente.
     * @param idIngrediente El ID del ingrediente al que se refiere este
     * detalle.
     * @param ingredienteInfo Una lista con la información completa del
     * ingrediente.
     */
    public DetalleEntrada(String nombreIngrediente, Double precioUnitario, Double precioTotal, Double cantidad, String nivelStock, ObjectId idIngrediente, List<Ingrediente> ingredienteInfo) {
        this.nombreIngrediente = nombreIngrediente;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.cantidad = cantidad;
        this.nivelStock = nivelStock;
        this.idIngrediente = idIngrediente;
        this.ingredienteInfo = ingredienteInfo;
    }

    // --- Métodos Getters y Setters ---
    /**
     * Obtiene el nombre del ingrediente.
     *
     * @return El String que representa el nombre del ingrediente.
     */
    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    /**
     * Establece el nombre del ingrediente.
     *
     * @param nombreIngrediente El String del nombre del ingrediente a
     * establecer.
     */
    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    /**
     * Obtiene el precio unitario del ingrediente.
     *
     * @return El Double que representa el precio unitario.
     */
    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Establece el precio unitario del ingrediente.
     *
     * @param precioUnitario El Double del precio unitario a establecer.
     */
    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene el precio total de la cantidad de este ingrediente.
     *
     * @return El Double que representa el precio total.
     */
    public Double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Establece el precio total de la cantidad de este ingrediente.
     *
     * @param precioTotal El Double del precio total a establecer.
     */
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * Obtiene la cantidad del ingrediente.
     *
     * @return El Double que representa la cantidad.
     */
    public Double getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del ingrediente.
     *
     * @param cantidad El Double de la cantidad a establecer.
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el identificador único del ingrediente.
     *
     * @return El ObjectId del ingrediente.
     */
    public ObjectId getIdIngrediente() {
        return idIngrediente;
    }

    /**
     * Establece el identificador único del ingrediente.
     *
     * @param idIngrediente El ObjectId del ingrediente a establecer.
     */
    public void setIdIngrediente(ObjectId idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    /**
     * Obtiene el nivel de stock del ingrediente.
     *
     * @return El String que representa el nivel de stock.
     */
    public String getNivelStock() {
        return nivelStock;
    }

    /**
     * Establece el nivel de stock del ingrediente.
     *
     * @param nivelStock El String del nivel de stock a establecer.
     */
    public void setNivelStock(String nivelStock) {
        this.nivelStock = nivelStock;
    }

    /**
     * Obtiene la información completa del ingrediente.
     *
     * @return La List de Ingrediente.
     */
    public List<Ingrediente> getIngredienteInfo() {
        return ingredienteInfo;
    }

    /**
     * Establece la información completa del ingrediente.
     *
     * @param ingredienteInfo La List de Ingrediente a establecer.
     */
    public void setIngredienteInfo(List<Ingrediente> ingredienteInfo) {
        this.ingredienteInfo = ingredienteInfo;
    }

    /**
     * Sobreescribe el método toString para proporcionar una representación de
     * cadena legible de un objeto DetalleEntrada. Útil para depuración y para
     * mostrar información del objeto.
     *
     * @return Una cadena que representa el objeto DetalleEntrada.
     */
    @Override
    public String toString() {
        return "DetalleEntrada{" + "nombreIngrediente=" + nombreIngrediente + ", precioUnitario=" + precioUnitario + ", precioTotal=" + precioTotal + ", cantidad=" + cantidad + ", nivelStock=" + nivelStock + ", idIngrediente=" + idIngrediente + ", ingredienteInfo=" + ingredienteInfo + '}';
    }
}
