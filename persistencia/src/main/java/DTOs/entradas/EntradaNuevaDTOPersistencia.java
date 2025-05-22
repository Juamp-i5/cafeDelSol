package DTOs.entradas;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author pablo Esta clase es un Objeto de Transferencia de Datos (DTO)
 * diseñado específicamente para la creación de nuevas entradas en la capa de
 * persistencia. Su objetivo principal es encapsular los datos necesarios para
 * registrar una nueva entrada sin incluir campos como el ID de la entrada, ya
 * que este suele ser generado automáticamente por la base de datos al momento
 * de la inserción. Sirve como un contrato claro entre la capa de negocio y la
 * capa de acceso a datos para operaciones de registro.
 */
public class EntradaNuevaDTOPersistencia {

    private LocalDateTime fechaHora;
    private String proveedor;
    private Double precioTotal;
    private List<DetalleEntradaDTOPersistencia> DetallesEntrada;

    /**
     * Constructor por defecto. Es esencial para que los frameworks de mapeo de
     * datos puedan instanciar objetos de esta clase, por ejemplo, al
     * deserializar datos.
     */
    public EntradaNuevaDTOPersistencia() {
    }

    /**
     * Constructor completo que permite inicializar todos los atributos de una
     * nueva entrada. Facilita la creación de objetos DTO con la información
     * lista para ser enviada a la capa de persistencia para su registro.
     *
     * @param fechaHora La fecha y hora en que se realizó la entrada.
     * @param proveedor El nombre del proveedor de la entrada.
     * @param precioTotal El precio total de la entrada.
     * @param DetallesEntrada Una lista de objetos
     * `DetalleEntradaDTOPersistencia` que componen esta entrada.
     */
    public EntradaNuevaDTOPersistencia(LocalDateTime fechaHora, String proveedor, Double precioTotal, List<DetalleEntradaDTOPersistencia> DetallesEntrada) {
        this.fechaHora = fechaHora;
        this.proveedor = proveedor;
        this.precioTotal = precioTotal;
        this.DetallesEntrada = DetallesEntrada;
    }

    /**
     * Obtiene la fecha y hora en que se registró la entrada.
     *
     * @return El objeto `LocalDateTime` que representa la fecha y hora de la
     * entrada.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora para la entrada.
     *
     * @param fechaHora El objeto `LocalDateTime` a establecer como fecha y hora
     * de la entrada.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el nombre del proveedor asociado a esta entrada.
     *
     * @return Una cadena de texto con el nombre del proveedor.
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * Establece el nombre del proveedor para esta entrada.
     *
     * @param proveedor La cadena de texto a establecer como nombre del
     * proveedor.
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Obtiene la lista de detalles de la entrada. Cada elemento de la lista es
     * un `DetalleEntradaDTOPersistencia` que describe un ingrediente específico
     * en la entrada.
     *
     * @return Una `List` de `DetalleEntradaDTOPersistencia`.
     */
    public List<DetalleEntradaDTOPersistencia> getDetallesEntrada() {
        return DetallesEntrada;
    }

    /**
     * Establece la lista de detalles para esta entrada. Esto permite asociar
     * múltiples ítems o ingredientes a una única entrada.
     *
     * @param DetallesEntrada La `List` de `DetalleEntradaDTOPersistencia` a
     * establecer.
     */
    public void setDetallesEntrada(List<DetalleEntradaDTOPersistencia> DetallesEntrada) {
        this.DetallesEntrada = DetallesEntrada;
    }

    /**
     * Obtiene el precio total de la entrada. Este campo resume el costo total
     * de todos los detalles de entrada incluidos.
     *
     * @return El valor `Double` del precio total.
     */
    public Double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Establece el precio total de la entrada.
     *
     * @param precioTotal El valor `Double` a establecer como precio total.
     */
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * Sobrescribe el método `toString()` para proporcionar una representación
     * legible del objeto `EntradaNuevaDTOPersistencia`. Esto es muy útil para
     * depuración y para registrar la información de las entradas antes de su
     * persistencia.
     *
     * @return Una cadena de texto que describe el estado actual del objeto.
     */
    @Override
    public String toString() {
        return "EntradaNuevaDTO{" + "fechaHora=" + fechaHora + ", proveedor=" + proveedor + ", precioTotal=" + precioTotal + ", DetallesEntrada=" + DetallesEntrada + '}';
    }
}
