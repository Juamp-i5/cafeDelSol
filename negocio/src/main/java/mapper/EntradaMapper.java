package mapper;

import DTOs.CRUDEntradas.EntradaNuevaDTO;
import DTOs.CRUDEntradas.EntradaViejaDTO;
import entidades.Entrada;
import interfacesMapper.IDetalleEntradaMapper;
import interfacesMapper.IEntradaMapper;
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
    public Entrada toEntityNuevo(EntradaNuevaDTO entradaDTO) {
        Entrada entrada = new Entrada();
        entrada.setProveedor(entradaDTO.getProveedor());
        entrada.setFechaHora(entradaDTO.getFechaHora());   
        entrada.setPrecioTotal(entradaDTO.getPrecioTotal());
        entrada.setDetallesEntrada(detalleEntradaMapper.toEntityList(entradaDTO.getDetallesEntrada()));
        return entrada;
    }

    @Override
    public EntradaNuevaDTO todtoNuevo(Entrada entrada) {
        EntradaNuevaDTO entradaDTO = new EntradaNuevaDTO();
        entradaDTO.setFechaHora(entrada.getFechaHora());
        entradaDTO.setProveedor(entrada.getProveedor());
        entradaDTO.setPrecioTotal(entrada.getPrecioTotal());
        entradaDTO.setDetallesEntrada(detalleEntradaMapper.todtoList(entrada.getDetallesEntrada()));
        return entradaDTO;
    }

    //Listas
    @Override
    public List<Entrada> toEntityNuevoList(List<EntradaNuevaDTO> entradasDTO) {
        List<Entrada> entradas = new ArrayList<>();
        for (EntradaNuevaDTO entradasdto : entradasDTO) {
            Entrada entrada = new Entrada();
            entrada.setFechaHora(entradasdto.getFechaHora());
            entrada.setProveedor(entradasdto.getProveedor());
            entrada.setPrecioTotal(entradasdto.getPrecioTotal());
            entrada.setDetallesEntrada(detalleEntradaMapper.toEntityList(entradasdto.getDetallesEntrada()));
            entradas.add(entrada);
        }
        return entradas;
    }

    @Override
    public List<EntradaNuevaDTO> todtoNuevoList(List<Entrada> entradas) {
        List<EntradaNuevaDTO> entradasDTO = new ArrayList<>();
        for (Entrada entrada : entradas) {
            EntradaNuevaDTO entradadto = new EntradaNuevaDTO();
            entradadto.setFechaHora(entrada.getFechaHora());
            entradadto.setProveedor(entrada.getProveedor());
            entradadto.setPrecioTotal(entrada.getPrecioTotal());
            entradadto.setDetallesEntrada(detalleEntradaMapper.todtoList(entrada.getDetallesEntrada()));
            entradasDTO.add(entradadto);
        }
        return entradasDTO;
    }

    //--------------------------Viejo----------------------------------------
    @Override
    public Entrada toEntityViejo(EntradaViejaDTO entradaDTO) {
        Entrada entrada = new Entrada();
        entrada.setFechaHora(entradaDTO.getFechaHora());
        entrada.setProveedor(entradaDTO.getProveedor());
        entrada.setPrecioTotal(entradaDTO.getPrecioTotal());
        entrada.setDetallesEntrada(detalleEntradaMapper.toEntityList(entradaDTO.getDetallesEntrada()));
        return entrada;
    }

    @Override
    public EntradaViejaDTO todtoViejo(Entrada entrada) {
        EntradaViejaDTO entradaDTO = new EntradaViejaDTO();
        entradaDTO.setFechaHora(entrada.getFechaHora());
        entradaDTO.setProveedor(entrada.getProveedor());
        entradaDTO.setPrecioTotal(entrada.getPrecioTotal());
        entradaDTO.setDetallesEntrada(detalleEntradaMapper.todtoList(entrada.getDetallesEntrada()));
        return entradaDTO;
    }

    //Listas
    @Override
    public List<Entrada> toEntityViejoList(List<EntradaViejaDTO> entradasDTO) {
        List<Entrada> entradas = new ArrayList<>();
        for (EntradaViejaDTO entradasdto : entradasDTO) {
            Entrada entrada = new Entrada();
            entrada.setFechaHora(entradasdto.getFechaHora());
            entrada.setProveedor(entradasdto.getProveedor());
            entrada.setPrecioTotal(entradasdto.getPrecioTotal());
            entrada.setDetallesEntrada(detalleEntradaMapper.toEntityList(entradasdto.getDetallesEntrada()));
            entradas.add(entrada);
        }
        return entradas;
    }

    @Override
    public List<EntradaViejaDTO> todtoViejoList(List<Entrada> entradas) {
        List<EntradaViejaDTO> entradasDTO = new ArrayList<>();
        for (Entrada entrada : entradas) {
            EntradaViejaDTO entradadto = new EntradaViejaDTO();
            entradadto.setFechaHora(entrada.getFechaHora());
            entradadto.setProveedor(entrada.getProveedor());
            entradadto.setPrecioTotal(entrada.getPrecioTotal());
            entradadto.setDetallesEntrada(detalleEntradaMapper.todtoList(entrada.getDetallesEntrada()));
            entradasDTO.add(entradadto);
        }
        return entradasDTO;
    }
}
