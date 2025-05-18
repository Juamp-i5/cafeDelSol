package DTOs.ingredientes;

import enumIngredientes.NivelStock;
import enumIngredientes.UnidadMedida;


/**
 *
 * @author Jp
 */
public class IngredienteViejoListDTOPersistencia {

    private String id;
    private String nombre;
    private double cantidadDisponible;
    private double cantidadMinima;
    private UnidadMedida unidadMedida;
    private NivelStock nivelStock;
    private String idProveedor;

    public IngredienteViejoListDTOPersistencia() {
    }

    public IngredienteViejoListDTOPersistencia(String nombre, Double cantidadDisponible, Double cantidadMinima, UnidadMedida unidadMedida, NivelStock nivelStock, String idProveedor) {
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadMinima = cantidadMinima;
        this.unidadMedida = unidadMedida;
        this.nivelStock = nivelStock;
        this.idProveedor = idProveedor;
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

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public String toString() {
        return "IngredienteDTO{" + "id=" + id + ", nombre=" + nombre + ", cantidadDisponible=" + cantidadDisponible + ", cantidadMinima=" + cantidadMinima + ", unidadMedida=" + unidadMedida + ", nivelStock=" + nivelStock + ", idProveedor=" + idProveedor + '}';
    }
      
    
}
