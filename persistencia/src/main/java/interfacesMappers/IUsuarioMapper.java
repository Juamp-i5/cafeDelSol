/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaUsuarioDTO;
import entidades.Usuario;

/**
 * Interfaz que define los métodos para mapear entre un {@link Usuario} y un
 * {@link PersistenciaUsuarioDTO}.
 *
 * <p>
 * Permite convertir objetos de la capa de persistencia a la capa de dominio y
 * viceversa.</p>
 *
 * @author Jp
 */
public interface IUsuarioMapper {

    /**
     * Convierte un objeto {@link PersistenciaUsuarioDTO} en una entidad
     * {@link Usuario}.
     *
     * @param dto el objeto de transferencia de datos a convertir.
     * @return la entidad {@link Usuario} correspondiente, o {@code null} si el
     * DTO es {@code null}.
     */
    public Usuario toEntity(PersistenciaUsuarioDTO dto);

    /**
     * Convierte una entidad {@link Usuario} en un objeto
     * {@link PersistenciaUsuarioDTO}.
     *
     * @param entidad la entidad {@link Usuario} a convertir.
     * @return el objeto {@link PersistenciaUsuarioDTO} correspondiente, o
     * {@code null} si la entidad es {@code null}.
     */
    public PersistenciaUsuarioDTO toDTO(Usuario entidad);

}
