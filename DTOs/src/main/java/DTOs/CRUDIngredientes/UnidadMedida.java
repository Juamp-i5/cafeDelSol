/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.CRUDIngredientes;

/**
 *
 * @author norma
 */
public enum UnidadMedida {
    GRAMOS,
    UNIDADES,
    MILILITROS;
    
    @Override
    public String toString() {
        switch (this) {
            case GRAMOS:
                return "GRAMOS";
            case UNIDADES:
                return "UNIDADES";
            case MILILITROS:
                return "MILILITROS";
            default:
                return "";
        }
    }
    
}
