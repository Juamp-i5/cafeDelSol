/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Jp
 */
public class UsuarioDTO {

    private String id;
    private String nombresPila;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoEmpleado;
    private String usuario;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String id, String nombresPila, String apellidoPaterno, String apellidoMaterno, String tipoEmpleado, String usuario) {
        this.id = id;
        this.nombresPila = nombresPila;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.tipoEmpleado = tipoEmpleado;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombresPila() {
        return nombresPila;
    }

    public void setNombresPila(String nombresPila) {
        this.nombresPila = nombresPila;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
