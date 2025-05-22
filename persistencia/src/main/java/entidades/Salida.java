/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.MotivoEnum;
import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 * Entidad que representa una salida de inventario en el sistema.
 * Una salida ocurre cuando se retira cierta cantidad de un ingrediente del inventario por motivos como
 * robo, caducidad o deterioro. 
 * Cada salida está asociada a un ingrediente específico.
 * @author katia
 */
public class Salida {

    private ObjectId id;
    private LocalDate fecha;
    private ObjectId idIngrediente;
    private Double cantidad;
    private MotivoEnum motivo;
    
    /**
     * Constructor por defecto.
     */
    public Salida() {
    }

    /**
     * Constructor completo que incluye el ID.
     * Usado para cargar salidas existentes.
     * @param id Identificador único.
     * @param fechaHora Fecha de la salida.
     * @param idIngrediente ID del ingrediente afectado.
     * @param cantidad Cantidad retirada.
     * @param motivo Motivo de la salida.
     */
    public Salida(ObjectId id, LocalDate fechaHora, ObjectId idIngrediente, Double cantidad, MotivoEnum motivo) {
        this.id = id;
        this.fecha = fechaHora;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
        this.motivo = motivo;
    }

    /**
     * Constructor sin ID.
     * Usado para crear nuevas salidas antes de guardarlas.
     * @param fechaHora Fecha de la salida.
     * @param idIngrediente ID del ingrediente afectado.
     * @param cantidad Cantidad retirada.
     * @param motivo Motivo de la salida.
     */
    public Salida(LocalDate fechaHora, ObjectId idIngrediente, Double cantidad, MotivoEnum motivo) {
        this.fecha = fechaHora;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
        this.motivo = motivo;
    }

    /**
     * 
     * @return Identificador único del documento. 
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * 
     * @return Fecha de la salida.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * 
     * @return Motivo de la salida.
     */
    public MotivoEnum getMotivo() {
        return motivo;
    }

    /**
     * 
     * @return  Cantidad que sale.
     */
    public Double getCantidad() {
        return cantidad;
    }

    /**
     * 
     * @return Identificador del ingrediente afectado.
     */
    public ObjectId getIdIngrediente() {
        return idIngrediente;
    }

    /**
     * Establece el ID del documento (normalmente asignado por MongoDB).
     * @param id Identificador del documento.
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Establece la fecha en que se realizó la salida.
     * @param fecha Fecha de salida.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    
    /**
     * Establece el motivo por el cual se realiza la salida.
     * @param motivo Motivo de la salida.
     */
    public void setMotivo(MotivoEnum motivo) {
        this.motivo = motivo;
    }

    /**
     * Establece la cantidad retirada del inventario.
     * @param cantidad Cantidad positiva.
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Establece el ID del ingrediente relacionado con la salida.
     * @param idIngrediente Identificador del ingrediente.
     */
    public void setIdIngrediente(ObjectId idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

}
