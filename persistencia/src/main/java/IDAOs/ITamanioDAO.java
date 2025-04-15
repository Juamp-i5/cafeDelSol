/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import entidades.Tamanio;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface ITamanioDAO {

    public List<Tamanio> buscarTodos() throws PersistenciaException;

    public Tamanio buscarPorNombre(String nombre) throws PersistenciaException;
}
