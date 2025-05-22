/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesMappers;

import DTOs.PersistenciaToppingDTO;
import entidades.Topping;

/**
 *
 * @author Jp 
 * Su propósito es establecer las operaciones de mapeo esenciales
 * para transformar los datos de los toppings entre el formato utilizado por la
 * base de datos (la entidad `Topping`) y el formato utilizado por la capa de
 * negocio (el DTO de persistencia), asegurando así el desacoplamiento y la
 * modularidad de las capas de la aplicación.
 */
public interface IToppingMapper {

    /**
     * Convierte un objeto de la entidad `Topping` (modelo de la base de datos)
     * a un `PersistenciaToppingDTO` (DTO para la capa de persistencia). Este
     * método es utilizado generalmente cuando se recuperan datos de la base de
     * datos y se necesitan transformar a un formato más genérico para ser
     * pasados a la capa de negocio.
     *
     * @param topping El objeto `Topping` (entidad) a convertir.
     * @return Un `PersistenciaToppingDTO` con los datos mapeados, o `null` si
     * la entidad de entrada es `null`.
     */
    public PersistenciaToppingDTO toDTO(Topping topping);

    /**
     * Convierte un `PersistenciaToppingDTO` (DTO de la capa de persistencia) a
     * un objeto de la entidad `Topping` (modelo de la base de datos). Este
     * método se utiliza principalmente cuando la capa de negocio envía datos de
     * un topping para ser guardados o actualizados en la base de datos. Se
     * encarga de cualquier transformación necesaria para que el DTO sea
     * compatible con la estructura de la entidad de la base de datos.
     *
     * @param toppingDto El `PersistenciaToppingDTO` a convertir.
     * @return Un objeto `Topping` (entidad) con los datos mapeados, o `null` si
     * el DTO de entrada es `null`.
     */
    public Topping toEntity(PersistenciaToppingDTO toppingDto);
}
