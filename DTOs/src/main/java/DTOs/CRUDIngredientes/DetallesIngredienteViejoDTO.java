package DTOs.CRUDIngredientes;

/**
 *
 * @author norma
 */
public class DetallesIngredienteViejoDTO {
    private String id;
    private String nombre;
    private Double cantidadDisponible;
    private Double cantidadMinima;
    private UnidadMedida unidadMedida;
    private NivelStock nivelStock;
    private String nombreProveedor;

    public DetallesIngredienteViejoDTO() {
    }

    public DetallesIngredienteViejoDTO(String id, String nombre, Double cantidadDisponible, Double cantidadMinima, UnidadMedida unidadMedida, NivelStock nivelStock, String nombreProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadMinima = cantidadMinima;
        this.unidadMedida = unidadMedida;
        this.nivelStock = nivelStock;
        this.nombreProveedor = nombreProveedor;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Double cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Double getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(Double cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
    public NivelStock getNivelStock() {
        return nivelStock;
    }

    public void setNivelStock(NivelStock nivelStock) {
        this.nivelStock = nivelStock;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String idProveedor) {
        this.nombreProveedor = idProveedor;
    }    

    @Override
    public String toString() {
        return "DetallesIngredienteViejoDTO{" + "id=" + id + ", nombre=" + nombre + ", cantidadDisponible=" + cantidadDisponible + ", cantidadMinima=" + cantidadMinima + ", unidadMedida=" + unidadMedida + ", nivelStock=" + nivelStock + ", nombreProveedor=" + nombreProveedor + '}';
    }

    
  
}
