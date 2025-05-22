/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion;

import BOs.cubiculos.ContadorBO;
import BOs.cubiculos.CubiculoBO;
import BOs.cubiculos.IContadorBO;
import BOs.cubiculos.ICubiculoBO;
import BOs.cubiculos.IReservacionBO;
import BOs.cubiculos.ReservacionBO;
import DTOs.cubiculos.CubiculoCompletoDTO;
import DTOs.cubiculos.EfectivoDTOCubiculo;
import DTOs.cubiculos.ReagendaDTO;
import DTOs.cubiculos.ReservacionCompletaDTO;
import DTOs.cubiculos.ReservacionDTOMostrar;
import DTOs.cubiculos.ReservacionDetalleDTO;
import DTOs.cubiculos.ReservacionNuevaDTO;
import excepciones.GestionCubiculosException;
import excepciones.NegocioCubiculoException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import validaciones.IValidadorCubiculos;
import validaciones.ValidadorCubiculos;

/**
 *
 * @author rodri
 */
public class GestorCubiculos implements IGestorCubiculos {

    private static GestorCubiculos instance;
    private ReservacionNuevaDTO reservacionNueva;
    private ReagendaDTO reagendaDTO;
    private ICubiculoBO cubiculoBO = CubiculoBO.getInstance();
    private IReservacionBO reservacionBO = ReservacionBO.getInstance();
    private IContadorBO contadorBO = ContadorBO.getInstance();
    private IValidadorCubiculos validador = new ValidadorCubiculos();

    private GestorCubiculos() {
    }

    public static GestorCubiculos getInstance() {
        if (instance == null) {
            instance = new GestorCubiculos();
        }
        return instance;
    }

