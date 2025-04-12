package DTOs;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author norma
 */
public class DetallesCobroTarjetaDTO {

    private String numeroTarjeta;
    private String nombreBanco;
    private String cvv;
    private String fechaExp;

    public DetallesCobroTarjetaDTO() {
    }

    public DetallesCobroTarjetaDTO(String numeroTarjeta, String nombreBanco, String cvv, String fechaExp) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreBanco = nombreBanco;
        this.cvv = cvv;
        this.fechaExp = fechaExp;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(String fechaExp) {
        this.fechaExp = fechaExp;
    }

    @Override
    public String toString() {
        return "DetallesCobroTarjetaDTO{" + "numeroTarjeta=" + numeroTarjeta + ", nombreBanco=" + nombreBanco + ", cvv=" + cvv + ", fechaExp=" + fechaExp + '}';
    }
    
}
