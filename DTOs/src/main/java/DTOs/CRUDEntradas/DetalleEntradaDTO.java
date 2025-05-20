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
    private String idIngrediente;

    public DetalleEntradaDTO() {
    }

    public DetalleEntradaDTO(String nombreIngrediente, Double precioUnitario, Double precioTotal, Double cantidadIngrediente, String idIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.cantidadIngrediente = cantidadIngrediente;
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

    public Double getCantidadIngrediente() {
        return cantidadIngrediente;
    }

    public void setCantidadIngrediente(Double cantidadIngrediente) {
        this.cantidadIngrediente = cantidadIngrediente;
    }

    public String getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(String idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    @Override
    public String toString() {
        return "DetalleEntradaDTO{" + "nombreIngrediente=" + nombreIngrediente + ", precioUnitario=" + precioUnitario + ", precioTotal=" + precioTotal + ", cantidadIngrediente=" + cantidadIngrediente + ", idIngrediente=" + idIngrediente + '}';
    }
}
