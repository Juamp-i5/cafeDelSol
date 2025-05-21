package DTOs.CRUDIngredientes;

import java.util.Objects;

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

    public IngredienteViejoListDTO(String id) {
        this.id = id;
    }

    public IngredienteViejoListDTO(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IngredienteViejoListDTO other = (IngredienteViejoListDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "IngredienteViejoListDTO{" + "id=" + id + ", nombre=" + nombre + ", cantidadDisponible=" + cantidadDisponible + ", unidadMedida=" + unidadMedida + ", nivelStock=" + nivelStock + '}';
    }

}
