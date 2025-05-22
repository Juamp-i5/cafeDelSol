package DTOs.entradas;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import java.util.List;

/**
 *
 * @author pablo
 * Esta clase sirve como un Objeto de Transferencia de Datos (DTO) para la capa de persistencia,
 * específicamente para representar los detalles de una entrada de un ingrediente.
 * Su propósito es transferir datos de manera eficiente entre las capas de la aplicación,
 * especialmente entre la capa de negocio y la capa de acceso a datos (DAO),
 * manteniendo un formato plano y simple que refleja cómo los datos podrían
 * almacenarse o recuperarse de la base de datos.
 */
public class DetalleEntradaDTOPersistencia {
    private String nombreIngrediente;
    private Double precioUnitario;
    private Double precioTotal;
    private Double cantidadIngrediente;
    private String nivelStock;
    private String idIngrediente;
    private List<IngredienteDTOPersistencia> ingredienteInfo;

    /**
     * Constructor por defecto. Es necesario para que los frameworks de persistencia
     * puedan instanciar la clase al recuperar datos de la base de datos.
     */
    public DetalleEntradaDTOPersistencia() {
    }

    /**
     * Constructor completo que permite inicializar todos los atributos de un `DetalleEntradaDTOPersistencia`.
     * Este constructor es útil para crear instancias del DTO con todos los datos necesarios
     * antes de ser transferidos a o desde la capa de persistencia.
     *
     * @param nombreIngrediente El nombre del ingrediente.
     * @param precioUnitario El precio por unidad del ingrediente.
     * @param precioTotal El precio total de la cantidad de este ingrediente.
     * @param cantidadIngrediente La cantidad del ingrediente.
     * @param nivelStock El nivel de stock actual del ingrediente.
     * @param idIngrediente El ID del ingrediente al que se refiere este detalle, en formato String.
     * @param ingredienteInfo Una lista (posiblemente con un solo elemento) que contiene la información completa del ingrediente asociada.
     */
    public DetalleEntradaDTOPersistencia(String nombreIngrediente, Double precioUnitario, Double precioTotal, Double cantidadIngrediente, String nivelStock, String idIngrediente, List<IngredienteDTOPersistencia> ingredienteInfo) {
        this.nombreIngrediente = nombreIngrediente;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.cantidadIngrediente = cantidadIngrediente;
        this.nivelStock = nivelStock;
        this.idIngrediente = idIngrediente;
        this.ingredienteInfo = ingredienteInfo;
    }
    
    /**
     * Obtiene el nombre del ingrediente asociado a este detalle de entrada.
     * @return El nombre del ingrediente.
     */
    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    /**
     * Establece el nombre del ingrediente para este detalle de entrada.
     * @param nombreIngrediente El nuevo nombre del ingrediente.
     */
    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    /**
     * Obtiene el precio unitario del ingrediente en este detalle de entrada.
     * @return El precio unitario del ingrediente.
     */
    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Establece el precio unitario del ingrediente para este detalle de entrada.
     * @param precioUnitario El nuevo precio unitario del ingrediente.
     */
    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene el precio total calculado para la cantidad de este ingrediente en el detalle de entrada.
     * @return El precio total del ingrediente en esta entrada.
     */
    public Double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Establece el precio total para la cantidad de este ingrediente en el detalle de entrada.
     * @param precioTotal El nuevo precio total.
     */
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * Obtiene la cantidad del ingrediente registrada en este detalle de entrada.
     * @return La cantidad del ingrediente.
     */
    public Double getCantidadIngrediente() {
        return cantidadIngrediente;
    }

    /**
     * Establece la cantidad del ingrediente para este detalle de entrada.
     * @param cantidadIngrediente La nueva cantidad del ingrediente.
     */
    public void setCantidadIngrediente(Double cantidadIngrediente) {
        this.cantidadIngrediente = cantidadIngrediente;
    }

    /**
     * Obtiene el ID del ingrediente asociado a este detalle de entrada, representado como un String.
     * @return El ID del ingrediente.
     */
    public String getIdIngrediente() {
        return idIngrediente;
    }

    /**
     * Establece el ID del ingrediente para este detalle de entrada.
     * @param idIngrediente El nuevo ID del ingrediente en formato String.
     */
    public void setIdIngrediente(String idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    /**
     * Obtiene la descripción del nivel de stock actual del ingrediente.
     * @return El nivel de stock del ingrediente.
     */
    public String getNivelStock() {
        return nivelStock;
    }

    /**
     * Establece la descripción del nivel de stock para este ingrediente.
     * @param nivelStock La nueva descripción del nivel de stock.
     */
    public void setNivelStock(String nivelStock) {
        this.nivelStock = nivelStock;
    }

    /**
     * Obtiene la lista de objetos `IngredienteDTOPersistencia` que contienen información detallada del ingrediente.
     * Esto es útil cuando la información del ingrediente se "une" o se incrusta en el detalle de la entrada.
     * @return Una lista con la información del ingrediente.
     */
    public List<IngredienteDTOPersistencia> getIngredienteInfo() {
        return ingredienteInfo;
    }

    /**
     * Establece la lista de objetos `IngredienteDTOPersistencia` para este detalle de entrada.
     * @param ingredienteInfo La nueva lista con la información del ingrediente.
     */
    public void setIngredienteInfo(List<IngredienteDTOPersistencia> ingredienteInfo) {
        this.ingredienteInfo = ingredienteInfo;
    }

    /**
     * Sobrescribe el método `toString()` para proporcionar una representación de cadena clara y legible
     * del objeto `DetalleEntradaDTOPersistencia`. Esto es especialmente útil para depuración
     * y para registrar información sobre los datos transferidos.
     * @return Una cadena que representa el estado actual del objeto.
     */
    @Override
    public String toString() {
        return "DetalleEntradaDTOPersistencia{" + "nombreIngrediente=" + nombreIngrediente + ", precioUnitario=" + precioUnitario + ", precioTotal=" + precioTotal + ", cantidadIngrediente=" + cantidadIngrediente + ", nivelStock=" + nivelStock + ", idIngrediente=" + idIngrediente + ", ingredienteInfo=" + ingredienteInfo + '}';
    }
}
