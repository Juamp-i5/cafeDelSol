/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import DTOs.PersistenciaUsuarioDTO;

/**
 *
 * @author Jp
 */
public interface IUsuarioDAO {

    public boolean usernameDisponible(String username);

    public void agregarUsuario(PersistenciaUsuarioDTO usuarioDTO);

    public boolean comprobarInicioSesion(String username, char[] contrasenia);

    public PersistenciaUsuarioDTO buscarPorUsername(String username);

}
