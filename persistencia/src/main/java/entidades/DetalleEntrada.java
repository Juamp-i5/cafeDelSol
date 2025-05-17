package entidades;

/**
 *
 * @author pablo
 */
public class DetalleEntrada {
    
    private String nombreIngrediente;
    private Double precioUnitario;
    private Double precioTotal;
    private Double cantidad;
    private Ingrediente ingrediente;

    public DetalleEntrada() {
    }

    public DetalleEntrada(String nombreIngrediente, Double precioUnitario, Double precioTotal, Double cantidad, Ingrediente ingrediente) {
        this.nombreIngrediente = nombreIngrediente;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.cantidad = cantidad;
        this.ingrediente = ingrediente;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    } 
}
