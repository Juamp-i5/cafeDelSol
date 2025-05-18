/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.CRUDIngredientes;

/**
 *
 * @author norma
 */
public enum NivelStock {
    ENSTOCK,
    BAJOSTOCK;

    @Override
    public String toString() {
        switch (this) {
            case ENSTOCK:
                return "ENSTOCK";
            case BAJOSTOCK:
                return "BAJOSTOCK";
            default:
                return "";
        }
    }
}
