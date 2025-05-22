package DTOs.entradas;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author pablo
 * Esta clase funciona como un Objeto de Transferencia de Datos (DTO) para representar
 * una entrada ya existente en el sistema, especialmente diseñada para ser utilizada
 * cuando se recuperan datos de la capa de persistencia. A diferencia de `EntradaNuevaDTOPersistencia`,
 * incluye el identificador único (`idEntrada`), ya que este es un dato fundamental
 * de un registro ya guardado. Sirve para transferir la información completa de una
 * entrada, incluyendo sus detalles, desde la base de datos a otras capas de la aplicación.
 */
public class EntradaViejaDTOPersistencia {
    private String idEntrada;
    private LocalDateTime fechaHora;
    private String proveedor;
    private Double precioTotal;
    private List<DetalleEntradaDTOPersistencia> DetallesEntrada;

    /**
     * Constructor por defecto. Es indispensable para que los frameworks de mapeo (ORM)
     * puedan crear instancias de esta clase al deserializar los datos recuperados de la base de datos.
     */
    public EntradaViejaDTOPersistencia() {
    }

    /**
     * Constructor completo que permite inicializar todos los atributos de una entrada existente.
     * Este constructor es útil cuando se construye el DTO con todos los datos necesarios
     * para ser transferidos desde la capa de persistencia.
     *
     * @param idEntrada El ID único de la entrada en formato String.
     * @param fechaHora La fecha y hora en que se registró la entrada.
     * @param proveedor El nombre del proveedor de la entrada.
     * @param precioTotal El precio total de la entrada.
     * @param DetallesEntrada Una lista de `DetalleEntradaDTOPersistencia` que componen esta entrada.
     */
    public EntradaViejaDTOPersistencia(String idEntrada, LocalDateTime fechaHora, String proveedor, Double precioTotal, List<DetalleEntradaDTOPersistencia> DetallesEntrada) {
        this.idEntrada = idEntrada;
        this.fechaHora = fechaHora;
        this.proveedor = proveedor;
        this.precioTotal = precioTotal;
        this.DetallesEntrada = DetallesEntrada;
    }

    /**
     * Constructor alternativo que permite inicializar una `EntradaViejaDTOPersistencia`
     * sin el ID de la entrada, aunque este DTO está más orientado a registros existentes
     * que ya poseen un ID. Puede ser útil en escenarios donde el ID se asigna posteriormente
     * o se obtiene de otra fuente.
     *
     * @param fechaHora La fecha y hora en que se registró la entrada.
     * @param proveedor El nombre del proveedor de la entrada.
     * @param precioTotal El precio total de la entrada.
     * @param DetallesEntrada Una lista de `DetalleEntradaDTOPersistencia` que componen esta entrada.
     */
    public EntradaViejaDTOPersistencia(LocalDateTime fechaHora, String proveedor, Double precioTotal, List<DetalleEntradaDTOPersistencia> DetallesEntrada) {
        this.fechaHora = fechaHora;
        this.proveedor = proveedor;
        this.precioTotal = precioTotal;
        this.DetallesEntrada = DetallesEntrada;
    }
   
    /**
     * Obtiene el identificador único de la entrada. Este ID es crucial para referenciar
     * y realizar operaciones sobre una entrada ya existente en la base de datos.
     * @return Una cadena de texto que representa el ID de la entrada.
     */
    public String getIdEntrada() {
        return idEntrada;
    }

    /**
     * Establece el identificador único de la entrada.
     * @param idEntrada La cadena de texto a establecer como ID de la entrada.
     */
    public void setIdEntrada(String idEntrada) {
        this.idEntrada = idEntrada;
    }

    /**
     * Obtiene la fecha y hora en que se registró la entrada.
     * @return El objeto `LocalDateTime` que representa la fecha y hora de la entrada.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora para la entrada.
     * @param fechaHora El objeto `LocalDateTime` a establecer como fecha y hora de la entrada.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el nombre del proveedor asociado a esta entrada.
     * @return Una cadena de texto con el nombre del proveedor.
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * Establece el nombre del proveedor para esta entrada.
     * @param proveedor La cadena de texto a establecer como nombre del proveedor.
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Obtiene la lista de detalles de la entrada. Cada elemento de la lista
     * es un `DetalleEntradaDTOPersistencia` que describe un ingrediente específico en la entrada.
     * @return Una `List` de `DetalleEntradaDTOPersistencia`.
     */
    public List<DetalleEntradaDTOPersistencia> getDetallesEntrada() {
        return DetallesEntrada;
    }

    /**
     * Establece la lista de detalles para esta entrada. Esto permite asociar
     * múltiples ítems o ingredientes a una única entrada.
     * @param DetallesEntrada La `List` de `DetalleEntradaDTOPersistencia` a establecer.
     */
    public void setDetallesEntrada(List<DetalleEntradaDTOPersistencia> DetallesEntrada) {
        this.DetallesEntrada = DetallesEntrada;
    }

    /**
     * Obtiene el precio total de la entrada. Este campo resume el costo total
     * de todos los detalles de entrada incluidos.
     * @return El valor `Double` del precio total.
     */
    public Double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Establece el precio total de la entrada.
     * @param precioTotal El valor `Double` a establecer como precio total.
     */
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * Sobrescribe el método `toString()` para proporcionar una representación legible
     * del objeto `EntradaViejaDTOPersistencia`. Esto es muy útil para depuración
     * y para registrar la información de las entradas después de ser recuperadas de la base de datos.
     * @return Una cadena de texto que describe el estado actual del objeto.
     */
    @Override
    public String toString() {
        return "EntradaViejaDTO{" + "idEntrada=" + idEntrada + ", fechaHora=" + fechaHora + ", proveedor=" + proveedor + ", precioTotal=" + precioTotal + ", DetallesEntrada=" + DetallesEntrada + '}';
    }
}
