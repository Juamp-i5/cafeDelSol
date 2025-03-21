/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author pablo
 */
public class TarjetaDTO {
    private String numTarjeta;
    private String nombreBanco;
    private String fechaExp;
    private String CVV;

    public TarjetaDTO() {
    }

    
    public TarjetaDTO(String numTarjeta, String nombreBanco, String fechaExp, String CVV) {
        this.numTarjeta = numTarjeta;
        this.nombreBanco = nombreBanco;
        this.fechaExp = fechaExp;
        this.CVV = CVV;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(String fechaExp) {
        this.fechaExp = fechaExp;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    @Override
    public String toString() {
        return "TarjetaDTO{" + "numTarjeta=" + numTarjeta + ", nombreBanco=" + nombreBanco + ", fechaExp=" + fechaExp + ", CVV=" + CVV + '}';
    }
}
