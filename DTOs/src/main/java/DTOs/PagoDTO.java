/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Jp
 */
public class PagoDTO {

    private String moneda;
    private TarjetaDTO tarjetaDTO;
    private ResultadoPagoDTO resultadoPagoDTO;
    private EfectivoDTO efectivoDTO;
    private CambioDTO cambioDTO;

    public PagoDTO() {
        this.moneda = "MXN";
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public TarjetaDTO getTarjetaDTO() {
        return tarjetaDTO;
    }

    public void setTarjetaDTO(TarjetaDTO tarjetaDTO) {
        this.tarjetaDTO = tarjetaDTO;
    }

    public ResultadoPagoDTO getResultadoPagoDTO() {
        return resultadoPagoDTO;
    }

    public void setResultadoPagoDTO(ResultadoPagoDTO resultadoPagoDTO) {
        this.resultadoPagoDTO = resultadoPagoDTO;
    }

    public EfectivoDTO getEfectivoDTO() {
        return efectivoDTO;
    }

    public void setEfectivoDTO(EfectivoDTO efectivoDTO) {
        this.efectivoDTO = efectivoDTO;
    }

    public CambioDTO getCambioDTO() {
        return cambioDTO;
    }

    public void setCambioDTO(CambioDTO cambioDTO) {
        this.cambioDTO = cambioDTO;
    }

}
