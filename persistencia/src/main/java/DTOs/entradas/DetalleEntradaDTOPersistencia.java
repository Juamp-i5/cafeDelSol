package DTOs.entradas;
import DTOs.ingredientes.DetallesIngredienteViejoDTOPersistencia;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import java.util.List;

/**
 *
 * @author pablo
 */
public class DetalleEntradaDTOPersistencia {
    private String nombreIngrediente;
    private Double precioUnitario;
    private Double precioTotal;
    private Double cantidadIngrediente;
    private String nivelStock;
    private String idIngrediente;
    private List<IngredienteDTOPersistencia> ingredienteInfo;

    public DetalleEntradaDTOPersistencia() {
    }

    public DetalleEntradaDTOPersistencia(String nombreIngrediente, Double precioUnitario, Double precioTotal, Double cantidadIngrediente, String nivelStock, String idIngrediente, List<IngredienteDTOPersistencia> ingredienteInfo) {
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

    public String getNivelStock() {
        return nivelStock;
    }

    public void setNivelStock(String nivelStock) {
        this.nivelStock = nivelStock;
    }

    public List<IngredienteDTOPersistencia> getIngredienteInfo() {
        return ingredienteInfo;
    }

    public void setIngredienteInfo(List<IngredienteDTOPersistencia> ingredienteInfo) {
        this.ingredienteInfo = ingredienteInfo;
    }

    @Override
    public String toString() {
        return "DetalleEntradaDTOPersistencia{" + "nombreIngrediente=" + nombreIngrediente + ", precioUnitario=" + precioUnitario + ", precioTotal=" + precioTotal + ", cantidadIngrediente=" + cantidadIngrediente + ", nivelStock=" + nivelStock + ", idIngrediente=" + idIngrediente + ", ingredienteInfo=" + ingredienteInfo + '}';
    }
}
