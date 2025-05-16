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
    GRAMO,
    UNIDAD,
    MILILITRO;
    
    @Override
    public String toString() {
        switch (this) {
            case GRAMO:
                return "Gramo";
            case UNIDAD:
                return "Unidad";
            case MILILITRO:
                return "Mililitro";
            default:
                return "";
        }
    }
    
}
