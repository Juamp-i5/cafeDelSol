/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.TamanioDTO;
import entidades.Tamanio;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jp
 */
public interface ITamanioMapper {

    public TamanioDTO toDTO(Tamanio entidad);

    public Tamanio toMongo(TamanioDTO dto) throws PersistenciaException;

    public List<TamanioDTO> toDTOList(List<Tamanio> entidades);

    public List<Tamanio> toMongoList(List<TamanioDTO> dtos) throws PersistenciaException;
}
