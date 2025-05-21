package DTOs.entradas;

import entidades.Entrada;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pablo
 */
public class EntradaMapperPersistencia implements IEntradaMapperPersistencia {

    IDetalleEntradaMapperPersistencia detalleEntradaMapper = DetalleEntradaMapperPersistencia.getInstance();
    private static EntradaMapperPersistencia instanceMapper;

    public static EntradaMapperPersistencia getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new EntradaMapperPersistencia();
        }
        return instanceMapper;
    }

    //--------------------------Nuevo----------------------------------------
    @Override
    public Entrada toEntityNuevo(EntradaNuevaDTOPersistencia entradaDTO) {
        Entrada entrada = new Entrada();
        entrada.setProveedor(entradaDTO.getProveedor());
        entrada.setFechaHora(entradaDTO.getFechaHora());   
        entrada.setPrecioTotal(entradaDTO.getPrecioTotal());
        entrada.setDetallesEntrada(detalleEntradaMapper.toEntityList(entradaDTO.getDetallesEntrada()));
        return entrada;
    }

    @Override
    public EntradaNuevaDTOPersistencia todtoNuevoPersistencia(Entrada entrada) {
        EntradaNuevaDTOPersistencia entradaDTO = new EntradaNuevaDTOPersistencia();
        entradaDTO.setFechaHora(entrada.getFechaHora());
        entradaDTO.setProveedor(entrada.getProveedor());
        entradaDTO.setPrecioTotal(entrada.getPrecioTotal());
        entradaDTO.setDetallesEntrada(detalleEntradaMapper.todtoPersistenciaList(entrada.getDetallesEntrada()));
        return entradaDTO;
    }

    //Listas
    @Override
    public List<Entrada> toEntityNuevoList(List<EntradaNuevaDTOPersistencia> entradasDTO) {
        List<Entrada> entradas = new ArrayList<>();
        for (EntradaNuevaDTOPersistencia entradasdto : entradasDTO) {
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
    public List<EntradaNuevaDTOPersistencia> todtoNuevoPersistenciaList(List<Entrada> entradas) {
        List<EntradaNuevaDTOPersistencia> entradasDTO = new ArrayList<>();
        for (Entrada entrada : entradas) {
            EntradaNuevaDTOPersistencia entradadto = new EntradaNuevaDTOPersistencia();
            entradadto.setFechaHora(entrada.getFechaHora());
            entradadto.setProveedor(entrada.getProveedor());
            entradadto.setPrecioTotal(entrada.getPrecioTotal());
            entradadto.setDetallesEntrada(detalleEntradaMapper.todtoPersistenciaList(entrada.getDetallesEntrada()));
            entradasDTO.add(entradadto);
        }
        return entradasDTO;
    }

    //--------------------------Viejo----------------------------------------
    @Override
    public Entrada toEntityViejo(EntradaViejaDTOPersistencia entradaDTO) {
        Entrada entrada = new Entrada();
        entrada.setFechaHora(entradaDTO.getFechaHora());
        entrada.setProveedor(entradaDTO.getProveedor());
        entrada.setPrecioTotal(entradaDTO.getPrecioTotal());
        entrada.setDetallesEntrada(detalleEntradaMapper.toEntityList(entradaDTO.getDetallesEntrada()));
        return entrada;
    }

    @Override
    public EntradaViejaDTOPersistencia todtoViejoPersistencia(Entrada entrada) {
        EntradaViejaDTOPersistencia entradaDTO = new EntradaViejaDTOPersistencia();
        entradaDTO.setFechaHora(entrada.getFechaHora());
        entradaDTO.setProveedor(entrada.getProveedor());
        entradaDTO.setPrecioTotal(entrada.getPrecioTotal());
        entradaDTO.setDetallesEntrada(detalleEntradaMapper.todtoPersistenciaList(entrada.getDetallesEntrada()));
        return entradaDTO;
    }

    //Listas
    @Override
    public List<Entrada> toEntityViejoList(List<EntradaViejaDTOPersistencia> entradasDTO) {
        List<Entrada> entradas = new ArrayList<>();
        for (EntradaViejaDTOPersistencia entradasdto : entradasDTO) {
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
    public List<EntradaViejaDTOPersistencia> todtoViejoPersistenciaList(List<Entrada> entradas) {
        List<EntradaViejaDTOPersistencia> entradasDTO = new ArrayList<>();
        for (Entrada entrada : entradas) {
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
