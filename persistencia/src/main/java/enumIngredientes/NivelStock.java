/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enumIngredientes;

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
                return "En stock";
            case BAJOSTOCK:
                return "Bajo stock";
            default:
                return "";
        }
    }
}
