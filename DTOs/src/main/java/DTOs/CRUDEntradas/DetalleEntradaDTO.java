package DTOs.CRUDEntradas;

import DTOs.CRUDIngredientes.DetallesIngredienteViejoDTO;
import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDIngredientes.NivelStock;
import java.util.List;

/**
 *
 * @author pablo
 */
public class DetalleEntradaDTO {
    private String nombreIngrediente;
    private Double precioUnitario;
    private Double precioTotal;
    private Double cantidadIngrediente;
    private NivelStock nivelStock;
    private String idIngrediente;
    private List<IngredienteViejoListDTO> ingredienteInfo;

    public DetalleEntradaDTO() {
    }

    public DetalleEntradaDTO(String nombreIngrediente, Double precioUnitario, Double precioTotal, Double cantidadIngrediente, NivelStock nivelStock, String idIngrediente, List<IngredienteViejoListDTO> ingredienteInfo) {
        this.nombreIngrediente = nombreIngrediente;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.cantidadIngrediente = cantidadIngrediente;
        this.nivelStock = nivelStock;
        this.idIngrediente = idIngrediente;
        this.ingredienteInfo = ingredienteInfo;
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

    public NivelStock getNivelStock() {
        return nivelStock;
    }

    public void setNivelStock(NivelStock nivelStock) {
        this.nivelStock = nivelStock;
    }

    public List<IngredienteViejoListDTO> getIngredienteInfo() {
        return ingredienteInfo;
    }

    public void setIngredienteInfo(List<IngredienteViejoListDTO> ingredienteInfo) {
        this.ingredienteInfo = ingredienteInfo;
    }

    @Override
    public String toString() {
        return "DetalleEntradaDTO{" + "nombreIngrediente=" + nombreIngrediente + ", precioUnitario=" + precioUnitario + ", precioTotal=" + precioTotal + ", cantidadIngrediente=" + cantidadIngrediente + ", nivelStock=" + nivelStock + ", idIngrediente=" + idIngrediente + ", ingredienteInfo=" + ingredienteInfo + '}';
    }
}
