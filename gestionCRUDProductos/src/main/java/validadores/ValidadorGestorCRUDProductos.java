/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validadores;

import DTOs.CRUDIngredientes.IngredienteViejoListDTO;
import DTOs.CRUDProductos.DetallesProductoDTO;
import DTOs.CRUDProductos.ProductoCreateDTO;
import DTOs.CRUDProductos.TamanioProducto;
import java.util.Arrays;
import java.util.Map;

/**
 *
 * @author oliva
 */
public class ValidadorGestorCRUDProductos implements IValidadorGestorCRUDProductos {

    private static final int MAX_LONGITUD_NOMBRE = 100;
    private static final int MAX_LONGITUD_DESCRIPCION = 500;

    @Override
    public void validarGuardarProducto(ProductoCreateDTO productoDTO) throws IllegalArgumentException {
        if (productoDTO == null) {
            throw new IllegalArgumentException("El DTO del producto no puede ser nulo.");
        }

        // Validar nombre
        if (productoDTO.getNombre() == null || productoDTO.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
        if (productoDTO.getNombre().length() > MAX_LONGITUD_NOMBRE) {
            throw new IllegalArgumentException("El nombre del producto no puede exceder los " + MAX_LONGITUD_NOMBRE + " caracteres.");
        }

        if (productoDTO.getDescipcion() != null && productoDTO.getDescipcion().length() > MAX_LONGITUD_DESCRIPCION) {
            throw new IllegalArgumentException("La descripción del producto no puede exceder los " + MAX_LONGITUD_DESCRIPCION + " caracteres.");
        }

        // Validar categoría
        if (productoDTO.getCategoria() == null) {
            throw new IllegalArgumentException("La categoría del producto es obligatoria.");
        }

        // Validar estado del producto
        if (productoDTO.getEstadoProducto() == null) {
            throw new IllegalArgumentException("El estado del producto es obligatorio.");
        }

        // Validar precios
        if (productoDTO.getPrecios() == null || productoDTO.getPrecios().isEmpty()) {
            throw new IllegalArgumentException("Se debe especificar al menos un precio para un tamaño de producto.");
        }
        for (Map.Entry<TamanioProducto, Double> entry : productoDTO.getPrecios().entrySet()) {
            if (entry.getKey() == null) {
                throw new IllegalArgumentException("El tamaño del producto en precios no puede ser nulo.");
            }
            if (entry.getValue() == null || entry.getValue() <= 0) {
                throw new IllegalArgumentException("El precio para el tamaño " + entry.getKey() + " debe ser un valor positivo.");
            }
        }

        // Validar ingredientes
        if (productoDTO.getIngredientes() != null) {
            if (productoDTO.getIngredientes().isEmpty()) {
                throw new IllegalArgumentException("Cada producto debe tener al menos 1 ingrediente");
            }

            for (Map.Entry<IngredienteViejoListDTO, Map<TamanioProducto, Double>> entry : productoDTO.getIngredientes().entrySet()) {
                // Validación del ingrediente
                if (entry.getKey() == null || entry.getKey().getId() == null || entry.getKey().getId().trim().isEmpty()) {
                    throw new IllegalArgumentException("Cada ingrediente debe tener un ID válido.");
                }

                Map<TamanioProducto, Double> cantidadesPorTamanio = entry.getValue();

                // Verificar que existan valores para TODOS los tamaños
                if (cantidadesPorTamanio == null || cantidadesPorTamanio.size() != TamanioProducto.values().length) {
                    throw new IllegalArgumentException("Debe especificarse la cantidad para TODOS los tamaños ("
                            + Arrays.toString(TamanioProducto.values()) + ") para el ingrediente "
                            + entry.getKey().getNombre());
                }

                // Validar cada tamaño específicamente
                for (TamanioProducto tamanio : TamanioProducto.values()) {
                    Double cantidad = cantidadesPorTamanio.get(tamanio);

                    if (cantidad == null) {
                        throw new IllegalArgumentException("Falta la cantidad para el tamaño "
                                + tamanio + " del ingrediente " + entry.getKey().getNombre());
                    }

                    if (cantidad <= 0) {
                        throw new IllegalArgumentException("La cantidad del ingrediente "
                                + entry.getKey().getNombre() + " para el tamaño " + tamanio
                                + " debe ser mayor a 0 (valor actual: " + cantidad + ")");
                    }
                }

            }
        } else {
            throw new IllegalArgumentException("Cada producto debe tener al menos 1 ingrediente");
        }
    }

    @Override
    public void validarActualizarProducto(DetallesProductoDTO productoDTO) throws IllegalArgumentException {
        if (productoDTO == null) {
            throw new IllegalArgumentException("El DTO del producto para actualizar no puede ser nulo.");
        }

        // Validar ID
        validarIdProducto(productoDTO.getId());

        // Validar nombre
        if (productoDTO.getNombre() == null || productoDTO.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
        if (productoDTO.getNombre().length() > MAX_LONGITUD_NOMBRE) {
            throw new IllegalArgumentException("El nombre del producto no puede exceder los " + MAX_LONGITUD_NOMBRE + " caracteres.");
        }

        // Validar descripción
        if (productoDTO.getDescipcion() != null && productoDTO.getDescipcion().length() > MAX_LONGITUD_DESCRIPCION) {
            throw new IllegalArgumentException("La descripción del producto no puede exceder los " + MAX_LONGITUD_DESCRIPCION + " caracteres.");
        }

        // Validar categoría
        if (productoDTO.getCategoria() == null) {
            throw new IllegalArgumentException("La categoría del producto es obligatoria.");
        }

        // Validar estado del producto
        if (productoDTO.getEstadoProducto() == null) {
            throw new IllegalArgumentException("El estado del producto es obligatorio.");
        }

        // Validar precios
        if (productoDTO.getPrecios() == null || productoDTO.getPrecios().isEmpty()) {
            throw new IllegalArgumentException("Se debe especificar al menos un precio para un tamaño de producto.");
        }
        for (Map.Entry<TamanioProducto, Double> entry : productoDTO.getPrecios().entrySet()) {
            if (entry.getKey() == null) {
                throw new IllegalArgumentException("El tamaño del producto en precios no puede ser nulo.");
            }
            if (entry.getValue() == null || entry.getValue() <= 0) {
                throw new IllegalArgumentException("El precio para el tamaño " + entry.getKey() + " debe ser un valor positivo.");
            }
        }

        // Validar ingredientes
        if (productoDTO.getIngredientes() != null) {
            for (Map.Entry<IngredienteViejoListDTO, Map<TamanioProducto, Double>> entry : productoDTO.getIngredientes().entrySet()) {
                if (entry.getKey() == null || entry.getKey().getId() == null || entry.getKey().getId().trim().isEmpty()) {
                    throw new IllegalArgumentException("Cada ingrediente debe tener un ID válido.");
                }

                if (entry.getValue() == null || entry.getValue().isEmpty()) {
                    throw new IllegalArgumentException("Debe especificarse la cantidad para al menos un tamaño para el ingrediente " + entry.getKey().getNombre());
                }
                for (Map.Entry<TamanioProducto, Double> cantidadEntry : entry.getValue().entrySet()) {
                    if (cantidadEntry.getKey() == null) {
                        throw new IllegalArgumentException("El tamaño del producto para la cantidad del ingrediente " + entry.getKey().getNombre() + " no puede ser nulo.");
                    }
                    if (cantidadEntry.getValue() == null || cantidadEntry.getValue() <= 0) {
                        throw new IllegalArgumentException("La cantidad del ingrediente " + entry.getKey().getNombre() + " para el tamaño " + cantidadEntry.getKey() + " debe ser positiva.");
                    }
                }
            }
        }
    }

    @Override
    public void validarIdProducto(String idProducto) throws IllegalArgumentException {
        if (idProducto == null || idProducto.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo o vacío.");
        }
    }

    @Override
    public void validarFiltrosProductos(String filtroNombre, String filtroCategoria) throws IllegalArgumentException {
        if (filtroNombre != null && filtroNombre.length() > MAX_LONGITUD_NOMBRE) {
            throw new IllegalArgumentException("El filtro de nombre no puede exceder los " + MAX_LONGITUD_NOMBRE + " caracteres.");
        }
        if (filtroCategoria != null && filtroCategoria.length() > MAX_LONGITUD_NOMBRE) {
            throw new IllegalArgumentException("El filtro de categoría no puede exceder los " + MAX_LONGITUD_NOMBRE + " caracteres.");
        }
    }
}
