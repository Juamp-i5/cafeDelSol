package DTOs.entradas;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author pablo
 */
public class EntradaNuevaDTOPersistencia {
    private LocalDateTime fechaHora;
    private String proveedor;
    private Double precioTotal;
    private List<DetalleEntradaDTOPersistencia> DetallesEntrada;

    public EntradaNuevaDTOPersistencia() {
    }

    public EntradaNuevaDTOPersistencia(LocalDateTime fechaHora, String proveedor, Double precioTotal, List<DetalleEntradaDTOPersistencia> DetallesEntrada) {
        this.fechaHora = fechaHora;
        this.proveedor = proveedor;
        this.precioTotal = precioTotal;
        this.DetallesEntrada = DetallesEntrada;
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

    public List<DetalleEntradaDTOPersistencia> getDetallesEntrada() {
        return DetallesEntrada;
    }

    public void setDetallesEntrada(List<DetalleEntradaDTOPersistencia> DetallesEntrada) {
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
        return "EntradaNuevaDTO{" + "fechaHora=" + fechaHora + ", proveedor=" + proveedor + ", precioTotal=" + precioTotal + ", DetallesEntrada=" + DetallesEntrada + '}';
    }
}
