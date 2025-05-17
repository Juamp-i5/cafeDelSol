package DTOs.CRUDEntradas;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author pablo
 */
public class EntradaViejaDTO {
    private String idEntrada;
    private LocalDateTime fechaHora;
    private String proveedor;
    private Double precioTotal;
    private List<DetalleEntradaDTO> DetallesEntrada;

    public EntradaViejaDTO() {
    }

    public EntradaViejaDTO(String idEntrada, LocalDateTime fechaHora, String proveedor, Double precioTotal, List<DetalleEntradaDTO> DetallesEntrada) {
        this.idEntrada = idEntrada;
        this.fechaHora = fechaHora;
        this.proveedor = proveedor;
        this.precioTotal = precioTotal;
        this.DetallesEntrada = DetallesEntrada;
    }

    public EntradaViejaDTO(LocalDateTime fechaHora, String proveedor, Double precioTotal, List<DetalleEntradaDTO> DetallesEntrada) {
        this.fechaHora = fechaHora;
        this.proveedor = proveedor;
        this.precioTotal = precioTotal;
        this.DetallesEntrada = DetallesEntrada;
    }
   
    public String getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(String idEntrada) {
        this.idEntrada = idEntrada;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public List<DetalleEntradaDTO> getDetallesEntrada() {
        return DetallesEntrada;
    }

    public void setDetallesEntrada(List<DetalleEntradaDTO> DetallesEntrada) {
        this.DetallesEntrada = DetallesEntrada;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return "EntradaViejaDTO{" + "idEntrada=" + idEntrada + ", fechaHora=" + fechaHora + ", proveedor=" + proveedor + ", precioTotal=" + precioTotal + ", DetallesEntrada=" + DetallesEntrada + '}';
    }
}
