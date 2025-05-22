package entidades;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo
 *
 * Clase que representa una "Entrada" o "Registro de Ingreso" en un sistema.
 * Podría ser, por ejemplo, la entrada de productos a un almacén o el registro
 * de una transacción.
 */
public class Entrada {

    @BsonId // Anotación que indica que este campo es el identificador único para MongoDB.
    private ObjectId idEntrada; // Identificador único de la entrada, típicamente usado en bases de datos NoSQL.
    private String proveedor;   // Nombre del proveedor de quien proviene la entrada.
    private LocalDateTime fechaHora; // Fecha y hora exacta en que se realizó la entrada.
    private Double precioTotal;     // El precio total de todos los artículos o elementos incluidos en esta entrada.
    private List<DetalleEntrada> detallesEntrada; // Una lista de objetos 'DetalleEntrada' que describe cada artículo o componente de la entrada.

    /**
     * Constructor por defecto de la clase Entrada. Es necesario para que
     * algunas librerías (como las de mapeo de objetos a BSON/JSON) puedan crear
     * instancias.
     */
    public Entrada() {
    }

    /**
     * Constructor completo para inicializar una nueva instancia de Entrada con
     * todos sus atributos.
     *
     * @param idEntrada El identificador único de la entrada.
     * @param proveedor El nombre del proveedor.
     * @param fechaHora La fecha y hora de la entrada.
     * @param precioTotal El precio total de la entrada.
     * @param detallesEntrada Una lista de los detalles de la entrada.
     */
    public Entrada(ObjectId idEntrada, String proveedor, LocalDateTime fechaHora, Double precioTotal, List<DetalleEntrada> detallesEntrada) {
        this.idEntrada = idEntrada;
        this.proveedor = proveedor;
        this.fechaHora = fechaHora;
        this.precioTotal = precioTotal;
        this.detallesEntrada = detallesEntrada;
    }

    /**
     * Constructor para inicializar una nueva instancia de Entrada sin el
     * idEntrada, útil cuando la base de datos es la que genera el ID
     * automáticamente.
     *
     * @param proveedor El nombre del proveedor.
     * @param fechaHora La fecha y hora de la entrada.
     * @param precioTotal El precio total de la entrada.
     * @param detallesEntrada Una lista de los detalles de la entrada.
     */
    public Entrada(String proveedor, LocalDateTime fechaHora, Double precioTotal, List<DetalleEntrada> detallesEntrada) {
        this.proveedor = proveedor;
        this.fechaHora = fechaHora;
        this.precioTotal = precioTotal;
        this.detallesEntrada = detallesEntrada;
    }

    // --- Métodos Getters y Setters ---
    /**
     * Obtiene el identificador único de la entrada.
     *
     * @return El ObjectId de la entrada.
     */
    public ObjectId getIdEntrada() {
        return idEntrada;
    }

    /**
     * Establece el identificador único de la entrada.
     *
     * @param idEntrada El ObjectId a establecer.
     */
    public void setIdEntrada(ObjectId idEntrada) {
        this.idEntrada = idEntrada;
    }

    /**
     * Obtiene el nombre del proveedor.
     *
     * @return El String que representa el proveedor.
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * Establece el nombre del proveedor.
     *
     * @param proveedor El String del proveedor a establecer.
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Obtiene el precio total de la entrada.
     *
     * @return El Double que representa el precio total.
     */
    public Double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Establece el precio total de la entrada.
     *
     * @param precioTotal El Double del precio total a establecer.
     */
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * Obtiene la fecha y hora de la entrada.
     *
     * @return El LocalDateTime de la fecha y hora.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de la entrada.
     *
     * @param fechaHora El LocalDateTime de la fecha y hora a establecer.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene la lista de detalles de la entrada.
     *
     * @return La List de DetalleEntrada.
     */
    public List<DetalleEntrada> getDetallesEntrada() {
        return detallesEntrada;
    }

    /**
     * Establece la lista de detalles de la entrada.
     *
     * @param detallesEntrada La List de DetalleEntrada a establecer.
     */
    public void setDetallesEntrada(List<DetalleEntrada> detallesEntrada) {
        this.detallesEntrada = detallesEntrada;
    }

    /**
     * Sobreescribe el método toString para proporcionar una representación de
     * cadena legible de un objeto Entrada. Esto es útil para depuración y para
     * mostrar información del objeto.
     *
     * @return Una cadena que representa el objeto Entrada.
     */
    @Override
    public String toString() {
        return "Entrada{" + "idEntrada=" + idEntrada + ", proveedor='" + proveedor + '\'' + ", fechaHora=" + fechaHora + ", precioTotal=" + precioTotal + ", detallesEntrada=" + detallesEntrada + '}';
    }
}
