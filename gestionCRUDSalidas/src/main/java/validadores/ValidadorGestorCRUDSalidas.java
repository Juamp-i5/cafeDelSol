/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validadores;

import DTOs.CRUDSalidas.SalidaNuevaDTO;
import excepciones.GestionCRUDSalidasException;
import java.time.LocalDate;

/**
 *
 * @author katia
 */
public class ValidadorGestorCRUDSalidas implements IValidadorGestorCRUDSalidas{

    @Override
    public void validarSalida(SalidaNuevaDTO salida) throws GestionCRUDSalidasException {
        if (salida == null) {
            throw new GestionCRUDSalidasException("La salida no puede ser nula.");
        }

        if (salida.getFecha() == null) {
            throw new GestionCRUDSalidasException("La fecha no puede ser nula.");
        }

        LocalDate hoy = LocalDate.now();
        if (salida.getFecha().isAfter(hoy) || salida.getFecha().isBefore(hoy.minusDays(2))) {
            throw new GestionCRUDSalidasException("La fecha debe ser hoy o máximo dos días antes.");
        }

        if (salida.getIdIngrediente() == null || salida.getIdIngrediente().isBlank()) {
            throw new GestionCRUDSalidasException("El ID del ingrediente es obligatorio.");
        }

        if (salida.getCantidad() == null || salida.getCantidad() <= 0) {
            throw new GestionCRUDSalidasException("La cantidad debe ser un número positivo.");
        }

        if (salida.getMotivo() == null) {
            throw new GestionCRUDSalidasException("Debe seleccionar un motivo de salida.");
        }
    }
}
