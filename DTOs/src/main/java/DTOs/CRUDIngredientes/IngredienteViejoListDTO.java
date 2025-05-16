package DTOs.CRUDIngredientes;

/**
 *
 * @author norma
 */
public class IngredienteViejoListDTO {
   private String id;
    private String nombre;
    private Double cantidadDisponible;
    private UnidadMedida unidadMedida;
    private NivelStock nivelStock;

    public IngredienteViejoListDTO() {
    }

    public IngredienteViejoListDTO(String id, String nombre, Double cantidadDisponible, UnidadMedida unidadMedida, NivelStock nivelStock) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.unidadMedida = unidadMedida;
        this.nivelStock = nivelStock;
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
  
}
