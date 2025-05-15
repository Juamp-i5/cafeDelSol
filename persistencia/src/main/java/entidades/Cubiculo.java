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
    
    private Long numeroCubiculo;
    private Double precioCubiculo;

    public Cubiculo() {
    }

    public Cubiculo(Long numeroCubiculo, Double precioCubiculo) {
        this.numeroCubiculo = numeroCubiculo;
        this.precioCubiculo = precioCubiculo;
    }

    public Long getNumeroCubiculo() {
        return numeroCubiculo;
    }

    public void setNumeroCubiculo(Long numeroCubiculo) {
        this.numeroCubiculo = numeroCubiculo;
    }

    public Double getPrecioCubiculo() {
        return precioCubiculo;
    }

    public void setPrecioCubiculo(Double precioCubiculo) {
        this.precioCubiculo = precioCubiculo;
    }

    @Override
    public String toString() {
        return "Cubiculo{" + "numeroCubiculo=" + numeroCubiculo + ", precioCubiculo=" + precioCubiculo + '}';
    }
    
    
}
