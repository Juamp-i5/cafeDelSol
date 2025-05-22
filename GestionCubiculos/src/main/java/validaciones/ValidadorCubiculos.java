/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import BOs.cubiculos.CubiculoBO;
import BOs.cubiculos.ICubiculoBO;
import BOs.cubiculos.IReservacionBO;
import BOs.cubiculos.ReservacionBO;
import DTOs.cubiculos.ReagendaDTO;
import DTOs.cubiculos.ReservacionCompletaDTO;
import DTOs.cubiculos.ReservacionDTOMostrar;
import DTOs.cubiculos.ReservacionNuevaDTO;
import excepciones.GestionCubiculosException;
import excepciones.NegocioCubiculoException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodri
 */
public class ValidadorCubiculos implements IValidadorCubiculos {

    private ICubiculoBO cubiculoBO = CubiculoBO.getInstance();
    private IReservacionBO reservacionBO = ReservacionBO.getInstance();

    @Override
    public void ValidarGuardarReservacion(ReservacionNuevaDTO reservacionNueva) throws GestionCubiculosException {
        List<String> errores = new ArrayList<>();

        // Validar nombre (no debe contener números)
        if (reservacionNueva.getNombre() == null || reservacionNueva.getNombre().matches(".*\\d.*")) {
            errores.add("El nombre no debe contener números.");
        }

        // Validar teléfono (debe tener solo 10 dígitos, sin espacios ni caracteres)
        String telefono = reservacionNueva.getTelefono();
        if (telefono == null || !telefono.matches("\\d{10}")) {
            errores.add("El teléfono debe contener exactamente 10 dígitos sin espacios ni caracteres.");
        }

        // Validar fecha de reservación (no puede ser anterior a hoy)
        LocalDate hoy = LocalDate.now();
        if (reservacionNueva.getFechaReserva() == null || reservacionNueva.getFechaReserva().isBefore(hoy)) {
            errores.add("La fecha de reservación no puede ser anterior al día de hoy.");
        }

        // Validar hora de inicio (entre 9:00 y 20:30)
        LocalTime horaInicio = reservacionNueva.getHoraInicio();
        if (horaInicio == null || horaInicio.isBefore(LocalTime.of(9, 0)) || horaInicio.isAfter(LocalTime.of(20, 30))) {
            errores.add("La hora de inicio debe estar entre 9:00 y 20:30.");
        }

        // Validar hora de fin (entre 9:30 y 21:00)
        LocalTime horaFin = reservacionNueva.getHoraFin();
        if (horaFin == null || horaFin.isBefore(LocalTime.of(9, 30)) || horaFin.isAfter(LocalTime.of(21, 0))) {
            errores.add("La hora de fin debe estar entre 9:30 y 21:00.");
        }

        // Validar que horaInicio < horaFin
        if (horaInicio != null && horaFin != null && !horaInicio.isBefore(horaFin)) {
            errores.add("La hora de inicio debe ser menor a la hora de fin.");
        }

        // Lanzar excepción con todos los errores si hay alguno
        if (!errores.isEmpty()) {
            throw new GestionCubiculosException(String.join("\n", errores));
        }
    }

