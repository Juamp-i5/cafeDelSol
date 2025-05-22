/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import org.bson.types.ObjectId;

/**
 * Clase que representa un proveedor en la base de datos.
 * @author Jp
 */
public class Proveedor {

    private ObjectId id;
    private String nombre;

    /**
     * Constructor por defecto
     */
    public Proveedor() {
    }

    /**
     * Constructor que inicializa todos los atributos de la clase.
     * @param id id del proveedor
     * @param nombre nombre del proveedor.
     */
    public Proveedor(ObjectId id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el id del proveedor.
     * @return id del proveedor.
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Establece el id del proveedor.
     * @param id id del proveedor.
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

     /**
     * Obtiene el nombre del proveedor.
     * @return nombre del proveedor.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del proveedor.
     * @param nombre nombre del proveedor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
