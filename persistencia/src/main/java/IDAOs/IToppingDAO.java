/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import DTOs.PersistenciaToppingDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author rodri
 * Establece un conjunto de métodos que cualquier implementación de este DAO
 * debe proporcionar para interactuar con una fuente de datos (como una base de datos).
 * Su propósito fundamental es asegurar la **separación de preocupaciones** en la
 * arquitectura, permitiendo que la capa de negocio interactúe con los datos de
 * los toppings sin depender de los detalles específicos de la implementación
 * de la base de datos subyacente.
 */
public interface IToppingDAO {

    /**
     * Recupera una lista de todos los objetos `Topping` disponibles en la fuente de datos.
     * Este método es vital para obtener un listado completo de los toppings registrados.
     *
     * @return Una `List` de `PersistenciaToppingDTO` que representan todos los toppings.
     * @throws PersistenciaException Si ocurre un error inesperado al interactuar con la fuente de datos.
     */
    public List<PersistenciaToppingDTO> buscarTodos() throws PersistenciaException;

    /**
     * Busca y recupera un objeto `Topping` específico utilizando su nombre.
     * Es útil para localizar un topping particular basándose en un identificador textual.
     *
     * @param nombre El nombre del topping a buscar.
     * @return Un `PersistenciaToppingDTO` si se encuentra un topping con el nombre especificado,
     * o `null` si no existe.
     * @throws PersistenciaException Si ocurre un error inesperado al interactuar con la fuente de datos.
     */
    public PersistenciaToppingDTO buscarPorNombre(String nombre) throws PersistenciaException;

    /**
     * Guarda un nuevo `Topping` en la fuente de datos. Este método se encarga de
     * persistir la información de un nuevo topping en la base de datos.
     *
     * @param toppingDTO El `PersistenciaToppingDTO` que contiene los datos del topping a guardar.
     * @throws PersistenciaException Si ocurre un error inesperado al intentar guardar el topping.
     */
    public void guardar(PersistenciaToppingDTO toppingDTO) throws PersistenciaException;
}
