/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author rodri
 */
public class Cubiculo {
    
    private String nombre;
    private Double precioHora;

    public Cubiculo() {
    }

    public Cubiculo(String nombre, Double precioHora) {
        this.nombre = nombre;
        this.precioHora = precioHora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(Double precioHora) {
        this.precioHora = precioHora;
    }

    @Override
    public String toString() {
        return "Cubiculo{" + "numeroCubiculo=" + nombre + ", precioCubiculo=" + precioHora + '}';
    }
    
    
}
