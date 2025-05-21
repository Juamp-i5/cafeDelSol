/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionSalidas;

import BOs.salidas.ISalidaBO;
import BOs.salidas.SalidaBO;
import DTOs.CRUDSalidas.SalidaNuevaDTO;
import excepciones.GestionCRUDSalidasException;
import excepciones.NegocioException;
import validadores.IValidadorGestorCRUDSalidas;

/**
 *
 * @author katia
 */
public class GestorCRUDSalidas implements IGestorCRUDSalidas{
    private static GestorCRUDSalidas instance;
    private final ISalidaBO salidaBO = new SalidaBO.getInstance();
    private final IValidadorGestorCRUDSalidas validador = new ValidadorGestorCRUDSalidas();
    
    
}