    @Override
    public void ValidarReagendarReservacionCampos(ReagendaDTO reagenda) throws GestionCubiculosException {
        try {
            List<String> errores = new ArrayList<>();

            // Validar numReservacion (Debe ser numérico)
            String numero = reagenda.getNumReservacion();
            if (numero == null || !numero.matches("\\d+")) {
                errores.add("El numero de reservacion solo acepta valores numéricos.");
            }

            // Validar fecha de reservación (no puede ser anterior a hoy)
            LocalDate hoy = LocalDate.now();
            if (reagenda.getFechaNueva() == null || reagenda.getFechaNueva().isBefore(hoy)) {
                errores.add("La fecha de reservación no puede ser anterior al día de hoy.");
            }

            // Validar hora de inicio (entre 9:00 y 20:30)
            LocalTime horaInicio = reagenda.getHoraInicio();
            if (horaInicio == null || horaInicio.isBefore(LocalTime.of(9, 0)) || horaInicio.isAfter(LocalTime.of(20, 30))) {
                errores.add("La hora de inicio debe estar entre 9:00 y 20:30.");
            }

            List<ReservacionDTOMostrar> listaValidacion;

            listaValidacion = reservacionBO.obtenerReservacionesHistorial(null, null);
            Integer numReservacionViejo = Integer.valueOf(reagenda.getNumReservacion());

            //Validar que la reservación original exista y su estado sea PENDIENTE
            Optional<ReservacionDTOMostrar> reservacionEncontrada = listaValidacion.stream()
                    .filter(r -> r.getNumReservacion().equals(numReservacionViejo))
                    .findFirst();

            if (!reservacionEncontrada.isPresent()) {
                errores.add("El número de reservación proporcionado no existe.");
            } else {
                String estado = reservacionEncontrada.get().getEstado();
                if (!"PENDIENTE".equalsIgnoreCase(estado)) {
                    errores.add("Solo se puede reagendar una reservación con estado 'PENDIENTE'.");
                }
            }

            // Lanzar excepción con todos los errores si hay alguno
            if (!errores.isEmpty()) {
                throw new GestionCubiculosException(String.join("\n", errores));
            }

        } catch (NegocioCubiculoException ex) {
            Logger.getLogger(ValidadorCubiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ValidarChoqueHorarios(ReagendaDTO reagenda, LocalTime horaFinNueva) throws GestionCubiculosException {
        try {
            List<ReservacionDTOMostrar> listaReservaciones = reservacionBO.obtenerReservacionesPendientes(null, null);

            for (ReservacionDTOMostrar reservacion : listaReservaciones) {
                // Ignorar si no es el mismo cubículo
                if (!reservacion.getNombreCubiculo().equalsIgnoreCase(reagenda.getNombreCubiculo())) {
                    continue;
                }

                // Ignorar si no es la misma fecha
                if (!reservacion.getFechaInicio().equals(reagenda.getFechaNueva())) {
                    continue;
                }

                // Ignorar si es la misma reservación
                if (reservacion.getNumReservacion().equals(reagenda.getNumReservacion())) {
                    continue;
                }

                // Validar cruce de horarios
                LocalTime inicioExistente = reservacion.getHoraInicio();
                LocalTime finExistente = reservacion.getHoraFin();
                LocalTime inicioNuevo = reagenda.getHoraInicio();

                boolean hayChoque = inicioNuevo.isBefore(finExistente) && horaFinNueva.isAfter(inicioExistente);
                if (hayChoque) {
                    throw new GestionCubiculosException("La nueva hora de la reservación choca con otra ya existente:\n"
                            + "Reservación:" + reservacion.getNumReservacion() + " de " + inicioExistente + " a " + finExistente);
                }
            }
        } catch (NegocioCubiculoException e) {
            throw new GestionCubiculosException("Error al validar cruce de horarios: " + e.getMessage(), e);
        }
    }

    @Override
    public void ValidarChoqueHorariosResNueva(ReservacionNuevaDTO reservacionNueva) throws GestionCubiculosException {
        try {
            List<ReservacionDTOMostrar> listaReservaciones = reservacionBO.obtenerReservacionesPendientes(null, null);

            for (ReservacionDTOMostrar reservacion : listaReservaciones) {
                // Ignorar si no es el mismo cubículo
                if (!reservacion.getNombreCubiculo().equalsIgnoreCase(reservacionNueva.getNombreCubiculo())) {
                    continue;
                }

                // Ignorar si no es la misma fecha
                if (!reservacion.getFechaInicio().equals(reservacionNueva.getFechaReserva())) {
                    continue;
                }

                // Validar cruce de horarios
                LocalTime inicioExistente = reservacion.getHoraInicio();
                LocalTime finExistente = reservacion.getHoraFin();
                LocalTime inicioNuevo = reservacionNueva.getHoraInicio();
                LocalTime finNuevo = reservacionNueva.getHoraFin();

                boolean hayChoque = inicioNuevo.isBefore(finExistente) && finNuevo.isAfter(inicioExistente);
                if (hayChoque) {
                    throw new GestionCubiculosException("La nueva hora de la reservación choca con otra ya existente:\n"
                            + "Reservación:" + reservacion.getNumReservacion() + " de " + inicioExistente + " a " + finExistente);
                }
            }
        } catch (NegocioCubiculoException e) {
            throw new GestionCubiculosException("Error al validar cruce de horarios: " + e.getMessage(), e);
        }
    }

    @Override
    public void ValidarIniciarReservacion(Integer numReservacion) throws GestionCubiculosException {
        try {
            ReservacionCompletaDTO dtoComp = reservacionBO.buscarPorId(numReservacion);

            LocalDateTime fechaHoraInicioReservacion = dtoComp.getFechaReserva().atTime(dtoComp.getHoraInicio());
            LocalDateTime ahora = LocalDateTime.now();

            if (fechaHoraInicioReservacion.isAfter(ahora)) {
                throw new GestionCubiculosException("No se puede iniciar la reservación antes del horario establecido.");
            }

        } catch (NegocioCubiculoException ex) {
            throw new GestionCubiculosException("Error al obtener los datos de la reservación.", ex);
        }
    }

}
