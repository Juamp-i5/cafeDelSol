/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import DTOs.PersistenciaSaborDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 * Su propósito fundamental es asegurar la **separación de preocupaciones** en la
 * arquitectura, permitiendo que la capa de negocio interactúe con los datos de
 * los sabores sin depender de los detalles específicos de la implementación
 * de la base de datos subyacente.
 */
public interface ISaborDAO {

    /**
     * Recupera una lista de todos los objetos `Sabor` disponibles en la fuente de datos.
     * Este método es vital para obtener un listado completo de los sabores registrados.
     *
     * @return Una `List` de `PersistenciaSaborDTO` que representan todos los sabores.
     * @throws PersistenciaException Si ocurre un error inesperado al interactuar con la fuente de datos.
     */
    public List<PersistenciaSaborDTO> buscarTodos() throws PersistenciaException;

    /**
     * Busca y recupera un objeto `Sabor` específico utilizando su nombre.
     * Es útil para localizar un sabor particular basándose en un identificador textual.
     *
     * @param nombre El nombre del sabor a buscar.
     * @return Un `PersistenciaSaborDTO` si se encuentra un sabor con el nombre especificado,
     * o `null` si no existe.
     * @throws PersistenciaException Si ocurre un error inesperado al interactuar con la fuente de datos.
     */
    public PersistenciaSaborDTO buscarPorNombre(String nombre) throws PersistenciaException;

    /**
     * Guarda un nuevo `Sabor` en la fuente de datos. Este método se encarga de
     * persistir la información de un nuevo sabor en la base de datos.
     *
     * @param saborDTO El `PersistenciaSaborDTO` que contiene los datos del sabor a guardar.
     * @throws PersistenciaException Si ocurre un error inesperado al intentar guardar el sabor.
     */
    public void guardar(PersistenciaSaborDTO saborDTO) throws PersistenciaException;
}
