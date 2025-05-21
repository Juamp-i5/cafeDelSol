/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DTOs.UsuarioDTO;

/**
 *
 * @author Jp
 */
public class Sesion {

    private static UsuarioDTO currentUsuario;

    public static UsuarioDTO getCurrentUsuario() {
        return currentUsuario;
    }

    public static void setCurrentUsuario(UsuarioDTO currentUsuario) {
        Sesion.currentUsuario = currentUsuario;
    }

}
