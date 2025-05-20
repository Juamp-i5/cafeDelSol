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
import DTOs.cubiculos.CubiculoCompletoDTOPersistencia;
import DTOs.cubiculos.EfectivoDTOCubiculo;
import DTOs.cubiculos.ReagendaDTO;
import DTOs.cubiculos.ReservacionCompletaDTO;
import DTOs.cubiculos.ReservacionNuevaDTO;
import excepciones.GestionCubiculosException;
import excepciones.NegocioCubiculoException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodri
 */
public class GestorCubiculos implements IGestorCubiculos {

    private static GestorCubiculos instance;
    private ReservacionNuevaDTO reservacionNueva;
    private ICubiculoBO cubiculoBO = CubiculoBO.getInstance();
    private IReservacionBO reservacionBO = ReservacionBO.getInstance();
    private IContadorBO contadorBO = ContadorBO.getInstance();

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
    public void setReservacionNueva(ReservacionNuevaDTO reservacionNueva) {
        this.reservacionNueva = reservacionNueva;
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
    public Integer realizarReagenda(ReagendaDTO reagenda) throws GestionCubiculosException {
        try {
            CubiculoCompletoDTO cubiculo;
            Integer numeroReservacion;
            ReservacionCompletaDTO dtoNueva;
            cubiculo = cubiculoBO.obtenerPorNombre(reagenda.getNombreCubiculo());
            numeroReservacion = contadorBO.obtenerContador();
            ReservacionCompletaDTO dtoViejo = reservacionBO.buscarPorId(reagenda.getNumReservacion());

            Duration duracion = Duration.between(dtoViejo.getHoraInicio(), dtoViejo.getHoraFin());
            LocalTime horaFinNueva = reagenda.getHoraInicio().plus(duracion);
            
            dtoNueva = new ReservacionCompletaDTO();
            dtoNueva.setNumReservacion(numeroReservacion);
            dtoNueva.setNombre(dtoViejo.getNombre());
            dtoNueva.setTelefono(dtoViejo.getTelefono());
            dtoNueva.setNombreCubiculo(reagenda.getNombreCubiculo());
            dtoNueva.setFechaReserva(reagenda.getFechaNueva());
            dtoNueva.setHoraInicio(reagenda.getHoraInicio());
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
}
