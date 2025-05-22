package entidades;

import org.bson.types.ObjectId;

/**
 *
 * @author pablo
 */
public class DetalleEntrada {
    
    private String nombreIngrediente;
    private Double precioUnitario;
    private Double precioTotal;
    private Double cantidad;
    private String nivelStock;
    private ObjectId idIngrediente;

    public DetalleEntrada() {
    }

    public DetalleEntrada(String nombreIngrediente, Double precioUnitario, Double precioTotal, Double cantidad, String nivelStock, ObjectId idIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.cantidad = cantidad;
        this.nivelStock = nivelStock;
        this.idIngrediente = idIngrediente;
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

    public ObjectId getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(ObjectId idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNivelStock() {
        return nivelStock;
    }

    public void setNivelStock(String nivelStock) {
        this.nivelStock = nivelStock;
    }

    @Override
    public String toString() {
        return "DetalleEntrada{" + "nombreIngrediente=" + nombreIngrediente + ", precioUnitario=" + precioUnitario + ", precioTotal=" + precioTotal + ", cantidad=" + cantidad + ", nivelStock=" + nivelStock + ", idIngrediente=" + idIngrediente + '}';
    }
}
