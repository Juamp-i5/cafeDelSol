/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacesBO;

import DTOs.InicioSesionDTO;
import DTOs.UsuarioDTO;
import excepciones.NegocioException;

/**
 *
 * @author Jp
 */
public interface IUsuarioBO {

    public UsuarioDTO comprobarInicioSesion(InicioSesionDTO inicioSesionDTO) throws NegocioException;
}