    @Override
    public List<String> obtenerCubiculos() throws GestionCubiculosException {
        try {
            return cubiculoBO.obtenerCubiculos();
        } catch (NegocioCubiculoException ex) {
            Logger.getLogger(GestorCubiculos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionCubiculosException("Error al cargar cubiculos");
        }
    }

    @Override
    public CubiculoCompletoDTO obtenerPorNombre(String nombre) throws GestionCubiculosException {
        try {
            return cubiculoBO.obtenerPorNombre(nombre);
        } catch (NegocioCubiculoException ex) {
            Logger.getLogger(GestorCubiculos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionCubiculosException("Error al buscar cubículo por nombre");
        }
    }

    @Override
    public ReservacionNuevaDTO getReservacionNueva() {
        return reservacionNueva;
    }

    @Override
    public boolean setReservacionNueva(ReservacionNuevaDTO reservacionNuevaParam) throws GestionCubiculosException {
        try {
            validador.ValidarGuardarReservacion(reservacionNuevaParam);
            this.reservacionNueva = reservacionNuevaParam;
            validador.ValidarChoqueHorariosResNueva(reservacionNueva);
            return true;
        } catch (GestionCubiculosException ex) {
            Logger.getLogger(GestorCubiculos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionCubiculosException("Datos inválidos");
        }
    }

    @Override
    public LocalTime setReagendaNueva(ReagendaDTO reagenda) throws GestionCubiculosException {
        try {
            validador.ValidarReagendarReservacionCampos(reagenda);
            this.reagendaDTO = reagenda;
            
            Integer numReservacionViejo = Integer.valueOf(reagendaDTO.getNumReservacion());
            ReservacionCompletaDTO dtoViejo = reservacionBO.buscarPorId(numReservacionViejo);
            Duration duracion = Duration.between(dtoViejo.getHoraInicio(), dtoViejo.getHoraFin());
            LocalTime horaFinNueva = reagendaDTO.getHoraInicio().plus(duracion);
            
            validador.ValidarChoqueHorarios(reagendaDTO,horaFinNueva);
            
            return horaFinNueva;
        } catch (GestionCubiculosException ex) {
            Logger.getLogger(GestorCubiculos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionCubiculosException("Datos inválidos" + ex.getMessage());
        } catch (NegocioCubiculoException ex) {
            Logger.getLogger(GestorCubiculos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionCubiculosException("Error al obtener la referencia de la reservación anterior");
        }
    }

    @Override
    public double calcularCambio(EfectivoDTOCubiculo efectivoDTO) {
        if (efectivoDTO.getCantidadIngresada() >= reservacionNueva.getPrecioReservacion()) {
            return efectivoDTO.getCantidadIngresada() - reservacionNueva.getPrecioReservacion();
        } else {
            return -1;
        }
    }

    @Override
    public Integer realizarReservacion() throws GestionCubiculosException {

        try {
            CubiculoCompletoDTO cubiculo;
            Integer numeroReservacion;
            cubiculo = cubiculoBO.obtenerPorNombre(reservacionNueva.getNombreCubiculo());
            numeroReservacion = contadorBO.obtenerContador();

            ReservacionCompletaDTO dtoCompleto = new ReservacionCompletaDTO();
            dtoCompleto.setNumReservacion(numeroReservacion);
            dtoCompleto.setNombre(reservacionNueva.getNombre());
            dtoCompleto.setTelefono(reservacionNueva.getTelefono());
            dtoCompleto.setNombreCubiculo(reservacionNueva.getNombreCubiculo());
            dtoCompleto.setFechaReserva(reservacionNueva.getFechaReserva());
            dtoCompleto.setHoraInicio(reservacionNueva.getHoraInicio());
            dtoCompleto.setHoraFin(reservacionNueva.getHoraFin());
            dtoCompleto.setIdCubiculo(cubiculo.getId());
            dtoCompleto.setPrecioHora(reservacionNueva.getPrecioHora());
            dtoCompleto.setPrecioReservacion(reservacionNueva.getPrecioReservacion());

            reservacionBO.agregarReservacion(dtoCompleto);
            return numeroReservacion;
        } catch (NegocioCubiculoException ex) {
            Logger.getLogger(GestorCubiculos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionCubiculosException("Error al registrar la reservación");
        }
    }

    @Override
    public Integer realizarReagenda(LocalTime horaFinNueva) throws GestionCubiculosException {
        try {
            CubiculoCompletoDTO cubiculo;
            Integer numeroReservacion;

            cubiculo = cubiculoBO.obtenerPorNombre(reagendaDTO.getNombreCubiculo());
            Integer numReservacionViejo = Integer.valueOf(reagendaDTO.getNumReservacion());
            ReservacionCompletaDTO dtoViejo = reservacionBO.buscarPorId(numReservacionViejo);
            numeroReservacion = contadorBO.obtenerContador();

            ReservacionCompletaDTO dtoNueva = new ReservacionCompletaDTO();
            dtoNueva.setNumReservacion(numeroReservacion);
            dtoNueva.setNombre(dtoViejo.getNombre());
            dtoNueva.setTelefono(dtoViejo.getTelefono());
            dtoNueva.setNombreCubiculo(reagendaDTO.getNombreCubiculo());
            dtoNueva.setFechaReserva(reagendaDTO.getFechaNueva());
            dtoNueva.setHoraInicio(reagendaDTO.getHoraInicio());
            dtoNueva.setHoraFin(horaFinNueva);
            dtoNueva.setIdCubiculo(cubiculo.getId());
            dtoNueva.setPrecioHora(dtoViejo.getPrecioHora());
            dtoNueva.setPrecioReservacion(dtoViejo.getPrecioReservacion());

            reservacionBO.agregarReservacion(dtoNueva);
            return numeroReservacion;
        } catch (NegocioCubiculoException ex) {
            Logger.getLogger(GestorCubiculos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionCubiculosException("Error al registar la reagenda");
        }

    }

    @Override
    public boolean modificarReservacion(Integer numReservacion, Integer numReservacionNueva, String motivo) throws GestionCubiculosException {
        try {
            reservacionBO.modificarReservacion(numReservacion, numReservacionNueva, motivo, LocalDateTime.now());
            return true;
        } catch (NegocioCubiculoException ex) {
            Logger.getLogger(GestorCubiculos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionCubiculosException("Error al modificar la reservcion");
        }
    }

    @Override
    public List<ReservacionDTOMostrar> obtenerReservacionesPendientes(LocalDate fechaInicio, LocalDate fechaFin) throws GestionCubiculosException {
        try {
            return reservacionBO.obtenerReservacionesPendientes(fechaInicio, fechaFin);
        } catch (NegocioCubiculoException ex) {
            Logger.getLogger(GestorCubiculos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionCubiculosException("Error al cargar reservaciones sin concluir");
        }
    }

    @Override
    public List<ReservacionDTOMostrar> obtenerReservacionesHistorial(LocalDate fechaInicio, LocalDate fechaFin) throws GestionCubiculosException {
        try {
            return reservacionBO.obtenerReservacionesHistorial(fechaInicio, fechaFin);
        } catch (NegocioCubiculoException ex) {
            Logger.getLogger(GestorCubiculos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionCubiculosException("Error al cargar reservaciones sin concluir");
        }
    }

    @Override
    public boolean actualizarEstado(Integer numReservacion, String estado) throws GestionCubiculosException {
        try {
            validador.ValidarIniciarReservacion(numReservacion);
            reservacionBO.actualizarEstado(numReservacion, estado);
            return true; 
        } catch (NegocioCubiculoException ex) {
            Logger.getLogger(GestorCubiculos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionCubiculosException("Error al actualizar el estado de la reservacion");
        }
    }

    @Override
    public ReservacionDetalleDTO getDetalleReservacion(Integer numReservacion) throws GestionCubiculosException {
        try {
            return reservacionBO.getDetalleReservacion(numReservacion);
        } catch (NegocioCubiculoException ex) {
            Logger.getLogger(GestorCubiculos.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestionCubiculosException("Error al obtener detalle de la reservacion");
        }
    }
}
