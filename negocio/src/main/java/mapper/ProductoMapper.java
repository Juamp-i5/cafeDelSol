/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDIngredientes.NivelStock;
import DTOs.CRUDIngredientes.UnidadMedida;
import DTOs.CRUDProductos.CategoriaProducto;
import DTOs.CRUDProductos.DetallesProductoDTO;
import DTOs.CRUDProductos.EstadoProducto;
import DTOs.CRUDProductos.ProductoCreateDTO;
import DTOs.CRUDProductos.ProductoListDTO;
import DTOs.CRUDProductos.TamanioProducto;
import DTOs.PersistenciaProductoDTO;
import DTOs.PersistenciaProductoTamanioDTO;
import DTOs.PersistenciaProductoTamanioIngredienteDTO;
import DTOs.PersistenciaTamanioDTO;
import DTOs.ProductoMostrarDTO;
import DTOs.ingredientes.IngredienteDTOPersistencia;
import interfacesMapper.IProductoMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author norma
 */
public class ProductoMapper implements IProductoMapper {

    private static ProductoMapper instanceMapper;

    private ProductoMapper() {
    }

    public static ProductoMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new ProductoMapper();
        }
        return instanceMapper;
    }

    @Override
    public ProductoMostrarDTO toProductoMostrarDTO(PersistenciaProductoDTO productoDTO) {
        if (productoDTO == null) {
            return null;
        }

        ProductoMostrarDTO dtoMostrar = new ProductoMostrarDTO();
        dtoMostrar.setNombre(productoDTO.getNombre());
        dtoMostrar.setPrecio(productoDTO.getPrecioBase());
        dtoMostrar.setImagenData(productoDTO.getImageData());

        return dtoMostrar;
    }

    @Override
    public ProductoListDTO toProductoListDTO(PersistenciaProductoDTO productoDTO) {
        if (productoDTO == null) {
            return null;
        }

        ProductoListDTO listDTO = new ProductoListDTO();

        listDTO.setId(productoDTO.getId());
        listDTO.setNombre(productoDTO.getNombre());
        listDTO.setCategoria(mapStringToCategoriaProducto(productoDTO.getCategoria()));
        listDTO.setEstadoProducto(mapStringToEstadoProducto(productoDTO.getEstado()));

        Map<TamanioProducto, Double> preciosMap = new HashMap<>();
        if (productoDTO.getTamanios() != null) {
            for (PersistenciaProductoTamanioDTO ptDTO : productoDTO.getTamanios()) {
                if (ptDTO != null && ptDTO.getTamanio() != null) {
                    PersistenciaTamanioDTO infoTamanio = ptDTO.getTamanio();
                    String nombreTamanioStr = infoTamanio.getNombre();

                    TamanioProducto tamanioEnum = mapStringToTamanioProducto(nombreTamanioStr);

                    if (tamanioEnum != null) {
                        double precioCalculado = productoDTO.getPrecioBase() + infoTamanio.getPrecioAdicional();
                        preciosMap.put(tamanioEnum, precioCalculado);
                    }
                }
            }
        }
        listDTO.setPrecios(preciosMap);

        return listDTO;
    }

    @Override
    public DetallesProductoDTO toDetallesProductoDTO(PersistenciaProductoDTO pDTO) {
        if (pDTO == null) {
            return null;
        }

        DetallesProductoDTO dto = new DetallesProductoDTO();

        dto.setId(pDTO.getId());
        dto.setNombre(pDTO.getNombre());
        dto.setDescipcion(pDTO.getDescripcion()); // Nota: 'descipcion' tiene una 'i' extra en tu DTO
        dto.setImagenData(pDTO.getImageData());

        dto.setCategoria(mapStringToCategoriaProducto(pDTO.getCategoria()));
        dto.setEstadoProducto(mapStringToEstadoProducto(pDTO.getEstado()));

        Map<TamanioProducto, Double> preciosMap = new HashMap<>();
        if (pDTO.getTamanios() != null) {
            for (PersistenciaProductoTamanioDTO ptTamanioDTO : pDTO.getTamanios()) {
                if (ptTamanioDTO != null && ptTamanioDTO.getTamanio() != null) {
                    PersistenciaTamanioDTO infoTamanio = ptTamanioDTO.getTamanio();
                    if (infoTamanio.getNombre() != null) {
                        TamanioProducto tamanioEnum = mapStringToTamanioProducto(infoTamanio.getNombre());
                        if (tamanioEnum != null) {
                            double precioCalculado = pDTO.getPrecioBase() + infoTamanio.getPrecioAdicional();
                            preciosMap.put(tamanioEnum, precioCalculado);
                        }
                    }
                }
            }
        }
        dto.setPrecios(preciosMap);

        Map<String, IngredienteViejoListDTO> ingredienteBaseMap = new HashMap<>();
        Map<IngredienteViejoListDTO, Map<TamanioProducto, Double>> ingredientesMap = new HashMap<>();

        if (pDTO.getTamanios() != null) {
            for (PersistenciaProductoTamanioDTO ptTamanioDTO : pDTO.getTamanios()) {
                if (ptTamanioDTO == null || ptTamanioDTO.getTamanio() == null || ptTamanioDTO.getTamanio().getNombre() == null) {
                    continue;
                }

                TamanioProducto currentTamanioEnum = mapStringToTamanioProducto(ptTamanioDTO.getTamanio().getNombre());
                if (currentTamanioEnum == null) {
                    continue;
                }

                if (ptTamanioDTO.getIngredientes() != null) {
                    for (PersistenciaProductoTamanioIngredienteDTO ppTamanioIngredienteDTO : ptTamanioDTO.getIngredientes()) {
                        if (ppTamanioIngredienteDTO == null || ppTamanioIngredienteDTO.getIngrediente() == null) {
                            continue;
                        }

                        IngredienteDTOPersistencia ingredientePersistencia = ppTamanioIngredienteDTO.getIngrediente();
                        String ingredienteId = ingredientePersistencia.getId();

                        IngredienteViejoListDTO ingredienteDTO = ingredienteBaseMap.computeIfAbsent(ingredienteId, id -> {
                            IngredienteViejoListDTO dtoIng = new IngredienteViejoListDTO();
                            dtoIng.setId(ingredientePersistencia.getId());
                            dtoIng.setNombre(ingredientePersistencia.getNombre());
                            dtoIng.setCantidadDisponible(ingredientePersistencia.getCantidadDisponible());
                            dtoIng.setUnidadMedida(mapStringToUnidadMedida(ingredientePersistencia.getUnidadMedida()));
                            dtoIng.setNivelStock(mapStringToNivelStock(ingredientePersistencia.getNivelStock()));
                            return dtoIng;
                        });

                        Map<TamanioProducto, Double> cantidadesPorTamanio = ingredientesMap.computeIfAbsent(ingredienteDTO, k -> new HashMap<>());
                        cantidadesPorTamanio.put(currentTamanioEnum, ppTamanioIngredienteDTO.getCantidad());
                    }
                }
            }
        }

        dto.setIngredientes(ingredientesMap);

        return dto;
    }

    @Override
    public PersistenciaProductoDTO toPersistenciaProductoDTO(DetallesProductoDTO detallesDTO) {
        if (detallesDTO == null) {
            return null;
        }

        PersistenciaProductoDTO pDTO = new PersistenciaProductoDTO();

        pDTO.setId(detallesDTO.getId());
        pDTO.setNombre(detallesDTO.getNombre());
        pDTO.setDescripcion(detallesDTO.getDescipcion());
        pDTO.setImageData(detallesDTO.getImagenData());

        if (detallesDTO.getCategoria() != null) {
            pDTO.setCategoria(detallesDTO.getCategoria().name());
        }
        if (detallesDTO.getEstadoProducto() != null) {
            pDTO.setEstado(detallesDTO.getEstadoProducto().name());
        }

        // Asignación de precioBase (el BO podría necesitar ajustar esto)
        double precioBaseAsumido = 0.0;
        if (detallesDTO.getPrecios() != null && detallesDTO.getPrecios().containsKey(TamanioProducto.CHICO)) {
            Double precioChicoFinal = detallesDTO.getPrecios().get(TamanioProducto.CHICO);
            if (precioChicoFinal != null) {
                precioBaseAsumido = precioChicoFinal;
            }
            pDTO.setPrecioBase(precioBaseAsumido);

            List<PersistenciaProductoTamanioDTO> listaPPTamanios = new ArrayList<>();
            if (detallesDTO.getPrecios() != null) {
                for (TamanioProducto tamanioEnum : detallesDTO.getPrecios().keySet()) {
                    PersistenciaProductoTamanioDTO ppTamanioDTO = new PersistenciaProductoTamanioDTO();
                    PersistenciaTamanioDTO infoTamanioParaPersistencia = new PersistenciaTamanioDTO();
                    infoTamanioParaPersistencia.setNombre(tamanioEnum.name()); // Nombre del enum (ej. "CHICO")
                    infoTamanioParaPersistencia.setId(null); // El BO lo establecerá
                    ppTamanioDTO.setTamanio(infoTamanioParaPersistencia);

                    // 2. Poblar la lista de ingredientes para este tamaño específico
                    List<PersistenciaProductoTamanioIngredienteDTO> listaIngredientesParaEsteTamanio = new ArrayList<>();
                    if (detallesDTO.getIngredientes() != null) {
                        for (Map.Entry<IngredienteViejoListDTO, Map<TamanioProducto, Double>> entry : detallesDTO.getIngredientes().entrySet()) {
                            IngredienteViejoListDTO ingredienteKey = entry.getKey();
                            Map<TamanioProducto, Double> cantidadesPorTamanio = entry.getValue();

                            if (cantidadesPorTamanio != null && cantidadesPorTamanio.containsKey(tamanioEnum)) {

                                PersistenciaProductoTamanioIngredienteDTO ppIngredienteDTO = new PersistenciaProductoTamanioIngredienteDTO();

                                IngredienteDTOPersistencia ingredientePersistencia = new IngredienteDTOPersistencia();
                                ingredientePersistencia.setId(ingredienteKey.getId());

                                ppIngredienteDTO.setCantidad(cantidadesPorTamanio.get(tamanioEnum));
                                ppIngredienteDTO.setIngrediente(ingredientePersistencia);
                                listaIngredientesParaEsteTamanio.add(ppIngredienteDTO);
                            }
                        }
                    }
                    ppTamanioDTO.setIngredientes(listaIngredientesParaEsteTamanio);
                    listaPPTamanios.add(ppTamanioDTO);
                }
            }
            pDTO.setTamanios(listaPPTamanios);
        }
        return pDTO;
    }

    @Override
    public PersistenciaProductoDTO toPersistenciaProductoDTO(ProductoCreateDTO createDTO) {
        if (createDTO == null) {
            return null;
        }

        PersistenciaProductoDTO persistenciaDTO = new PersistenciaProductoDTO();

        // Campos directos y manejo de enums
        persistenciaDTO.setId(null); // El ID se genera/asigna usualmente en la capa de persistencia
        persistenciaDTO.setNombre(createDTO.getNombre());
        persistenciaDTO.setDescripcion(createDTO.getDescipcion()); // Asumiendo getter getDescipcion() por el typo
        persistenciaDTO.setImageData(createDTO.getImagenData());

        if (createDTO.getCategoria() != null) {
            persistenciaDTO.setCategoria(createDTO.getCategoria().name());
        }
        if (createDTO.getEstadoProducto() != null) {
            persistenciaDTO.setEstado(createDTO.getEstadoProducto().name());
        }

        // Calcular precioBase (usualmente el precio del tamaño más pequeño, ej. CHICO)
        double calculatedPrecioBase = 0.0;
        if (createDTO.getPrecios() != null && createDTO.getPrecios().containsKey(TamanioProducto.CHICO)) {
            Double precioChico = createDTO.getPrecios().get(TamanioProducto.CHICO);
            if (precioChico != null) {
                calculatedPrecioBase = precioChico;
            }
        }
        persistenciaDTO.setPrecioBase(calculatedPrecioBase);

        // Mapear tamaños, sus precios adicionales e ingredientes
        List<PersistenciaProductoTamanioDTO> listaPPTamanios = new ArrayList<>();
        if (createDTO.getPrecios() != null) {
            for (Map.Entry<TamanioProducto, Double> precioEntry : createDTO.getPrecios().entrySet()) {
                TamanioProducto tamanioEnum = precioEntry.getKey();
                Double precioFinalEsteTamanio = precioEntry.getValue();

                if (precioFinalEsteTamanio == null) {
                    continue;
                }

                PersistenciaProductoTamanioDTO ppTamanioDTO = new PersistenciaProductoTamanioDTO();

                // 1. Mapear información de PersistenciaTamanioDTO
                PersistenciaTamanioDTO infoTamanioParaPersistencia = new PersistenciaTamanioDTO();
                infoTamanioParaPersistencia.setNombre(tamanioEnum.name());
                ppTamanioDTO.setTamanio(infoTamanioParaPersistencia);

                // 2. Mapear ingredientes para este tamaño específico
                List<PersistenciaProductoTamanioIngredienteDTO> listaIngredientesParaEsteTamanio = new ArrayList<>();
                if (createDTO.getIngredientes() != null) {
                    for (Map.Entry<IngredienteViejoListDTO, Map<TamanioProducto, Double>> ingredienteEntry : createDTO.getIngredientes().entrySet()) {
                        IngredienteViejoListDTO ingredienteKey = ingredienteEntry.getKey(); // Este es el IngredienteViejoListDTO
                        Map<TamanioProducto, Double> cantidadesPorTamanio = ingredienteEntry.getValue();

                        // Verificar si este ingrediente tiene una cantidad para el tamaño actual (tamanioEnum)
                        if (cantidadesPorTamanio != null && cantidadesPorTamanio.containsKey(tamanioEnum)) {
                            Double cantidadIngrediente = cantidadesPorTamanio.get(tamanioEnum);
                            if (cantidadIngrediente == null) {
                                continue; // Opcional: manejar si la cantidad es null
                            }

                            PersistenciaProductoTamanioIngredienteDTO ppIngredienteDTO = new PersistenciaProductoTamanioIngredienteDTO();
                            ppIngredienteDTO.setCantidad(cantidadIngrediente);

                            IngredienteDTOPersistencia ingredientePersistencia = new IngredienteDTOPersistencia();
                            ingredientePersistencia.setId(ingredienteKey.getId());

                            ppIngredienteDTO.setIngrediente(ingredientePersistencia);
                            listaIngredientesParaEsteTamanio.add(ppIngredienteDTO);
                        }
                    }
                }
                ppTamanioDTO.setIngredientes(listaIngredientesParaEsteTamanio);
                listaPPTamanios.add(ppTamanioDTO);
            }
        }
        persistenciaDTO.setTamanios(listaPPTamanios);

        return persistenciaDTO;
    }

    //AUXILIARES (de enums)
    private CategoriaProducto mapStringToCategoriaProducto(String categoriaStr) {
        if (categoriaStr == null || categoriaStr.trim().isEmpty()) {
            return null;
        }

        try {
            return CategoriaProducto.valueOf(categoriaStr);
        } catch (IllegalArgumentException e) {
            System.err.println("Advertencia: No se encontró el enum CategoriaProducto para el string: '" + categoriaStr + "')");
            return null;
        }
    }

    private EstadoProducto mapStringToEstadoProducto(String estadoStr) {
        if (estadoStr == null || estadoStr.trim().isEmpty()) {
            return null;
        }
        try {
            return EstadoProducto.valueOf(estadoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Advertencia: No se encontró el enum EstadoProducto para el string: '" + estadoStr + "'");
            return null;
        }
    }

    private TamanioProducto mapStringToTamanioProducto(String tamanioStr) {
        if (tamanioStr == null || tamanioStr.trim().isEmpty()) {
            return null;
        }
        try {
            if (tamanioStr.equals("PEQUEÑO")) {
                tamanioStr = "CHICO";
            }

            return TamanioProducto.valueOf(tamanioStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Advertencia: No se encontró el enum TamanioProducto para el string: '" + tamanioStr + "'");
            return null;
        }
    }

    private UnidadMedida mapStringToUnidadMedida(String unidadMedidaStr) {
        if (unidadMedidaStr == null || unidadMedidaStr.trim().isEmpty()) {
            return null;
        }
        if (unidadMedidaStr.equals("GRAMO")) {
            unidadMedidaStr = "GRAMOS";
        }
        if (unidadMedidaStr.equals("MILILITRO")) {
            unidadMedidaStr = "MILILITROS";
        }
        if (unidadMedidaStr.equals("UNIDAD") || unidadMedidaStr.equals("PIEZA")) {
            unidadMedidaStr = "UNIDADES";
        }

        try {
            return UnidadMedida.valueOf(unidadMedidaStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Adv: Mapper: UnidadMedida no mapeable: '" + unidadMedidaStr + "'");
            return null;
        }
    }

    private NivelStock mapStringToNivelStock(String nivelStockStr) {
        if (nivelStockStr == null || nivelStockStr.trim().isEmpty()) {
            return null;
        }
        try {
            return NivelStock.valueOf(nivelStockStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Adv: Mapper: NivelStock no mapeable: '" + nivelStockStr + "'");
            return null;
        }
    }
}
