package entidades;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo
 */
public class Entrada {
    @BsonId
    private ObjectId idEntrada;
    private String proveedor;
    private LocalDateTime fechaHora;
    private Double precioTotal;
    private List<DetalleEntrada> detallesEntrada;

    public Entrada() {
    }

    public Entrada(ObjectId idEntrada, String proveedor, LocalDateTime fechaHora, Double precioTotal, List<DetalleEntrada> detallesEntrada) {
        this.idEntrada = idEntrada;
        this.proveedor = proveedor;
        this.fechaHora = fechaHora;
        this.precioTotal = precioTotal;
        this.detallesEntrada = detallesEntrada;
    }

    public Entrada(String proveedor, LocalDateTime fechaHora, Double precioTotal, List<DetalleEntrada> detallesEntrada) {
        this.proveedor = proveedor;
        this.fechaHora = fechaHora;
        this.precioTotal = precioTotal;
        this.detallesEntrada = detallesEntrada;
    }

    public ObjectId getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(ObjectId idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public List<DetalleEntrada> getDetallesEntrada() {
        return detallesEntrada;
    }

    public void setDetallesEntrada(List<DetalleEntrada> detallesEntrada) {
        this.detallesEntrada = detallesEntrada;
    }

    @Override
    public String toString() {
        return "Entrada{" + "idEntrada=" + idEntrada + ", proveedor=" + proveedor + ", fechaHora=" + fechaHora + ", precioTotal=" + precioTotal + ", detallesEntrada=" + detallesEntrada + '}';
    }
}
