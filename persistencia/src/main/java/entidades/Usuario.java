/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import org.bson.types.ObjectId;

/**
 * Representa a un usuario del sistema. Contiene información personal y de
 * autenticación del usuario, como nombre, apellidos, tipo de empleado, nombre
 * de usuario y contraseña.
 *
 * <p>
 * Esta clase forma parte del modelo de dominio y puede usarse para operaciones
 * CRUD o de lógica de negocio.</p>
 *
 * <p>
 * El campo {@code id} corresponde al identificador único generado por
 * MongoDB.</p>
 *
 * @author Jp
 */
public class Usuario {

    private ObjectId id;
    private String nombresPila;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoEmpleado;
    private String usuario;
    private String contrasenia;

    /**
     * Crea una nueva instancia vacía de {@code Usuario}.
     */
    public Usuario() {
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return el {@link ObjectId} del usuario.
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id el nuevo {@link ObjectId} del usuario.
     */
    public void setId(ObjectId id) {
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
     * Obtiene el tipo de empleado del usuario.
     *
     * @return el tipo de empleado.
     */
    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    /**
     * Establece el tipo de empleado del usuario.
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
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasenia la contraseña a establecer.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}
