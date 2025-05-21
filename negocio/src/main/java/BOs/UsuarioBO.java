/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.InicioSesionDTO;
import DTOs.PersistenciaUsuarioDTO;
import DTOs.UsuarioDTO;
import IDAOs.IUsuarioDAO;
import acceso.AccesoDatos;
import excepciones.NegocioException;
import interfacesBO.IUsuarioBO;
import interfacesMapper.IUsuarioMapper;
import mapper.UsuarioMapper;

/**
 *
 * @author Jp
 */
public class UsuarioBO implements IUsuarioBO {

    IUsuarioDAO usuarioDAO = AccesoDatos.getUsuarioDAO();
    IUsuarioMapper usuarioMapper = new UsuarioMapper();

    private static UsuarioBO instancia;

    private UsuarioBO() {
    }

    public static UsuarioBO getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioBO();
        }
        return instancia;
    }

    @Override
    public UsuarioDTO comprobarInicioSesion(InicioSesionDTO inicioSesionDTO) throws NegocioException {
        boolean valido = usuarioDAO.comprobarInicioSesion(inicioSesionDTO.getUsuario(), inicioSesionDTO.getContrasenia());

        if (valido) {
            PersistenciaUsuarioDTO usuario = usuarioDAO.buscarPorUsername(inicioSesionDTO.getUsuario());
            return usuarioMapper.toUsuarioDTO(usuario);
        } else {
            throw new NegocioException("Datos de inicio de sesión incorrectos");
        }
    }

}
