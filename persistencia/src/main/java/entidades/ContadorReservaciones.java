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
public class ContadorReservaciones {

    ObjectId id;
    Long secuencia;

    public ContadorReservaciones() {
    }

    public ContadorReservaciones(ObjectId id, Long secuencia) {
        this.id = id;
        this.secuencia = secuencia;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Long secuencia) {
        this.secuencia = secuencia;
    }

    @Override
    public String toString() {
        return "ContadorReservaciones{" + "id=" + id + ", secuencia=" + secuencia + '}';
    }

}
