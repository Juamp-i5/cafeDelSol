package entidades;

/**
 *
 * @author Jp
 */
public class DetallesTarjeta {

    private String tipoTarjeta;
    private String ultimosDigitos;
    private String marca;
    private String banco;
    private String titular;
    private String noAutorizacion;

    public DetallesTarjeta() {
    }

    public DetallesTarjeta(String tipoTarjeta, String ultimosDigitos, String marca, String banco, String titular, String noAutorizacion) {
        this.tipoTarjeta = tipoTarjeta;
        this.ultimosDigitos = ultimosDigitos;
        this.marca = marca;
        this.banco = banco;
        this.titular = titular;
        this.noAutorizacion = noAutorizacion;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getUltimosDigitos() {
        return ultimosDigitos;
    }

    public void setUltimosDigitos(String ultimosDigitos) {
        this.ultimosDigitos = ultimosDigitos;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNoAutorizacion() {
        return noAutorizacion;
    }

    public void setNoAutorizacion(String noAutorizacion) {
        this.noAutorizacion = noAutorizacion;
    }

}
