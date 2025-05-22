/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import DTOs.PersistenciaUsuarioDTO;

/**
 * Interfaz que define las operaciones de acceso a datos para Usuario.
 * Esta interfaz forma parte de la capa de persistencia y permite registrar, consultar
 * y autenticar usuarios en el sistema, además de verificar la disponibilidad de usernames.
 */
public interface IUsuarioDAO {

    /**
     * Verifica si un nombre de usuario ya está registrado en la base de datos.
     * @param username Nombre de usuario a verificar.
     * @return true si el nombre está disponible, false si ya está en uso.
     */
    public boolean usernameDisponible(String username);

    /**
     * Agrega un nuevo usuario al sistema.
     * La implementación debe encargarse de hashear la contraseña antes de almacenarla.
     * @param usuarioDTO Objeto que contiene los datos del usuario a registrar.
     */
    public void agregarUsuario(PersistenciaUsuarioDTO usuarioDTO);

    /**
     * Verifica las credenciales de inicio de sesión de un usuario.
     * @param username Nombre de usuario ingresado.
     * @param contrasenia Contraseña ingresada (como arreglo de caracteres).
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean comprobarInicioSesion(String username, char[] contrasenia);

    /**
     * Busca y recupera la información de un usuario a partir de su nombre de usuario.
     * @param username Nombre de usuario a buscar.
     * @return DTO con los datos del usuario, o null si no existe.
     */
    public PersistenciaUsuarioDTO buscarPorUsername(String username);

}
