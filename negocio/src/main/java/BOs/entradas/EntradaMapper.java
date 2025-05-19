package BOs.entradas;

import BOs.entradas.DetalleEntradaMapper;
import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import entidades.Entrada;
import BOs.entradas.IDetalleEntradaMapper;
import BOs.entradas.IEntradaMapper;
import DTOs.entradas.DetalleEntradaMapperPersistencia;
import DTOs.entradas.EntradaNuevaDTOPersistencia;
import DTOs.entradas.EntradaViejaDTOPersistencia;
import DTOs.entradas.IDetalleEntradaMapperPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pablo
 */
public class EntradaMapper implements IEntradaMapper {

    IDetalleEntradaMapper detalleEntradaMapper = DetalleEntradaMapper.getInstance();
    private static EntradaMapper instanceMapper;

    public static EntradaMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new EntradaMapper();
        }
        return instanceMapper;
    }

    //--------------------------Nuevo----------------------------------------
    @Override
    public EntradaNuevaDTO toDtoNuevo(EntradaNuevaDTOPersistencia entradaDTO) {
        EntradaNuevaDTO entrada = new EntradaNuevaDTO();
        entrada.setProveedor(entradaDTO.getProveedor());
        entrada.setFechaHora(entradaDTO.getFechaHora());
        entrada.setPrecioTotal(entradaDTO.getPrecioTotal());
        entrada.setDetallesEntrada(detalleEntradaMapper.todtoList(entradaDTO.getDetallesEntrada()));
        return entrada;
    }

    @Override
    public EntradaNuevaDTOPersistencia toDtoNuevoPersistencia(EntradaNuevaDTO entrada) {
        EntradaNuevaDTOPersistencia entradaDTO = new EntradaNuevaDTOPersistencia();
        entradaDTO.setProveedor(entrada.getProveedor());
        entradaDTO.setFechaHora(entrada.getFechaHora());
        entradaDTO.setPrecioTotal(entrada.getPrecioTotal());
        entradaDTO.setDetallesEntrada(detalleEntradaMapper.todtoPersistenciaList(entrada.getDetallesEntrada()));
        return entradaDTO;
    }

// Listas
    @Override
    public List<EntradaNuevaDTO> toDtoNuevoList(List<EntradaNuevaDTOPersistencia> entradasDTO) {
        List<EntradaNuevaDTO> entradas = new ArrayList<>();
        for (EntradaNuevaDTOPersistencia entradaDTO : entradasDTO) {
            entradas.add(toDtoNuevo(entradaDTO));
        }
        return entradas;
    }

    @Override
    public List<EntradaNuevaDTOPersistencia> toDtoNuevoPersistenciaList(List<EntradaNuevaDTO> entradas) {
        List<EntradaNuevaDTOPersistencia> entradasDTO = new ArrayList<>();
        for (EntradaNuevaDTO entrada : entradas) {
            entradasDTO.add(toDtoNuevoPersistencia(entrada));
        }
        return entradasDTO;
    }

    //--------------------------Viejo----------------------------------------
    @Override
    public EntradaViejaDTO todtoViejo(EntradaViejaDTOPersistencia entradaDTO) {
        EntradaViejaDTO entrada = new EntradaViejaDTO();
        entrada.setFechaHora(entradaDTO.getFechaHora());
        entrada.setProveedor(entradaDTO.getProveedor());
        entrada.setPrecioTotal(entradaDTO.getPrecioTotal());
        entrada.setDetallesEntrada(detalleEntradaMapper.todtoList(entradaDTO.getDetallesEntrada()));
        return entrada;
    }

    @Override
    public EntradaViejaDTOPersistencia todtoViejoPersistencia(EntradaViejaDTO entrada) {
        EntradaViejaDTOPersistencia entradaDTO = new EntradaViejaDTOPersistencia();
        entradaDTO.setFechaHora(entrada.getFechaHora());
        entradaDTO.setProveedor(entrada.getProveedor());
        entradaDTO.setPrecioTotal(entrada.getPrecioTotal());
        entradaDTO.setDetallesEntrada(detalleEntradaMapper.todtoPersistenciaList(entrada.getDetallesEntrada()));
        return entradaDTO;
    }

    //Listas
    @Override
    public List<EntradaViejaDTO> todtoViejoList(List<EntradaViejaDTOPersistencia> entradasDTO) {
        List<EntradaViejaDTO> entradas = new ArrayList<>();
        for (EntradaViejaDTOPersistencia entradasdto : entradasDTO) {
            EntradaViejaDTO entrada = new EntradaViejaDTO();
            entrada.setFechaHora(entradasdto.getFechaHora());
            entrada.setProveedor(entradasdto.getProveedor());
            entrada.setPrecioTotal(entradasdto.getPrecioTotal());
            entrada.setDetallesEntrada(detalleEntradaMapper.todtoList(entradasdto.getDetallesEntrada()));
            entradas.add(entrada);
        }
        return entradas;
    }

    @Override
    public List<EntradaViejaDTOPersistencia> todtoViejoPersistenciaList(List<EntradaViejaDTO> entradas) {
        List<EntradaViejaDTOPersistencia> entradasDTO = new ArrayList<>();
        for (EntradaViejaDTO entrada : entradas) {
            EntradaViejaDTOPersistencia entradadto = new EntradaViejaDTOPersistencia();
            entradadto.setFechaHora(entrada.getFechaHora());
            entradadto.setProveedor(entrada.getProveedor());
            entradadto.setPrecioTotal(entrada.getPrecioTotal());
            entradadto.setDetallesEntrada(detalleEntradaMapper.todtoPersistenciaList(entrada.getDetallesEntrada()));
            entradasDTO.add(entradadto);
        }
        return entradasDTO;
    }
}
