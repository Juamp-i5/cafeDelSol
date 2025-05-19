/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.cubiculos;

import BOs.ingredientes.IIngredienteMapper;
import BOs.ingredientes.IngredienteBO;
import BOs.ingredientes.IngredienteMapper;
import IDAOs.cubiculos.IReservacionDAO;
import IDAOs.ingredientes.IIngredienteDAOMongo;
import acceso.AccesoDatos;

/**
 *
 * @author rodri
 */
public class ReservacionBO {

    IReservacionDAO reservacionDAO = AccesoDatos.getReservacionDAO();
}
