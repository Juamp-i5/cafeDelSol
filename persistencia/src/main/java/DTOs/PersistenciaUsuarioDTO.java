/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * Data Transfer Object (DTO) utilizado para la persistencia de información de
 * usuarios.
 *
 * <p>
 * Este DTO es útil para transferir datos entre capas, especialmente entre la
 * capa de presentación y la capa de persistencia o servicios, sin exponer
 * directamente la entidad del modelo de dominio.</p>
 *
 * <p>
 * Incluye información básica de un usuario, como su nombre, apellidos, tipo de
 * empleado, usuario y contraseña. El identificador {@code id} se maneja como
 * una cadena para facilitar su transporte.</p>
 *
 * @author Jp
 */
public class PersistenciaUsuarioDTO {

    private String id;
    private String nombresPila;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoEmpleado;
    private String usuario;
    private char[] contrasenia;

    /**
     * Crea una nueva instancia vacía de {@code PersistenciaUsuarioDTO}.
     */
    public PersistenciaUsuarioDTO() {
    }

    /**
     * Obtiene el identificador del usuario.
     *
     * @return el identificador como cadena.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @param id el nuevo identificador como cadena.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene los nombres de pila del usuario.
     *
     * @return los nombres de pila.
     */
    public String getNombresPila() {
        return nombresPila;
    }

    /**
     * Establece los nombres de pila del usuario.
     *
     * @param nombresPila los nombres de pila a establecer.
     */
    public void setNombresPila(String nombresPila) {
        this.nombresPila = nombresPila;
    }

    /**
     * Obtiene el apellido paterno del usuario.
     *
     * @return el apellido paterno.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del usuario.
     *
     * @param apellidoPaterno el apellido paterno a establecer.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del usuario.
     *
     * @return el apellido materno.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del usuario.
     *
     * @param apellidoMaterno el apellido materno a establecer.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el tipo de empleado.
     *
     * @return el tipo de empleado.
     */
    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    /**
     * Establece el tipo de empleado.
     *
     * @param tipoEmpleado el tipo de empleado a establecer.
     */
    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return el nombre de usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param usuario el nombre de usuario a establecer.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña como un arreglo de caracteres.
     *
     * @return la contraseña.
     */
    public char[] getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña como un arreglo de caracteres.
     *
     * @param contrasenia la contraseña a establecer.
     */
    public void setContrasenia(char[] contrasenia) {
        this.contrasenia = contrasenia;
    }
}
