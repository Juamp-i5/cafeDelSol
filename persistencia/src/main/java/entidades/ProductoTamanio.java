/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;

/**
 *
 * @author Jp
 */
public class ProductoTamanio {

    private Tamanio tamanio;
    private List<ProductoTamanioIngrediente> ingredientes;

    public ProductoTamanio() {
    }

    public Tamanio getTamanio() {
        return tamanio;
    }

    public void setTamanio(Tamanio tamanio) {
        this.tamanio = tamanio;
    }

    public List<ProductoTamanioIngrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<ProductoTamanioIngrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

}
