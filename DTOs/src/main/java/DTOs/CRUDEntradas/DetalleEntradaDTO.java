package DTOs.CRUDEntradas;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;

/**
 *
 * @author pablo
 */
public class DetalleEntradaDTO {
    private String nombreIngrediente;
    private Double precioUnitario;
    private Double precioTotal;
    private Double cantidadIngrediente;
    private DetallesIngredienteViejoDTO ingrediente;

    public DetalleEntradaDTO() {
    }

    public DetalleEntradaDTO(String nombreIngrediente, Double precioUnitario, Double precioTotal, Double cantidadIngrediente, DetallesIngredienteViejoDTO ingrediente) {
        this.nombreIngrediente = nombreIngrediente;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.cantidadIngrediente = cantidadIngrediente;
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

    public Double getCantidadIngrediente() {
        return cantidadIngrediente;
    }

    public void setCantidadIngrediente(Double cantidadIngrediente) {
        this.cantidadIngrediente = cantidadIngrediente;
    }

    public DetallesIngredienteViejoDTO getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(DetallesIngredienteViejoDTO ingrediente) {
        this.ingrediente = ingrediente;
    }

    @Override
    public String toString() {
        return "DetalleEntradaNuevoDTO{" + "nombreIngrediente=" + nombreIngrediente + ", precioUnitario=" + precioUnitario + ", precioTotal=" + precioTotal + ", cantidadIngrediente=" + cantidadIngrediente + ", ingrediente=" + ingrediente + '}';
    }
}
