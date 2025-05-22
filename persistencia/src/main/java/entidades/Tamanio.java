/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import org.bson.types.ObjectId;

/**
 * Representa la entidad de un tamaño de producto en la base de datos.
 * Contiene información como el identificador único, nombre, precio adicional
 * asociado a este tamaño y datos de imagen.
 *
 * @author rodri
 */
public class Tamanio {

    /**
     * Identificador único del tamaño.
     */
    private ObjectId id;

    /**
     * Nombre descriptivo del tamaño (ej. "Grande", "Mediano").
     */
    private String nombre;

    /**
     * Precio adicional que se suma al precio base del producto cuando se
     * selecciona este tamaño.
     */
    private Double precioAdicional;

    /**
     * Datos binarios de la imagen asociada al tamaño, si aplica.
     */
    private byte[] imagenData;


    public Tamanio() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecioAdicional() {
        return precioAdicional;
    }

    public void setPrecioAdicional(Double precioAdicional) {
        this.precioAdicional = precioAdicional;
    }

    public byte[] getImagenData() {
        return imagenData;
    }

    public void setImagenData(byte[] imagenData) {
        this.imagenData = imagenData;
    }

}
