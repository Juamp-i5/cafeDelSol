/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.MotivoEnum;
import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 *
 * @author katia
 */
public class Salida {
    private ObjectId id;
    private LocalDate fecha;
    private ObjectId idIngrediente;
    private Double cantidad;
    private MotivoEnum motivo;
    
    public Salida() {
    }

    public Salida(ObjectId id, LocalDate fechaHora, ObjectId idIngrediente, Double cantidad, MotivoEnum motivo) {
        this.id = id;
        this.fecha = fechaHora;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
        this.motivo = motivo;
    }

    public Salida(LocalDate fechaHora, ObjectId idIngrediente, Double cantidad, MotivoEnum motivo) {
        this.fecha = fechaHora;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
        this.motivo = motivo;
    }

    public ObjectId getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public MotivoEnum getMotivo() {
        return motivo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public ObjectId getIdIngrediente() {
        return idIngrediente;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMotivo(MotivoEnum motivo) {
        this.motivo = motivo;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public void setIdIngrediente(ObjectId idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    
}
