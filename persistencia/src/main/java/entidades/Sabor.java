/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import org.bson.types.ObjectId;

/**
 *
 * @author rodri
 */
public class Sabor {

    private ObjectId id;
    private String nombre;
    private byte[] imagenData;

    public Sabor() {
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

    public byte[] getImagenData() {
        return imagenData;
    }

    public void setImagenData(byte[] imagenData) {
        this.imagenData = imagenData;
    }

}