/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import entidades.Cancelacion;
import entidades.Reservacion;

/**
 *
 * @author rodri
 */
public interface ICancelacionDAO {
    
    public Cancelacion cancelarReservacion(Reservacion reservacion);
}
